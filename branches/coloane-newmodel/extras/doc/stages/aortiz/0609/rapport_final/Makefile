RF = rapport_final

all: $(RF).pdf

$(RF).dvi: $(RF).tex sections/*.tex
	latex $(RF)
	latex $(RF)
	latex $(RF)

$(RF).pdf: $(RF).dvi
	dvipdf $(RF)

clean:
	rm -f *~ \#* $(RF).toc $(RF).aux $(RF).log $(RF).dvi
	cd sections && rm -f *~ \#* $(RF).toc $(RF).aux $(RF).log $(RF).dvi

cleanall: clean
	rm -f $(RF).pdf