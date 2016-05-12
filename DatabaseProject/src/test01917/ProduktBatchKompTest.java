package test01917;

import java.sql.SQLException;
import java.util.List;

import connector01917.Connector;
import daoimpl01917.MySQLOperatoerDAO;
import daoimpl01917.MySQLProduktBatchDAO;
import daoimpl01917.MySQLProduktBatchKompDAO;
import daoimpl01917.MySQLRaavareBatchDAO;
import daoimpl01917.MySQLRaavareDAO;
import daoimpl01917.MySQLReceptDAO;
import daointerfaces01917.DALException;
import dto01917.OperatoerDTO;
import dto01917.ProduktBatchDTO;
import dto01917.ProduktBatchKompDTO;
import dto01917.RaavareBatchDTO;
import dto01917.RaavareDTO;
import dto01917.ReceptDTO;

public class ProduktBatchKompTest {

	public static void main(String[] args) {
		try { new Connector(); } 
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) { e.printStackTrace(); }
		MySQLReceptDAO recept = new MySQLReceptDAO();
		MySQLRaavareDAO raavare = new MySQLRaavareDAO();
		MySQLRaavareBatchDAO rb = new MySQLRaavareBatchDAO();
		MySQLProduktBatchDAO pb = new MySQLProduktBatchDAO();
		MySQLOperatoerDAO opr = new MySQLOperatoerDAO();
		MySQLProduktBatchKompDAO superlongname = new MySQLProduktBatchKompDAO();
		
		System.out.println("Indsaettelse af nyt produktbatchkomponent rbid = 1, pbid = 1");
		ProduktBatchKompDTO pbkdtosuperlongname1 = null;
		try {
			raavare.createRaavare(new RaavareDTO(1, "Ost", "Ostehuset"));
			recept.createRecept(new ReceptDTO(1, "All Might"));
			rb.createRaavareBatch(new RaavareBatchDTO(1, 1, 200.9));
			pb.createProduktBatch(new ProduktBatchDTO(1, 0, 1));
			opr.createOperatoer(new OperatoerDTO(15, "Overpowered", "OP", "000000-0001", "DTUstandard666"));
			pbkdtosuperlongname1 = new ProduktBatchKompDTO(1, 1, 50.0, 57.2, 15);
			superlongname.createProduktBatchKomp(pbkdtosuperlongname1);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Opdatering af produktbatchkomponent");
		pbkdtosuperlongname1.setNetto(0.0);
		pbkdtosuperlongname1.setTara(0.0);
		try { superlongname.updateProduktBatchKomp(pbkdtosuperlongname1);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Indsaettelse af nyt produktbatchkomponent rbid = 2, pbid = 1");
		try {
			raavare.createRaavare(new RaavareDTO(10, "Ukendt", "Uranus"));
			rb.createRaavareBatch(new RaavareBatchDTO(2, 10, 2.1));
			ProduktBatchKompDTO pbkdtosuperlongname2 = new ProduktBatchKompDTO(1, 2, 99.9, 0.1, 15);
			superlongname.createProduktBatchKomp(pbkdtosuperlongname2);
		} catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Hent produktbatchkomponenter med pbid = 1: ");
		try {
			List<ProduktBatchKompDTO> list = superlongname.getProduktBatchKompList(1);
			for (ProduktBatchKompDTO objekt : list) {
				System.out.println(objekt);
			}
		} catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Hent produktbatchkomponent pbid = 1, rbid = 2: ");
		try { System.out.println(superlongname.getProduktBatchKomp(1, 2));
		} catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Alle produktbatchkomponenter:");
		try {
			List<ProduktBatchKompDTO> list = superlongname.getProduktBatchKompList();
			for (ProduktBatchKompDTO objekt : list) {
				System.out.println(objekt);
			}
		} catch (DALException e) { System.out.println(e.getMessage()); }
	}

}
