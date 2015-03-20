package edu.uniandes.ecos.psp21;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.*;

/**
 * 
 * @author Jorge Alfonso Beltrán Sandoval
 * @category Print
 *
 * Attributes
 * PFunction
 * iterations
 * iterationsRange
 * listFx
 * 
 * Methods
 * doGet
 * showHome
 * printListSimpleDouble
 * printListSimpleInteger
 * printListSimpleInteger
 * printHeader
 * printFooter
 */

public class PrintWeb extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * printListSimpleDouble: Answer with a simple html table of double values
	 * @param linkedList
	 * @return text
	 */
	public String printListSimpleDouble(LinkedList<Double> linkedList){
		String text="<table>";
		for(double value: linkedList){
			text+="<tr>";
			text+="<td>"+value+"</td>";
			text+="</tr>";
		}
		text+="</table>";
		return text;
	}

	

	public String  printListSimpleString(LinkedList<String> linkedList){
		String text="<table>";
		for(String value: linkedList){
			text+="<tr>";
			text+="<td>"+value+"</td>";
			text+="</tr>";
		}
		text+="</table>";
		return text;
	}
	
	
	/**
	 * printListSimpleInteger: Answer with a simple html table of integer values
	 * @param linkedList
	 * @return text
	 */
	public String printListSimpleInteger(LinkedList<Integer> linkedList){
		String text="<table>";
		for(int value: linkedList){
			text+="<tr>";
			text+="<td>"+value+"</td>";
			text+="</tr>";
		}
		text+="</table>";
		return text;
	}
	


	/**
	 * printHeader: Answer with a simple header of an html doc
	 * @return text
	 */
	public String printHeader(){
		String text="<html><head><style>table, th, td {border: solid black 1px;}</style></head><body>";
		return text;
	}


	/**
	 * printFooter: Answer close the html doc
	 * @return text
	 */
	public String printFooter(){
		String texto="</body></html>";
		return texto;
	}
}


