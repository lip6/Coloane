#!/usr/bin/perl

use strict;
use diagnostics;
use Archive::Zip qw(:ERROR_CODES :CONSTANTS);
use Archive::Zip::MemberRead;
use XML::Twig;
use File::stat;

#
# Author : Jean-Baptiste Voron (LIP6/MoVe)
# Description : Rewrite site.xml to produce update site products
#
# Use : perl build_site.pl <SITE FILE> <FEATURE DIR>

my $debug = 1;

sub compute_version {
	my $dir = shift; # Where to look for...
	my $id = shift;  # What to look for...
	
	my @files; # All JAR files that match the ID

	# Open the directory and look for matching file's name
	opendir(DIR,$dir) or die "The directory $dir cannot be browsed --> FAILURE !";
	while (my $file=readdir(DIR)) {
		if ($file =~ /^$id\_/) {
			push(@files,$file) ;
			print "Found : $file \n";
		}
	}
	closedir(DIR);
	
	# Check that at least one file has been discovered
	if (@files <= 0) { 
		print "No file has been found for component $id \n" if ($debug);
		exit 1;
	}
	
	# Sort and extract the most recent file
	my @sorted = sort { (stat($dir."/".$a))->ctime <=> (stat($dir."/".$b))->ctime } @files;
	my $newest = pop(@sorted);
	my $archive = Archive::Zip->new();
	die "Read error on file $newest" if $archive->read($dir."/".$newest) != AZ_OK;
	
	# Return value must be composeb of file's size and file's version
	my @return;
	push(@return, (-s $dir."/".$newest));

	my $fh  = Archive::Zip::MemberRead->new($archive, "META-INF/MANIFEST.MF");
	while (defined($_ = $fh->getline())) {
		if ($_ =~ /^Bundle-Version: (.*)\s+$/) { 
			my $bundleversion = $1; 
			push(@return, $bundleversion);
			return @return;
		}
	}
}


# Fetch parameters
my $sitefile = shift;
my $buildname = shift;
my $deploypath = shift;

# Check the site description file
if (!(-e $sitefile)) {
	print "No site.xml file... Continue... \n";
	exit;
}

my $release = 1;
my $featuredir = $deploypath."/updates/features";

# Determine whether it's a release or a snapshost
if ($buildname =~ /SNAPSHOT/) {
	$release = 0;
	print "Deploying a 'night-update' site\n" if $debug;
	$featuredir = $deploypath."/night-updates/features";
} else {
	print "Deploying an 'update' site\n" if $debug;
}

# Initialize parser
my $xml = XML::Twig->new(); 
$xml->parsefile($sitefile);
                
# Find the root element and fill som information
my $root = $xml->root;
my $descurl = $root->first_child('description');
if (!$release) {
	$descurl->set_att('url' => "http://coloane.lip6.fr/night-updates/");
	$descurl->set_text("Coloane night-updates site");
} else {
	$descurl->set_att('url' => "http://coloane.lip6.fr/night-updates/");
	$descurl->set_text("Coloane updates site");
}

# Find version of associated features
my @features = $root->children('feature');
foreach my $feature (@features) {
	my $featureid = $feature->att('id');
	print "Processing published feature : $featureid \n" if $debug;
	
	# Find the last version on he associated feature (identified by $id)
	my @featureinfos = compute_version($featuredir, $featureid);
	(my $featuresize, my $lastfeatureversion) = @featureinfos;
	print "Last version : $lastfeatureversion (size: $featuresize Bytes)\n" if $debug;	
	
	$feature->set_att(url => "features/".$featureid."_".$lastfeatureversion.".jar"); 
	$feature->set_att(version => $lastfeatureversion); 
}

# Openning feature.xml for writing
print "Writing the update site file...\n" if $debug;
mkdir("resources", 0755);
$xml->print_to_file("resources/".$sitefile, pretty_print => 'indented');

1;
