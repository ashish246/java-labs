package com.jdbc.demo;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.util.Properties;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DatabaseMetaDataDemo {

	public static void main(String args[]) {

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

			Connection con = mysqlDS.getConnection();

			/*
			 * Connection con = DriverManager.getConnection(
			 * props.getProperty("jdbc.url"),
			 * props.getProperty("jdbc.username"),
			 * props.getProperty("jdbc.password"));
			 */

			DatabaseMetaData dbmd = con.getMetaData();

			System.out.println("Driver Name: " + dbmd.getDriverName());
			System.out.println("Driver Version: " + dbmd.getDriverVersion());
			System.out.println("UserName: " + dbmd.getUserName());
			System.out.println("Database Product Name: "
					+ dbmd.getDatabaseProductName());
			System.out.println("Database Product Version: "
					+ dbmd.getDatabaseProductVersion());

			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

	}
}