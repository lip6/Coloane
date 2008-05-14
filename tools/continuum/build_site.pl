#!/usr/bin/perl

use strict;
use diagnostics;
diagnostics::enable();

#
# Author : Jean-Baptiste Voron (LIP6/MoVe)
# Description : Rewrite site.xml to produce night-builds products
#
# Use : perl build_site.pl <SITE FILE> <FEATURE DIR>

use XML::Twig;

my $debug = 1;

# Fetch paramaters
my $sitefile = shift;
my $dirfeature = shift;

# Initialize parser
my $xml = XML::Twig->new(); 
$xml->parsefile($sitefile);
                
# Find the root and the 'feature' element
my $root = $xml->root;

# Find version of associated features
my @features = $root->children('feature');
foreach my $feature (@features) {
	my $nameid = $feature->att('id');
	print "Processing associated feature : $id \n" if $debug;
	
	# Find, open and read the associated descriptor
	my $filedesc = $dirfeature."/last_".$nameid;
	open (DESC, "<$filedesc") or die "FAILURE !!! ($filedesc)\n";
	my $lastversion = <DESC>;
	chomp $lastversion;
	print "Last version : $lastversion\n" if $debug;
	close(DESC);
	
	$feature->set_att(url => "features/".$nameid."_".$lastversion.".jar"); 
	$feature->set_att(version => $lastversion); 
}

# Openning feature.xml for writing
print "Writing...\n" if $debug;
mkdir("resources", 0755);
$xml->print_to_file("resources/".$sitefile,pretty_print => 'indented');

1;
