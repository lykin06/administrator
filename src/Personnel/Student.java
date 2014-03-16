package Personnel;

/**
 * Class Studient
 * 
 * @author Aurelien COLOMBET
 * 
 */
public class Student extends Borrower {

	private static final long serialVersionUID = -312171983529593115L;

	public Student(String firstName, String name, String email,
			String password, int identifiant, int delay) {
		super(firstName, name, email, password, identifiant, delay);
	}

	@Override
	public String toString() {
		return "etudiant";
	}

}
