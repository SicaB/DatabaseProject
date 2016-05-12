package test01917;

import java.sql.SQLException;
import java.util.List;

import connector01917.Connector;
import daoimpl01917.MySQLRaavareBatchDAO;
import daoimpl01917.MySQLRaavareDAO;
import daointerfaces01917.DALException;
import dto01917.RaavareBatchDTO;
import dto01917.RaavareDTO;

public class RaavareBatchTest {

	public static void main(String[] args) {
		try { new Connector(); } 
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) { e.printStackTrace(); }
		MySQLRaavareBatchDAO rb = new MySQLRaavareBatchDAO();
		MySQLRaavareDAO raavare = new MySQLRaavareDAO();
		
		System.out.println("Indsaettelse af ny raavarebatch 1");
		RaavareBatchDTO rbd1 = null;
		try {
			raavare.createRaavare(new RaavareDTO(1, "Ost", "Ostehuset"));
			rbd1 = new RaavareBatchDTO(1, 1, 42.0);
			try { rb.createRaavareBatch(rbd1);
			} catch (DALException e) { System.out.println(e.getMessage()); }
		} catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Opdatering af raavarebatch");
		rbd1.setMaengde(13.37);
		try { rb.updateRaavareBatch(rbd1);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Indsaettelse af ny raavarebatch 2");
		try {
			raavare.createRaavare(new RaavareDTO(7, "Ost", "Moon Factory"));
			RaavareBatchDTO pbd2 = new RaavareBatchDTO(2, 7, 6.9);
			try { rb.createRaavareBatch(pbd2);
			} catch (DALException e) { System.out.println(e.getMessage()); }
		} catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Hent raavarebatch 1: ");
		try { System.out.println(rb.getRaavareBatch(1));
		} catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Alle raavarebatcher:");
		try {
			List<RaavareBatchDTO> list = rb.getRaavareBatchList();
			for (RaavareBatchDTO objekt : list) {
				System.out.println(objekt);
			}
		} catch (DALException e) { System.out.println(e.getMessage()); }
	}

}
