public class test7 {

	public static void main(String[] args) throws Exception{

		System.out.println("Declaring invariant foo which has to be less or equal than 5");
		//@ INVARIANT I foo <= 5
		System.out.println("Declaring foo with an initial value of 4");
		int foo = 4;
		System.out.println("Changing the value of foo to -1");
		foo = -1;
		System.out.println("Changing the value of foo to 3 + 3");
		foo = 3 + 3;
		System.out.println("Changing the value of foo to 5");
		foo = 5;

	}
}