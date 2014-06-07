package invariants;

import java.io.Serializable;
import java.util.HashMap;

public class Invariants implements Serializable{

	private static final long serialVersionUID = 1L;
	private HashMap<String, Invariant<? extends Comparable<?>>> invariants;

	private Invariants() {
		this.invariants = new HashMap<String, Invariant<? extends Comparable<?>>>();
	}

	private static class InvariantsHolder {
		public static final Invariants INSTANCE = new Invariants();
	}

	public static Invariants getInstance() {
		return InvariantsHolder.INSTANCE;
	}

	protected Object readResolve() {
		return getInstance();
	}

	// If an invariant with given name does not exist it adds it the the map and returns true, else returns false
	public boolean addInvariant(String key, Invariant<? extends Comparable<?>> value){
		boolean keyExists = invariants.containsKey(key);
		if(!keyExists){
			this.invariants.put(key, value);
			return true;
		}
		
		return false;
	}
	
	// Gets an invariant
	public Invariant<?> getInvariant(String key){
		if(checkInvariant(key))
			return this.invariants.get(key);
		else
			return null;
	}
	
	// checks if a given invariant name exists within the map, if so returns true, else returns false
    public boolean checkInvariant(String name){
    	return this.invariants.containsKey(name);
    }
    
    // returns false if the invariant doesn't exist or if the value isn't within its values, true if everything is fine
    public <T extends Comparable<?>> boolean checkInvariantValue(String name, T value){
    	if( !checkInvariant(name) )
    		return false;
    	
    	return this.invariants.get(name).checkValue(value);
    }
}
