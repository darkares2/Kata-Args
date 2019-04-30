package dk.darkares.Kata_Args.args.types;

import dk.darkares.Kata_Args.args.IArgument;

public class StringArg implements IArgument {

	private String value;
	
	public StringArg(String value) {
		this.value = value;
	}
	public boolean hasValue() {
		return true;
	}
	
	public Object getValue() { return value; } 

	public void parseValue(String value) {
		this.value = value;
	}

}
