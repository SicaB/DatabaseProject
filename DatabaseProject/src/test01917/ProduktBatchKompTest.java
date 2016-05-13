package test01917;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
	MySQLReceptDAO recept;
	MySQLRaavareDAO raavare;
	MySQLRaavareBatchDAO rb;
	MySQLProduktBatchDAO pb;
	MySQLOperatoerDAO opr;
	MySQLProduktBatchKompDAO pbk;
	ProduktBatchKompDTO pbkDTO;

	@Before
	public void setup() {
		recept = new MySQLReceptDAO();
		raavare = new MySQLRaavareDAO();
		rb = new MySQLRaavareBatchDAO();
		pb = new MySQLProduktBatchDAO();
		opr = new MySQLOperatoerDAO();
		pbk = new MySQLProduktBatchKompDAO();
		try {
			new Connector();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			Assert.fail(e.getMessage());
		}
	}

	@After
	public void teardown() {
		recept = null;
		raavare = null;
		rb = null;
		pb = null;
		opr = null;
		pbk = null;
	}

	@Test
	public void testUpdate() {
		// Indsaettelse af nyt produktbatchkomponent rbid = 1, pbid = 1
		try {
			raavare.createRaavare(new RaavareDTO(1, "Ost", "Ostehuset"));
			recept.createRecept(new ReceptDTO(1, "All Might"));
			rb.createRaavareBatch(new RaavareBatchDTO(1, 1, 200.9));
			pb.createProduktBatch(new ProduktBatchDTO(1, 0, 1));
			opr.createOperatoer(new OperatoerDTO(15, "Overpowered", "OP", "000000-0001", "DTUstandard666"));
			pbkDTO = new ProduktBatchKompDTO(1, 1, 50.0, 57.2, 15);
			pbk.createProduktBatchKomp(pbkDTO);
		} catch (DALException e) {
			Assert.fail("Fail to create objects, error message: " + e.getMessage());
		}
		// Opdatering af produktbatchkomponent
		pbkDTO.setNetto(0.0);
		pbkDTO.setTara(0.0);
		try {
			pbk.updateProduktBatchKomp(pbkDTO);
		} catch (DALException e) {
			Assert.fail("Fail to update, error message: " + e.getMessage());
		}
	}

	@Test
	public void testRetrieveListByPbId() {
		// Indsaettelse af nyt produktbatchkomponent rbid = 2, pbid = 1
		try {
			raavare.createRaavare(new RaavareDTO(10, "Ukendt", "Uranus"));
			rb.createRaavareBatch(new RaavareBatchDTO(2, 10, 2.1));
			pbkDTO = new ProduktBatchKompDTO(1, 2, 99.9, 0.1, 15);
			pbk.createProduktBatchKomp(pbkDTO);
		} catch (DALException e) {
			Assert.fail("Fail to create objects, error message: " + e.getMessage());
		}
		// Hent produktbatchkomponenter med pbid = 1
		try {
			List<ProduktBatchKompDTO> list = pbk.getProduktBatchKompList(1);
			for (ProduktBatchKompDTO objekt : list) {
				System.out.println(objekt);
			}
		} catch (DALException e) {
			Assert.fail("Fail to retrieve list by pbId, error message: " + e.getMessage());
		}
	}

	@Test
	public void testRetrieveListByPbIdAndRbId() {
		try {
			raavare.createRaavare(new RaavareDTO(10, "Ukendt", "Uranus"));
			rb.createRaavareBatch(new RaavareBatchDTO(2, 10, 2.1));
			pbkDTO = new ProduktBatchKompDTO(1, 2, 99.9, 0.1, 15);
			pbk.createProduktBatchKomp(pbkDTO);
		} catch (DALException e) {
			Assert.fail("Fail to create objects, error message: " + e.getMessage());
		}
		// Hent produktbatchkomponent pbid = 1, rbid = 2
		try {
			System.out.println(pbk.getProduktBatchKomp(1, 2));
		} catch (DALException e) {
			Assert.fail("Fail to retrieve list by pbId and rbId, error message: " + e.getMessage());
		}
	}

	@Test
	public void testRetrieveList() {
		try {
			raavare.createRaavare(new RaavareDTO(10, "Ukendt", "Uranus"));
			rb.createRaavareBatch(new RaavareBatchDTO(2, 10, 2.1));
			pbkDTO = new ProduktBatchKompDTO(1, 2, 99.9, 0.1, 15);
			pbk.createProduktBatchKomp(pbkDTO);
		} catch (DALException e) {
			Assert.fail("Fail to create objects, error message: " + e.getMessage());
		}
		// Alle produktbatchkomponenter
		try {
			List<ProduktBatchKompDTO> list = pbk.getProduktBatchKompList();
			for (ProduktBatchKompDTO objekt : list) {
				System.out.println(objekt);
			}
		} catch (DALException e) {
			Assert.fail("Fail to retrieve list, error message: " + e.getMessage());
		}
	}

}
