package eis.iilang;

public class XMLVisitor implements IILObjectVisitor {

	private static String indent = "  ";
	private static String newline = "\n";

	@Override
	public Object visit(Action element, Object object) {

		String ret = "";
		
		ret += object.toString() + "<action name=\"" + element.name + "\">" + newline;
		for ( Parameter p : element.getParameters() ) {
			ret += object.toString() + indent + "<actionParameter>" + newline;
			ret += p.accept(this,object.toString() + indent + indent);
			ret += object.toString() + indent + "</actionParameter>" + newline;
		}
		ret += object.toString() + "</action>";

		return ret;
	
	}

	@Override
	public Object visit(DataContainer element, Object object) {
		assert false : "Not expected";
		return "";
	}

	@Override
	public Object visit(Function element, Object object) {
	
		String ret = "";
		
		ret += object.toString() + "<function name=\"" + element.getName() + "\">" + newline;
		for ( Parameter p : element.getParameters() ) {
			ret += p.accept(this,object.toString() + indent);
		}
		ret += object.toString() + "</function>"+ newline;

		return ret;

	}

	@Override
	public Object visit(Identifier element, Object object) {

		return object.toString() + "<identifier value=\"" + element.getValue() + "\"/>" + newline;

	}

	@Override
	public Object visit(IILElement element, Object object) {
		assert false : "Not expected";
		return "";
	}

	@Override
	public Object visit(Numeral element, Object object) {

		return object.toString() + "<number value=\"" + element.getValue() + "\"/>" + newline;
	
	}
	
	@Override
	public Object visit(Parameter element, Object object) {
		assert false : "Not expected";
		return "";
	}

	@Override
	public Object visit(ParameterList element, Object object) {
		String ret = "";
		
		ret += object.toString() + "<parameterList>" + newline;
		for ( Parameter p : element ) {
			ret += p.accept(this,object.toString() + indent);
		}
		ret += object.toString() + "</parameterList>" + newline;

		return ret;
	}

	@Override
	public Object visit(Percept element, Object object) {

		String ret = "";
		
		ret += object.toString() + "<percept name=\"" + element.name + "\">" + newline;
		ret += object.toString() + indent + "<perceptParameter>" + newline;
		for ( Parameter p : element.getParameters() ) {
			ret += p.accept(this,object.toString() + indent + indent);
		}
		ret += object.toString() + indent + "</perceptParameter>" + newline;
		ret += object.toString() + "</percept>" + newline;

		return ret;
	
	}

	@Override
	public Object visit(TruthValue element, Object object) {

		return object.toString() + "<truthvalue value=\"" + element.getValue() + "\"/>" + newline;

	}

	public static String staticVisit(IILElement element) {
		
		String ret = "";
		
		IILObjectVisitor visitor = (IILObjectVisitor) new XMLVisitor();
		
		if ( element instanceof Action ) {
			ret += visitor.visit((Action)element,"");
		}
		else if ( element instanceof Function ) {
			ret += visitor.visit((Function)element,"");
		}
		else if ( element instanceof Identifier ) {
			ret += visitor.visit((Identifier)element,"");
		}
		else if ( element instanceof Numeral ) {
			ret += visitor.visit((Numeral)element,"");
		}
		else if ( element instanceof ParameterList ) {
			ret += visitor.visit((ParameterList)element,"");
		}
		else if ( element instanceof Percept ) {
			ret += visitor.visit((Percept)element,"");
		}
		else if ( element instanceof TruthValue ) {
			ret += visitor.visit((TruthValue)element,"");
		}
		else if ( element instanceof Percept ) {
			assert false : "not expected " + element.getClass();
		}

		return ret;
		
	}
	
}
