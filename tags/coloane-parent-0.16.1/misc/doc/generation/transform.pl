#!/bin/perl

my $infos; # Fichier contenant les informations sur le dossier
my $revisions; # Fichier contenant les differentes revisions
my $garde; # Fichier duplicata de la page de garde

# Divers
$flag = 0;

# Les donnees necessaire a la creation de la page de garde
my $auteur;
my $date;
my $type;
my $nbpages;
my $titre;

my $revauteur;
my $revdate;
my $revcomment;
my $revnum;

# Recuperation des fichiers d'informations
$infos = shift @ARGV;
$revisions = shift @ARGV;

# Parcours du fichier d'informations
open(INF, $infos) or exit -1;

while (<INF>) {
	my $ligne = $_;
	
	# Recuperation de l'auteur
	if ($ligne =~ /^AUTEUR\=(.+)$/) {
		$auteur = $1;
		next;
	}
	
	# Recuperation de la date
	if ($ligne =~ /^DATE\=(.+)$/) {
		$date = $1;
		next;
	}
	
	# Recuperation du type de document
	if ($ligne =~ /^TYPE\=(.+)$/) {
		$type = $1;
		next;
	}
	
	# Recuperation du nombre de pages du document
	if ($ligne =~ /^NBPAGES\=(.+)$/) {
		$nbpages = $1;
		next;
	} 
	
	# Recuperation du nombre de pages du document
	if ($ligne =~ /^TITRE\=(.+)$/) {
		$titre = $1;
		next;
	}
	
	# Toute autre entree est une erreur
	print "[1] Erreur $ligne";
}

if (($auteur eq "") || ($titre eq "") || ($date eq "") || ($type eq "") || ($nbpages eq "")) {
	print $auteur, $date, $titre, $nbpages, $type;
	print "[2] Echec lors de la lecture du fichier d'informations\n";
	exit -1;
}
 
# On verifie la taille du fichier de revisions
open(REV, $revisions) or die "[3] Impossible d'ouvrir le fichier de revisions\n";  

while (<REV>) {
	my $ligne = $_;
	if ($ligne =~ /^{/) {
		$flag=1 
	}
}
close REV;

# Si le fichier de revisions contient deja des informations, alors on le complete
if ($flag) {
	print "Information concernant la modification...\n";
	print "Date de la modification : \n";
	$revdate = <STDIN>;
	print "Numero de revision : \n";
	$revnum = <STDIN>;
	print "Auteur de la modification : \n";
	$revauteur = <STDIN>;
	print "Commentaires de revision : \n";
	$revcomment = <STDIN>;	
} else {
	$revauteur = $auteur;
	$revdate = $date;
	$revcomment = "Creation du document";
	$revnum = "1.0";
}

# Ecriture dans le fichier de revisions
open(REV, ">>$revisions") or die "Impossible d'ouvrir le fichier de revisions";
print REV "{\\Large \\strut}$revdate & $revnum & $revauteur & $revcomment \\\\ \\hline \n";  
close REV;

open(GAR, ">garde.tex");
# Ecriture de la page de garde
print GAR "% \n";
print GAR "%  Template de rapport \n";
print GAR "%  Projet Coloane \n";
print GAR "%  LIP6 / MoVe \n";
print GAR "% \n";
print GAR "%  Auteur : Jean-Baptiste Voron \n";
print GAR "%  Date   : 2007-04-13. \n";
print GAR "% \n";
print GAR " \n";
print GAR "\\documentclass[10pt]{article} \n";
print GAR " \n";
print GAR "\\usepackage[francais]{babel} \n";
print GAR "\\usepackage[utf8]{inputenc} \n"; 
print GAR " \n";
print GAR "% Quelques options... \n";
print GAR "\\usepackage{tabularx} \n";
print GAR "\\usepackage{graphicx} \n";
print GAR "\\usepackage{longtable} \n";
print GAR "\\usepackage{a4} \n";
print GAR " \n";
print GAR "% Pour utiliser une police sans serif tout au long du document \n";
print GAR "\\renewcommand{\\rmdefault}{phv} \n";
print GAR " \n";
print GAR "% Suppression de la numerotation \n";
print GAR "\\pagestyle{empty} \n";
print GAR " \n";
print GAR "% Modification des marges du document \n";
print GAR "\\oddsidemargin=-0.9cm \n";
print GAR "\\headsep=-1cm \n";
print GAR "\\textwidth 18cm \n";
print GAR "\\textheight 24cm \n";
print GAR " \n";	
print GAR "\\begin{document} \n";
print GAR "	 \n";                
print GAR "	%%% ENTETE %%%  \n";
print GAR "	\\noindent \\rule{18cm}{0.05cm}\\\\[0.25cm] \n";
print GAR "	\\noindent \n"; 
print GAR "	{\\LARGE Projet Coloane} \\hfill {\\Large LIP6 / MoVe}\\\\ \n";
print GAR "	\\noindent \\rule{18cm}{0.05cm}\\\\[0.25cm] \n";
print GAR "	{\\Large \\strut}{\\large Auteur: \\textbf{".$auteur."}}\\\\ \n";
print GAR "	{\\Large \\strut}{\\large Date: \\textbf{".$date."}}\\\\ \n";
print GAR "	{\\Large \\strut}{\\large Type: \\textbf{".$type."}}\\\\ \n";
print GAR "	{\\Large \\strut}{\\large Nombre de pages: \\textbf{".$nbpages."}}\\\\ \n";
print GAR "	\\rule{18cm}{0.05cm}\\\\ \n"; 
print GAR "	 \n";
print GAR "	%%% LOGO %%%  \n";
print GAR "	\\begin{figure}[htbp] \n";
print GAR "		\\begin{center} \n";
print GAR "			\\includegraphics[width=3.5cm]{iconeile.jpg} \n";
print GAR "		\\end{center} \n";
print GAR "	\\end{figure} \n";
print GAR "	 \n";
print GAR "	 \n";
print GAR "	%%% TITRE %%% \n";
print GAR "	 \n";
print GAR "	\\begin{center} \n";
print GAR "		\\LARGE{".$titre."} \n";
print GAR "	\\end{center} \n";
print GAR "	 \n";
print GAR "	\\vskip 1cm \n";
print GAR "	 \n";
print GAR "	%%% Tableau des versions %%% \n";
print GAR "	\\begin{center} \n";
print GAR "	\\begin{tabularx}{18cm}{|p{2.5cm}|p{2cm}|p{3.5cm}|X|} \n";
print GAR "	\\hline \n";
print GAR "	 {\\Large\\strut} {\\large Date} & {\\large Version} & {\\large Auteur} & {\\large Commentaire}\\\\ \n";
print GAR "	\\hline \n";
print GAR "	\\hline \n";
print GAR "	\\input{rev.dat} \n";
print GAR "	\\end{tabularx} \n";
print GAR "	\\end{center} \n";
print GAR "	 \n";
print GAR "\\end{document} \n";
close GAR;

1;



