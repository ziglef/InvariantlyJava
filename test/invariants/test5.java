public class test5 {

	public static void main(String[] args) throws Exception{

		System.out.println("Declaring invariant foo which has to be equal to 5.0");
		//@ INVARIANT F foo == 5.0
		System.out.println("Declaring foo with an initial value of 5.0");
		float foo = 5.0;
		System.out.println("Changing the value of foo to 3.0 + 3.0");
		foo = 3.0 + 3.0;
		System.out.println("Changing the value of foo to 4.2");
		foo = 4.2;
	}
}