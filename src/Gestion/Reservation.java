package Gestion;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import Material.Material;
import Personnel.Borrower;

/**
 * Class Reservation TODO commenter cette classe
 * 
 * @author Aurelien COLOMBET
 * 
 */
public class Reservation implements Serializable {

	private static final long serialVersionUID = -4466367633419131483L;
	private Borrower borrower;
	private Material material;
	private Date start;
	private Date finish;
	private boolean accepted;

	public Reservation(Borrower borrower, Material material, Date start, Date finish) {
		this.borrower = borrower;
		this.material = material;
		this.start = start;
		this.finish = finish;
		this.accepted = false;
	}

	@Override
	public String toString() {
		String format = "dd/MM/yy";
		SimpleDateFormat formater = new SimpleDateFormat(format);
		StringBuilder str = new StringBuilder();
		str.append(material.toString());
		str.append(" - du ");
		str.append(formater.format(start));
		str.append(" au ");
		str.append(formater.format(finish));
		if(accepted) {
		    str.append(" - acceptee");
		} else {
		    str.append(" - pas encore acceptee");
		}
		
		return str.toString();
	}
	
	public boolean equals(Material mat){
		if (this.material.getQuantity()==mat.getQuantity()
				&& this.material.getName().equals(mat.getName())
				&& this.material.getDureeEmprunt()==mat.getDureeEmprunt())
			return true;
		else return false;			
	}

	/**
	 * @return the material
	 */
	public Material getMaterial() {
		return material;
	}

	/**
	 * @param material
	 *            the material to set
	 */
	public void setMaterial(Material material) {
		this.material = material;
	}

	/**
	 * @return the borrower
	 */
	public Borrower getBorrower() {
		return borrower;
	}

	/**
	 * @param borrower
	 *            the borrower to set
	 */
	public void setBorrower(Borrower borrower) {
		this.borrower = borrower;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return start;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.start = date;
	}
	
	public Date getFinish() {
	    return finish;
	}
	
	public void setFinish(Date finish) {
	    this.finish = finish;
	}

	public boolean isAccepted() {
		return accepted;
	}

	/**
	 * @param accepted
	 *            the accepted to set
	 */
	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}
}
