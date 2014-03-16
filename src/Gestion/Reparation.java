package Gestion;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import Material.Laptop;
import Material.Material;
import Material.Phone;
import Material.Tablet;
import Material.WithOS;

/**
 * <b>Class Reparation</b>
 * <p>
 * This class can send materials in reparation. Any reparation lasts one week
 * and start the same day is asked.
 * </p>
 * 
 * @author user
 * 
 */
public class Reparation implements Serializable {

	private static final long serialVersionUID = -7148847267441888226L;

	/**
     * Type of material to send in reparation
     */
    private Material material;

    /**
     * Starting date
     */
    private Date start;

    /**
     * Finishing date
     */
    private Date finish;

    /**
     * Number of objects to send in reparation
     */
    private int number;

    /**
     * <b>Constructor</b>
     * <p>
     * Create a Reparation for material
     * </p>
     * 
     * @param material
     *            the material to send in reparation
     */
    @SuppressWarnings("deprecation")
    public Reparation(Material material, int number) {
        this.material = material;
        this.start = new Date();
        this.finish = new Date(start.getYear(), start.getMonth(),
        start.getDate() + 7);
        this.number = number;
    }

    @Override
    public String toString() {
        String format = "dd/MM/yy";
        SimpleDateFormat formater = new SimpleDateFormat(format);
        StringBuilder str = new StringBuilder();

        str.append(number);
        str.append(" ");
        str.append(material.getName());
        if (material instanceof Tablet || material instanceof Laptop
                || material instanceof Phone) {
            str.append('(');
            str.append(((WithOS) material).getOs());
            str.append(')');
        }
        str.append(" en reparation du ");
        str.append(formater.format(start));
        str.append(" au ");
        str.append(formater.format(finish));

        return str.toString();
    }

    /********************* Getters and Setters *********************/

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getFinish() {
        return finish;
    }

    public void setFinish(Date finish) {
        this.finish = finish;
    }
}
