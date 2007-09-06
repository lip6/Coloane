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

# Fetch paramaters
my $sitefile = shift;
my $dirfeature = shift;

# Initialize parser
my $xml = XML::Twig->new(); 
$xml->parsefile($sitefile);
                
# Find the root and the 'feature' element
my $root = $xml->root;
my $feature = $root->first_child('feature');
my $nameid = $feature->att('id');

open (DESC, "<last_$nameid") or die "FAILURE !!! (last_$nameid)\n";
my $lastversion = <DESC>;
chomp $lastversion;
print "Last version : $lastversion\n" if $debug;
close(DESC);

$feature->set_att(url => $dirfeature.$nameid."_".$lastversion.".jar"); 
$feature->set_att(version => $lastversion); 

# Openning feature.xml for writing
$xml->print_to_file("resources/".$sitefile,pretty_print => 'indented');

1;