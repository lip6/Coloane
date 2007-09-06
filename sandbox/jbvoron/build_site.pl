#!/usr/bin/perl

use strict;
use diagnostics;
diagnostics::enable();

#
# Author : Jean-Baptiste Voron (LIP6/MoVe)
# Description : Rewrite site.xml to produce night-builds products
#
# Use : perl build_site.pl <SITE FILE> <FEATURE VERSION> <FEATURE PATH>

use XML::Twig;

# Fetch paramaters
my $sitefile = shift;
my $versionfeature = shift;
my $pathfeature = shift;

# Initialize parser
my $xml = XML::Twig->new(); 
$xml->parsefile($sitefile);
                
# Find the root and the 'feature' element
my $root = $xml->root;
my $feature = $root->first_child('feature');
$feature->set_att(url => $pathfeature); 
$feature->set_att(version => $versionfeature); 

# Openning feature.xml for writing
$xml->print_to_file("resources/".$sitefile,pretty_print => 'indented');

1;