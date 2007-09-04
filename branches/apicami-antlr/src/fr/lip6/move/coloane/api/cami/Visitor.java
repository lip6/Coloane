package fr.lip6.move.coloane.api.cami;

public abstract class Visitor<Base, Result> {

	public abstract Result defaultVisit(Base o);
	
	@SuppressWarnings("unchecked")
	public Result visit(Base o) {
		try {
			Class<? extends Visitor> self = this.getClass();
			return (Result) self.getMethod("visitExact", o.getClass()).invoke(this, o);
		} catch (Exception e) {
			System.err.println("No method visit found for type " + o.getClass().getName() + " of visitor " + this.getClass().getName());
			return this.defaultVisit(o);
		}
	}
		
}
