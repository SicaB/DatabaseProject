package test01917;

import java.sql.SQLException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import connector01917.Connector;
import daoimpl01917.MySQLRaavareDAO;
import daoimpl01917.MySQLReceptDAO;
import daoimpl01917.MySQLReceptKompDAO;
import daointerfaces01917.DALException;
import dto01917.RaavareDTO;
import dto01917.ReceptDTO;
import dto01917.ReceptKompDTO;

public class ReceptKompTest {
	
	MySQLReceptDAO recept;
	MySQLRaavareDAO raavare;
	MySQLReceptKompDAO komp;
	
	@Before
	public void setUp() {
		try { new Connector(); } 
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) { e.printStackTrace(); }
		recept = new MySQLReceptDAO();
		raavare = new MySQLRaavareDAO();
		komp = new MySQLReceptKompDAO();
	}
	
	@Test
	public void testIndsaet1() {
		System.out.println("Indsaettelse af nyt receptkomponent receptid = 1, raavareid = 1");
		try {
			try { recept.createRecept(new ReceptDTO(1, "All Might")); } catch (DALException e) {}
			try { raavare.createRaavare(new RaavareDTO(1, "Ost", "Ostehuset")); } catch (DALException e) {}
			ReceptKompDTO rk1 = new ReceptKompDTO(1, 1, 420.5, 0.1);
			komp.createReceptKomp(rk1);
			Assert.assertTrue("testIndsaet1 success", komp.getReceptKomp(1, 1).toString().equals(rk1.toString()));
		} catch (DALException e) { System.out.println(e.getMessage()); }
	}
	
	@Test
	public void testOpdater() {
		System.out.println("Opdatering af receptkomponent");
		// Vi prøver først at indsætte receptkomponentet hvis det ikke allerede findes.
		ReceptKompDTO rk1 = null;
		try {
			try { recept.createRecept(new ReceptDTO(1, "All Might")); } catch (DALException e) {}
			try { raavare.createRaavare(new RaavareDTO(1, "Ost", "Ostehuset")); } catch (DALException e) {}
			rk1 = new ReceptKompDTO(1, 1, 420.5, 0.1);
			komp.createReceptKomp(rk1);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		// Opdater nom netto og tolerance.
		rk1.setNomNetto(99999.99999);
		rk1.setTolerance(0.4);
		try {
			komp.updateReceptKomp(rk1);
			Assert.assertTrue("testOpdater success", komp.getReceptKomp(1, 1).toString().equals(rk1.toString()));
		} catch (DALException e) { System.out.println(e.getMessage()); }
	}
	
	@Test
	public void testIndsaet2() {
		System.out.println("Indsaettelse af nyt receptkomponent receptid = 100, raavareid = 6");
		// Vi prøver først at indsætte receptkomponentet fra testIndsæt1, hvis det ikke allerede findes.
		try {
			try { recept.createRecept(new ReceptDTO(1, "All Might")); } catch (DALException e) {}
			try { raavare.createRaavare(new RaavareDTO(1, "Ost", "Ostehuset")); } catch (DALException e) {}
			ReceptKompDTO rk1 = new ReceptKompDTO(1, 1, 420.5, 0.1);
			komp.createReceptKomp(rk1);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		// Vi prøver nu også at indsætte et nyt receptkomponent med receptid = 100 og råvareid = 6.
		try {
			try { recept.createRecept(new ReceptDTO(100, "Vinter Sommer")); } catch (DALException e) {}
			try { raavare.createRaavare(new RaavareDTO(6, "Suppe", "SoupMan")); } catch (DALException e) {}
			ReceptKompDTO rk2 = new ReceptKompDTO(100, 6, 420.5, 0.1);
			komp.createReceptKomp(rk2);
			Assert.assertTrue("testIndsaet2 success", komp.getReceptKomp(100, 6).toString().equals(rk2.toString()));
		} catch (DALException e) { System.out.println(e.getMessage()); }
	}
	
	@Test
	public void testIndsaet3() {
		System.out.println("Indsaettelse af nyt receptkomponent receptid = 1, raavareid = 5");
		// Vi prøver først at indsætte receptkomponentet fra testIndsæt1, hvis det ikke allerede findes.
		try {
			try { recept.createRecept(new ReceptDTO(1, "All Might")); } catch (DALException e) {}
			try { raavare.createRaavare(new RaavareDTO(1, "Ost", "Ostehuset")); } catch (DALException e) {}
			ReceptKompDTO rk1 = new ReceptKompDTO(1, 1, 420.5, 0.1);
			komp.createReceptKomp(rk1);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		try {
			raavare.createRaavare(new RaavareDTO(5, "Feta ost", "Ostehuset"));
			ReceptKompDTO rk3 = new ReceptKompDTO(1, 5, 100.0, 0.0);
			komp.createReceptKomp(rk3);
			Assert.assertTrue("testIndsaet3 success", komp.getReceptKomp(1, 5).toString().equals(rk3.toString()));
		} catch (DALException e) { System.out.println(e.getMessage()); }
	}
	
	@Test
	public void testHent1() {
		System.out.println("Hent receptkomponenter med receptid = 1: ");
		ReceptKompDTO rk1 = null;
		ReceptKompDTO rk2 = null;
		ReceptKompDTO rk3 = null;
		// Vi prøver først at indsætte receptkomponentet fra testIndsæt1, hvis det ikke allerede findes.
		try {
			try { recept.createRecept(new ReceptDTO(1, "All Might")); } catch (DALException e) {}
			try { raavare.createRaavare(new RaavareDTO(1, "Ost", "Ostehuset")); } catch (DALException e) {}
			rk1 = new ReceptKompDTO(1, 1, 420.5, 0.1);
			komp.createReceptKomp(rk1);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		// Vi prøver nu at indsætte receptkomponentet fra testIndsæt2 hvis det ikke allerede findes.
		try {
			try { recept.createRecept(new ReceptDTO(100, "Vinter Sommer")); } catch (DALException e) {}
			try { raavare.createRaavare(new RaavareDTO(6, "Suppe", "SoupMan")); } catch (DALException e) {}
			rk2 = new ReceptKompDTO(100, 6, 420.5, 0.1);
			komp.createReceptKomp(rk2);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		// Vi prøver nu at indsætte receptkomponentet fra testIndsæt3 hvis det ikke allerede findes.
		try {
			try { raavare.createRaavare(new RaavareDTO(5, "Feta ost", "Ostehuset")); } catch (DALException e) {}
			rk3 = new ReceptKompDTO(1, 5, 100.0, 0.0);
			komp.createReceptKomp(rk3);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		try {
			List<ReceptKompDTO> list = komp.getReceptKompList(1);
			String result = "";
			String expect = rk1.toString() + "\n" + rk3.toString() + "\n";
			for (ReceptKompDTO objekt : list) {
				result += objekt.toString() + "\n";
			}
			System.out.println(result);
			Assert.assertTrue("testHent1 success", result.equals(expect));
		} catch (DALException e) { System.out.println(e.getMessage()); }
	}
	
	@Test
	public void testHent15() {
		System.out.println("Hent receptkomponent receptid = 1, raavareid = 5: ");
		ReceptKompDTO rk1 = null;
		ReceptKompDTO rk2 = null;
		ReceptKompDTO rk3 = null;
		// Vi prøver først at indsætte receptkomponentet fra testIndsæt1, hvis det ikke allerede findes.
		try {
			try { recept.createRecept(new ReceptDTO(1, "All Might")); } catch (DALException e) {}
			try { raavare.createRaavare(new RaavareDTO(1, "Ost", "Ostehuset")); } catch (DALException e) {}
			rk1 = new ReceptKompDTO(1, 1, 420.5, 0.1);
			komp.createReceptKomp(rk1);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		// Vi prøver nu at indsætte receptkomponentet fra testIndsæt2 hvis det ikke allerede findes.
		try {
			try { recept.createRecept(new ReceptDTO(100, "Vinter Sommer")); } catch (DALException e) {}
			try { raavare.createRaavare(new RaavareDTO(6, "Suppe", "SoupMan")); } catch (DALException e) {}
			rk2 = new ReceptKompDTO(100, 6, 420.5, 0.1);
			komp.createReceptKomp(rk2);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		// Vi prøver nu at indsætte receptkomponentet fra testIndsæt3 hvis det ikke allerede findes.
		try {
			try { raavare.createRaavare(new RaavareDTO(5, "Feta ost", "Ostehuset")); } catch (DALException e) {}
			rk3 = new ReceptKompDTO(1, 5, 100.0, 0.0);
			komp.createReceptKomp(rk3);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		try {
			ReceptKompDTO rkhent = komp.getReceptKomp(1, 5);
			System.out.println(rkhent);
			Assert.assertTrue("testHent15 success", rk3.toString().equals(rkhent.toString()));
		} catch (DALException e) { System.out.println(e.getMessage()); }
	}
	
	@Test
	public void testHentAlle() {
		System.out.println("Alle receptkomponenter:");
		ReceptKompDTO rk1 = null;
		ReceptKompDTO rk2 = null;
		ReceptKompDTO rk3 = null;
		// Vi prøver først at indsætte receptkomponentet fra testIndsæt1, hvis det ikke allerede findes.
		try {
			try { recept.createRecept(new ReceptDTO(1, "All Might")); } catch (DALException e) {}
			try { raavare.createRaavare(new RaavareDTO(1, "Ost", "Ostehuset")); } catch (DALException e) {}
			rk1 = new ReceptKompDTO(1, 1, 420.5, 0.1);
			komp.createReceptKomp(rk1);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		// Vi prøver nu at indsætte receptkomponentet fra testIndsæt2 hvis det ikke allerede findes.
		try {
			try { recept.createRecept(new ReceptDTO(100, "Vinter Sommer")); } catch (DALException e) {}
			try { raavare.createRaavare(new RaavareDTO(6, "Suppe", "SoupMan")); } catch (DALException e) {}
			rk2 = new ReceptKompDTO(100, 6, 420.5, 0.1);
			komp.createReceptKomp(rk2);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		// Vi prøver nu at indsætte receptkomponentet fra testIndsæt3 hvis det ikke allerede findes.
		try {
			try { raavare.createRaavare(new RaavareDTO(5, "Feta ost", "Ostehuset")); } catch (DALException e) {}
			rk3 = new ReceptKompDTO(1, 5, 100.0, 0.0);
			komp.createReceptKomp(rk3);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		try {
			List<ReceptKompDTO> list = komp.getReceptKompList();
			String result = "";
			String expect = rk1.toString() + "\n" + rk2.toString() + "\n" + rk3.toString() + "\n";
			for (ReceptKompDTO objekt : list) {
				result += objekt.toString() + "\n";
			}
			System.out.println(result);
			Assert.assertTrue("testHentAlle success", result.equals(expect));
		} catch (DALException e) { System.out.println(e.getMessage()); }
	}

}
