package com.autozone.database.dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.autozone.database.DatabaseConnection;
import com.autozone.models.Producto;

public class ProductDAO {

	public void insertProductosEnLote(List<Producto> productos) {
		String sql = "INSERT INTO tbl_products(nombre, precio, cantidad) VALUES (?, ?, ?);";
		
		try(Connection connection = DatabaseConnection.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			
			connection.setAutoCommit(false);
			
			for (Producto producto : productos) {
				statement.setString(1, producto.getNombre());
				statement.setBigDecimal(2, producto.getPrecio());
				statement.setInt(3, producto.getCantidad());
				
				statement.addBatch();
			}
			
			int[] results = statement.executeBatch();
			connection.commit();
			
			for (int index : results) {
				if(index == PreparedStatement.EXECUTE_FAILED) {
					throw new SQLException("Error al insertar un producto en lote");
				}
			}
			
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}
	
	public void actualizarStock(int productoId, int nuevaCantidad) {
		String sql = "{call sp_actualizar_stock(?,?)}";
		
		try(Connection connection = DatabaseConnection.getInstance().getConnection()) {
			CallableStatement statement = connection.prepareCall(sql);
			
			statement.setInt(1, productoId);
			statement.setInt(2, nuevaCantidad);
			
			statement.execute();			
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}
	
}
