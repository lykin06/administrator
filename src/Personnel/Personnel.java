package Personnel;

import java.io.Serializable;

/**
 * Class Personnel
 * 
 * @author Aurelien COLOMBET
 * 
 */
public class Personnel implements Serializable {

	private static final long serialVersionUID = -943659132695225669L;
	private String firstName;
    private String name;
    private String email;
    private String password;
    private int identifiant;

    public Personnel(String firstName, String name, String email,
            String password, int identifiant) {
        this.setFirstName(firstName);
        this.setName(name);
        this.setEmail(email);
        this.setPassword(password);
        this.setIdentifiant(identifiant);
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName
     *            the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     *            the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the identifiant
     */
    public int getIdentifiant() {
        return identifiant;
    }

    /**
     * @param identifiant
     *            the identifiant to set
     */
    public void setIdentifiant(int identifiant) {
        this.identifiant = identifiant;
    }

    /**
     * @param personnel
     * @return a boolean which says if this personnel is egal to the personnel
     *         in parameters.
     */
    public boolean equals(Personnel personnel) {
        if (this.firstName.equals(personnel.getFirstName())
                && this.name.equals(personnel.getName())
                && this.email.equals(personnel.getEmail())
                && this.password.equals(personnel.getPassword())
                && this.identifiant == personnel.getIdentifiant()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "" + this.getFirstName() + " " + this.getName();
    }

    /**
     * @return the user's information
     */
    public String toStringLong() {
        StringBuilder str = new StringBuilder();
        str.append(identifiant);
        str.append(".\t");
        str.append(firstName);
        str.append("\t");
        str.append(name);
        str.append("\t");
        str.append(email);

        return str.toString();
    }
}
