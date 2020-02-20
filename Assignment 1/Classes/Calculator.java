package idk;

public class Calculator implements ICalculator {
	@Override
	public int add(int x, int y) {
		return x+y;
	}
	@Override
	public float divide(int x, int y) {
		if(y==0) {
			throw new RuntimeException("INVALID : Division by zero.");
		}else if(x==0 || x==-0){
			return 0;
		}else {
			return (float)x/y;
		}
		
	}
}
