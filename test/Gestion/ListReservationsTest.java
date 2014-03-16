package Gestion;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Material.Material;
import Personnel.Borrower;
import Personnel.Student;

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

