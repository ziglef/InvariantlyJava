package comp;

import japa.parser.JavaParser;
import japa.parser.ast.CompilationUnit;

import java.io.FileInputStream;

public class Main {

	public static void main(String[] args) throws Exception{
		if( args.length <= 0){
			System.out.println("Usage: java invariants <java_file>");
			System.exit(-1);
		}
		
		FileInputStream in = new FileInputStream(args[0]);
		
		CompilationUnit cu;
		try {
			cu = JavaParser.parse(in);
		} finally {
			in.close();
		}
		
	}

}
