package invariants;

import java.util.ArrayList;

public class Invariant<T extends Comparable<T>> {

	public enum invType { LESSER, EQUAL, LESSERE, GREATERE, NOTEQUAL, GREATER, BETWEEN };
	
	private final invType type;
	private final String varType;

	private final T value;
	private final ArrayList<T> values;

	public Invariant( invType type, String varType, T value ){
		this.type = type;
		this.value = value;
		this.values = null;
		this.varType = varType;
	}

	public Invariant( invType type, String varType, ArrayList<T> values ){
		this.type = type;
		this.value = null;
		this.values = values;
		this.varType = varType;
	}

	public String getType(){
		return this.varType;
	}
	
	public invType getCompType(){
		return this.type;
	}
	
	public <U extends Comparable<?>> boolean checkValue(U value) {
		// If the value isn't a character ...
		if( !value.getClass().getName().equals(Character.class.getName()) ){
			switch(this.type){
				case LESSER: return this.value.compareTo((T) value) <= 0 ? true : false;
				case EQUAL: return this.value.compareTo((T) value) != 0 ? true : false;
				case LESSERE: return this.value.compareTo((T) value) < 0 ? true : false;
				case GREATERE: return this.value.compareTo((T) value) > 0 ? true : false;
				case NOTEQUAL: return this.value.compareTo((T) value) == 0 ? true : false;
				case GREATER: return this.value.compareTo((T) value) >= 0 ? true : false;
				case BETWEEN: return ( this.value.compareTo(values.get(0)) > 0 && this.value.compareTo(values.get(1)) < 0 ) ? true : false;
				default: return false;
			}
		} else {
			boolean eqResult = false;
			boolean difResult = true;
			for(int i=0; i<values.size(); i++){
				if( values.get(i).compareTo((T) value) == 0 ){
					difResult = false;
					eqResult = true;
				}
			}
			
			switch(this.type){
				case EQUAL: return !eqResult;
				case NOTEQUAL: return !difResult;
				default: return false;
			}
		}
	}


}
