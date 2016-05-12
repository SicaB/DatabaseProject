package test01917;

import java.sql.SQLException;
import java.util.List;

import connector01917.Connector;
import daoimpl01917.MySQLProduktBatchDAO;
import daoimpl01917.MySQLReceptDAO;
import daointerfaces01917.DALException;
import dto01917.ProduktBatchDTO;
import dto01917.ReceptDTO;

public class ProduktBatchTest {

	public static void main(String[] args) {
		try { new Connector(); } 
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) { e.printStackTrace(); }
		MySQLProduktBatchDAO pb = new MySQLProduktBatchDAO();
		MySQLReceptDAO recept = new MySQLReceptDAO();
		
		System.out.println("Indsaettelse af ny produktbatch 1");
		ProduktBatchDTO pbd1 = null;
		try {
			recept.createRecept(new ReceptDTO(1, "Test recept"));
			pbd1 = new ProduktBatchDTO(1, 0, 1);
			pb.createProduktBatch(pbd1);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Opdatering af produktbatch");
		pbd1.setStatus(2);
		try { pb.updateProduktBatch(pbd1);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Indsaettelse af ny produktbatch 2");
		try {
			recept.createRecept(new ReceptDTO(5, "Hero Potter and the Goblin of Fira"));
			ProduktBatchDTO pbd2 = new ProduktBatchDTO(2, 1, 5);
			try { pb.createProduktBatch(pbd2);
			} catch (DALException e) { System.out.println(e.getMessage()); }
		} catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Hent produktbatch 2: ");
		try { System.out.println(pb.getProduktBatch(2));
		} catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Alle produktbatcher:");
		try {
			List<ProduktBatchDTO> list = pb.getProduktBatchList();
			for (ProduktBatchDTO objekt : list) {
				System.out.println(objekt);
			}
		} catch (DALException e) { System.out.println(e.getMessage()); }
	}

}
