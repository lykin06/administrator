package Material;

import Config.Config;

/**
 * Class representing the Tablets.
 * 
 * @author Aurelien COLOMBET
 * 
 */
public class Tablet extends WithOS {

	private static final long serialVersionUID = -6583889558894299170L;

	public Tablet(String os) {
		super(os, "tablette", Config.DUREE_EMPRUNT_TABLET, Config.QUANTITE_INITIAL_TABLET);
	}
}
