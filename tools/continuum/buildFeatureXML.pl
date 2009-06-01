#!/usr/bin/perl

use strict;
use diagnostics;
use Archive::Zip qw(:ERROR_CODES :CONSTANTS);
use Archive::Zip::MemberRead;
use XML::Twig;
use File::stat;


diagnostics::enable();

#
# Author : Jean-Baptiste Voron (LIP6/MoVe)
# Description : Rewrite feature.xml to produce features products suitable for Eclipse deployement
# Use : perl buildFeatureXML.pl <FEATURE FILE> <MAVEN NAME> <BUILD NUMBER> <PATH_TO_DEPLOY>


my $debug = 1;

# Fetch all parameters
my $featurefile = shift;
my $buildname = shift;
my $buildnumber = shift;
my $deploypath = shift;

sub compute_version {
	my $dir = shift; # Where to look for...
	my $id = shift;  # What to look for...
	
	my @files; # All JAR files that match the ID

	# Open the directory and look for matching file's name
	opendir(DIR,$dir) or die "The directory $dir cannot be browsed --> FAILURE !";
	while (my $file=readdir(DIR)) {
		push(@files,$file) if ($file =~ /^$id/);
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

if (!defined($featurefile)) {
	print "Bad invocation :: <FEATURE FILE> <MAVEN NAME> <BUILD NUMBER> <PATH_TO_DEPLOY>\n";
	exit;
}

# Check the file
if (!(-e $featurefile)) {
	print "No feature.xml file... Continue... \n";
	exit;
}

my $release = 1;
my $featuredir = $deploypath."/updates/features";
my $plugindir = $deploypath."/updates/plugins";

# Determine whether it's a release or a snapshost
if ($buildname =~ /SNAPSHOT/) {
	$release = 0;
	$featuredir = $deploypath."/night-updates/features";
	$plugindir  = $deploypath."/night-updates/plugins";
	print "Building a Snapshot feature \n" if $debug;
} else {
	print "Building a Release feature \n" if $debug;
}

# Prepare the XML parser
my $xml = XML::Twig->new(); 
$xml->parsefile($featurefile);
                
# Find the root and the "version" element
my $root = $xml->root;
my $version = $root->att('version');
my $nameid = $root->att('id');

my $newversion = $version;
$newversion = $version.".r".$buildnumber if !$release;

print "Writing the new version : $newversion (previously $version) \n" if $debug;
$root->set_att(version => $newversion); 

# Find version of associated features
my @features = $root->children('includes');
foreach my $feature (@features) {
	my $featureid = $feature->att('id');
	print "Processing associated feature : $featureid --> " if $debug;
	
	# Find the last version on he associated feature (identified by $id)
	my @featureinfos = compute_version($featuredir, $featureid);
	(my $featuresize, my $lastfeatureversion) = @featureinfos;
	print "Last version : $lastfeatureversion (size: $featuresize Bytes)\n" if $debug;	
	$feature->set_att(version => $lastfeatureversion);
	$feature->set_att('install-size' => int($featuresize/1000));
	$feature->set_att('download-size' => int($featuresize/1000));
}

# Comparison variable
my $tmpbuild = 0;

# Find versions of plugins
my @plugins = $root->children('plugin');
foreach my $plugin (@plugins) {
	my $pluginid = $plugin->att('id');
	print "Processing plugin : $pluginid --> " if $debug;
	
	# Find the last version on he associated feature (identified by $id)
	my @plugininfos = compute_version($plugindir, $pluginid);
	(my $pluginsize, my $lastpluginversion) = @plugininfos;
	print "Last version : $lastpluginversion (size: $pluginsize Bytes)\n" if $debug;	
	$plugin->set_att(version => $lastpluginversion);
	$plugin->set_att('install-size' => int($pluginsize/1000));
	$plugin->set_att('download-size' => int($pluginsize/1000));
}

# Find update site address.
my $url = $root->first_child('url');
my $updatesite = $url->first_child('update');
if (!$release) {
	$updatesite->set_att('label' => "Coloane Night-Updates");
	$updatesite->set_att('url' => "http://coloane.lip6.fr/night-updates/");
} else {
	$updatesite->set_att('label' => "Coloane Updates");
	$updatesite->set_att('url' => "http://coloane.lip6.fr/updates/");
}

# Openning feature.xml for writing
print "Writing the feature.xml file...\n" if $debug;
#mkdir("resources", 0755);
$xml->print_to_file($featurefile,pretty_print => 'indented');

# Print on MANIFEST the new version of feature (and its name)
print STDOUT "Writing the manifest.mf file...\n" if $debug;

#mkdir("META-INF", 0755);
open (META, ">META-INF/MANIFEST.MF") or die "FAILURE for MANIFEST !!!\n"; 
print META "Manifest-Version: 1.0\n";
print META "Bundle-SymbolicName: $nameid\n";
print META "Bundle-Version: $newversion\n";
close(META);

0;
