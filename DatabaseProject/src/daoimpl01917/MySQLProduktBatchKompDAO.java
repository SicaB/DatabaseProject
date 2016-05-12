package daoimpl01917;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector01917.Connector;
import daointerfaces01917.DALException;
import daointerfaces01917.ProduktBatchKompDAO;
import dto01917.ProduktBatchKompDTO;

public class MySQLProduktBatchKompDAO implements ProduktBatchKompDAO {

	@Override
	public ProduktBatchKompDTO getProduktBatchKomp(int pbId, int rbId) throws DALException {
		ResultSet rs = Connector.doQuery("SELECT * FROM produktbatchkomponent WHERE pb_id = " + pbId + " AND rb_id = " + rbId);
	    try {
	    	if (!rs.first()) throw new DALException("Produktbatchkomponenten pb_id = " + pbId + ", rb_id = " + rbId + " findes ikke");
	    	return new ProduktBatchKompDTO(pbId, rbId, rs.getDouble("tara"), rs.getDouble("netto"), rs.getInt("opr_id"));
	    }
	    catch (SQLException e) {throw new DALException(e); }
	}

	@Override
	public List<ProduktBatchKompDTO> getProduktBatchKompList(int pbId) throws DALException {
		ResultSet rs = Connector.doQuery("SELECT * FROM produktbatchkomponent WHERE pb_id = " + pbId);
	    try {
	    	List <ProduktBatchKompDTO> list = new ArrayList<ProduktBatchKompDTO>();
	    	while(rs.next()){
	    		list.add(new ProduktBatchKompDTO(pbId, rs.getInt("rb_id"), rs.getDouble("tara"), 
	    				rs.getDouble("netto"), rs.getInt("opr_id")));
	    	}
	    	return list;
	    }
	    catch (SQLException e) {throw new DALException(e); }
	}

	@Override
	public List<ProduktBatchKompDTO> getProduktBatchKompList() throws DALException {
		ResultSet rs = Connector.doQuery("SELECT * FROM produktbatchkomponent");
	    try {
	    	List <ProduktBatchKompDTO> list = new ArrayList<ProduktBatchKompDTO>();
	    	while(rs.next()){
	    		list.add(new ProduktBatchKompDTO(rs.getInt("pb_id"), rs.getInt("rb_id"), rs.getDouble("tara"), 
	    				rs.getDouble("netto"), rs.getInt("opr_id")));
	    	}
	    	return list;
	    }
	    catch (SQLException e) {throw new DALException(e); }
	}

	@Override
	public void createProduktBatchKomp(ProduktBatchKompDTO produktbatchkomponent) throws DALException {
		Connector.doUpdate(
				"INSERT INTO produktbatchkomponent(pb_id, rb_id, tara, netto, opr_id) VALUES " +
				"(" + produktbatchkomponent.getPbId() + ", " + produktbatchkomponent.getRbId() + ", " +
						produktbatchkomponent.getTara() + ", " + produktbatchkomponent.getNetto() + ", " +
						produktbatchkomponent.getOprId() + ")");
	}

	@Override
	public void updateProduktBatchKomp(ProduktBatchKompDTO produktbatchkomponent) throws DALException {
		Connector.doUpdate(
				"UPDATE produktbatchkomponent SET tara = " + produktbatchkomponent.getTara() + ", netto = " +
						produktbatchkomponent.getNetto() + ", opr_id = " + produktbatchkomponent.getOprId() +
				" WHERE pb_id = " + produktbatchkomponent.getPbId() + " AND rb_id = " + produktbatchkomponent.getRbId());
	}

}
