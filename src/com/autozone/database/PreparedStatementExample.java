package com.autozone.database;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class PreparedStatementExample {

	public static void main(String[] args) {
		/*try(Connection connection = DatabaseConnection.getInstance().getConnection()) {
			String sql = "INSERT INTO tbl_products(nombre, precio, cantidad) VALUES (?, ?, ?);";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, "Bocina");
			statement.setBigDecimal(2, new BigDecimal("49.99"));
			statement.setInt(3, 100);
			
			statement.executeUpdate();			
		} catch (SQLException exception) {
			System.out.println("Fallo al conectar a la base de datos");
			exception.printStackTrace();
		}*/
		
		try(Connection connection = DatabaseConnection.getInstance().getConnection()) {
			String sql = "CALL sp_insertar_producto(?,?,?)";
			CallableStatement statement = connection.prepareCall(sql);
			
			statement.setString(1, "Camara");
			statement.setBigDecimal(2, new BigDecimal("99.99"));
			statement.setInt(3, 1);
			
			statement.execute();			
		} catch (SQLException exception) {
			System.out.println("Fallo al conectar a la base de datos");
			exception.printStackTrace();
		}
		
	}
	
}
