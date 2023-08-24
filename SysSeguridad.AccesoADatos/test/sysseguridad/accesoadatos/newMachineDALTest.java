/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package sysseguridad.accesoadatos;

import java.sql.ResultSet;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sysseguridad.entidadesdenegocio.Machine;

/**
 *
 * @author Not4l
 */
public class newMachineDALTest {
    
    public newMachineDALTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getCampos method, of class newMachineDAL.
     */
    @Test
    public void testGetCampos() {
        System.out.println("getCampos");
        String expResult = "";
        String result = newMachineDAL.getCampos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of create method, of class newMachineDAL.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        Machine pMachine = null;
        int expResult = 0;
        int result = newMachineDAL.create(pMachine);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modify method, of class newMachineDAL.
     */
    @Test
    public void testModify() throws Exception {
        System.out.println("modify");
        Machine pMachine = null;
        int expResult = 0;
        int result = newMachineDAL.modify(pMachine);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class newMachineDAL.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        Machine pMachine = null;
        int expResult = 0;
        int result = newMachineDAL.delete(pMachine);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of asignDataOfResultSet method, of class newMachineDAL.
     */
    @Test
    public void testAsignDataOfResultSet() throws Exception {
        System.out.println("asignDataOfResultSet");
        Machine pMachine = null;
        ResultSet pResultSet = null;
        int pIndex = 0;
        int expResult = 0;
        int result = newMachineDAL.asignDataOfResultSet(pMachine, pResultSet, pIndex);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getById method, of class newMachineDAL.
     */
    @Test
    public void testGetById() throws Exception {
        System.out.println("getById");
        Machine pMachine = null;
        Machine expResult = null;
        Machine result = newMachineDAL.getById(pMachine);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAll method, of class newMachineDAL.
     */
    @Test
    public void testGetAll() throws Exception {
        System.out.println("getAll");
        ArrayList<Machine> expResult = null;
        ArrayList<Machine> result = newMachineDAL.getAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of querySelect method, of class newMachineDAL.
     */
    @Test
    public void testQuerySelect() throws Exception {
        System.out.println("querySelect");
        Machine pMachine = null;
        ComunDB.UtilQuery pUtilQuery = null;
        newMachineDAL.querySelect(pMachine, pUtilQuery);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchIncludeUser method, of class newMachineDAL.
     */
    @Test
    public void testSearchIncludeUser() throws Exception {
        System.out.println("searchIncludeUser");
        Machine pMachine = null;
        ArrayList<Machine> expResult = null;
        ArrayList<Machine> result = newMachineDAL.searchIncludeUser(pMachine);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
