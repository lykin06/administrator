package fr.boute.administrator.material;

import fr.boute.administrator.config.Config;

/**
 * Class representing the Cameras.
 *
 * @author Aurelien COLOMBET
 */
public class Camera extends Material {


    private static final long serialVersionUID = 9010826948871546644L;

    public Camera() {
        super("camera", Config.DUREE_EMPRUNT_CAMERA, Config.QUANTITE_INITIAL_CAMERA);
    }
}
