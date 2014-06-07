public class test6 {

	public static void main(String[] args) throws Exception{

		System.out.println("Declaring invariant foo which has not to be equal to 5.0");
		//@ INVARIANT F foo != 5.0
		System.out.println("Declaring foo with an initial value 3.0 + 3.0");
		float foo = 3.0 + 3.0;
		System.out.println("Changing the value of foo to 5.0");
		foo = 5.0;
		System.out.println("Changing the value of foo to 4.2");
		foo = 4.2;
	}
}