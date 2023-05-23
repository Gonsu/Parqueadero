/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;
import ModeloVO.Vehiculo;
import java.sql.*;
/**
 *
 * @author jakeline 
 */


public class VehiculoDAO {
    private Connection connection;

    public VehiculoDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean consultarPlaca(String placa) {
        try {
            String query = "SELECT * FROM vehiculos WHERE placa = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, placa);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void registrarVehiculo(Vehiculo vehiculo) {
        try {
            String query = "INSERT INTO vehiculos (tipo, placa, modelo, precio) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, vehiculo.getTipo());
            statement.setString(2, vehiculo.getPlaca());
            statement.setInt(3, vehiculo.getModelo());
            statement.setDouble(4, vehiculo.getPrecio());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Otros métodos y lógica relacionada con el acceso a la base de datos

    public void cerrarConexion() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
