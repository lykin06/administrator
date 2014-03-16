package Gestion;

import java.util.ArrayList;

import Serialization.Data;

/**
 * <b>Class ListReparation</b>
 * <p>
 * This class load and store the list of reparation from the reparation.data
 * file
 * </p>
 * 
 * @author mathieu BOUTELIER
 * 
 */
public class ListReparation {
    /**
     * List of reparation
     */
    public ArrayList<Reparation> reparations;

    /**
     * <b>Constructor</b>
     * <p>
     * Load the list of Reparation from the file
     * </p>
     * 
     * @see Data#load(String)
     */
    @SuppressWarnings("unchecked")
	public ListReparation() {
        this.reparations = (ArrayList<Reparation>) Data.load("reparation");
    }

    /**
     * <b>Add a Reparation</b>
     * 
     * @param reparation
     *            the reparation to add
     * @return the result of {@link Data#store(Object, String)}
     */
    public boolean add(Reparation reparation) {
        this.reparations.add(reparation);
        return Data.store(reparations, "reparation");
    }

    /**
     * <b>Remove a Reparation</b>
     * 
     * @param reparation
     *            the reparation to remove
     * @return the result of {@link Data#store(Object, String)}
     */
    public boolean remove(Reparation reparation) {
        this.reparations.remove(reparation);
        return Data.store(reparations, "reparation");
    }
    
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < reparations.size(); ++i) {
            str.append(reparations.get(i).toString());
            str.append('\n');
        }
        
        return str.toString();
    }

    /********************* Getters and Setters *********************/

    public ArrayList<Reparation> getReparation() {
        return reparations;
    }

    /**
     * @param reparations
     *            the list to set
     * @return the result of {@link Data#store(Object, String)}
     */
    public boolean setReparation(ArrayList<Reparation> reparations) {
        this.reparations = reparations;
        return Data.store(reparations, "reparation");
    }

    public boolean reinitialize() {
        this.reparations = new ArrayList<Reparation>();
        return Data.store(reparations, "reparation");
    }
}
