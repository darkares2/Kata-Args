package dk.darkares.Kata_Args;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.mockito.Mockito;

import dk.darkares.Kata_Args.args.ArgParser;
import dk.darkares.Kata_Args.args.ArgSchema;
import dk.darkares.Kata_Args.args.IArgTypeFactory;
import dk.darkares.Kata_Args.args.types.BoolArg;
import dk.darkares.Kata_Args.args.types.NumberArg;
import dk.darkares.Kata_Args.args.types.StringArg;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ArgParserTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ArgParserTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( ArgParserTest.class );
    }

    
	String schema = String.join("\n"
	         , "b,bool,Boolean parameter,false"
	         , "n,number,Number parameter,1"
	         , "s,string,String parameter,default"
	);
	String stringTest = "Hello world";
	int numberTest = 1024;
	int numberTest2 = -5;
	

    /**
     * Full ArgParser test
     * @throws Exception 
     */
    public void testFullStringNumberAndDefaultBool() throws Exception
    {
    	ArgParser target = new ArgParser(schema);
    	String[] test = {"-s", stringTest, "-n", "" + numberTest};
    	target.parse(test);
    	assertFalse((boolean)target.getValueFor('b'));
    	assertEquals(stringTest, target.getValueFor('s'));
    	assertEquals(numberTest, (int)target.getValueFor('n'));
    }

    /**
     * Full ArgParser test
     * @throws Exception 
     */
    public void testFullBoolNegativeNumberDefaultString() throws Exception
    {
    	ArgParser target = new ArgParser(schema);
    	String[] test = {"-b", "-n", "" + numberTest2};
    	target.parse(test);
    	assertTrue((boolean)target.getValueFor('b'));
    	assertEquals("default", target.getValueFor('s'));
    	assertEquals(numberTest2, (int)target.getValueFor('n'));
    }

    /**
     * Full ArgParser test
     * @throws Exception 
     */
    public void testFullStringNegativeNumberBoolAtEnd() throws Exception
    {
    	ArgParser target = new ArgParser(schema);
    	String[] test = {"-s", stringTest, "-n", "" + numberTest2, "-b"};
    	target.parse(test);
    	assertTrue((boolean)target.getValueFor('b'));
    	assertEquals(stringTest, target.getValueFor('s'));
    	assertEquals(numberTest2, (int)target.getValueFor('n'));
    }

    /**
     * Full ArgParser test
     * @throws Exception 
     */
    public void testFullMissingValue() throws Exception
    {
    	ArgParser target = new ArgParser(schema);
    	String[] test = {"-s"};
    	assertThatThrownBy(() -> {target.parse(test);}).isInstanceOf(Exception.class).hasMessageContaining("Missing value");
    }
    /**
     * Full ArgParser test
     * @throws Exception 
     */
    public void testFullInvalidSyntax() throws Exception
    {
    	ArgParser target = new ArgParser(schema);
    	String[] test = {"-s", stringTest, "dummy", "-n", "" + numberTest2, "-b"};
    	assertThatThrownBy(() -> {target.parse(test);}).isInstanceOf(Exception.class).hasMessageContaining("Invalid argument syntax");
    }

    /**
     * Full ArgParser test
     * @throws Exception 
     */
    public void testFullInvalidArgument() throws Exception
    {
    	ArgParser target = new ArgParser(schema);
    	String[] test = {"-s", stringTest, "-f", "-n", "" + numberTest2, "-b"};
    	assertThatThrownBy(() -> {target.parse(test);}).isInstanceOf(Exception.class).hasMessageContaining("Invalid argument");
    }

}
