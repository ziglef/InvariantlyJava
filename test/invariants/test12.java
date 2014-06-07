public class test12 {

	public static void main(String[] args) throws Exception{

		System.out.println("Declaring invariant foo which has not to be equal than 5");
		//@ INVARIANT I foo != 5
		System.out.println("Declaring foo with an initial value of 3 + 3");
		float foo = 3 + 3;
		System.out.println("Changing the value of foo to 5");
		foo = 5;
		System.out.println("Changing the value of foo to 4");
		foo = 4;
	}
}