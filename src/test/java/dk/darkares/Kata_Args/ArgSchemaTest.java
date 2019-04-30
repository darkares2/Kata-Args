package dk.darkares.Kata_Args;

import org.mockito.Mockito;

import dk.darkares.Kata_Args.args.ArgSchema;
import dk.darkares.Kata_Args.args.IArgTypeFactory;
import dk.darkares.Kata_Args.args.types.BoolArg;
import dk.darkares.Kata_Args.args.types.NumberArg;
import dk.darkares.Kata_Args.args.types.StringArg;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ArgSchemaTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ArgSchemaTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( ArgSchemaTest.class );
    }

    
	String schema = String.join("\n"
	         , "b,bool,Boolean parameter,false"
	         , "n,number,Number parameter,1"
	         , "s,string,String parameter,default"
	);

    /**
     * Simple schema test.
     * @throws Exception 
     */
    public void testSchemaArgTypeFactory() throws Exception
    {
    	IArgTypeFactory argTypeFactoryMock = Mockito.mock(IArgTypeFactory.class);
    	ArgSchema target = new ArgSchema(argTypeFactoryMock, schema);
    	Mockito.verify(argTypeFactoryMock, Mockito.times(1)).buildArgument("bool", "false");
    	Mockito.verify(argTypeFactoryMock, Mockito.times(1)).buildArgument("number", "1");
    	Mockito.verify(argTypeFactoryMock, Mockito.times(1)).buildArgument("string", "default");
    }

    /**
     * Test that list was correctly built.
     * @throws Exception 
     */
    public void testSimpleSchema() throws Exception
    {
    	IArgTypeFactory argTypeFactoryMock = Mockito.mock(IArgTypeFactory.class);
    	Mockito.when(argTypeFactoryMock.buildArgument(Mockito.eq("bool"), Mockito.anyString())).thenReturn(new BoolArg(false));
    	Mockito.when(argTypeFactoryMock.buildArgument(Mockito.eq("number"), Mockito.anyString())).thenReturn(new NumberArg(10));
    	Mockito.when(argTypeFactoryMock.buildArgument(Mockito.eq("string"), Mockito.anyString())).thenReturn(new StringArg("Hello"));
    	ArgSchema target = new ArgSchema(argTypeFactoryMock, schema);
    	assert((boolean)target.getHandlerForShortIdentifier('b').getValue() == false);
    	assert((int)target.getHandlerForShortIdentifier('n').getValue() == 10);
    	assert(target.getHandlerForShortIdentifier('s').getValue().equals("Hello"));
    	assertNull(target.getHandlerForShortIdentifier('f'));
    }

}
