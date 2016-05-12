package daoimpl01917;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector01917.Connector;
import daointerfaces01917.DALException;
import daointerfaces01917.RaavareBatchDAO;
import dto01917.RaavareBatchDTO;

public class MySQLRaavareBatchDAO implements RaavareBatchDAO {

	@Override
	public RaavareBatchDTO getRaavareBatch(int rbId) throws DALException {
		ResultSet rs = Connector.doQuery("SELECT * FROM raavarebatch WHERE rb_id = " + rbId);
		try {
			if (!rs.first()) throw new DALException("Raavarebatchen rb_id = " + rbId + " findes ikke");
			return new RaavareBatchDTO(rbId, rs.getInt("raavare_id"), rs.getDouble("maengde"));
		}
		catch (SQLException e) { throw new DALException(e); }
	}

	@Override
	public List<RaavareBatchDTO> getRaavareBatchList() throws DALException {
		ResultSet rs = Connector.doQuery("SELECT * FROM raavarebatch");
	    try {
	    	List <RaavareBatchDTO> list = new ArrayList<RaavareBatchDTO>();
	    	while(rs.next()){
	    		list.add(new RaavareBatchDTO(rs.getInt("rb_id"), rs.getInt("raavare_id"), rs.getDouble("maengde")));
	    	}
	    	return list;
	    }
	    catch (SQLException e) { throw new DALException(e); }
	}

	@Override
	public List<RaavareBatchDTO> getRaavareBatchList(int raavareId) throws DALException {
		ResultSet rs = Connector.doQuery("SELECT * FROM raavarebatch WHERE raavare_id = " + raavareId);
	    try {
	    	List <RaavareBatchDTO> list = new ArrayList<RaavareBatchDTO>();
	    	while(rs.next()){
	    		list.add(new RaavareBatchDTO(rs.getInt("rb_id"), rs.getInt("raavare_id"), rs.getDouble("maengde")));
	    	}
	    	return list;
	    }
	    catch (SQLException e) { throw new DALException(e); }
	}

	@Override
	public void createRaavareBatch(RaavareBatchDTO raavarebatch) throws DALException {
		Connector.doUpdate(
				"INSERT INTO raavarebatch (rb_id, raavare_id, maengde) VALUES " +
				"(" + raavarebatch.getRbId() + ", " + raavarebatch.getRaavareId() + ", " + raavarebatch.getMaengde() + ")");
	}

	@Override
	public void updateRaavareBatch(RaavareBatchDTO raavarebatch) throws DALException {
		Connector.doUpdate(
				"UPDATE raavarebatch SET raavare_id = " + raavarebatch.getRaavareId() + ", maengde = " + raavarebatch.getMaengde() +
				" WHERE rb_id = " + raavarebatch.getRbId());
	}

}
