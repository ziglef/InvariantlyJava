public class test14 {

	public static void main(String[] args) throws Exception{

		System.out.println("Declaring invariant foo which has to be equal to 'a' OR 'b' OR 'c' ");
		//@ INVARIANT C foo == ['a','b','c']
		System.out.println("Declaring foo with an initial value of 'a'");
		char foo = 'a';
		System.out.println("Changing the value of foo to 'b'");
		foo = 'b';
		System.out.println("Changing the value of foo to 'd'");
		foo = 'd';
	}
}