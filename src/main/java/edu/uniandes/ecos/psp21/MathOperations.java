package edu.uniandes.ecos.psp21;

import java.math.BigDecimal;
import java.util.LinkedList;


/**
 * 
 * @author Jorge Alfonso Beltrán Sandoval
 * @category Math
 * 
 * Attributes
 * x, dof, numSeg 
 * 
 * Methods
 * sum
 * TFunctionPartConstant
 * TFunctionPartA
 * TfunctionPartB
 * TFunction
 * PFunction
 * Multiplier
 * w
 * gammaFunction
 * SetValues 
 * 
 */
public class MathOperations {

	/**
	 * Attributes
	 */
	private double x; 
	private double estimatedError; 
	private int dof;
	private double error;
	private int numSeg;
	private double pOfX;
	public LinkedList<Integer> iterations=new LinkedList<Integer>();
	public LinkedList<Double> iterationsRange=new LinkedList<Double>();
	public LinkedList<Double> listFx=new LinkedList<Double>();
	
	
	
	/**
	 * Methods
	 */
	
	/**
	 * Constructor
	 * Initialize the the attributes on zero
	 */
	public MathOperations(){
		x=0;
		dof=0;
		numSeg=0;
	}
	
	
	/**
	 * Sum: Adds the values of a LinkedList and returns the result
	 * 
	 * @param linkedList
	 * @return result
	 */
	public double Sum(LinkedList<Double> linkedList){
		double result=0;
		for(Double valor : linkedList){
			result+=valor;
		}
		return result;
	}

	/**
	 * TFunctionPartA: Obtains the value of the first part TFunction 
	 * 
	 * @param x_on_i
	 * @return result
	 */
	public double TFunctionPartA(double x_on_i){
		double result=0;
		if(dof!=0){
			result=1+(Math.pow(x_on_i,2)/dof);
			return result;
		}
		else{
			System.out.println("El valor de dof (grados de libertad) es cero");
			return result;
		}
	}
		
	/**
	 * TFunctionPartB: Obtains the value of the first part elevated
	 * 
	 * @param x_on_i
	 * @return result
	 */
	public double TFunctionPartB(double x_on_i){
		double result=0;
		double exp=0;
		double resultTFunctionPartA=0;
		if(dof!=0){
			exp=(dof+1)/2*(-1);
			resultTFunctionPartA=TFunctionPartA(x_on_i);
			result=Math.pow(resultTFunctionPartA, exp);
			return result;
		}
		else{
			System.out.println("El valor de dof (grados de libertad) es cero");
			return result;
		}
	}

	/**
	 * TFunctionPartConstant: Obtains the constant value TFunction 
	 * 
	 * @return result
	 */
	public double TFunctionPartConstant(){
		double result=0;
		if(dof!=0){
			double factor=(dof+1)/(double)2;
			result=gammaFunction(factor)/(Math.pow(dof*Math.PI,0.5)*gammaFunction(dof/(double)2));
			return result;
		}
		else{
			System.out.println("El valor de dof (grados de libertad) es cero");
			return result;
		}
	}
	
	/**
	 * TFunction: Obtains the value TFunction for each x_on_i
	 * 
	 * @return listFunctionx_on_i
	 */
	public LinkedList<Double> TFunction(){
		LinkedList<Double> listFunctionx_on_i=new LinkedList<Double>();
		double constant=TFunctionPartConstant();
		double x_on_i=0;
		for(int i=0;i<=numSeg;i++){
			listFunctionx_on_i.add(TFunctionPartB(x_on_i)*multiplier(x_on_i,i)*constant*width()/3);
			iterationsRange.add(x_on_i);
			listFx=listFunctionx_on_i;
			x_on_i+=width();
			iterations.add(i);
		}
		return listFunctionx_on_i;
	}
	
	/**
	 * gammaFunction: Obtains the value of the factorial depending if fraction  or integer 
	 * 
	 * @param factor
	 * @return result 
	 */
	public double gammaFunction(double factor){
		double result=0;
		if(factor%1!=0){
			result=factorialFraction(factor-1);
		}
		else{
			result=factorial(factor-1);
		}
		return result;
	}

