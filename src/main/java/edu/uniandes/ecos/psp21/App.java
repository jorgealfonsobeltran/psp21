package edu.uniandes.ecos.psp21;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.omg.CORBA.SystemException;


/**
 * 
 * @author Jorge Alfonso Beltrán Sandoval
 * @category Control
 *
 */
public class App extends HttpServlet 
{
	public double PFunction=0;
	public LinkedList<Integer> iterations=new LinkedList<Integer>();
	public static LinkedList<LinkedList<Double>> matrix;
	public LinkedList<Double> listFx=new LinkedList<Double>();
	/**
	 * 
	 * @param args
	 * 
	 */
    public static void main( String[] args )
    {
    	PrintConsole impresoraConsola=new PrintConsole();
    	PrintWeb impresoraWeb=new PrintWeb();
    	MathOperations calculos=new MathOperations();
    	FileToList fileToList=new FileToList();
    	LinkedList<LinkedList<Double>> doubleListOfList =new LinkedList<LinkedList<Double>>();
    	LinkedList<Double> doubleListInside =new LinkedList<Double>();
    	LinkedList<Double> resultList=new LinkedList<Double>();
    	LinkedList<LinkedList<Double>> doubleListOfListTransponse =new LinkedList<LinkedList<Double>>(); 
    	int lineNumber=0;
    	double value=0;
    	
    	double resultado=0;
    	try {
    		doubleListOfList =fileToList.fileToMultipleList("arreglo.txt");
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1); 
		}
		
    	for(LinkedList<Double> doubleList:doubleListOfList ){
        	lineNumber++;
        	 calculos=new MathOperations();
    		calculos.setValues(doubleList.get(0),doubleList.get(1).intValue(),doubleList.get(2).intValue(),doubleList.get(3),doubleList.get(4));
        	resultado=calculos.iterator(0.5, 0);
        	resultList.add(resultado);
        	System.out.println("Resultado fila "+lineNumber);
        	System.out.print("Resultado x fila "+lineNumber+": ");
        	System.out.println(resultado);
    		resultado=calculos.iteratorOverError(0);
        	System.out.print("Resultado P(x) fila "+lineNumber+": ");
        	System.out.println(resultado);
        	System.out.print("Resultado Error fila "+lineNumber+": ");
        	System.out.println(calculos.getError()+"\n");

    	}
    	lineNumber=0;
    	for(int i=0;i<doubleListOfList.get(0).size();i++){
			doubleListInside=new LinkedList<Double>();
			for(int j=0;j<doubleListOfList.size();j++){
				doubleListInside.add(doubleListOfList.get(j).get(i).doubleValue());
				
    		}
			doubleListOfListTransponse.add(doubleListInside);
			if(i==doubleListOfList.get(0).size()-1){
				doubleListInside=new LinkedList<Double>();
				for(int j=0;j<resultList.size();j++){
					doubleListInside.add(resultList.get(j));
				}
				doubleListOfListTransponse.add(doubleListInside);
			}
    	}
    	matrix=doubleListOfListTransponse;
	    Server server = new Server(Integer.valueOf(System.getenv("PORT")));
	    ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
	    context.setContextPath("/");
	    server.setHandler(context);
	    context.addServlet(new ServletHolder(new App()),"/*");
		try {
			server.start();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	    try {
			server.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

    }
 

	/**
	 * doGet: Answer when called by get
	 * 
	 * @param req
	 * @param resp
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		 // Set response content type
		try{
		PrintWeb printweb=new PrintWeb();
  		int countLine=0;
  		MathOperations calculos=new MathOperations();
		  String resultadoString="";
		  PrintWriter out = response.getWriter();
		  String title = "Resultados ";
  		response.setContentType("text/html");
		if(req.getParameter("x_test")!=null && req.getParameter("dof")!=null && req.getParameter("iterations")!=null && req.getParameter("pOfX")!=null){
			calculos.setValues(Double.parseDouble(req.getParameter("x_test")),Integer.valueOf(req.getParameter("dof")),Integer.valueOf(req.getParameter("iterations")),Double.parseDouble(req.getParameter("pOfX")),0.000001);
		      
			  Double resultado=(calculos.iterator(0.5, 0));
			  resultadoString=resultado.toString();
			}
	      String docType =
	      "<!doctype html public \"-//w3c//dtd html 4.0 " +
	      "transitional//en\">\n";
	      out.println(docType +
	                "<html>\n" +
	                "<head><title>" + title + "</title></head>\n" +
	                "<body bgcolor=\"#f0f0f0\">\n" +
	                "<h1 align=\"center\">" + title + "</h1>\n" +
	                "<ul>\n" +
			    	"X="+resultadoString
	                +"  <li><b>X de prueba</b>: "
	                + req.getParameter("x_test") + "<br>" +
	                "  <li><b>Grados de libertad</b>: "
	                + req.getParameter("dof") + "<br>" +
	                "  <li><b>Iteraciones</b>: "
	                + req.getParameter("iterations") + "<br>" +
	                "  <li><b>P(x)</b>: "
	                + req.getParameter("pOfX") + "<br>" +
	                "</ul>\n" +
	                "<h1 align=\"center\">Resultados Consola</h1>\n" +
	                "<table style='table, th, td{ border: 1px solid black; }'><tr><th>Valores</th><th>X de prueba</th><th>Grados de libertad</th><th>Iteraciones</th><th>P de x</th><th>Error definido</th><th>X</th></tr>"+
	                "<tr><td>"+PFunction);
		    for(LinkedList<Double> listaDouble:matrix){
		    	out.print("<td>"+printweb.printListSimpleDouble(listaDouble)+"</td>");
		    	countLine++;
		    }
		    out.print("</tr></table><html><body><form action='form' method='GET'><table><tr><td>X de prueba: </td><td><input type='text' name='x_test'></td></tr><tr><td>Grados de libertad: </td><td><input type='text' name='dof' /></td></tr><tr><td>Iteraciones: </td><td><input type='text' name='iterations' /></td></tr><tr><td>P(x): </td><td><input type='text' name='pOfX' /></td></tr><tr><td rowspan='2'><input type='submit' value='Submit' /></td></tr></table></form></body></html>");
		}
		catch(Exception e){

		      PrintWriter out = response.getWriter();
			String docType =
			      "<!doctype html public \"-//w3c//dtd html 4.0 " +
			    	      "transitional//en\">\n";
			    	      out.println(docType +
			    	                "<html>\n" +
			    	                "<head><title>Error en la insercion de datos</title></head>\n" +
			    	                "<body bgcolor=\"	#f0f0f0\">\n" +
			    	                "<h1 align=\"center\">Error en la insercion de datos</h1>\n</body></html>");

		}
	}

}
