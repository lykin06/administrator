package fr.boute.administrator.gestion;

import fr.boute.administrator.personnel.Borrower;
import fr.boute.administrator.serialization.Data;

import java.util.ArrayList;

/**
 * <b>Class ListReservations</b>
 * <p>
 * This class load and store the list of reservations from the reservation.data
 * file.
 * </p>
 *
 * @author Aurelien COLOMBET
 */
public class ListReservations {
    /**
     * List of reservations
     */
    private ArrayList<Reservation> reservations;

    /**
     * <b>Constructor</b>
     * <p>
     * Generate the list of reservation from the file
     * </p>
     *
     * @see Data#load(String)
     */
    @SuppressWarnings("unchecked")
    public ListReservations() {
        this.reservations = (ArrayList<Reservation>) Data.load("reservation");
    }

    /**
     * <b>Add a reservation</b>
     *
     * @param reservation
     * @return a boolean which says if the reservation it is stored.
     */
    public boolean add(Reservation reservation) {
        this.reservations.add(reservation);
        return Data.store(this.reservations, "reservation");
    }

    /**
     * <b>Remove a reservation</b>
     *
     * @param reservation Method which remove a reservation from database.
     */
    public void remove(Reservation reservation) {
        for (int i = 0; i < this.reservations.size(); i++) {
            if (this.reservations.get(i).equals(reservation)) {
                this.reservations.remove(i);
                break;
            }
        }
        this.store();
    }

    /**
     * @return the reservations list
     */
    public ArrayList<Reservation> getReservations() {
        return reservations;
    }

    /**
     * @param reservations the reservations list to set
     */
    public void setReservations(ArrayList<Reservation> reservations) {
        this.reservations = reservations;
    }

    /**
     * @return the list of accepted reservations
     */
    public ArrayList<Reservation> getAcceptedReservation() {
        ArrayList<Reservation> accepted = new ArrayList<Reservation>();
        for (int i = 0; i < reservations.size(); ++i) {
            if (reservations.get(i).isAccepted()) {
                accepted.add(reservations.get(i));
            }
        }

        return accepted;
    }

    /**
     * <b>Return the reservations of the current user</b>
     *
     * @param currentUser
     * @return a list of reservation
     */
    public ArrayList<Reservation> getReservationsOf(Borrower currentUser) {
        ArrayList<Reservation> userReservations = new ArrayList<Reservation>();

        for (int i = 0; i < this.reservations.size(); i++) {
            if (this.reservations.get(i).getBorrower().equals(currentUser)) {
                userReservations.add(this.reservations.get(i));
            }
        }

        return userReservations;
    }

    /**
     * <b>authorize a reservation</b>
     *
     * @param reservation to authorize
     */
    public void authorize(Reservation reservation) {
        for (int i = 0; i < this.reservations.size(); ++i) {
            if (this.reservations.get(i).equals(reservation)) {
                this.reservations.get(i).setAccepted(true);
            }
        }
        this.store();
    }

    /**
     * <b>Reset the reservation file</b>
     */
    public void reinitialize() {
        this.reservations = new ArrayList<Reservation>();
        this.store();
    }

    public void store() {
        Data.store(this.reservations, "reservation");
    }

    /**
     * <b>Show the material in reservation</b>
     */
    public String toString() {
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < reservations.size(); i++) {
            str.append(i);
            str.append(" - ");
            str.append(reservations.get(i).toString());
            str.append('\n');
        }
        return str.toString();
    }
}
