#!/usr/bin/perl  

#
# Config file generation for the SVN mailing script
#
# - By Jean-Baptiste Voron (jean-baptiste.voron@lip6.fr)
# - Version 1.2
# - Date 24/07/2007
#

use strict;
use diagnostics;
diagnostics::enable();

# Usefull for debugging
# 1 -> DEBUG ON
# 0 -> DEBUG OFF
my $debug = 0	;

# Check the number of arguments
if ($#ARGV < 2) {
	print "Usage : perl wrapper_mail.pl <header_file> <sender> <user_config> <trac_url> <repos>\n";
	exit 0;
}

#----
my $header_file = shift @ARGV;
my $sender = shift @ARGV;
my $user_config = shift @ARGV;
#----
my $trac_url = shift @ARGV;
my $repos = shift @ARGV;
#----

my $output = "/tmp/mail_param_$repos.cfg";

# Creating the new config file
open(OUT, ">$output") or die "Error : Impossible to create the output file\n";

# Openning & Reading & Copying the header file
open(HEADER, $header_file) or die "Error : Impossible to open the header file. Please check $header_file \n";
my @header_contents = <HEADER>; # Slurp the file
print OUT join("",@header_contents); # Copying the contents into the confg file
print OUT "\n"; # Spacing
print OUT "# -- End of headers --";
print OUT "\n";
close(HEADER);

# Add the default section to the config file
print OUT "\n[defaults] \n";
print OUT "from_addr=$sender";
print OUT "\n";

# Openning & Reading the user's config file
open(USR, $user_config) or die "Error : Impossible to open and read the user's config file \n";

# The config file must be parsed a first time to build a FS tree
my $line;
my @dest; # Array contains all recipients
my $cc = ""; # Global recipient

# Flag indicates whether a directory is browsed or not
my $flag = 0;

# Parameters (default values)
my $ptitle = "[%(repository)s] r%(revision)s - %(file)s";
my $pdiff = "add modify copy delete propchange";
my $ptrac = 1;
my $ptruncate = "5000";


while (<USR>) {
	$line = $_; # The current line
	chomp($line);
	print "[?] Current Line : $line \n" if $debug;
	
	# Do not take care of comments
	if (($line =~ /^\#/) || (length $line <= 1)) {
		next;
	}
	
	# If the line is an indication of parameter
	if ($line =~ /^(title|truncate|diff|cc|trac_url)\s*\=\s*(.*)$/) {
		print "[?] Parameter : $1 --> $2 \n" if $debug;
		
		if ($1 eq "title") {
			# Processing title's tokens
			my $title_value = $2;
			# Replace the token %repository by its value
			$title_value =~ s/%repository/$repos/;

			# Find and prepare the other tokens in the title
			$title_value =~ s/%(author|revision|files|dirs\/files|dirs)/"%($1)s"/ge;
			print "[?] New value for the title : $title_value \n" if $debug;
			
			# The default value must be changed
			$ptitle = $title_value;
			
		} elsif ($1 eq "diff") { 
			$pdiff = $2; # The default value must be changed
		} elsif (($1 eq "trac_url") && ($2 eq "false")) {
			$ptrac = 0; # The default value must be changed
		} elsif (($1 eq "truncate") && ($2 ne "0")) {
			$ptruncate = $2; # The default value must be changed
		} elsif ($1 eq "cc") {
			if (!$flag) { $cc = $2 };
		}
	}
	
	# If the line is an indication of directory
	if ($line =~ /^\[(\/(.*))\]$/) {
		my $group; my $path;
		
		# -- PREVIOUS BLOCK --
		# Print global parameters for the previous block
		print OUT "commit_subject_template=$ptitle \n" if ($ptitle ne "");
		print OUT "generate_diffs=$pdiff \n" if ($pdiff ne "");
		print OUT "browser_base_url= trac ".$trac_url."/browser \n" if ($ptrac);
		print OUT "long_mail_action=$ptruncate truncate \n" if ($ptruncate ne "0");

		# If a directory was previously processed... Finish the job by printing email
		if ($flag && (($#dest >=  0) || ($cc ne ""))) {
			if ($cc ne "") { push (@dest,$cc) };
			print "[?] Printing the adresses for the previous directory\n" if $debug;
			print OUT "to_addr=";
			print OUT join(" ",@dest);
			print OUT "\n\n";
		} else {
			print OUT "\n";
		}
		
		# Processing a directory
		$flag = 1;

		# Reset the list of recipients
		@dest = ();
		
		# Reset properties
		$ptitle = "";
		$pdiff = "";
		$ptrac = 0;
		$ptruncate = 0;
		# -- -- -- -- -- -- --
		
		# -- CURRENT BLOCK --
		my $current_dir = $1; # Current directory browsing
		print "[?] Directory found : $current_dir \n" if $debug;
		
		# Try to find the root indication or correct the path for other dirs
		if ($1 eq "/") { $group = "root"; $path = ""; } else { $group = $1; $path = $2; $path .= "/" if ($2 =~ /^.*[^\/]$/); }

		print OUT "[".$group."]\n";
		print OUT "for_paths=^$path \n";
		
		my $exclude_path; 
		if ($group eq "root") { $group = "/"; } else { $group = $2; }
		
 
		
		next;
	}
		
	# Processing email adresses (only if a directory is browsed)
	if (($line =~ /^([^\@\s]+@[^\s]+)\s*$/) && $flag) {
		push (@dest,$1);
		print ("[!] Add recipient : $1 \n") if $debug;
	}
}

# The last processed directory must be finished
if ($flag && (($#dest >=  0) || ($cc ne ""))) {
	if ($cc ne "") { push (@dest,$cc) };

	# Print global parameters for the previous block
	print OUT "commit_subject_template=$ptitle \n" if ($ptitle ne "");
	print OUT "generate_diffs=$pdiff \n" if ($pdiff ne "");
	print OUT "browser_base_url= trac ".$trac_url."/browser \n" if ($ptrac);
	print OUT "long_mail_action=$ptruncate truncate \n" if ($ptruncate ne "0");

	print "[?] Printing the adresses for the previous directory\n" if $debug;
	print OUT "to_addr=";
	print OUT join(" ",@dest);
	print OUT "\n\n";
}

# Closing all flows
close(OUT);
close(USR);

# Print the path of output file
print "$output \n";

# Everything's gonna be allright
1;
