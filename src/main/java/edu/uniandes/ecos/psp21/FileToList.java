package edu.uniandes.ecos.psp21;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class FileToList {

	/**
	 * fileToMultipleList: Reads the file as and put the components on a list of; list of double values
	 * 
	 * @param matrix 
	 * @return vector 
	 */
	public LinkedList<LinkedList<Double>> fileToMultipleList(String fileName) throws IOException{
		LinkedList<LinkedList<Double>> matrix=new LinkedList<LinkedList<Double>>();
		LinkedList<Double> vector=new LinkedList<Double>();
		File file=new File(fileName);
		FileReader fileReader=new FileReader(file);
		BufferedReader bufferReader=new BufferedReader(fileReader);
		String line;
		String[] parts;
		while((line=bufferReader.readLine ()) != null){
			
			parts=null;
			parts=line.split("-");
			try{
				vector=new LinkedList<Double>();
				for(String content:parts){
					vector.add(Double.parseDouble(content));
				}
				matrix.add(vector);
			}
			catch(Exception e){
				System.out.println("La linea "+vector);
			}
		}
		bufferReader.close();
		return matrix;
	}
	
	/**
	 * fileToSingleList: Reads the file send as string getting root as base path
	 * 
	 * @param fileName
	 * @return linkedList
	 */
	public LinkedList<Double> fileToSingleList(String fileName) throws IOException{
		LinkedList<Double> linkedList=new LinkedList<Double>();
		File archivo=new File(fileName);
		FileReader lectorArchivo=new FileReader(archivo);
		BufferedReader bufferLector=new BufferedReader(lectorArchivo);
		String linea;
		int numeroLinea=0;
		while((linea=bufferLector.readLine ()) != null){
			numeroLinea++;
			try{
				linkedList.add(Double.parseDouble(linea));
			}
			catch(Exception e){
				System.out.println("La linea "+numeroLinea);
			}
		}
		bufferLector.close();
		return linkedList;
	}
}
