package fr.boute.administrator.vue;

import fr.boute.administrator.gestion.*;
import fr.boute.administrator.material.Material;
import fr.boute.administrator.material.WithOS;
import fr.boute.administrator.personnel.Administrator;

import java.util.ArrayList;
import java.util.Date;

/**
 * <b>MaterialGestion Class</b>
 * <p>
 * This class provides an interface to manage all the materials.
 * </p>
 *
 * @author mathieu BOUTELIER
 */
public class MaterialGestion {
    private Administrator user;
    private Manager manager;
    private Database database;
    private ListMaterial listMaterial;
    private ListReservations listReservations;
    private ListReparation listReparations;

    public MaterialGestion(Database database, Administrator administrator,
                           Manager manager, ListMaterial listMaterial,
                           ListReservations listReservations) {
        this.manager = manager;
        this.database = database;
        this.user = administrator;
        this.listMaterial = listMaterial;
        this.listReservations = listReservations;

        this.listReparations = new ListReparation();
        if (this.listReparations.getReparation() == null) {
            this.listReparations.reinitialize();
        }

        this.managementMaterial();
    }

    /**
     * <b>Shows the command on materials</b>
     * <p>
     * An administrator can do the following actions on materials:
     * <ul>
     * <li>Add a new material</li>
     * <li>Add or create new type of fr.boute.administrator.Material</li>
     * <li>Delete a material</li>
     * <li>See the list of materials</li>
     * <li>Reset the stock and the reservation</li>
     * <li>Send and recover material in reparation</li>
     * </ul>
     * </p>
     */
    private void managementMaterial() {
        System.out.println("1. Ajouter du materiel.");
        System.out.println("2. Ajouter un nouveau type de materiel.");
        System.out.println("3. Creer un nouveau type de materiel.");
        System.out.println("4. Supprimer un materiel.");
        System.out.println("5. Envoyer du materiel en reparation.");
        System.out.println("6. Recuperer le materiel repare.");
        System.out.println("7. Afficher la liste des materiels");
        System.out
                .println("8. Reinitialiser le stock de materiel (!Attention cela va Reinitialiser les reservations)");
        System.out.println("9. Retour");

        int value = this.manager.requestInt(1, 9);

        switch (value) {
            case 1:
                this.addMaterial();
                break;
            case 2:
                this.addNewType();
                break;
            case 3:
                System.out
                        .println("Attention, le materiel sera perdu en cas de reinitialisation.");
                Material material = this.createMaterial();
                ArrayList<Material> materials = this.listMaterial.getMaterials();
                materials.add(material);
                this.listMaterial.setMaterials(materials);
                break;
            case 4:
                this.removeMaterial();
                break;
            case 5:
                this.addReparation();
                break;
            case 6:
                this.recoverReparation();
                break;
            case 7:
                System.out.println(this.displayMaterials(true));
                break;
            case 8:
                System.out
                        .println("Etes-vous sur de vouloir reinitialiser tout votre stock ?\n1.\tOui\n2.\tNon");
                int ask = this.manager.requestInt(1, 2);
                if (ask == 1) {
                    this.listMaterial.reinitialize();
                    this.listReservations.reinitialize();
                    if (!this.listReparations.reinitialize()) {
                        System.err.println("Store in the file failed.");
                    }
                }
                break;
            case 9:
                new AdminMenu(database, user);
                return;
            default:
                break;
        }

        this.managementMaterial();
    }

    /**
     * <b>Add material to the stock</b>
     * <p>
     * This method can be use by the administrator in order to show the
     * variation of the stock when he add some Materials. He can add at the
     * maximum 10 objects.
     * </p>
     */
    private void addMaterial() {
        ArrayList<Material> materials = this.listMaterial.getMaterials();

        System.out.println(this.displayMaterials(false));
        System.out
                .println("Quel est le materiel que vous souhaitez ajouter au stock ?");
        int indice = this.manager.requestInt(1, materials.size()) - 1;

        System.out
                .println("Combien d'objets voulez-vous ajouter ? (Maximum 10)");
        int n = manager.requestInt(0, 10);
        materials.get(indice).setQuantity(
                materials.get(indice).getQuantity() + n);

        this.listMaterial.setMaterials(materials);
    }

    /**
     * <b>Add new type of material</b>
     * <p/>
     * This method can be used to add a new type of material
     * <p/>
     */
    private void addNewType() {
        System.out.println("Recherche de nouveau materiel.");
        this.listMaterial.addNewMaterial();
    }