	/**
	 * factorialFraction: Obtains the factorial of fraction
	 * 
	 * @param factor
	 * @return result
	 */
	public double factorialFraction(double factor){
		double result=1;
		for(double i=factor;i>0;i--){
			if(i==0.5){
				result*=Math.sqrt(Math.PI)*i;	
			}
			else{
				result*=i;
			}
		}
		return result;
	}

	/**
	 * factorial: Obtains the factorial
	 * 
	 * @param factor
	 * @return result
	 */
	public double factorial(double factor){
		double result=1;
		for(int i=1;i<=factor;i++){
			result*=i;
		}
		return result;
	}
	
	/**
	 * multiplier: Obtains the value of the multiplier in each iteration
	 * @param x_on_i
	 * @param iterator
	 * @return result
	 */
	public int multiplier(double x_on_i,int iterator){
		if(x_on_i==0 || x_on_i==x){
			return 1;
		}
		else if(iterator%2>0){
			return 4;
		}
		else if(iterator%2==0){
			return 2;
		}
		else{
			return 0;
		}
	}	

	/**
	 * width: Obtains the value of segment width
	 * @return result
	 */
	public double width(){
		double result= x/numSeg;
		return result;
	}

	/**
	 * PFunction: Obtains the value of Simpson rule
	 * @return result
	 */
	public double PFunction(){
		double result= 0;	
		result= Sum(TFunction());
		return result;
	}
	
	/**
	 * setValues: Set the values of the  private attributes
	 * @param setX
	 * @param setDof
	 * @param setNumSeg
	 * @param setPOfX
	 * @param setError
	 */
	public void setValues(double setX, int setDof, int setNumSeg,double setPOfX,double setError){
		x=setX;
		dof=setDof;
		numSeg=setNumSeg;
		pOfX=setPOfX;
		estimatedError=setError;
	}
	
	/**
	 * iterator: Executes the PFunction to calcuate x depending on the error and the division number
	 * @param substract: Set the number to subtract var that wil vary depending of the error
	 * @param pastError: Set the number of the last error 
	 */
	public double iterator(double subtract,double pastError){
		double p=PFunction();
		error=p-pOfX;
		if(Math.abs(error)<estimatedError){
			return x;
		}
		else{
			if(error>0){
				if(pastError!=0 && ((error>0 && pastError<0) || (error<0 && pastError>0))){
					subtract=subtract/2;
				}
				x=x-subtract;
			}
			else{
				if(pastError!=0 && ((error>0 && pastError<0) || (error<0 && pastError>0))){
					subtract=subtract/2;
				}
				x=x+subtract;
			}
			return iterator(subtract,error);
		}
	}
	
	

	/**
	 * iteratorOverError: get the value of current PFunction and subtract it from the one of the past to obtain a precise value of Integral of TFunction
	 * @param pFuntionPast
	 */
	public double iteratorOverError(double pFuntionPast){
		double pFunctionNow=PFunction();
		error=pFunctionNow-pFuntionPast;
		if(Math.abs(error)<estimatedError){
			return pFunctionNow;
		}
		else{
			numSeg=numSeg*2;
			return iteratorOverError(pFunctionNow);
		}
	}

	public double getX() {
		return x;
	}


	public void setX(double x) {
		this.x = x;
	}


	public double getEstimatedError() {
		return estimatedError;
	}


	public void setEstimatedError(double estimatedError) {
		this.estimatedError = estimatedError;
	}


	public int getDof() {
		return dof;
	}


	public void setDof(int dof) {
		this.dof = dof;
	}


	public int getNumSeg() {
		return numSeg;
	}


	public void setNumSeg(int numSeg) {
		this.numSeg = numSeg;
	}


	public double getpOfX() {
		return pOfX;
	}


	public void setpOfX(double pOfX) {
		this.pOfX = pOfX;
	}

	public double getError() {
		return error;
	}


	public void setError(double error) {
		this.error = error;
	}

}
