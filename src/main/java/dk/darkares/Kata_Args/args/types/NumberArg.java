package dk.darkares.Kata_Args.args.types;

import dk.darkares.Kata_Args.args.IArgument;

public class NumberArg implements IArgument {

	private int value;
	
	public NumberArg(int value) {
		this.value = value;
	}
	public boolean hasValue() {
		return true;
	}
	
	public Object getValue() { return value; } 

	public void parseValue(String value) {
		this.value = Integer.parseInt(value);
	}

}
