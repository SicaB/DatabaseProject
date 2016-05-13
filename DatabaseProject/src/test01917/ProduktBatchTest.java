package test01917;

import java.sql.SQLException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import connector01917.Connector;
import daoimpl01917.MySQLProduktBatchDAO;
import daoimpl01917.MySQLReceptDAO;
import daointerfaces01917.DALException;
import dto01917.ProduktBatchDTO;
import dto01917.ReceptDTO;

public class ProduktBatchTest {
	
	MySQLProduktBatchDAO pb;
	MySQLReceptDAO recept;
	
	@Before
	public void setUp() {
		try { new Connector(); } 
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) { e.printStackTrace(); }
		pb = new MySQLProduktBatchDAO();
		recept = new MySQLReceptDAO();
	}
	
	@Test
	public void testIndsaet1() {
		System.out.println("Indsaettelse af ny produktbatch 1");
		try {
			try { recept.createRecept(new ReceptDTO(1, "Test recept")); // Lav recepten hvis den ikke allerede findes.
			} catch (DALException e) {}
			ProduktBatchDTO pbd1 = new ProduktBatchDTO(1, 0, 1);
			pb.createProduktBatch(pbd1);
			Assert.assertTrue("testIndsaet1 success", pb.getProduktBatch(1).toString().equals(pbd1.toString()));
		} catch (DALException e) { System.out.println(e.getMessage()); }
	}
	
	@Test
	public void testOpdater() {
		System.out.println("Opdatering af produktbatch");
		ProduktBatchDTO pbd1 = null;
		try {
			try { recept.createRecept(new ReceptDTO(1, "Test recept")); // Lav recepten hvis den ikke allerede findes.
			} catch (DALException e) {}
			pbd1 = new ProduktBatchDTO(1, 0, 1);
			pb.createProduktBatch(pbd1);
			Assert.assertTrue("testIndsaet1 success", pb.getProduktBatch(1).toString().equals(pbd1.toString()));
		} catch (DALException e) { System.out.println(e.getMessage()); }
		// Opdater status til 2
		pbd1.setStatus(2);
		try {
			// Opdater i databasen
			pb.updateProduktBatch(pbd1);
			Assert.assertTrue("testOpdater success", pb.getProduktBatch(1).toString().equals(pbd1.toString()));
		} catch (DALException e) { System.out.println(e.getMessage()); }
	}
	
	@Test
	public void testIndsaet2() {
		System.out.println("Indsaettelse af ny produktbatch 2");
		// Prøver først at indsætte produktbatch #1 hvis den ikke allerede findes.
		try {
			try { recept.createRecept(new ReceptDTO(1, "Test recept")); // Lav recepten hvis den ikke allerede findes.
			} catch (DALException e) {}
			ProduktBatchDTO pbd1 = new ProduktBatchDTO(1, 0, 1);
			pb.createProduktBatch(pbd1);
			Assert.assertTrue("testIndsaet1 success", pb.getProduktBatch(1).toString().equals(pbd1.toString()));
		} catch (DALException e) { System.out.println(e.getMessage()); }
		try {
			try { recept.createRecept(new ReceptDTO(5, "Hero Potter and the Goblin of Fira"));
			} catch (DALException e) {}
			ProduktBatchDTO pbd2 = new ProduktBatchDTO(2, 1, 5);
			pb.createProduktBatch(pbd2);
			Assert.assertTrue("testIndsaet2 success", pb.getProduktBatch(2).toString().equals(pbd2.toString()));
		} catch (DALException e) { System.out.println(e.getMessage()); }
	}
	
	@Test
	public void testHent() {
		System.out.println("Hent produktbatch 2: ");
		// Prøver først at indsætte produktbatch #2 hvis den ikke allerede findes.
		ProduktBatchDTO pbd2 = null;
		try {
			try { recept.createRecept(new ReceptDTO(5, "Hero Potter and the Goblin of Fira"));
			} catch (DALException e) {}
			pbd2 = new ProduktBatchDTO(2, 1, 5);
			pb.createProduktBatch(pbd2);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		try {
			ProduktBatchDTO pbhent = pb.getProduktBatch(2);
			System.out.println(pbhent);
			Assert.assertTrue("testHent success", pbhent.toString().equals(pbd2.toString()));
		} catch (DALException e) { System.out.println(e.getMessage()); }
	}
	
	@Test
	public void testHentAlle() {
		System.out.println("Alle produktbatcher:");
		ProduktBatchDTO pbd1 = null;
		ProduktBatchDTO pbd2 = null;
		try {
			try { recept.createRecept(new ReceptDTO(1, "Test recept")); // Lav recepten hvis den ikke allerede findes.
			} catch (DALException e) {}
			pbd1 = new ProduktBatchDTO(1, 0, 1);
			pb.createProduktBatch(pbd1);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		try {
			try { recept.createRecept(new ReceptDTO(5, "Hero Potter and the Goblin of Fira"));
			} catch (DALException e) {}
			pbd2 = new ProduktBatchDTO(2, 1, 5);
			pb.createProduktBatch(pbd2);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		try {
			List<ProduktBatchDTO> list = pb.getProduktBatchList();
			String result = "";
			String expect = pbd1.toString() + "\n" + pbd2.toString() + "\n";
			for (ProduktBatchDTO objekt : list) {
				result += objekt.toString();
			}
			System.out.println(result);
			Assert.assertTrue("testHentAlle success", result.equals(expect));
		} catch (DALException e) { System.out.println(e.getMessage()); }
	}

}
