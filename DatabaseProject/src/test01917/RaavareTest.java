package test01917;

import java.sql.SQLException;
import java.util.List;

import connector01917.Connector;
import daoimpl01917.MySQLRaavareDAO;
import daointerfaces01917.DALException;
import dto01917.RaavareDTO;

public class RaavareTest {

	public static void main(String[] args) {
		try { new Connector(); } 
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) { e.printStackTrace(); }
		MySQLRaavareDAO raavare = new MySQLRaavareDAO();
		
		System.out.println("Indsaettelse af ny raavare 1");
		RaavareDTO vare = new RaavareDTO(1, "Tomat", "Heinz");
		try { raavare.createRaavare(vare);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Opdatering af raavare");
		vare.setRaavareNavn("Stor tomat");
		try { raavare.updateRaavare(vare);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Indsaettelse af ny raavare 2");
		RaavareDTO vare2 = new RaavareDTO(2, "Citron", "Citronfabrik A/S");
		try { raavare.createRaavare(vare2);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Hent raavare 1: ");
		try { System.out.println(raavare.getRaavare(1));
		} catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Alle raavarer:");
		try {
			List<RaavareDTO> list = raavare.getRaavareList();
			for (RaavareDTO objekt : list) {
				System.out.println(objekt);
			}
		} catch (DALException e) { System.out.println(e.getMessage()); }
	}

}
