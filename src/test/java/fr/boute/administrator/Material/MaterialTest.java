package fr.boute.administrator.material;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MaterialTest {
    private Material material;

    @Before
    public void setUp() throws Exception {
        material = new Material("name", 0, 0);
    }

    @After
    public void tearDown() throws Exception {
        material = null;
    }

    @Test
    public void isEmptyTest() {
        material.setQuantity(0);
        assertTrue(material.isEmpty());
    }
}
