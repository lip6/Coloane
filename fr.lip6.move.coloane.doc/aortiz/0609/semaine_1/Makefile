CR1 = compte_rendu_1

all: $(CR1).pdf

$(CR1).dvi: $(CR1).tex
	latex $(CR1)

$(CR1).pdf: $(CR1).dvi
	dvipdf $(CR1)

clean:
	rm -f *~ \#* $(CR1).toc $(CR1).aux $(CR1).log $(CR1).dvi

cleanall: clean
	rm -f $(CR1).pdf