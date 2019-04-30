package dk.darkares.Kata_Args.args;

public interface IArgTypeFactory {
	IArgument buildArgument(String argType, String defaultValue);
}
