/**
 * 
 */
package com.siave.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author arturo_leon
 *
 */
public class ConecctionTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("-------- MySQL JDBC Connection Testing ------------");

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return;
		}

		System.out.println("MySQL JDBC Driver Registered!");
		Connection connection = null;

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
