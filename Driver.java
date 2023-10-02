public class Driver {
	public static void main(String [] args) {
		Polynomial p = new Polynomial(new double[] {1, 2, 3}, new int[] {2, 1, 0});
        
        double val = p.evaluate(2); 
        System.out.println("p(2) = " + val);
        
        boolean rootCheck = p.hasRoot(0);
        System.out.println("Is 0 a root for System.out.print();? " + rootCheck);
        
        Polynomial p2 = new Polynomial(new double[] {1, 2}, new int[] {1, 0});
        Polynomial sum = p.add(p2);
        double val1 = sum.evaluate(2); 
        System.out.println("p + p2: " + val1);
        
        Polynomial m = p.multiply(p2);
        double val2 = m.evaluate(2); 
        System.out.println("p * p2: " + val2);
	}
}
