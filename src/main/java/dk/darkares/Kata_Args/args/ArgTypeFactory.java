package dk.darkares.Kata_Args.args;

import dk.darkares.Kata_Args.args.types.BoolArg;
import dk.darkares.Kata_Args.args.types.NumberArg;
import dk.darkares.Kata_Args.args.types.StringArg;

public class ArgTypeFactory implements IArgTypeFactory {

	public IArgument buildArgument(String argType, String defaultValue) {
		switch(argType) {
		case "bool": return new BoolArg(defaultValue != null && defaultValue.toLowerCase().equals("true"));
		case "number": return new NumberArg(Integer.parseInt(defaultValue));
		case "string": return new StringArg(defaultValue);
		}
		return null;
	}

}
