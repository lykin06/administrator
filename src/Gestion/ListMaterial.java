package Gestion;

import java.util.ArrayList;

import Material.Camera;
import Material.Headphone;
import Material.Laptop;
import Material.Material;
import Material.Microphone;
import Material.Phone;
import Material.Tablet;
import Serialization.Data;

/**
 * <b>Class ListMaterial</b>
 * <p>
 * This class contains the stock of materials. It can load it and store it in
 * the file material.data
 * </p>
 * 
 * @author Aurelien COLOMBET
 * 
 * @see Data
 */
public class ListMaterial {

    /**
     * List of materials
     */
    private ArrayList<Material> materials;

    /**
     * <b>Constructor</b>
     * <p>
     * Generate the list of materials from the file.
     * </p>
     */
    public ListMaterial() {
        this.materials = this.load();
    }

    /**
     * <b>Load the information on the materials</b>
     * <p>
     * this method load the informations in the database and set them in the
     * arraylist.
     * <p>
     * 
     * @return a list of materials
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Material> load() {
        return (ArrayList<Material>) Data.load("material");
    }

    /**
     * <b>Writes a list of materials who are available</b>
     * 
     * <p>
     * This method skip all the materials who are not available for the moment.
     * If the user is a student, it skips all the materials who are forbidden to
     * them.
     * </p>
     * 
     * @param students
     *            if the user is a student
     * @return the list of available materials for Students
     */
    public String avaibleMaterials(boolean isStudent) {

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < materials.size(); ++i) {
            if (this.isNotAvailable(isStudent, materials.get(i))) {
                continue; // Skip this index
            }
            str.append(i + 1);
            str.append(". ");
            str.append(this.materials.get(i).toString());
            str.append(" [");
            str.append(this.materials.get(i).getQuantity());
            str.append("]\n");
        }
        return str.toString();
    }

    /**
     * <b>Watches if the material is still available</b>
     * 
     * @param student
     * @param material
     * @return true if the material is not available
     */
    public boolean isNotAvailable(boolean student, Material material) {
        if (student) {
            // Add here all materials who are not available for students:
            if (material instanceof Phone)
                return true;
            if (material instanceof Camera)
                return true;
            if (material instanceof Microphone)
                return true;
        }

        if (material.getQuantity() == 0)
            return true;

        return false;
    }

    /**
     * This method reinitialize the list of material
     */
    public void reinitialize() {
        materials = new ArrayList<Material>();
        materials.add(new Camera());
        materials.add(new Headphone());
        materials.add(new Microphone());
        materials.add(new Laptop("GNU/Linux"));
        materials.add(new Laptop("Windows"));
        materials.add(new Laptop("MAC"));
        materials.add(new Laptop("Chrome OS"));
        materials.add(new Phone("iOS"));
        materials.add(new Phone("Android"));
        materials.add(new Phone("Windows Phone"));
        materials.add(new Tablet("iOS"));
        materials.add(new Tablet("Android"));
        materials.add(new Tablet("Windows Phone"));

        this.store();
    }

    /**
     * <b>Add new Material</b>
     * <p>
     * This method search if there are new material and add them in the list.
     * </p>
     */
    public void addNewMaterial() {
        ArrayList<Material> temp = this.materials;
        this.reinitialize();

        if (this.materials.size() == temp.size()) {
            System.out.println("Aucun nouveau type de materiel trouve.");
        } else {
            for (int i = 0; i < materials.size(); ++i) {
                String name = this.materials.get(i).toString();
                if (!this.search(temp, name)) {

                    System.out.println("Nouveau type de materiel ajoute: "
                            + name);
                    temp.add(i,this.materials.get(i));
                }
            }
        }
        this.materials = temp;
        this.store();
    }

    /**
     * <b>Search if the temp list contains the material</b>
     * 
     * @param temp
     *            the list to search
     * @param name
     *            the name of the material
     * @return true if the material is in the list
     */
    private boolean search(ArrayList<Material> temp, String name) {
        for (int i = 0; i < temp.size(); ++i) {
            if (temp.get(i).toString().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * <b>Writes a list of materials</b>
     * 
     * @return the list of materials
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < materials.size(); ++i) {
            str.append(i + 1);
            str.append(". ");
            str.append(materials.get(i).toString());
            str.append(" [");
            str.append(materials.get(i).getQuantity());
            str.append("]\n");
        }
        return str.toString();
    }

    /**
     * @return the materials list
     */
    public ArrayList<Material> getMaterials() {
        return materials;
    }

    /**
     * <b>Set the list and it size</b>
     * 
     * @param materials
     *            the materials list to set
     */
    public void setMaterials(ArrayList<Material> materials) {
        this.materials = materials;
        this.store();
    }

    /**
     * <b>Store the materials list in the file</b>
     * 
     * @see Data#store(Object, String)
     */
    public void store() {
        Data.store(materials, "material");
    }

    /**
     * @param material
     * @return the indice in the list of material
     */
    public int getIndice(Material material) {
        for (int i = 0; i < materials.size(); ++i) {
            if (material.getClass().equals(materials.get(i).getClass())) {
                return i;
            }
        }
        return -1;
    }
}
