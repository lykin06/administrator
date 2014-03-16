package Vue;

import java.util.ArrayList;

import Gestion.Database;
import Gestion.ListBorrowing;
import Gestion.ListMaterial;
import Gestion.ListReservations;
import Gestion.Manager;
import Gestion.Reservation;
import Personnel.Administrator;

/**
 * <b>AdminMenu class<b>
 * <p>
 * This class operates all the options available to an administrator.
 * </p>
 * <p>
 * An administarator can do the following things:
 * <ul>
 * <li>Manage the reservations</li>
 * <li>Manage the users</li>
 * <li>Manage the materials</li>
 * <li>See the actual statistics</li>
 * </ul>
 * </p>
 * 
 * @author Aurelien COLOMBET
 */
public class AdminMenu {
    private Administrator user;
    private Manager manager;
    private Database database;
    private ListMaterial listMaterial;
    private ListReservations listReservations;
    private ListBorrowing listBorrowing;

    /**
     * <b>Constructor</b>
     * 
     * @param database
     */
    public AdminMenu(Database database, Administrator user) {
        this.manager = new Manager();
        this.database = database;
        this.listMaterial = new ListMaterial();
        this.listReservations = new ListReservations();
        if (this.listMaterial.getMaterials() == null) {
            this.listMaterial.reinitialize();
        }
        if (this.listReservations == null) {
            this.listReservations.reinitialize();
        }
        this.listBorrowing = new ListBorrowing(this.listReservations);
        this.user = user;
        adminInterface();
    }

    /**
     * 
     * This method shows the admin's interface.
     */
    private void adminInterface() {
        System.out.println(this.user.getFirstName() + " - Administrateur");
        System.out.println("1. Gestion des demandes de pret.");
        System.out.println("2. Gestion des utilisateurs.");
        System.out.println("3. Gestion du materiel");
        System.out.println("4. Voir les statistiques actuelles");
        System.out.println("5. Deconnexion");

        int value = this.manager.requestInt(1, 5);

        switch (value) {
        case 1:
            this.managementLoan();
            break;
        case 2:
            new UserGestion(database, user, manager);
            return;
        case 3:
            new MaterialGestion(database, user, manager, listMaterial,
                    listReservations);
            return;
        case 4:
            this.statistiques();
            break;
        case 5:
            new Login();
            break;
        default:
            break;
        }

        this.adminInterface();
    }

    /**
     * <b>Valid the reservations</b>
     * <p>
     * This method travels the list of reservation. When it encounters a non
     * accepted reservation it proposes to accept it or remove it.
     * </p>
     */
    private void managementLoan() {
        ArrayList<Reservation> reservations = this.listReservations
                .getReservations();
        boolean isaccepted = false;

        StringBuilder str = new StringBuilder();
        str.append("Voulez-vous valider cette reservation ?\n");
        str.append("1.\tOui\n");
        str.append("2.\tNon\n");
        str.append("3.\tAnnuler");

        for (int j = 0; j < reservations.size(); ++j) {
            if (!reservations.get(j).isAccepted()) {
                isaccepted = true;
                System.out.println(reservations.get(j).toString());
                System.out.println(str.toString());

                int indice = this.manager.requestInt(1, 3);
                switch (indice) {
                case 1:
                    this.listReservations.authorize(reservations.get(j));
                    break;
                case 2:
                    this.listReservations.remove(reservations.get(j));
                    break;
                default:
                    return;
                }
            }
        }
        if (isaccepted == false) {
            System.out.println("Aucune demande de pret.");
            return;
        }
    }

    private void statistiques() {
        System.out.println("1: le materiel le plus emprunte");
        System.out.println("2: le plus gros emprunteurs ");
        System.out.println("3: l'emprunteur qui ne respecte pas les delais");
        System.out.println("4: Annuler");

        int rep = this.manager.requestInt(1, 4);
        switch (rep) {
        case 1:
            String leplusemprunte = this.listBorrowing.leplusEmprunte();
            if (leplusemprunte == null) {
                System.out
                        .println("Aucun emprunts en cours. Impossible de savoir quel materiel est le plus emprunte");
            } else {
                System.out
                        .println("le material le plus emprunte est actuellement: "
                                + leplusemprunte);
            }
            return;
        case 2:
            System.out.println(this.listBorrowing.plusGrosEmprunteur());
            return;
        case 3:
            System.out.println(this.listBorrowing.plusGrosRetard());
            return;
        default:
            return;
        }
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
}
