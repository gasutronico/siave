/**
 * 
 */
package com.siave.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * @author arturo_leon
 *
 */
public class ConecctionTestDS {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("-------- MySQL JDBC Connection Testing ------------");
		
		InitialContext initialContext= null;
		try {
			initialContext = new InitialContext();
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		DataSource ds=null;
		try {
			ds = (DataSource)initialContext.lookup("java:comp/env/jdbc/siaveds");
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Connection connection = null;
		try {
			connection = ds.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return;
		}

		System.out.println("MySQL JDBC Driver Registered!");
		
		try {
			connection = DriverManager
			.getConnection("jdbc:mysql://localhost:3306/siavedyc","root", "admin");

		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}

		if (connection != null) {
			System.out.println("You made it, take control your database now!");
			
			try {
				Statement cmd = connection.createStatement();
				ResultSet rs = cmd.executeQuery("Select * from catsupercategoria");
				while (rs.next()) {
				    String nombre = rs.getString("nombresupercategoria");
				    int id = rs.getInt(1);
				    System.out.println(nombre + "-" + id);
				}

				rs.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("Failed to make connection!");
		}

	}

}



