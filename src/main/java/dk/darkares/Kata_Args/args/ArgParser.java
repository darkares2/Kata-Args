package dk.darkares.Kata_Args.args;

public class ArgParser {
	private ArgSchema argSchema;
	private boolean valueState;
	
	public ArgParser(String schema) throws Exception {
		argSchema = new ArgSchema(new ArgTypeFactory(), schema);
	}
	
	public void parse(String[] args) throws Exception {
		valueState = false;
		IArgument currentArgument = null;
		for (String item : args) {
			if (valueState) {
				currentArgument.parseValue(item);
				valueState = false;
			} else {
				if (item.length() != 2 || item.charAt(0) != '-')
					throw new Exception("Invalid argument syntax (" + item + ")");
				currentArgument = argSchema.getHandlerForShortIdentifier(item.charAt(1));
				if (currentArgument == null)
					throw new Exception("Invalid argument (" + item + ")");
				if (currentArgument.hasValue())
					valueState = true;
				else 
					currentArgument.parseValue(null);						
			}
		}
		if (valueState)
			throw new Exception("Missing value for argument");
	}
	
	public Object getValueFor(char shortIdentifier) {
		return argSchema.getValueFor(shortIdentifier);
	}

}
