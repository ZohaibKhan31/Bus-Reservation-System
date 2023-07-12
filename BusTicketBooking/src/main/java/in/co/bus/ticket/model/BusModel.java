package in.co.bus.ticket.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.bus.ticket.bean.BusBean;
import in.co.bus.ticket.exception.ApplicationException;
import in.co.bus.ticket.exception.DatabaseException;
import in.co.bus.ticket.exception.DuplicateRecordException;
import in.co.bus.ticket.util.JDBCDataSource;

public class BusModel {

	private static Logger log = Logger.getLogger(BusModel.class);

	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM A_Bus");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();

		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model nextPK End");
		return pk + 1;
	}

	public long add(BusBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;
		int pk = 0;

		BusBean existbean = findByName(bean.getBusName());

		if (existbean != null) {
			throw new DuplicateRecordException("Bus is already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO A_Bus VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getBusNo());
			pstmt.setString(3, bean.getBusName());
			pstmt.setString(4, bean.getFromCity());
			pstmt.setString(5, bean.getToCity());
			pstmt.setDate(6, new java.sql.Date(bean.getDate().getTime()));
			pstmt.setString(7, bean.getDescription());
			pstmt.setString(8, bean.getTime());
			pstmt.setString(9, bean.getTravelDuraion());
			pstmt.setLong(10, bean.getTicketPrice());
			pstmt.setString(11, bean.getBusStopName());
			pstmt.setString(12, bean.getCreatedBy());
			pstmt.setString(13, bean.getModifiedBy());
			pstmt.setTimestamp(14, bean.getCreatedDatetime());
			pstmt.setTimestamp(15, bean.getModifiedDatetime());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {

			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add User");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return pk;
	}

	public BusBean findByName(String name) throws ApplicationException {
		log.debug("Model findByLogin Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM A_Bus WHERE busName=?");
		BusBean bean = null;
		Connection conn = null;
		System.out.println("sql" + sql);

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new BusBean();
				bean.setId(rs.getLong(1));
				bean.setBusNo(rs.getString(2));
				bean.setBusName(rs.getString(3));
				bean.setFromCity(rs.getString(4));
				bean.setToCity(rs.getString(5));
				bean.setDate(rs.getDate(6));
				bean.setDescription(rs.getString(7));
				bean.setTime(rs.getString(8));
				bean.setTravelDuraion(rs.getString(9));
				bean.setTicketPrice(rs.getLong(10));
				bean.setBusStopName(rs.getString(11));
				bean.setCreatedBy(rs.getString(12));
				bean.setModifiedBy(rs.getString(13));
				bean.setCreatedDatetime(rs.getTimestamp(14));
				bean.setModifiedDatetime(rs.getTimestamp(15));

			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by login");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByLogin End");
		return bean;
	}

	/**
	 * Find User by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return bean
	 * @throws DatabaseException
	 */

	public BusBean findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM A_Bus WHERE ID=?");
		BusBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new BusBean();
				bean.setId(rs.getLong(1));
				bean.setBusNo(rs.getString(2));
				bean.setBusName(rs.getString(3));
				bean.setFromCity(rs.getString(4));
				bean.setToCity(rs.getString(5));
				bean.setDate(rs.getDate(6));
				bean.setDescription(rs.getString(7));
				bean.setTime(rs.getString(8));
				bean.setTravelDuraion(rs.getString(9));
				bean.setTicketPrice(rs.getLong(10));
				bean.setBusStopName(rs.getString(11));
				bean.setCreatedBy(rs.getString(12));
				bean.setModifiedBy(rs.getString(13));
				bean.setCreatedDatetime(rs.getTimestamp(14));
				bean.setModifiedDatetime(rs.getTimestamp(15));

			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		return bean;
	}

	public void delete(BusBean bean) throws ApplicationException {

		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM A_Bus WHERE ID=?");
			pstmt.setLong(1, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();

		} catch (Exception e) {

			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete User");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	public void update(BusBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;

		BusBean beanExist = findByName(bean.getBusName());
		// Check if updated LoginId already exist
		if (beanExist != null && !(beanExist.getId() == bean.getId())) {
			throw new DuplicateRecordException("Bus is already exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE A_Bus SET busNo=?,busName=?,FromCity=?,ToCity=?,Date=?,Description=?,Time=?,TravelDuraion=?,TicketPrice=?,busStopName=?,"
							+ "CREATEDBY=?,MODIFIEDBY=?,CREATEDDATETIME=?,MODIFIEDDATETIME=? WHERE ID=?");
			pstmt.setString(1, bean.getBusNo());
			pstmt.setString(2, bean.getBusName());
			pstmt.setString(3, bean.getFromCity());
			pstmt.setString(4, bean.getToCity());
			pstmt.setDate(5, new java.sql.Date(bean.getDate().getTime()));
			pstmt.setString(6, bean.getDescription());
			pstmt.setString(7, bean.getTime());
			pstmt.setString(8, bean.getTravelDuraion());
			pstmt.setLong(9, bean.getTicketPrice());
			pstmt.setString(10, bean.getBusStopName());
			pstmt.setString(11, bean.getCreatedBy());
			pstmt.setString(12, bean.getModifiedBy());
			pstmt.setTimestamp(13, bean.getCreatedDatetime());
			pstmt.setTimestamp(14, bean.getModifiedDatetime());
			pstmt.setLong(15, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating User ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}

	public List<BusBean> search(BusBean bean) throws ApplicationException {
		return search(bean, 0, 0);
	}

	/**
	 * Search User with pagination
	 * 
	 * @return list : List of Users
	 * @param bean
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * 
	 * @throws DatabaseException
	 */

	@SuppressWarnings("deprecation")
	public List<BusBean> search(BusBean bean, int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM a_bus WHERE 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getBusName() != null && bean.getBusName().length() > 0) {
				sql.append(" AND busName like '" + bean.getBusName() + "%'");
			}
			if (bean.getBusNo() != null && bean.getBusNo().length() > 0) {
				sql.append(" AND busNo like '" + bean.getBusNo() + "%'");
			}
			if (bean.getToCity() != null && bean.getToCity().length() > 0) {
				sql.append(" AND toCity like '" + bean.getToCity() + "%'");
			}
			if (bean.getFromCity() != null && bean.getFromCity().length() > 0) {
				sql.append(" AND FromCity like '" + bean.getFromCity() + "%'");
			}
			if (bean.getDate() != null && bean.getDate().getDate() > 0) {
				sql.append(" AND Date = "+ new java.sql.Date(bean.getDate().getTime()));
			}

		}

		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + ", " + pageSize);
			// sql.append(" limit " + pageNo + "," + pageSize);
		}

		System.out.println("user model search  :" + sql);
		ArrayList<BusBean> list = new ArrayList<BusBean>();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new BusBean();
				bean.setId(rs.getLong(1));
				bean.setBusNo(rs.getString(2));
				bean.setBusName(rs.getString(3));
				bean.setFromCity(rs.getString(4));
				bean.setToCity(rs.getString(5));
				bean.setDate(rs.getDate(6));
				bean.setDescription(rs.getString(7));
				bean.setTime(rs.getString(8));
				bean.setTravelDuraion(rs.getString(9));
				bean.setTicketPrice(rs.getLong(10));
				bean.setBusStopName(rs.getString(11));
				bean.setCreatedBy(rs.getString(12));
				bean.setModifiedBy(rs.getString(13));
				bean.setCreatedDatetime(rs.getTimestamp(14));
				bean.setModifiedDatetime(rs.getTimestamp(15));

				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in search user" +e);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model search End");
		return list;
	}

	public List<BusBean> list() throws ApplicationException {
		return list(0, 0);
	}

	/**
	 * Get List of User with pagination
	 * 
	 * @return list : List of users
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws DatabaseException
	 */

	public List<BusBean> list(int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model list Started");
		ArrayList<BusBean> list = new ArrayList<BusBean>();
		StringBuffer sql = new StringBuffer("select * from A_Bus");
		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}

		System.out.println("sql in list user :" + sql);
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				BusBean bean = new BusBean();
				bean.setId(rs.getLong(1));
				bean.setBusNo(rs.getString(2));
				bean.setBusName(rs.getString(3));
				bean.setFromCity(rs.getString(4));
				bean.setToCity(rs.getString(5));
				bean.setDate(rs.getDate(6));
				bean.setDescription(rs.getString(7));
				bean.setTime(rs.getString(8));
				bean.setTravelDuraion(rs.getString(9));
				bean.setTicketPrice(rs.getLong(10));
				bean.setBusStopName(rs.getString(11));
				bean.setCreatedBy(rs.getString(12));
				bean.setModifiedBy(rs.getString(13));
				bean.setCreatedDatetime(rs.getTimestamp(14));
				bean.setModifiedDatetime(rs.getTimestamp(15));

				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting list of users");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model list End");
		return list;

	}

}
