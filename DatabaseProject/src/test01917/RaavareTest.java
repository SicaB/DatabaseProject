package test01917;

import java.sql.SQLException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import connector01917.Connector;
import daoimpl01917.MySQLRaavareDAO;
import daointerfaces01917.DALException;
import dto01917.RaavareDTO;

public class RaavareTest {
	
	MySQLRaavareDAO raavare;
	
	@Before
	public void setUp() {
		try { new Connector(); } 
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) { e.printStackTrace(); }
		raavare = new MySQLRaavareDAO();
	}
	
	@Test
	public void testIndsaet1() {
		System.out.println("Indsaettelse af ny raavare 1");
		// Først laves råvaren tomat
		RaavareDTO vare = new RaavareDTO(1, "Tomat", "Heinz");
		try {
			// Her indsættes råvaren i databasen
			raavare.createRaavare(vare);
			Assert.assertTrue("testIndsaet1 success", raavare.getRaavare(1).toString().equals(vare.toString()));
		} catch (DALException e) { System.out.println(e.getMessage()); }
	}
	
	@Test
	public void testOpdater() {
		System.out.println("Opdatering af raavare");
		
		// Først laves varen fra testIndsæt1. Hvis den allerede findes gider vi ikke have en fejl.
		RaavareDTO vare = new RaavareDTO(1, "Tomat", "Heinz");
		try { raavare.createRaavare(vare);
		} catch (DALException e) { }
		
		// Opdater råvare navnet og opdater i databasen.
		vare.setRaavareNavn("Stor tomat");
		try {
			raavare.updateRaavare(vare);
			Assert.assertTrue("testOpdater success", raavare.getRaavare(1).toString().equals(vare.toString()));
		} catch (DALException e) { System.out.println(e.getMessage()); }
	}
	
	@Test
	public void testIndsaet2() {
		System.out.println("Indsaettelse af ny raavare 2");
		// Først laves varen fra testIndsæt1. Hvis den allerede findes gider vi ikke have en fejl.
		RaavareDTO vare = new RaavareDTO(1, "Tomat", "Heinz");
		try { raavare.createRaavare(vare);
		} catch (DALException e) { }
		
		// Så laves råvaren citron
		RaavareDTO vare2 = new RaavareDTO(2, "Citron", "Citronfabrik A/S");
		try {
			// Her indsættes råvaren i databasen
			raavare.createRaavare(vare2);
			Assert.assertTrue("testIndsaet2 success", raavare.getRaavare(2).toString().equals(vare2.toString()));
		} catch (DALException e) { System.out.println(e.getMessage()); }
	}
	
	@Test
	public void testHent() {
		System.out.println("Hent raavare 1: ");
		// Først laves varen fra testIndsæt1. Hvis den allerede findes gider vi ikke have en fejl.
		RaavareDTO vare = new RaavareDTO(1, "Tomat", "Heinz");
		try { raavare.createRaavare(vare);
		} catch (DALException e) { }
		
		try {
			RaavareDTO varefundet = raavare.getRaavare(1);
			System.out.println(varefundet);
			Assert.assertTrue("testHent success", varefundet.toString().equals(vare.toString()));
		} catch (DALException e) { System.out.println(e.getMessage()); }
	}
	
	@Test
	public void testHentAlle() {
		System.out.println("Alle raavarer:");
		// Først laves varerne fra testIndsæt1 og testIndsæt2. Hvis de allerede findes gider vi ikke have en fejl.
		RaavareDTO vare = new RaavareDTO(1, "Tomat", "Heinz");
		RaavareDTO vare2 = new RaavareDTO(2, "Citron", "Citronfabrik A/S");
		try { raavare.createRaavare(vare);
		} catch (DALException e) { }
		try { raavare.createRaavare(vare2);
		} catch (DALException e) { }
		
		try {
			List<RaavareDTO> list = raavare.getRaavareList();
			String result = "";
			String expect = vare.toString() + "\n" + vare2.toString() + "\n";
			for (RaavareDTO objekt : list) {
				result += objekt.toString() + "\n";
			}
			System.out.println(result);
			Assert.assertTrue("testHentAlle success", result.equals(expect));
		} catch (DALException e) { System.out.println(e.getMessage()); }
	}

}
