package fr.boute.administrator.Personnel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PersonnelTest {

    private Personnel p;
    private Personnel p2;
    private Personnel p3;
    private Personnel p4;
    private Personnel p5;

    @Before
    public void setUp() throws Exception {
        this.p = new Personnel("a", "b", "x@x.x", "c", 0);
        this.p2 = new Personnel("b", "b", "x@x.x", "c", 0);
        this.p3 = new Personnel("a", "c", "x@x.x", "c", 0);
        this.p4 = new Personnel("a", "b", "y@y.y", "c", 0);
        this.p5 = new Personnel("a", "b", "x@x.x", "c", 1);
    }

    @After
    public void tearDown() throws Exception {
        this.p = null;
        this.p2 = null;
        this.p3 = null;
        this.p4 = null;
        this.p5 = null;
    }

    @Test
    public void equalsTest() {
        assertTrue(p.equals(p));
        assertFalse(p.equals(p2));
        assertFalse(p.equals(p3));
        assertFalse(p.equals(p4));
        assertFalse(p.equals(p5));
    }

}
