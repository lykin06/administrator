package fr.boute.administrator.personnel;

/**
 * Class Borrower
 *
 * @author Aurelien COLOMBET
 */
public class Borrower extends Personnel {

    private static final long serialVersionUID = 6406407122573806023L;
    private int delay;

    public Borrower(String firstName, String name, String email,
                    String password, int identifiant, int delay) {
        super(firstName, name, email, password, identifiant);
        this.delay = delay;
    }

    public boolean equals(Borrower borrower) {
        if (this.getEmail().equals(borrower.getEmail())
                && this.getFirstName().equals(borrower.getFirstName())
                && this.getIdentifiant() == borrower.getIdentifiant()
                && this.getName().equals(borrower.getName())
                && this.getPassword().equals(borrower.getPassword())
                && this.delay == borrower.getCompteur()) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + delay;
    }

    public int getCompteur() {
        return this.delay;
    }

    public void setCompteur(int compteur) {
        this.delay = compteur;

    }
}
