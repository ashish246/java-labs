package com.jta.demo;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DatabaseUtil
{
	static MysqlDataSource createDatasource() throws SQLException
	{
		Properties props = new Properties();
		FileInputStream fis = null;

		MysqlDataSource dataSource = new MysqlDataSource();

		/*try
		{*/
			//fis = new FileInputStream("src/databaseConfig.properties");
			//props.load(fis);
			dataSource = new MysqlDataSource();
			dataSource.setURL("jdbc:mysql://localhost:3306/test");
			dataSource.setUser("root");
			dataSource.setPassword("1729");
		/*} catch (IOException e)
		{
			e.printStackTrace();
		}*/

		return dataSource;
	}

}
