#!/usr/bin/perl

use strict;
use diagnostics;
diagnostics::enable();

# Months list
my @months = ("Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec");

#
# Author : Jean-Baptiste Voron (LIP6/MoVe)
# Description : Try to find the number of downloads of Coloane
#
# Use : perl download_stats.pl
#

#
# This function pads the day's value to match XX 
# Arg : day to pad
#
sub pad_day {
	my $day = shift;
	if ($day < 10) {
		$day = "0".$day
	}
	return $day;
}

# Counter and Details
my $counter = 0;
my %details;

# Debug state
my $debug = 1;

# Open stats file
# my $filedesc = "/coloane/logs/access.log";
my $filedesc = "access.log";
open (DESC, "<$filedesc") or die "FAILURE !!! Unable to read : $filedesc \n";

# Browse the stats file and look for insterresting lines
my ($sec,$min,$hour,$mday,$mon,$year,$wday,$yday,$isdst) = localtime(time - 24 * 60 * 60);
my $pattern_date = pad_day($mday)."/".$months[$mon]."/".($year+1900);

while (<DESC>) {
	my $ligne = $_;
	chomp $ligne;
	
	# Pattern calculation
	my $pattern = '^(\d+\.\d+\.\d+\.\d+) - - \['.$pattern_date.'.+\] \"GET \/night-updates\/site.xml ';
	
	if ($ligne =~ /$pattern/) {
		if (exists $details{$1}) { $details{$1}++ } else { $details{$1}=1; }
		$counter++;
	}
}

print "@@ ".$pattern_date." : ".$counter." \n";
for my $ip (keys %details) {
	print "%% $ip -> ".$details{$ip}." \n";
}

close (DESC);