package test01917;

import java.sql.SQLException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import connector01917.Connector;
import daoimpl01917.MySQLReceptDAO;
import daointerfaces01917.DALException;
import dto01917.ReceptDTO;

public class ReceptTest {
	
	MySQLReceptDAO recept;
	
	@Before
	public void setUp() {
		try { new Connector(); } 
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) { e.printStackTrace(); }
		recept = new MySQLReceptDAO();
	}
	
	@Test
	public void testIndsaet1() {
		System.out.println("Indsaettelse af ny recept 1");
		// Vi laver først en ny recept med receptid = 1 og receptnavn = Eksempel
		ReceptDTO rec1 = new ReceptDTO(1, "Eksempel");
		try {
			recept.createRecept(rec1);
			Assert.assertTrue("testIndsaet1 success", recept.getRecept(1).toString().equals(rec1.toString()));
		} catch (DALException e) { System.out.println(e.getMessage()); }
	}
	
	@Test
	public void testOpdater() {
		System.out.println("Opdatering af recept");
		// Vi laver først recepten fra testIndsæt1. Hvis den allerede findes, gider vi ikke have en fejl.
		ReceptDTO rec1 = new ReceptDTO(1, "Eksempel");
		try { recept.createRecept(rec1);
		} catch (DALException e) { }
		
		// Opdater receptens navn og opdater den i databasen
		rec1.setReceptNavn("Eksempel Tekst");
		try {
			recept.updateRecept(rec1);
			Assert.assertTrue("testOpdater success", recept.getRecept(1).toString().equals(rec1.toString()));
		} catch (DALException e) { System.out.println(e.getMessage()); }
	}
	
	@Test
	public void testIndsaet2() {
		System.out.println("Indsaettelse af ny recept 2");
		// Vi laver først recepten fra testIndsæt1. Hvis den allerede findes, gider vi ikke have en fejl.
		ReceptDTO rec1 = new ReceptDTO(1, "Eksempel");
		try { recept.createRecept(rec1);
		} catch (DALException e) { }
		
		// Vi laver nu en ny recept med receptid = 2 og receptnavn = star wars stuff...
		ReceptDTO rec2 = new ReceptDTO(2, "A long time ago in a galaxy far away...");
		try {
			recept.createRecept(rec2);
			Assert.assertTrue("testIndsaet2 success", recept.getRecept(2).toString().equals(rec2.toString()));
		} catch (DALException e) { System.out.println(e.getMessage()); }
	}
	
	@Test
	public void testHent() {
		System.out.println("Hent recept 2: ");
		// Vi laver først recepten fra testIndsæt2. Hvis den allerede findes, gider vi ikke have en fejl.
		ReceptDTO rec2 = new ReceptDTO(2, "A long time ago in a galaxy far away...");
		try { recept.createRecept(rec2);
		} catch (DALException e) { }
		
		try {
			ReceptDTO recepthent = recept.getRecept(2);
			System.out.println(recepthent);
			Assert.assertTrue("testHent success", recepthent.toString().equals(rec2.toString()));
		} catch (DALException e) { System.out.println(e.getMessage()); }
	}
	
	@Test
	public void testHentAlle() {
		System.out.println("Alle recepter:");
		// Vi laver først recepterne fra testIndsæt1 og testIndsæt2. Hvis de allerede findes, gider vi ikke have en fejl.
		ReceptDTO rec1 = new ReceptDTO(1, "Eksempel");
		ReceptDTO rec2 = new ReceptDTO(2, "A long time ago in a galaxy far away...");
		try { recept.createRecept(rec1);
		} catch (DALException e) { }
		try { recept.createRecept(rec2);
		} catch (DALException e) { }
		
		try {
			List<ReceptDTO> list = recept.getReceptList();
			String result = "";
			String expect = rec1.toString() + "\n" + rec2.toString() + "\n";
			for (ReceptDTO objekt : list) {
				result += objekt.toString() + "\n";
			}
			System.out.println(expect);
			Assert.assertTrue("testHentAlle success", result.equals(expect));
		} catch (DALException e) { System.out.println(e.getMessage()); }
	}

}
