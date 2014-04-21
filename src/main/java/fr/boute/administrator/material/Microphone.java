package fr.boute.administrator.material;

import fr.boute.administrator.config.Config;

public class Microphone extends Material {

    private static final long serialVersionUID = 8846849414495687761L;

    public Microphone() {
        super("microphone", Config.DUREE_EMPRUNT_MICROPHONE, Config.QUANTITE_INITIAL_MICROPHONE);
    }

}
