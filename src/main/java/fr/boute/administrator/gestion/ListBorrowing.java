package fr.boute.administrator.gestion;

import fr.boute.administrator.material.*;
import fr.boute.administrator.personnel.Borrower;

import java.util.ArrayList;

/**
 * Contains those borrower whose list of borrows is not empty
 *
 * @author Khady FALL
 */
public class ListBorrowing {

    private ListReservations listReservations;

    /**
     * List of accepted reservations
     */
    private ArrayList<Reservation> listBorrowing = new ArrayList<Reservation>();

    /**
     * <b>Constructor</b>
     * <p>
     * Get all the accepted reservations from the reservations list in
     * listReservations.
     * </p>
     *
     * @param listReservations
     */
    public ListBorrowing(ListReservations listReservations) {
        this.listReservations = listReservations;
        if (this.listReservations.getReservations() != null) {
            listBorrowing = this.listReservations.getAcceptedReservation();
        }
    }

    public ArrayList<Reservation> getListBorrowing() {
        return this.listBorrowing;
    }

    public Reservation getReservation(int i) {
        return listBorrowing.get(i);
    }

    /**
     * <b>Return the number of accepted reservations of thisUser</b>
     *
     * @param thisUser
     * @return the number of accepted reservations of thisUser
     */
    public int getNombreReservationsOf(Borrower thisUser) {
        return this.getReservationsOf(thisUser).size();
    }

    /**
     * <b>Return the list of accepted reservations of thisUser</b>
     *
     * @param thisUser
     * @return the list of accepted reservations of thisUser
     */
    public ArrayList<Reservation> getReservationsOf(Borrower thisUser) {
        ArrayList<Reservation> reservations = new ArrayList<Reservation>();

        for (int j = 0; j < this.listBorrowing.size(); ++j) {
            if (this.listBorrowing.get(j).getBorrower().equals(thisUser)) {
                reservations.add(this.listBorrowing.get(j));
            }
        }
        return reservations;
    }

    /**
     * <b>Return the number of material lent</b>
     *
     * @param material
     * @return the number of material lent
     */
    public int getNombreReserveOf(Material material) {
        int cpt = 0;

        for (int i = 0; i < listBorrowing.size(); ++i) {
            Reservation reservation = this.listBorrowing.get(i);
            if ((reservation.getMaterial().getClass().getSimpleName())
                    .equals(material.getClass().getSimpleName())) {
                ++cpt;
            }

        }
        return cpt;
    }

    /**
     * <b>Computes the most borrowed material</b>
     *
     * @return
     */
    public String leplusEmprunte() {
        String os = "os";
        Camera cam = new Camera();
        Headphone head = new Headphone();
        Microphone micro = new Microphone();
        Laptop lap = new Laptop(os);
        Phone phone = new Phone(os);
        Tablet tab = new Tablet(os);
        int nHeadphone = this.getNombreReserveOf(head);
        int nLaptop = this.getNombreReserveOf(lap);
        int nPhone = this.getNombreReserveOf(phone);
        int nTablet = this.getNombreReserveOf(tab);
        int nCamera = this.getNombreReserveOf(cam);
        int nMicro = this.getNombreReserveOf(micro);

        String mat = new String();
        int first = 0;
        int max = 0;

        if (nCamera >= max) {
            if (nCamera == max && first != 0) {
                mat = mat + " & Camera";
            } else {
                max = nCamera;
                mat = "Camera";
                ++first;
            }
        }

        if (nHeadphone >= max) {
            if (nHeadphone == max && first != 0) {
                mat = mat + " & casque";
            } else {
                max = nHeadphone;
                mat = "casque";
                ++first;
            }
        }

        if (nPhone >= max) {
            if (nPhone == max && first != 0) {
                mat = mat + " & Telephones";
            } else {
                max = nPhone;
                mat = "Telephones";
                ++first;
            }
        }

        if (nLaptop >= max) {
            if (nLaptop == max && first != 0) {
                mat = mat + " & Ordinateurs portables";
            } else {
                max = nLaptop;
                mat = "Ordinateurs portables";
                ++first;
            }
        }

        if (nTablet >= max) {
            if (nTablet == max && first != 0) {
                mat = mat + " & Tablettes";
            } else {
                max = nTablet;
                mat = "Tablettes";
                ++first;
            }
        }

        if (nMicro >= max) {
            if (nMicro == max && first != 0) {
                mat = mat + " & Micro";
            } else {
                max = nMicro;
                mat = "Micro";
                ++first;
            }
        }

        if (max == 0) {
            mat = null;
        }

        return mat;

    }

    /**
     * @return the borrower with the highest number of reservations accepted
     */
    public String plusGrosEmprunteur() {
        if (this.listBorrowing.isEmpty()) {
            return "Aucune reservations acceptees, impossible de savoir le plus gros emprunteur.";
        }
        Borrower b = listBorrowing.get(0).getBorrower();
        int max = this.getNombreReservationsOf(b);

        for (int i = 1; i < this.listBorrowing.size(); ++i) {
            Borrower borrower = listBorrowing.get(i).getBorrower();
            if (this.getNombreReservationsOf(borrower) > max) {
                b = this.listBorrowing.get(i).getBorrower();
                max = this.getNombreReservationsOf(b);
            }

        }
        return ("Actuellement le plus gros emprunteur est: " + b.getFirstName()
                + " " + b.getName());

    }

    public String plusGrosRetard() {
        if (this.listBorrowing.isEmpty()) {
            return "Aucune reservations acceptees, impossible de savoir le plus gros emprunteur.";
        }
        Borrower b = listBorrowing.get(0).getBorrower();
        int max = b.getCompteur();

        for (int i = 1; i < this.listBorrowing.size(); ++i) {
            Borrower borrower = listBorrowing.get(i).getBorrower();
            if (borrower.getCompteur() > max) {
                b = this.listBorrowing.get(i).getBorrower();
                max = b.getCompteur();
            }

        }
        if (max == 0) {
            return "Aucun emprunteur n'a de retards";
        }

        return ("Actuellement l'emprunteur avec le plus de retard est: "
                + b.getFirstName() + " " + b.getName());

    }
}
