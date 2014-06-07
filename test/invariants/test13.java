public class test13 {

	public static void main(String[] args) throws Exception{

		System.out.println("Declaring invariant foo which has not to be equal to 'a' AND 'b' AND 'c' ");
		//@ INVARIANT C foo != ['a','b','c']
		System.out.println("Declaring foo with an initial value of 'd'");
		char foo = 'd';
		System.out.println("Changing the value of foo to 'a'");
		foo = 'a';
		System.out.println("Changing the value of foo to 'e'");
		foo = 'e';
	}
}