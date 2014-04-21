package fr.boute.administrator.material;

import fr.boute.administrator.config.Config;

/**
 * Class representing the Phones.
 *
 * @author Aurelien COLOMBET
 */
public class Phone extends WithOS {

    private static final long serialVersionUID = 8093059069111048374L;

    public Phone(String os) {
        super(os, "telephone", Config.DUREE_EMPRUNT_PHONE, Config.QUANTITE_INITIAL_PHONE);
    }
}
