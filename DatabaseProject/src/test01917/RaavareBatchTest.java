package test01917;

import java.sql.SQLException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import connector01917.Connector;
import daoimpl01917.MySQLRaavareBatchDAO;
import daoimpl01917.MySQLRaavareDAO;
import daointerfaces01917.DALException;
import dto01917.RaavareBatchDTO;
import dto01917.RaavareDTO;

public class RaavareBatchTest {
	
	MySQLRaavareBatchDAO rb;
	MySQLRaavareDAO raavare;
	
	@Before
	public void setUp() {
		try { new Connector(); } 
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) { e.printStackTrace(); }
		rb = new MySQLRaavareBatchDAO();
		raavare = new MySQLRaavareDAO();
	}
	
	@Test
	public void testIndsaet1() {
		System.out.println("Indsaettelse af ny raavarebatch 1");
		try {
			try { raavare.createRaavare(new RaavareDTO(1, "Ost", "Ostehuset")); // Vi pr�ver at inds�tte vores nye r�varer, og hvis den findes, gider vi ikke have en fejl.
			} catch (DALException e) {}
			// Her inds�tter vi en r�varebatch med rbId = 1, r�vareId = 1, m�ngde = 42
			RaavareBatchDTO rbd1 = new RaavareBatchDTO(1, 1, 42.0);
			rb.createRaavareBatch(rbd1);
			Assert.assertTrue("testIndsaet1 success", rb.getRaavareBatch(1).toString().equals(rbd1.toString()));
		} catch (DALException e) { System.out.println(e.getMessage()); }
	}
	
	@Test
	public void testOpdater() {
		System.out.println("Opdatering af raavarebatch");
		try { raavare.createRaavare(new RaavareDTO(1, "Ost", "Ostehuset")); // Vi pr�ver at inds�tte vores nye r�varer, og hvis den findes, gider vi ikke have en fejl.
		} catch (DALException e) {}
		// Vi laver r�varebatchen fra testInds�t1.
		RaavareBatchDTO rbd1 = new RaavareBatchDTO(1, 1, 42.0);
		
		// Vi �ndrer m�ngden til 13.37
		rbd1.setMaengde(13.37);
		try {
			// Her pr�ver vi at opdatere r�varebatchen i databasen.
			rb.updateRaavareBatch(rbd1);
			Assert.assertTrue("testOpdater success", rb.getRaavareBatch(1).toString().equals(rbd1.toString()));
		} catch (DALException e) { System.out.println(e.getMessage()); }
	}
	
	@Test
	public void testIndsaet2() {
		System.out.println("Indsaettelse af ny raavarebatch 2");
		// Vi inds�tter f�rst vores r�varebatch fra testInds�t1, hvis den ikke allerede findes.
		try {
			try { raavare.createRaavare(new RaavareDTO(1, "Ost", "Ostehuset")); // Vi pr�ver at inds�tte vores nye r�varer, og hvis den findes, gider vi ikke have en fejl.
			} catch (DALException e) {}
			// Her inds�tter vi en r�varebatch med rbId = 1, r�vareId = 1, m�ngde = 42
			RaavareBatchDTO rbd1 = new RaavareBatchDTO(1, 1, 42.0);
			rb.createRaavareBatch(rbd1);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		try {
			try { raavare.createRaavare(new RaavareDTO(7, "Ost", "Moon Factory")); // Vi pr�ver at inds�tte vores nye r�varer, og hvis den findes, gider vi ikke have en fejl.
			} catch (DALException e) {}
			// Her inds�tter vi en r�varebatch med rbId = 2, r�vareId = 7, m�ngde = 6.9
			RaavareBatchDTO rbd2 = new RaavareBatchDTO(2, 7, 6.9);
			rb.createRaavareBatch(rbd2);
			Assert.assertTrue("testIndsaet2 success", rb.getRaavareBatch(2).toString().equals(rbd2.toString()));
		} catch (DALException e) { System.out.println(e.getMessage()); }
	}
	
	@Test
	public void testHent() {
		System.out.println("Hent raavarebatch 1: ");
		// Inds�t r�varebatchen fra testInds�t1, hvis den ikke allerede findes.
		RaavareBatchDTO rbd1 = null;
		try {
			try { raavare.createRaavare(new RaavareDTO(1, "Ost", "Ostehuset")); // Vi pr�ver at inds�tte vores nye r�varer, og hvis den findes, gider vi ikke have en fejl.
			} catch (DALException e) {}
			// Her inds�tter vi en r�varebatch med rbId = 1, r�vareId = 1, m�ngde = 42
			rbd1 = new RaavareBatchDTO(1, 1, 42.0);
			rb.createRaavareBatch(rbd1);
		} catch (DALException e) { }
		try {
			RaavareBatchDTO rbhent = rb.getRaavareBatch(1);
			System.out.println(rbhent);
			Assert.assertTrue("testHent success", rbhent.toString().equals(rbd1.toString()));
		} catch (DALException e) { System.out.println(e.getMessage()); }
	}
	
	@Test
	public void testHentAlle() {
		System.out.println("Alle raavarebatcher:");
		// Inds�t r�varebatcherne fra testInds�t1 og testInds�t2, hvis de ikke allerede findes.
		RaavareBatchDTO rbd1 = null;
		RaavareBatchDTO rbd2 = null;
		try {
			try { raavare.createRaavare(new RaavareDTO(1, "Ost", "Ostehuset")); // Vi pr�ver at inds�tte vores nye r�varer, og hvis den findes, gider vi ikke have en fejl.
			} catch (DALException e) {}
			// Her inds�tter vi en r�varebatch med rbId = 1, r�vareId = 1, m�ngde = 42
			rbd1 = new RaavareBatchDTO(1, 1, 42.0);
			rb.createRaavareBatch(rbd1);
		} catch (DALException e) { }
		try {
			try { raavare.createRaavare(new RaavareDTO(7, "Ost", "Moon Factory")); // Vi pr�ver at inds�tte vores nye r�varer, og hvis den findes, gider vi ikke have en fejl.
			} catch (DALException e) {}
			// Her inds�tter vi en r�varebatch med rbId = 2, r�vareId = 7, m�ngde = 6.9
			rbd2 = new RaavareBatchDTO(2, 7, 6.9);
			rb.createRaavareBatch(rbd2);
		} catch (DALException e) { }
		try {
			List<RaavareBatchDTO> list = rb.getRaavareBatchList();
			String result = "";
			String expect = rbd1.toString() + "\n" + rbd2.toString() + "\n";
			for (RaavareBatchDTO objekt : list) {
				result += objekt.toString() + "\n";
			}
			System.out.println(result);
			Assert.assertTrue("testHentAlle success", result.equals(expect));
		} catch (DALException e) { System.out.println(e.getMessage()); }
	}

}
