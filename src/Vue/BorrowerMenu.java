package Vue;

import java.util.ArrayList;
import java.util.Date;

import Gestion.Database;
import Gestion.ListMaterial;
import Gestion.ListReservations;
import Gestion.Manager;
import Gestion.Reservation;
import Material.Material;
import Personnel.Borrower;
import Personnel.Personnel;
import Personnel.Student;

/**
 * BorrowerMenu class
 * 
 * @author Aurelien COLOMBET
 */
public class BorrowerMenu {
    private Database database;
    private Manager manager;
    private ListReservations listReservations;
    private ListMaterial listMaterial;
    private Date today;

    public BorrowerMenu(Database database) {
        this.database = database;
        this.manager = new Manager();
        this.listMaterial = new ListMaterial();
        this.listReservations = new ListReservations();
        if (this.listMaterial.getMaterials() == null) {
            this.listMaterial.reinitialize();
        }
        if (this.listReservations.getReservations() == null) {
            this.listReservations.reinitialize();
        }
        this.today = new Date();
        this.studentMenu();
    }

    private void studentMenu() {
        System.out.println(this.database.getCurrentUser().getFirstName()
                + " - " + this.database.getCurrentUser().toString());
        System.out.println("1. Voir le materiel emprunte");
        System.out.println("2. Emprunter du materiel");
        System.out.println("3. Rendre un materiel");
        System.out.println("4. Deconnexion");

        int value = this.manager.requestInt(1, 4);

        switch (value) {
        case 1:
            this.displaysReservations();
            break;
        case 2:
            this.borrow();
            break;
        case 3:
            this.displaysReservations();
            this.giveBack();
            break;
        case 4:
            new Login();
            break;
        default:
            break;
        }

        studentMenu();
    }

    /**
     * <b>To give back a material</b>
     */
    private void giveBack() {
        ArrayList<Reservation> reservations = this.listReservations
                .getReservationsOf((Borrower) this.database.getCurrentUser());

        if (!reservations.isEmpty()) {
            System.out
                    .println("Quel materiel voulez-vous rendre ? (pour annuler: "
                            + (reservations.size() + 1) + ")");
            int indice = this.manager.requestInt(1, reservations.size() + 1) - 1;
            if (indice == reservations.size()) {
                return;
            } else {
                Reservation reservationDelete = reservations.get(indice);

                reservations
                        .get(indice)
                        .getMaterial()
                        .setQuantity(
                                reservations.get(indice).getMaterial()
                                        .getQuantity() + 1);
                if (reservationDelete.getFinish().before(today)) {
                    Borrower b = (Borrower) this.database.getCurrentUser();
                    b.setCompteur(b.getCompteur() + 1);
                }
                reservations.remove(indice);
                this.listReservations.remove(reservationDelete);

            }
        }
    }

    /**
     * <b>Method to borrow materials</b>
     */
    @SuppressWarnings("deprecation")
    private void borrow() {
        System.out.println("Que voulez vous emprunter ? (0 pour quitter)");
        Personnel user = database.getCurrentUser();

        // Display the material list available
        System.out.println(listMaterial
                .avaibleMaterials(user instanceof Student));

        // Select the material and watch if it is available
        int indice = manager.requestInt(0, listMaterial.getMaterials().size()) - 1;
        if (indice < 0) {
            return;
        }

        Material material = listMaterial.getMaterials().get(indice);
        while (listMaterial.isNotAvailable(user instanceof Student, material)) {
            indice = manager.requestInt(0, listMaterial.getMaterials().size()) - 1;
            if (indice < 0) {
                return;
            }

            material = listMaterial.getMaterials().get(indice);
        }

        // Date selection
        Date start = today;
        System.out.println("1. Des aujourd'hui.\n2. Faire une reservation");
        int value = this.manager.requestInt(1, 2);
        int maxStart = 1;
        if (value == 2) {
            if (user instanceof Student) {
                maxStart = 7;
            } else {
                maxStart = 20;
            }
            System.out.println("Dans combien de jours voulez-vous l'objet ("
                    + maxStart + " jours max):");
            int jours = manager.requestInt(1, maxStart);
            start = new Date(today.getYear(), today.getMonth(), today.getDate()
                    + jours);
        }

        // Rendering date
        int maxFinish = this.dureeMax(user instanceof Student, material);
        System.out
                .println("Pendant combien de jours voulez-vous garder l'objet (min 0, max "
                        + maxFinish + "):");
        int jours = manager.requestInt(0, maxFinish);
        Date finish = new Date(start.getYear(), start.getMonth(),
                start.getDate() + jours);

        listReservations.add(new Reservation((Borrower) user, material, start,
                finish));
        listMaterial
                .getMaterials()
                .get(indice)
                .setQuantity(
                        listMaterial.getMaterials().get(indice).getQuantity() - 1);
        listMaterial.store();
    }

    /**
     * <b>Compute the maximum length of a reservation</b>
     * <p>
     * The maximum length of reservation is divided by 2 if the user is a
     * student.
     * </p>
     * 
     * @param isStudent
     *            if the user is a student
     * @param material
     *            the material wanted by the user
     * @return the maximum length of reservation
     */
    public int dureeMax(boolean isStudent, Material material) {
        int dureeEmprunt = material.getDureeEmprunt();

        if (isStudent) {
            return (dureeEmprunt / 2);
        }
        return dureeEmprunt;
    }

    /**
     * <b>Display user's reservation list</b>
     */
    private void displaysReservations() {
        ArrayList<Reservation> reservations = this.listReservations
                .getReservationsOf((Borrower) this.database.getCurrentUser());
        if (reservations.isEmpty()) {
            System.out
                    .println("Vous n'avez pas emprunte de materiel (menu 2 pour emprunter).");
        } else {
            System.out.println("Voici la liste du vos reservations :");
            for (int i = 0; i < reservations.size(); i++) {
                System.out.println((i + 1) + ". "
                        + reservations.get(i).toString());
                if (reservations.get(i).getFinish().before(today)) {
                    System.out.println("    Vous devez rendre cette emprunt.");
                }
            }
        }
    }

    /**
     * @return the database
     */
    public Database getDatabase() {
        return database;
    }

    /**
     * @param database
     *            the database to set
     */
    public void setDatabase(Database database) {
        this.database = database;
    }

    /**
     * @return the manager
     */
    public Manager getManager() {
        return manager;
    }

    /**
     * @param manager
     *            the manager to set
     */
    public void setManager(Manager manager) {
        this.manager = manager;
    }

    /**
     * @return the listReservations
     */
    public ListReservations getListReservations() {
        return listReservations;
    }

    /**
     * @param listReservations
     *            the listReservations to set
     */
    public void setListReservations(ListReservations listReservations) {
        this.listReservations = listReservations;
    }

    /**
     * @return the listMaterial
     */
    public ListMaterial getListMaterial() {
        return listMaterial;
    }

    /**
     * @param listMaterial
     *            the listMaterial to set
     */
    public void setListMaterial(ListMaterial listMaterial) {
        this.listMaterial = listMaterial;
    }
}
