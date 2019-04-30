package dk.darkares.Kata_Args;

import dk.darkares.Kata_Args.args.ArgParser;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws Exception {
		String schema = String.join("\n", 
				"w,string,World name,World"
		);
    	ArgParser argParser = new ArgParser(schema);
    	argParser.parse(args);
		System.out.println("Hello " + argParser.getValueFor('w'));
	}
}
