package edu.uniandes.ecos.psp21;

import java.util.LinkedList;

/**
 * 
 * @author Jorge Alfonso Beltrán Sandoval
 * @category Print
 * 
 * Methods
 * printListDouble
 * printListInteger
 */
public class PrintConsole {

	/**
	 * printListDouble: Print a list of doubles in horizontal way separated by tab
	 * 
	 * @param linkedList
	 */
	public void printListDouble(LinkedList<Double> linkedList){
		for(Double value: linkedList){
			System.out.print(value+"\t");
		}
		System.out.print("\n");
	}
	

	/**
	 * printListInteger: Print a list of integers in horizontal way separated by tab
	 * 
	 * @param linkedList
	 */
	public void printListInteger(LinkedList<Integer> linkedList){
		for(Integer value: linkedList){
			System.out.print(value+"\t");
		}
		System.out.print("\n");
	}

}
