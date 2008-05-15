INFOS=infos.dat
REV=rev.dat
GARDE=garde.tex

help:
	@echo -e "\n----------------------------------"
	@echo -e "Utilisation du Makefile\n"
	@echo -e "Pour creer une premiere page de garde"
	@echo -e "  1. Remplissez le fichier infos.dat"
	@echo -e "  2. make rev\n"
	@echo -e "Pour une simple revision"
	@echo -e "  1. make rev\n"
	@echo -e "Pour regenerer la pag de garde sans rien changer"
	@echo -e "  1. make again"
	@echo -e "----------------------------------\n"

rev: transform.pl $(INFOS) $(REV)
	perl transform.pl $(INFOS) $(REV) 
	pdflatex $(GARDE)
	
again: transform.pl $(REV) $(INFOS)
	pdflatex $(GARDE)
	
$(INFOS):
	touch $(INFOS)
	
$(REV):
	touch $(REV)
	
clean:
	rm -f garde.*
	
reset: clean
	rm -f rev.dat;
	rm -f infos.dat;
	@echo "AUTEUR=" >> infos.dat
	@echo "DATE=" >> infos.dat
	@echo "TYPE=" >> infos.dat
	@echo "NBPAGES=" >> infos.dat
	@echo "TITRE=" >> infos.dat
	
	
	
	
       
	
	
