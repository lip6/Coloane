package fr.lip6.move.coloane.api.cami;

public abstract class VoidVisitor<Base> {

	public abstract void defaultVisit(Base o) throws Exception;

	@SuppressWarnings("unchecked")
	public void visit(Base o) throws Exception {
		try {
			Class< ? extends VoidVisitor> self = this.getClass();
			self.getMethod("visitExact", o.getClass()).invoke(this, o);
		} catch (Exception e) {
			System.err.println("No method visitExact found for type " + o.getClass().getName() + " of visitor " + this.getClass().getName());
			this.defaultVisit(o);
		}
	}

}
