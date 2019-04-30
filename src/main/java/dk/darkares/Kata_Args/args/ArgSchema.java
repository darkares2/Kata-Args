package dk.darkares.Kata_Args.args;

import java.util.ArrayList;
import java.util.List;

public class ArgSchema {

	private class SchemaEntry {
		private char shortIdentifier;
		private IArgument argument;
		private String shortDescription;
		
		public char getShortIdentifier() {
			return shortIdentifier;
		}
		public void setShortIdentifier(char shortIdentifier) {
			this.shortIdentifier = shortIdentifier;
		}
		public IArgument getArgument() {
			return argument;
		}
		public void setArgument(IArgument argument) {
			this.argument = argument;
		}
		public String getShortDescription() {
			return shortDescription;
		}
		public void setShortDescription(String shortDescription) {
			this.shortDescription = shortDescription;
		}
		
	}
	private IArgTypeFactory argTypeFactory;
	private String schema;
	private List<SchemaEntry> argumentList = new ArrayList<ArgSchema.SchemaEntry>();
	
	public ArgSchema(IArgTypeFactory argTypeFactory, String schema) throws Exception {
		this.argTypeFactory = argTypeFactory;
		this.schema = schema;
		build();
	}
	
	private void build() throws Exception {		
		for (String item: schema.split("\n")) {
			String[] items = item.split(",");
			if (items.length < 2)
				throw new Exception("Invalid argument schema");
			String defaultValue = "";
			SchemaEntry entry = new SchemaEntry();
			entry.setShortIdentifier(items[0].charAt(0));
			if (items.length >= 3)
				entry.setShortDescription(items[2]);
			if (items.length >= 4)
				defaultValue = items[3];
			entry.setArgument(argTypeFactory.buildArgument(items[1], defaultValue));
			argumentList.add(entry);
		} 		
	}

	public IArgument getHandlerForShortIdentifier(char shortIdentifier) {
		SchemaEntry entry = argumentList.stream().filter(arg -> arg.getShortIdentifier() == shortIdentifier).findFirst().orElse(null);
		return entry != null ? entry.argument : null;
	}

	public Object getValueFor(char shortIdentifier) {
		SchemaEntry entry = argumentList.stream().filter(arg -> arg.getShortIdentifier() == shortIdentifier).findFirst().orElse(null);
		return entry != null ? entry.argument.getValue() : null;
	}
}
