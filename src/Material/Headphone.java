package Material;

import Config.Config;

/**
 * Class representing the Headphones.
 * 
 * @author Aurelien COLOMBET
 * 
 */
public class Headphone extends Material {


	private static final long serialVersionUID = -5073088514073466832L;

	public Headphone() {
		super("casque", Config.DUREE_EMPRUNT_HEADPHONE, Config.QUANTITE_INITIAL_HEADPHONE);
	}
}
