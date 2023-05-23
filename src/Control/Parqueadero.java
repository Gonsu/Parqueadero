/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import ModeloDAO.ConexionBD;
import ModeloVO.Vehiculo;
import ModeloDAO.VehiculoDAO;
import java.time.LocalDate;

public class Parqueadero {
    private VehiculoDAO vehiculoDAO;

    public Parqueadero(VehiculoDAO vehiculoDAO) {
        this.vehiculoDAO = vehiculoDAO;
    }

    public void registrarVehiculo(String tipo, String placa, int modelo) {
        // Calcular el precio del vehículo
        double precio = calcularPrecioVehiculo(tipo, modelo);

        // Crear el objeto VehiculoVO
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setTipo(tipo);
        vehiculo.setPlaca(placa);
        vehiculo.setModelo(modelo);
        vehiculo.setPrecio(precio);

        // Guardar el vehículo en la base de datos
        vehiculoDAO.registrarVehiculo(vehiculo);
    }

    public boolean consultarPlaca(String placa) {
        return vehiculoDAO.consultarPlaca(placa);
    }

    private double calcularPrecioVehiculo(String tipo, int modelo) {
        double precioBase;
        double adicional;

        if (tipo.equalsIgnoreCase("carro")) {
            if (modelo < 2012) {
                precioBase = 2000;
            } else if (modelo >= 2012 && modelo < LocalDate.now().getYear()) {
                precioBase = 2500;
            } else {
                precioBase = calcularPrecioUltimoModelo(2000);
            }
            adicional = calcularAdicionalCarro(modelo);
        } else if (tipo.equalsIgnoreCase("moto")) {
            if (modelo < 2012) {
                precioBase = 1000;
            } else if (modelo >= 2012 && modelo < LocalDate.now().getYear()) {
                precioBase = 1200;
            } else {
                precioBase = calcularPrecioUltimoModelo(1000);
            }
            adicional = calcularAdicionalMoto(modelo);
        } else if (tipo.equalsIgnoreCase("bicicleta")) {
            return 500; // Precio fijo por hora para bicicletas
        } else {
            throw new IllegalArgumentException("Tipo de vehículo inválido");
        }

        return precioBase + (precioBase * adicional);
    }

    private double calcularPrecioUltimoModelo(double precioBase) {
        return precioBase * 0.2;
    }

    private double calcularAdicionalCarro(int modelo) {
        return 0; // Sin adicional para carros
    }

    private double calcularAdicionalMoto(int modelo) {
        return 0; // Sin adicional para motos
    }
}
