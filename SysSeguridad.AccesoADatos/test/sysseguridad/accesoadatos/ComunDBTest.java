/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package sysseguridad.accesoadatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Not4l
 */
public class ComunDBTest {
    
    public ComunDBTest() {
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
     * Test of obtenerConexion method, of class ComunDB.
     */
    @Test
    public void testObtenerConexion() throws Exception {
        System.out.println("obtenerConexion");
        Connection expResult = null;
        Connection result = ComunDB.obtenerConexion();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createStatement method, of class ComunDB.
     */
    @Test
    public void testCreateStatement() throws Exception {
        System.out.println("createStatement");
        Connection pConn = null;
        Statement expResult = null;
        Statement result = ComunDB.createStatement(pConn);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createPreparedStatement method, of class ComunDB.
     */
    @Test
    public void testCreatePreparedStatement() throws Exception {
        System.out.println("createPreparedStatement");
        Connection pConn = null;
        String pSql = "";
        PreparedStatement expResult = null;
        PreparedStatement result = ComunDB.createPreparedStatement(pConn, pSql);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerResultSet method, of class ComunDB.
     */
    @Test
    public void testObtenerResultSet_Statement_String() throws Exception {
        System.out.println("obtenerResultSet");
        Statement pStatement = null;
        String pSql = "";
        ResultSet expResult = null;
        ResultSet result = ComunDB.obtenerResultSet(pStatement, pSql);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerResultSet method, of class ComunDB.
     */
    @Test
    public void testObtenerResultSet_PreparedStatement() throws Exception {
        System.out.println("obtenerResultSet");
        PreparedStatement pPreparedStatement = null;
        ResultSet expResult = null;
        ResultSet result = ComunDB.obtenerResultSet(pPreparedStatement);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ejecutarSQL method, of class ComunDB.
     */
    @Test
    public void testEjecutarSQL() throws Exception {
        System.out.println("ejecutarSQL");
        String pSql = "";
        int expResult = 0;
        int result = ComunDB.ejecutarSQL(pSql);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