    /**
     * <b>Create a new type of fr.boute.administrator.Material</b>
     * <p>
     * This method create a new type of material. In case of resetting of the
     * application you will loose all the type created with this method. If you
     * don't want to loose them, you have to add them manually in the code and
     * use the {@link MaterialGestion#addNewType()} method to add it.
     * </p>
     *
     * @return
     */
    private Material createMaterial() {
        System.out.println("Entrez le nom de votre materiel:");
        String name = this.manager.requestString();

        System.out.println("Entrez la duree maximale d'un emprunt (max 21):");
        int duration = this.manager.requestInt(1, 21);

        System.out.println("Entrez le nombre de materiel (max 100)");
        int number = this.manager.requestInt(1, 100);

        System.out
                .println("Votre materiel a-t-il besoin d'un OS ?\n1.\tOui\n2.\tNon");
        int askOs = this.manager.requestInt(1, 2);

        if (askOs == 1) {
            System.out.println("Entrez le nom de l'OS:");
            String os = this.manager.requestString();
            return new WithOS(os, name, duration, number);
        } else {
            return new Material(name, duration, number);
        }
    }

    /**
     * <b>Remove material from the stock</b>
     * <p>
     * This method can be use by the admin in order to show the variation of the
     * stock when he remove some Materials
     * </p>
     */
    private void removeMaterial() {
        ArrayList<Material> materials = this.listMaterial.getMaterials();
        System.out.println(this.displayMaterials(false));

        System.out
                .println("Quel est le materiel que vous souhaitez retirer du stock ?");
        int indice = this.manager.requestInt(1, materials.size()) - 1;
        Material material = materials.get(indice);

        if (material.isEmpty()) {
            System.out.println("Aucun materiel a enlever.");
            return;
        }

        StringBuilder str = new StringBuilder();
        str.append("Combien de ");
        str.append(material.getName());
        str.append(" voulez-vous enlever ? (il en reste ");
        str.append(material.getQuantity());
        str.append(')');
        System.out.println(str.toString());

        int n = manager.requestInt(0, material.getQuantity());

        materials.get(indice).setQuantity(
                materials.get(indice).getQuantity() - n);
        this.listMaterial.setMaterials(materials);
    }

    /**
     * <b>Show the material in the stock</b>
     */
    private String displayMaterials(boolean withResa) {
        StringBuilder str = new StringBuilder();

        str.append("Le stock contient :\n");
        str.append(listMaterial.toString());

        if (withResa) {
            str.append("\nListe des reservations :\n");
            str.append(this.listReservations.toString());
            str.append("\nListe des reparations en cours : \n");
            str.append(this.listReparations.toString());
        }
        return str.toString();
    }

    /**
     * <b>Send some materials to Reparation</b>
     */
    private void addReparation() {
        ArrayList<Material> materials = this.listMaterial.getMaterials();
        System.out.println(this.displayMaterials(false));
        System.out
                .println("Quel materiel faut-il envoyer en reparation ? (0 pour quitter)");

        int indice = this.manager.requestInt(0, materials.size()) - 1;
        if (!(indice == -1)) {
            Material material = materials.get(indice);

            if (material.isEmpty()) {
                System.out.println("Aucun materiel a enlever.");
                return;
            }

            int max = material.getQuantity();
            System.out
                    .println("Combien de materiel voulez-vous envoyer en reparation ? (maximum "
                            + max + ") (-1 pour quitter)");
            int quantity = manager.requestInt(-1, max);

            if (!(quantity == -1)) {
                listReparations.add(new Reparation(material, quantity));
                listMaterial.getMaterials().get(indice)
                        .setQuantity(max - quantity);
                listMaterial.store();
            }
        }
    }

    /**
     * <b>Recover the finished Reparations</b>
     */
    private void recoverReparation() {
        ArrayList<Reparation> reparations = this.listReparations
                .getReparation();
        ArrayList<Material> materials = this.listMaterial.getMaterials();

        Date today = new Date();

        for (int i = 0; i < reparations.size(); ++i) {
            Reparation reparation = reparations.get(i);

            if (reparation.getFinish().before(today)) {
                int indice = this.listMaterial.getIndice(reparation
                        .getMaterial());
                if (indice < 0) {
                    System.err.println("This material is not in listMaterial");
                    return;
                }
                int quantity = materials.get(indice).getQuantity()
                        + reparation.getNumber();
                materials.get(indice).setQuantity(quantity);

                this.listReparations.remove(reparation);
            }
        }

        this.listMaterial.setMaterials(materials);
    }

}
