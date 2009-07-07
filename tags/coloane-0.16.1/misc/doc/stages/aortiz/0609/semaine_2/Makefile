CR2 = compte_rendu_2

all: $(CR2).pdf

$(CR2).dvi: $(CR2).tex
	latex $(CR2)

$(CR2).pdf: $(CR2).dvi
	dvipdf $(CR2)

clean:
	rm -f *~ \#* $(CR2).toc $(CR2).aux $(CR2).log $(CR2).dvi

cleanall: clean
	rm -f $(CR2).pdf