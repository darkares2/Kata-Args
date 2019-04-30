package dk.darkares.Kata_Args.args.types;

import dk.darkares.Kata_Args.args.IArgument;

public class BoolArg implements IArgument {

	private boolean value;
	
	public BoolArg(boolean value) {
		this.value = value;
	}
	
	public boolean hasValue() {
		return false;
	}
	

	public Object getValue() { return value; }

	public void parseValue(String value) {
		if (value == null || value.toLowerCase().equals("true"))
			this.value = true;
		else
			this.value = false;
	}

}
