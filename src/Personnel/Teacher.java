package Personnel;

/**
 * Class Studient.
 * 
 * @author Aurelien COLOMBET
 * 
 */
public class Teacher extends Borrower {

	private static final long serialVersionUID = 1644172460332196612L;

	public Teacher(String firstName, String name, String email,
			String password, int identifiant, int delay) {
		super(firstName, name, email, password, identifiant, delay);
	}

	@Override
	public String toString() {
		return "enseignant";
	}
}
