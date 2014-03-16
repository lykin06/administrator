package Material;

/**
 * Class representing the Objects with OS.
 * 
 * @author Aurelien COLOMBET
 * 
 */
public class WithOS extends Material {

	private static final long serialVersionUID = -7136737206906137215L;
	private String os;

	public WithOS(String os, String name, int dureeEmprunt, int quantity) {
		super(name, dureeEmprunt, quantity);
		this.os = os;
	}

	/**
	 * @return the os
	 */
	public String getOs() {
		return os;
	}

	/**
	 * @param os
	 *            the os to set
	 */
	public void setOs(String os) {
		this.os = os;
	}

	@Override
	public String toString() {
		return this.getName() + "(" + this.getOs() + ")";
	}
}
