package fr.boute.administrator.Gestion;

import fr.boute.administrator.Material.Material;
import fr.boute.administrator.Personnel.Borrower;
import fr.boute.administrator.Personnel.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class ListReservationsTest {
    ListReservations listReservations;

    @Before
    public void setUp() throws Exception {
        listReservations = new ListReservations();
    }

    @After
    public void tearDown() throws Exception {
        listReservations = null;
    }

    @Test
    public void addTest() {
        Borrower borrower = new Student(" ", " ", " ", " ", 0, 0);
        Date date = new Date();
        Material material = new Material("Laptop", 1, 1);
        Reservation reservation = new Reservation(borrower, material, date, date);
        assertTrue(listReservations.add(reservation));
    }

}

