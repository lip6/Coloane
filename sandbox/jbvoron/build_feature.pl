#!/usr/bin/perl

use strict;
use diagnostics;
diagnostics::enable();

#
# Author : Jean-Baptiste Voron (LIP6/MoVe)
# Description : Rewrite feature.xml to produce night-builds products
#
# Use : perl build_feature.pl <FEATURE FILE> <BUILD NUMBER> <PLUGIN DIR> <FEATURE DIR>

use XML::Twig;

# Debug state
my $debug = 0;

# Fetch parameters
my $featurefile = shift;
my $build = shift;
my $plugindir = shift;
my $featuredir = shift;

# Prepare the XML parser
my $xml = XML::Twig->new(); 
$xml->parsefile($featurefile);
                
# Find the root and the 'version' element
my $root = $xml->root;
my $version = $root->att('version');
my $newversion = $version.".r".$build;
$root->set_att(version => $newversion); 

# Find version of associated features
my @features = $root->children('includes');
foreach my $feature (@features) {
	my $id = $feature->att('id');
	print "Processing associated feature : $id \n" if $debug;
	
	# Find, open and read the associated descriptor
	my $filedesc = $featuredir."/last_".$id;
	open (DESC, "<$filedesc") or die "FAILURE !!! ($filedesc)\n";
	my $lastversion = <DESC>;
	chomp $lastversion;
	print "Last version : $lastversion\n" if $debug;
	close(DESC);
	
	$feature->set_att(version => $lastversion);
}

# Find versions of plugins
my @plugins = $root->children('plugin');
foreach my $plugin (@plugins) {
	my $id = $plugin->att('id');
	print "Processing plugin : $id \n" if $debug;
	
	# Find, open and read the associated descriptor
	my $filedesc = $plugindir."/last_".$id;
	open (DESC, "<$filedesc") or die "FAILURE !!! ($filedesc)\n";
	my $lastversion = <DESC>;
	chomp $lastversion;
	print "Last version : $lastversion\n" if $debug;
	close(DESC);
	
	$plugin->set_att(version => $lastversion);
}   


#$xml->flush();

# Openning feature.xml for writing
print "Writing...\n" if $debug;
$xml->print_to_file($featurefile,pretty_print => 'indented');

# Print on STDOUT the new version of feature
print STDOUT $newversion;

1;