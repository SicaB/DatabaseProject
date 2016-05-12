package test01917;

import java.sql.SQLException;
import java.util.List;

import connector01917.Connector;
import daoimpl01917.MySQLReceptDAO;
import daointerfaces01917.DALException;
import dto01917.ReceptDTO;

public class ReceptTest {

	public static void main(String[] args) {
		try { new Connector(); } 
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) { e.printStackTrace(); }
		MySQLReceptDAO recept = new MySQLReceptDAO();
		
		System.out.println("Indsaettelse af ny recept 1");
		ReceptDTO rec1 = new ReceptDTO(1, "Eksempel");
		try { recept.createRecept(rec1);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Opdatering af recept");
		rec1.setReceptNavn("Eksempel Tekst");
		try { recept.updateRecept(rec1);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Indsaettelse af ny recept 2");
		ReceptDTO rec2 = new ReceptDTO(2, "A long time ago in a galaxy far away...");
		try { recept.createRecept(rec2);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Hent recept 2: ");
		try { System.out.println(recept.getRecept(2));
		} catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Alle recepter:");
		try {
			List<ReceptDTO> list = recept.getReceptList();
			for (ReceptDTO objekt : list) {
				System.out.println(objekt);
			}
		} catch (DALException e) { System.out.println(e.getMessage()); }
	}

}
