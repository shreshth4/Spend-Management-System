/**
 * @author Shreshth Sandilya (shreshth.4@gmail.com)
 */

package com.project.rest.providers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.sakaiproject.entitybus.EntityReference;
import org.sakaiproject.entitybus.entityprovider.CoreEntityProvider;
import org.sakaiproject.entitybus.entityprovider.EntityProviderManager;
import org.sakaiproject.entitybus.entityprovider.capabilities.RESTful;
import org.sakaiproject.entitybus.entityprovider.extension.Formats;
import org.sakaiproject.entitybus.entityprovider.search.Search;
import com.project.rest.util.DBConnect;

/**
 * @author Shreshth Sandilya (shreshth.4@gmail.com)
 */
public class VendorEntityProvider extends AbstractRESTProvider implements
		CoreEntityProvider, RESTful {

	public VendorEntityProvider(EntityProviderManager entityProviderManager) {
		super(entityProviderManager);
	}

	public String getEntityPrefix() {
		return "vendor";
	}

	public boolean entityExists(String id) {

		boolean isValid = false;
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		DBConnect dBConnect = new DBConnect();

		try {

			conn = dBConnect.getDbConnection();

			preparedStatement = conn
					.prepareStatement(" SELECT * from SMSDB.Vendor where id=? ");
			preparedStatement.setString(1, id);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				isValid = true;
			} else {
				isValid = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				preparedStatement.close();
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return isValid;
	}

	public Object getEntity(EntityReference reference) {

		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		DBConnect dBConnect = new DBConnect();

		try {

			conn = dBConnect.getDbConnection();

			preparedStatement = conn
					.prepareStatement(" SELECT * from SMSDB.Vendor where id=? ");
			preparedStatement.setString(1, reference.getId());

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				VendorEntity e = new VendorEntity();
				e.setId(resultSet.getString(1));
				e.setName(resultSet.getString(2));
				e.setPoCreated(resultSet.getBoolean(3));
				e.setPoNumber(resultSet.getInt(4));
				e.setPoDetails(resultSet.getString(5));
				e.setPoType(resultSet.getString(6));
				return e;
			} else {
				throw new IllegalArgumentException("Invalid id:"
						+ reference.getId());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				preparedStatement.close();
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;

	}

	public List<?> getEntities(EntityReference ref, Search search) {
		List<VendorEntity> entities = new ArrayList<VendorEntity>();
		
		if (search.isEmpty()) {
			// return all
			Connection conn = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;

			DBConnect dBConnect = new DBConnect();
			try {

				conn = dBConnect.getDbConnection();

				preparedStatement = conn
						.prepareStatement(" SELECT * from SMSDB.Vendor ");
				resultSet = preparedStatement.executeQuery();

				while (resultSet.next()) {
					VendorEntity e = new VendorEntity();
					e.setId(resultSet.getString(1));
					e.setName(resultSet.getString(2));
					e.setPoCreated(resultSet.getBoolean(3));
					e.setPoNumber(resultSet.getInt(4));
					e.setPoDetails(resultSet.getString(5));
					e.setPoType(resultSet.getString(6));
					entities.add(e);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					conn.close();
					preparedStatement.close();
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		} 
		return entities;
	}

	public Object getSampleEntity() {
		return new VendorEntity(null, 10);
	}

	public String createEntity(EntityReference ref, Object entity,
			Map<String, Object> params) {
		VendorEntity me = (VendorEntity) entity;

		Connection conn = null;
		PreparedStatement preparedStatement = null;

		DBConnect dBConnect = new DBConnect();

		try {

			conn = dBConnect.getDbConnection();
			me.setId(System.currentTimeMillis() + "");

			preparedStatement = conn
					.prepareStatement(" INSERT INTO SMSDB.Vendor (id,name,poCreated,poNumber,poDetails,poType) VALUES (?,?,?,?,?,?) ");
			preparedStatement.setString(1, me.getId());
			preparedStatement.setString(2, me.getName());
			preparedStatement.setBoolean(3, me.isPoCreated());
			preparedStatement.setInt(4, me.getPoNumber());
			preparedStatement.setString(5, me.getPoDetails());
			preparedStatement.setString(6, me.getPoType());

			int masterCount = preparedStatement.executeUpdate();
			System.out.println("no of rows added = " + masterCount);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				conn.close();
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return me.getId();

	}

	public void updateEntity(EntityReference ref, Object entity,
			Map<String, Object> params) {
		VendorEntity me = (VendorEntity) entity;
		if (me.getName() == null) {
			throw new IllegalArgumentException(
					"stuff is not set, it is required");
		}

		Connection conn = null;
		PreparedStatement preparedStatement = null;

		DBConnect dBConnect = new DBConnect();

		try {

			conn = dBConnect.getDbConnection();
			preparedStatement = conn
					.prepareStatement(" UPDATE SMSDB.Vendor set name=?, poCreated=?, poNumber=?, poDetails =?, poType=? where id=? ");
			preparedStatement.setString(1, me.getName());
			preparedStatement.setBoolean(2, me.isPoCreated());
			preparedStatement.setInt(3, me.getPoNumber());
			preparedStatement.setString(4, me.getPoDetails());
			preparedStatement.setString(5, me.getPoType());
			preparedStatement.setString(6, ref.getId());

			int masterCount = preparedStatement.executeUpdate();
			System.out.println("no of rows updated= " + masterCount);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				conn.close();
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

	public void deleteEntity(EntityReference ref, Map<String, Object> params) {

		if (ref.getId() == null) {
			throw new IllegalArgumentException(
					"Invalid entity id, cannot find entity to remove: " + ref);
		}

		Connection conn = null;
		PreparedStatement preparedStatement = null;

		DBConnect dBConnect = new DBConnect();

		try {

			conn = dBConnect.getDbConnection();

			preparedStatement = conn
					.prepareStatement("DELETE from SMSDB.Vendor where id=? ");

			preparedStatement.setString(1, ref.getId());

			int count = preparedStatement.executeUpdate();
			System.out.println("no of rows deleted = " + count);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	public String[] getHandledOutputFormats() {
		return new String[] { Formats.HTML, Formats.JSON, Formats.XML,
				Formats.FORM };
	}

	public String[] getHandledInputFormats() {
		return new String[] { Formats.HTML, Formats.JSON, Formats.XML };
	}

}
