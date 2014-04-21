package fr.boute.administrator.personnel;

/**
 * Class Administrator
 *
 * @author Aurelien COLOMBET
 */
public class Administrator extends Personnel {

    private static final long serialVersionUID = -8508595362644698149L;

    public Administrator(String firstName, String name, String email,
                         String password, int identifiant) {
        super(firstName, name, email, password, identifiant);
    }

    @Override
    public String toString() {
        return "administrateur";
    }
}
