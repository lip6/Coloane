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
# Use : perl download_stats.pl [-f=file with stats] [-t analyse today activity]
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

# Some properties
my $filedesc = "/coloane/logs/access.log";
my $when = 0;
my $backday = 1;

# Browse arguments
if ($#ARGV > -1) {
	for (my $i = -1; $i <= $#ARGV; $i++) {
		my $tmp = shift @ARGV;
		$filedesc = $1 if ($tmp =~ /^-f=(.*)$/);
		$when = 1 if ($tmp =~ /^-t$/);
		$backday = $1 if ($tmp =~ /^-b(\d+)$/);
	}
}

# Open stats file
open (DESC, "<$filedesc") or die "FAILURE !!! Unable to read : $filedesc \n";

# Calculate the date
my $period = (time - 24 * 60 * 60 * $backday);
$period = (time) if ($when);
my ($sec,$min,$hour,$mday,$mon,$year,$wday,$yday,$isdst) = localtime($period);
my $pattern_date = pad_day($mday)."/".$months[$mon]."/".($year+1900);

# Browse the stats file and look for insterresting lines
while (<DESC>) {
	my $ligne = $_;
	chomp $ligne;
	
	# Pattern calculation
	my $pattern = '^(\d+\.\d+\.\d+\.\d+) - - \['.$pattern_date.'.+\] \"GET \/night-updates\/site\.xml.*200 ';
	
	if ($ligne =~ /$pattern/) {
		if (exists $details{$1}) { $details{$1}++ } else { $details{$1}=1; }
		$counter++;
	}
}

# Output 
print "@@ ".$pattern_date." : ".$counter." \n";
for my $ip (keys %details) {
	print "%% $ip -> ".$details{$ip}." \n";
}

close (DESC);