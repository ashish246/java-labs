package com.jdbc.demo;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 * For Oracle stored procedure returns CURSOR parameter, you can
 * 
 * Registered via JDBC
 * CallableStatement.registerOutParameter(index,OracleTypes.CURSOR).
 * 
 * Get it back via callableStatement.getObject(index).
 * 
 * @author Administrator
 *
 */
public class CallableDemo {

	/*
	 * 
	 * CREATE OR REPLACE PROCEDURE getDBUSERCursor( p_username IN
	 * users.empId%TYPE, c_dbuser OUT SYS_REFCURSOR) IS BEGIN
	 * 
	 * OPEN c_dbuser FOR SELECT * FROM users WHERE empId LIKE p_username || '%';
	 * 
	 * END; /
	 */

	public static void main(String[] argv) {

		try {

			callOracleStoredProcCURSORParameter();

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}

	}

	private static void callOracleStoredProcCURSORParameter()
			throws SQLException {

		Connection dbConnection = null;
		CallableStatement callableStatement = null;
		ResultSet rs = null;

		String getDBUSERCursorSql = "{call getDBUSERCursor(?,?)}";

		try {
			dbConnection = getDBConnection();
			callableStatement = dbConnection.prepareCall(getDBUSERCursorSql);

			callableStatement.setString(1, "mkyong");
			callableStatement.registerOutParameter(2, Types.ARRAY);

			// execute getDBUSERCursor store procedure
			callableStatement.executeUpdate();

			// get cursor and cast it to ResultSet
			rs = (ResultSet) callableStatement.getObject(2);

			while (rs.next()) {
				String userid = rs.getString("USER_ID");
				String userName = rs.getString("USERNAME");
				String createdBy = rs.getString("CREATED_BY");
				String createdDate = rs.getString("CREATED_DATE");

				System.out.println("UserName : " + userid);
				System.out.println("UserName : " + userName);
				System.out.println("CreatedBy : " + createdBy);
				System.out.println("CreatedDate : " + createdDate);
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (rs != null) {
				rs.close();
			}

			if (callableStatement != null) {
				callableStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}

	}

	private static Connection getDBConnection() {

		Connection dbConnection = null;

		Properties props = new Properties();

		FileInputStream fis = null;

		try {
			fis = new FileInputStream("src/databaseConfig.properties");
			props.load(fis);

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Class.forName(props.getProperty("jdbc.driverClassName"));

			MysqlDataSource mysqlDS = null;
			// fis = new FileInputStream("db.properties");
			// props.load(fis);
			mysqlDS = new MysqlDataSource();
			mysqlDS.setURL(props.getProperty("jdbc.url"));
			mysqlDS.setUser(props.getProperty("jdbc.username"));
			mysqlDS.setPassword(props.getProperty("jdbc.password"));

			dbConnection = mysqlDS.getConnection();

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dbConnection;
	}
}
