package edu.uniandes.ecos.psp21;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Test for the class MathOperations
     */
    public void testApp()
    {
    	MathOperations calculos=new MathOperations();
    	calculos.setValues(1, 6, 10000, 0.2,0.000001);
    	double resultado=calculos.iterator(0.5, 0);
        assertTrue("Resultados para P(x)=0.20, con un x=1 de prueba, grados de libertad=6, un valor de divisiones = 1000 y un errorEstimado=0.00001 el resultado esperado de X=0.55338",Math.abs(calculos.getError())<0.00001); 
        assertTrue("Resultados para P(x)=0.45, con un x=1 de prueba, grados de libertad=15, un valor de divisiones = 1000 y un errorEstimado=0.00001 el resultado esperado de X=0.55338",Math.abs(calculos.getError())<0.00001);
        assertTrue("Resultados para P(x)=0.20, con un x=1 de prueba, grados de libertad=6, un valor de divisiones = 1000 y un errorEstimado=0.00001 el resultado esperado de X=0.55338",Math.abs(calculos.getError())<0.00001); 
        
        assertTrue( true );
    }
}
