package dk.darkares.Kata_Args.args;

public interface IArgument {
	boolean hasValue();
	void parseValue(String value);
	Object getValue();
}
