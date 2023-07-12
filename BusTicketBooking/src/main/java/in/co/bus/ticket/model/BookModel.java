package in.co.bus.ticket.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.bus.ticket.bean.BookBean;
import in.co.bus.ticket.bean.BusBean;
import in.co.bus.ticket.exception.ApplicationException;
import in.co.bus.ticket.exception.DatabaseException;
import in.co.bus.ticket.exception.DuplicateRecordException;
import in.co.bus.ticket.util.JDBCDataSource;

public class BookModel {
	
	private static Logger log = Logger.getLogger(BookModel.class);

	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM A_Book");
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
	
	public long add(BookBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;
		int pk = 0;

		BusModel fModel=new BusModel();
		BusBean fBean=fModel.findByPK(bean.getBusId());
		//bean.setFinalPrice((fBean.getTicketPrice()*bean.getNoOfPerson()));

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO A_Book VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setLong(2,bean.getBusId());
			pstmt.setString(3, fBean.getBusName());
			pstmt.setString(4, bean.getFirstName());
			pstmt.setString(5, bean.getLastName());
			pstmt.setString(6, bean.getMobileNo());
			pstmt.setDate(7, new java.sql.Date(bean.getBookDate().getTime()));
			pstmt.setString(8, bean.getEmailId());
			pstmt.setString(9, bean.getAddress());
			pstmt.setLong(10, bean.getNoOfPerson());
			pstmt.setLong(11, fBean.getTicketPrice());
			pstmt.setLong(12, bean.getFinalPrice());
			pstmt.setString(13, bean.getCreatedBy());
			pstmt.setString(14, bean.getModifiedBy());
			pstmt.setTimestamp(15, bean.getCreatedDatetime());
			pstmt.setTimestamp(16, bean.getModifiedDatetime());
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
	
	public BookBean findByName(String name) throws ApplicationException {
		log.debug("Model findByLogin Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM A_Book WHERE firstName=?");
		BookBean bean = null;
		Connection conn = null;
		System.out.println("sql" + sql);

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new BookBean();
				bean.setId(rs.getLong(1));
				bean.setBusId(rs.getLong(2));
				bean.setBusName(rs.getString(3));
				bean.setFirstName(rs.getString(4));
				bean.setLastName(rs.getString(5));
				bean.setMobileNo(rs.getString(6));
				bean.setBookDate(rs.getDate(7));
				bean.setEmailId(rs.getString(8));
				bean.setAddress(rs.getString(9));
				bean.setNoOfPerson(rs.getLong(10));
				bean.setPrice(rs.getLong(11));
				bean.setFinalPrice(rs.getLong(12));
				bean.setCreatedBy(rs.getString(13));
				bean.setModifiedBy(rs.getString(14));
				bean.setCreatedDatetime(rs.getTimestamp(15));
				bean.setModifiedDatetime(rs.getTimestamp(16));

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

	public BookBean findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM A_Book WHERE ID=?");
		BookBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new BookBean();
				bean.setId(rs.getLong(1));
				bean.setBusId(rs.getLong(2));
				bean.setBusName(rs.getString(3));
				bean.setFirstName(rs.getString(4));
				bean.setLastName(rs.getString(5));
				bean.setMobileNo(rs.getString(6));
				bean.setBookDate(rs.getDate(7));
				bean.setEmailId(rs.getString(8));
				bean.setAddress(rs.getString(9));
				bean.setNoOfPerson(rs.getLong(10));
				bean.setPrice(rs.getLong(11));
				bean.setFinalPrice(rs.getLong(12));
				bean.setCreatedBy(rs.getString(13));
				bean.setModifiedBy(rs.getString(14));
				bean.setCreatedDatetime(rs.getTimestamp(15));
				bean.setModifiedDatetime(rs.getTimestamp(16));

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
	
	public void delete(BookBean bean) throws ApplicationException {

		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM A_Book WHERE ID=?");
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
	
	public void update(BookBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;

		BusModel fModel=new BusModel();
		BusBean fBean=fModel.findByPK(bean.getBusId());
		bean.setFinalPrice((fBean.getTicketPrice()*bean.getNoOfPerson()));

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE A_Book SET busId=?,busName=?,FirstName=?,LastName=?,MobileNo=?,BookDate=?,EmailId=?,Address=?,NoOfPerson=?,Price=?,FinalPrice=?,"
							+ "CREATEDBY=?,MODIFIEDBY=?,CREATEDDATETIME=?,MODIFIEDDATETIME=? WHERE ID=?");
			pstmt.setLong(1,bean.getBusId());
			pstmt.setString(2, fBean.getBusName());
			pstmt.setString(3, bean.getFirstName());
			pstmt.setString(4, bean.getLastName());
			pstmt.setString(5, bean.getMobileNo());
			pstmt.setDate(6, new java.sql.Date(bean.getBookDate().getTime()));
			pstmt.setString(7, bean.getEmailId());
			pstmt.setString(8, bean.getAddress());
			pstmt.setLong(9, bean.getNoOfPerson());
			pstmt.setLong(10, fBean.getTicketPrice());
			pstmt.setLong(11, bean.getFinalPrice());
			pstmt.setString(12, bean.getCreatedBy());
			pstmt.setString(13, bean.getModifiedBy());
			pstmt.setTimestamp(14, bean.getCreatedDatetime());
			pstmt.setTimestamp(15, bean.getModifiedDatetime());
			pstmt.setLong(16, bean.getId());
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
	
	public List<BookBean> search(BookBean bean) throws ApplicationException {
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

	public List<BookBean> search(BookBean bean, int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM A_Book WHERE 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getBusName() != null && bean.getBusName().length() > 0) {
				sql.append(" AND BusName like '" + bean.getBusName() + "%'");
			}
			if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {
				sql.append(" AND FirstName like '" + bean.getFirstName() + "%'");
			}
			if (bean.getLastName() != null && bean.getLastName().length() > 0) {
				sql.append(" AND LastName like '" + bean.getLastName() + "%'");
			}
			if (bean.getEmailId() != null && bean.getEmailId().length() > 0) {
				sql.append(" AND EmailId like '" + bean.getEmailId() + "%'");
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
		ArrayList<BookBean> list = new ArrayList<BookBean>();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new BookBean();
				bean.setId(rs.getLong(1));
				bean.setBusId(rs.getLong(2));
				bean.setBusName(rs.getString(3));
				bean.setFirstName(rs.getString(4));
				bean.setLastName(rs.getString(5));
				bean.setMobileNo(rs.getString(6));
				bean.setBookDate(rs.getDate(7));
				bean.setEmailId(rs.getString(8));
				bean.setAddress(rs.getString(9));
				bean.setNoOfPerson(rs.getLong(10));
				bean.setPrice(rs.getLong(11));
				bean.setFinalPrice(rs.getLong(12));
				bean.setCreatedBy(rs.getString(13));
				bean.setModifiedBy(rs.getString(14));
				bean.setCreatedDatetime(rs.getTimestamp(15));
				bean.setModifiedDatetime(rs.getTimestamp(16));

				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in search user");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model search End");
		return list;
	}
	
	public List<BookBean> list() throws ApplicationException {
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

	public List<BookBean> list(int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model list Started");
		ArrayList<BookBean> list = new ArrayList<BookBean>();
		StringBuffer sql = new StringBuffer("select * from A_Book");
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
				BookBean bean = new BookBean();
				bean.setId(rs.getLong(1));
				bean.setBusId(rs.getLong(2));
				bean.setBusName(rs.getString(3));
				bean.setFirstName(rs.getString(4));
				bean.setLastName(rs.getString(5));
				bean.setMobileNo(rs.getString(6));
				bean.setBookDate(rs.getDate(7));
				bean.setEmailId(rs.getString(8));
				bean.setAddress(rs.getString(9));
				bean.setNoOfPerson(rs.getLong(10));
				bean.setPrice(rs.getLong(11));
				bean.setFinalPrice(rs.getLong(12));
				bean.setCreatedBy(rs.getString(13));
				bean.setModifiedBy(rs.getString(14));
				bean.setCreatedDatetime(rs.getTimestamp(15));
				bean.setModifiedDatetime(rs.getTimestamp(16));
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
