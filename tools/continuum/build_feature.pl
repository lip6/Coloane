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
my $debug = 1;

# Fetch parameters
my $featurefile = shift;
my $build = shift;
my $plugindir = shift;
my $featuredir = shift;

if (!(-e $featurefile)) {
	print "No feature.xml file... Continue... \n";
	exit;
}

# Prepare the XML parser
my $xml = XML::Twig->new(); 
$xml->parsefile($featurefile);
                
# Find the root and the 'version' element
my $root = $xml->root;
my $version = $root->att('version');
my $nameid = $root->att('id');
my $newversion = $version.".r".$build;
print "Writing the new version : $newversion (previously $version) \n" if $debug;
$root->set_att(version => $newversion); 

# Find version of associated features
my @features = $root->children('includes');
foreach my $feature (@features) {
	my $id = $feature->att('id');
	print "Processing associated feature : $id \n" if $debug;
	
	# Find, open and read the associated descriptor
	my $filedesc = $featuredir."/last_".$id;
	open (DESC, "<$filedesc") or die "The feature $id has not been built \nFAILURE !!! Unable to load the descriptor file: $filedesc\nPlease be sure to have packaged the feature";
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
	open (DESC, "<$filedesc") or die "The plugin $id has not been built \nFAILURE !!! Unable to load the descriptor file: $filedesc\nPlease be sure to have packaged the plugin";
	my $lastversion = <DESC>;
	chomp $lastversion;
	print "Last version : $lastversion\n" if $debug;
	
	# Check whether all plugins have been released from the same revision
	if ($lastversion =~ /^\d+\.\d+\.\d+\.r(\d+)$/) {
		my $refbuild = $1;
		if ($refbuild != $build) {
			print "The plugin $id was not correctly built (build number $refbuid)... Feature construction failed !\n";
			return 0;
		}
	}
	
	close(DESC);
	
	$plugin->set_att(version => $lastversion);
}   

#$xml->flush();

# Openning feature.xml for writing
print "Writing...\n" if $debug;
mkdir("resources", 0755);
$xml->print_to_file("resources/".$featurefile,pretty_print => 'indented');

# Print on MANIFEST the new version of feature (and its name)
print STDOUT "Writing the MANIFEST file\n" if $debug;

mkdir("META-INF", 0755);
open (LAST, ">META-INF/MANIFEST.MF") or die "FAILURE for MANIFEST !!!\n"; 
print LAST "Manifest-Version: 1.0\n";
print LAST "Bundle-SymbolicName: $nameid\n";
print LAST "Bundle-Version: $version\n";
close(LAST);

1;
