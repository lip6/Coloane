package fr.lip6.move.coloane.api.camiParser;

/** When walking ahead with cyclic DFA or for syntactic predicates,
 *  we need to record the state of the input stream (char index,
 *  line, etc...) so that we can rewind the state after scanning ahead.
 *
 *  This is the complete state of a stream.
 */
public final class CharStreamState {
	/** final into the char stream of next lookahead char */
	int p;

	/** What line number is the scanner at before processing buffer[p]? */
	int line;
	
	/** What char position 0..n-1 in line is scanner before processing buffer[p]? */
	int charPositionInLine;
}
