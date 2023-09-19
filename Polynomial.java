

public class Polynomial{

	double[] coefficients;

	public Polynomial(){
		this.coefficients = new double[]{0};
	}

	public Polynomial(double[] c){
		this.coefficients = c;
	}

	public Polynomial add(Polynomial p2){
		int len = Math.max(this.coefficients.length,p2.coefficients.length);

		double[] added = new double[len];

		for(int i=0;i<len;i++){
			if(i<this.coefficients.length){
				added[i] += this.coefficients[i];
			}
			if(i<p2.coefficients.length){
				added[i] += p2.coefficients[i];
			}
		}
		return new Polynomial(added);
	}

	public double evaluate(double x){
		double result = 0;

        for (int i = 0; i < this.coefficients.length; i++) {
			result += this.coefficients[i]*Math.pow(x,i);
		}

		return result;
	}

	public boolean hasRoot(double x){
		return this.evaluate(x) == 0;

	}

}