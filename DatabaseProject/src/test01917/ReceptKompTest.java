package test01917;

import java.sql.SQLException;
import java.util.List;

import connector01917.Connector;
import daoimpl01917.MySQLRaavareDAO;
import daoimpl01917.MySQLReceptDAO;
import daoimpl01917.MySQLReceptKompDAO;
import daointerfaces01917.DALException;
import dto01917.RaavareDTO;
import dto01917.ReceptDTO;
import dto01917.ReceptKompDTO;

public class ReceptKompTest {

	public static void main(String[] args) {
		try { new Connector(); } 
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) { e.printStackTrace(); }
		MySQLReceptDAO recept = new MySQLReceptDAO();
		MySQLRaavareDAO raavare = new MySQLRaavareDAO();
		MySQLReceptKompDAO komp = new MySQLReceptKompDAO();
		
		System.out.println("Indsaettelse af nyt receptkomponent receptid = 1, raavareid = 1");
		ReceptKompDTO rk1 = null;
		try {
			recept.createRecept(new ReceptDTO(1, "All Might"));
			raavare.createRaavare(new RaavareDTO(1, "Ost", "Ostehuset"));
			rk1 = new ReceptKompDTO(1, 1, 420.5, 0.1);
			komp.createReceptKomp(rk1);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Opdatering af receptkomponent");
		rk1.setNomNetto(99999.99999);
		rk1.setTolerance(0.4);
		try { komp.updateReceptKomp(rk1);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Indsaettelse af nyt receptkomponent receptid = 100, raavareid = 6");
		try {
			recept.createRecept(new ReceptDTO(100, "Vinter Sommer"));
			raavare.createRaavare(new RaavareDTO(6, "Suppe", "SoupMan"));
			ReceptKompDTO rk2 = new ReceptKompDTO(100, 6, 420.5, 0.1);
			komp.createReceptKomp(rk2);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Indsaettelse af nyt receptkomponent receptid = 1, raavareid = 5");
		try {
			raavare.createRaavare(new RaavareDTO(5, "Feta ost", "Ostehuset"));
			ReceptKompDTO rk3 = new ReceptKompDTO(1, 5, 100.0, 0.0);
			komp.createReceptKomp(rk3);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Hent receptkomponenter med receptid = 1: ");
		try {
			List<ReceptKompDTO> list = komp.getReceptKompList(1);
			for (ReceptKompDTO objekt : list) {
				System.out.println(objekt);
			}
		} catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Hent receptkomponent receptid = 1, raavareid = 5: ");
		try { System.out.println(komp.getReceptKomp(1, 5));
		} catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Alle receptkomponenter:");
		try {
			List<ReceptKompDTO> list = komp.getReceptKompList();
			for (ReceptKompDTO objekt : list) {
				System.out.println(objekt);
			}
		} catch (DALException e) { System.out.println(e.getMessage()); }
	}

}
