RAPPORT=rapport

all: $(RAPPORT).pdf clean

$(RAPPORT).pdf: $(RAPPORT).tex
	pdflatex $<
	pdflatex $<

clean:
	rm -f *aux *log *~ *toc

cleanall: clean
	rm -f $(RAPPORT).pdf