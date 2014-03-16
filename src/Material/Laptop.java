package Material;

import Config.Config;

/**
 * Class representing the Laptops.
 * 
 * @author Aurelien COLOMBET
 * 
 */
public class Laptop extends WithOS {

	private static final long serialVersionUID = -5991672158471121632L;

	public Laptop(String os) {
		super(os, "ordinateur", Config.DUREE_EMPRUNT_LAPTOP, Config.QUANTITE_INITIAL_LAPTOP);
	}
}
