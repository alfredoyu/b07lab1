import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Polynomial{

	double[] coefficients;
	int[] exponents;

	public Polynomial(double[] c, int[] e){
		this.coefficients = c;
		this.exponents = e;
	}
	
	public Polynomial(){
		this.coefficients = new double[]{0};
		this.exponents = new int[]{0};
	}
	
	public Polynomial(File file){
		List<double> coList = new ArrayList<>();
		List<int> exList = new ArrayList<>();
		try {
			Scanner scan = new Scanner(file);
			String content = scan.next();
			String[] terms = content.split("(?=[+-])");
		
		
			for (String term : terms) {
				if(term.contains("x")) {
					String[] parts = term.split("x");
					if(parts.length == 1) {
						coList.add(Double.parseDouble(parts[0]));
						exList.add(1);
					}
					else {
						coList.add(Double.parseDouble(parts[0]));
						exList.add(Integer.parseInt(parts[1]));
					}
				}
				else {
					coList.add(Double.parseDouble(term));
					exList.add(0);
				}
			}
		}catch(FileNotFoundException e) {
				e.printStackTrace();
		}
		this.coefficients = coList.stream().mapToDouble(Double::doubleValue).toArray();
	    this.exponents = exList.stream().mapToInt(Integer::intValue).toArray();
	}

	public Polynomial add(Polynomial p2){
		List<double> coList =  new ArrayList<>();
		List<int> exList = new ArrayList<>();
		
		for (int i = 0; i < this.coefficients.length; i++) {
	        int index = -1;
	        for (int j = 0; j < exList.size(); j++) {
	            if (exList.get(j) == this.exponents[i]) {
	                index = j;
	                break;
	            }
	        }
	        if (index != -1) {
	            coList.set(index, coList.get(index) + this.coefficients[i]);
	        } else {
	            exList.add(this.exponents[i]);
	            coList.add(this.coefficients[i]);
	        }
	    }
		
		for (int i = 0; i < p2.coefficients.length; i++) {
	        int index = -1;
	        for (int j = 0; j < exList.size(); j++) {
	            if (exList.get(j) == p2.exponents[i]) {
	                index = j;
	                break;
	            }
	        }
	        if (index != -1) {
	            coList.set(index, coList.get(index) + p2.coefficients[i]);
	        } else {
	            exList.add(p2.exponents[i]);
	            coList.add(p2.coefficients[i]);
	        }
	    }

	    double[] coArray = coList.stream().mapToDouble(Double::doubleValue).toArray();
	    int[] exArray = exList.stream().mapToInt(Integer::intValue).toArray();
	    return new Polynomial(coArray, exArray);
	}

		
	public double evaluate(double x){
		double result = 0;

        for (int i = 0; i < this.coefficients.length; i++) {
			result += this.coefficients[i]*Math.pow(x,this.exponents[i]);
		}

		return result;
	}

	public boolean hasRoot(double x){
		return this.evaluate(x) == 0;

	}
	
	public Polynomial multiply(Polynomial p2) {
		List<double> coList = new ArrayList<>();
		List<int> exList = new ArrayList<>();
		
		for (int i = 0; i < this.coefficients.length; i++) {
	        for (int j = 0; j < p2.coefficients.length; j++) {
	            double currentCo = this.coefficients[i] * p2.coefficients[j];
	            int currentEx = this.exponents[i] + p2.exponents[j];

	            int index = -1;
	            for (int k = 0; k < exList.size(); k++) {
	                if (exList.get(k) == currentEx) {
	                    index = k;
	                    break;
	                }
	            }

	            if (index != -1) {
	                coList.set(index, coList.get(index) + currentCo);
	            } else {
	                exList.add(currentEx);
	                coList.add(currentCo);
	            }
	        }
	    }

	    double[] coArray = coList.stream().mapToDouble(Double::doubleValue).toArray();
	    int[] exArray = exList.stream().mapToInt(Integer::intValue).toArray();
	    return new Polynomial(coArray, exArray);
	}
	
	public void saveToFile(String fileName) {
		try {
	        FileWriter polyFile = new FileWriter(fileName);
	        for (int i = 0; i < this.coefficients.length; i++) {
	            if (i != 0 && this.coefficients[i] > 0) {
	                polyFile.write('+');
	            }
	            if (this.coefficients[i] != 0) {
	                polyFile.write(String.valueOf(this.coefficients[i]));
	            }
	            if (this.exponents[i] != 0) {
	                polyFile.write('x');
	                if (this.exponents[i] != 1) { // don't write power if it's 1
	                    polyFile.write('^' + String.valueOf(this.exponents[i]));
	                }
	            }
	        }
	        polyFile.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

}