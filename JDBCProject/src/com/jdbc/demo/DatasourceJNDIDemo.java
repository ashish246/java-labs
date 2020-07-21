package com.jdbc.demo;

import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 * This is because MysqlDataSource is not implementing
 * javax.naming.spi.InitialContextFactory interface. If you are not in a
 * container you can use rmi registry. Something like:
 * 
 * http://stackoverflow.com/questions/20016503/javax-naming-
 * noinitialcontextexception-with-mysql-datasource
 * 
 * Context ctx = new InitialContext(); generally works when you try access the
 * Context from within a servlet or any class that is deployed as EAR/WAR on the
 * server itself.
 * 
 * @author Administrator
 *
 */
public class DatasourceJNDIDemo {

	public static void main(String args[]) throws RemoteException,
			NamingException {

		Properties props = new Properties();

		FileInputStream fis = null;

		try {
			fis = new FileInputStream("src/databaseConfig.properties");
			props.load(fis);

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		/** Uses JNDI and Datasource (preferred style). */

		startRegistry();

		InitialContext context = createContext();
		
		//DataSource mysqlDs = (DataSource) context.lookup("java:/datasources/empPortalDS"); 
		//System.out.println("mysqlDs -> "+ mysqlDs);

		MysqlDataSource mysqlDs = new MysqlDataSource();
		mysqlDs.setURL(props.getProperty("jdbc.url"));
		mysqlDs.setUser(props.getProperty("jdbc.username"));
		mysqlDs.setPassword(props.getProperty("jdbc.password"));

		context.rebind("jdbc/empDS", mysqlDs);

		try {
			DataSource datasource = (DataSource) context.lookup("jdbc/empDS");
			if (datasource != null) {
				Connection result = datasource.getConnection();

				System.out.println("result -> " + result.toString());
			}
		} catch (NamingException ex) {
			System.out.println("Cannot get connection: " + ex);
		} catch (SQLException ex) {
			System.out.println("Cannot get connection: " + ex);
		}
	}

	/**
	 * @throws RemoteException
	 */
	private static void startRegistry() throws RemoteException {
		LocateRegistry.createRegistry(1098);
		System.out.println("RMI registry ready.");
	}

	/**
	 * @return
	 * @throws NamingException
	 */
	private static InitialContext createContext() throws NamingException {
		Properties env = new Properties();
		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.sun.jndi.rmi.registry.RegistryContextFactory");
		env.put(Context.PROVIDER_URL, "rmi://localhost:1098");
		InitialContext context = new InitialContext(env);
		return context;
	}
}
