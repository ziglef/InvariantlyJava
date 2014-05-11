package invariants;

import java.util.ArrayList;

public class Invariant<T extends Comparable<T>> {

	// TODO: Change to enum //
	private final int type;
	/* 
	 * Type can have 7 values:
	 * 0 - Must be lesser than value
	 * 1 - Must be equal to value
	 * 2 - Must be lesser or equal to the value
	 * 3 - Must be greater or equal to the value
	 * 4 - Must not be equal to the value
	 * 5 - Must be greater than to the value
	 * 6 - Must be between value X and Y
	 */

	private final T value;
	private final ArrayList<T> values;

	public Invariant( int type, T value ) throws InvalidTypeException{
		if( type < 0 || type > 6 )
			throw new InvalidTypeException();

		this.type = type;
		this.value = value;
		this.values = null;
	}

	public Invariant( int type, ArrayList<T> values ) throws WrongSizeException, InvalidTypeException{
		if( type < 0 || type > 6 )
			throw new InvalidTypeException();

		this.type = type;
		this.value = null;

		if( values.size() != 2 )
			throw new WrongSizeException();

		this.values = values;
	}

	@SuppressWarnings("unchecked")
	public <U extends Comparable<?>> boolean checkValue(U value) {

		// If the value isn't a character ...
		if( !value.getClass().getName().equals(Character.class.getName()) ){
			switch(this.type){
				case 0: return this.value.compareTo((T) value) < 0 ? true : false;
				case 1: return this.value == value ? true : false;
				case 2: return this.value.compareTo((T) value) <= 0 ? true : false;
				case 3: return this.value.compareTo((T) value) >= 0 ? true : false;
				case 4: return this.value != value ? true : false;
				case 5: return this.value.compareTo((T) value) > 0 ? true : false;
				case 6: return ( this.value.compareTo(values.get(0)) > 0 && this.value.compareTo(values.get(1)) < 0 ) ? true : false;
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
				case 1: return eqResult;
				case 4: return difResult;
				default: return false;
			}
		}
	}


}
