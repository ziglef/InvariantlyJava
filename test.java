
public class test {

	public static void main(String[] args) throws Exception{
		
		System.out.println("Declaring invariant foo which has to be less than 5.0");
		//@ INVARIANT F foo <= 5.0
		System.out.println("Declaring foo with an initial value of 4.0");
		float foo = 4.0;
		System.out.println("Changing the value of foo to 3.0");
		foo = 3.0;
		System.out.println("Changing the value of foo to -1");
		foo = -1;
		System.out.println("Changing the value of foo to 5.2");
		foo = 5.2;
		System.out.println("Changing the value of foo to 6.0");
		foo = 6.0;
		System.out.println("Changing the value of foo to 3.0 + 3.0");
		foo = 3.0 + 3.0;
	}
}
