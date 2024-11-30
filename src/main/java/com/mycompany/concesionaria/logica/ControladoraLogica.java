package com.mycompany.concesionaria.logica;

import com.mycompany.concesionaria.models.Automovil;
import com.mycompany.concesionaria.persistencia.ControladoraPersistencia;
import com.mycompany.concesionaria.ui.Catalogo;
import java.util.List;

public class ControladoraLogica {

    private ControladoraPersistencia controladoraPersistencia = new ControladoraPersistencia();

    // Guardar un Automovil
    public void guardar(String cantPuertas, String color, String marca, String motor, String modelo, String patente) {
        try {
            // Validar los parámetros de entrada
            validarParametros(cantPuertas, color, marca, motor, modelo);

            // Convertir cantPuertas a int
            int cantidadPuertas = Integer.parseInt(cantPuertas);

            // Validar que el número de puertas esté entre 0 y 5
            if (cantidadPuertas < 0 || cantidadPuertas > 5) {
                throw new IllegalArgumentException("La cantidad de puertas debe estar entre 0 y 5.");
            }

            // Crear y configurar el objeto Automovil
            Automovil automovil = new Automovil();
            automovil.setCantidadPuertas(cantidadPuertas);
            automovil.setColor(color);
            automovil.setMarca(marca);
            automovil.setMotor(motor);
            automovil.setModelo(modelo);
            automovil.setPatente(patente);

            // Guardar el vehículo
            controladoraPersistencia.guardar(automovil);
            System.out.println("Vehículo guardado correctamente: " + automovil);

        } catch (NumberFormatException e) {
            // Manejar error si el valor no es un número
            throw new IllegalArgumentException("La cantidad de puertas debe ser un número entero válido.", e);
        } catch (IllegalArgumentException e) {
            // Re-lanzar la excepción para que pueda ser manejada en la UI o registrar un error
            throw e;
        }
    }

    // Validación de los parámetros
    private void validarParametros(String cantPuertas, String color, String marca, String motor, String modelo) {
        if (color == null || color.trim().isEmpty()) {
            throw new IllegalArgumentException("El color no puede estar vacío.");
        }
        if (marca == null || marca.trim().isEmpty()) {
            throw new IllegalArgumentException("La marca no puede estar vacía.");
        }
        if (motor == null || motor.trim().isEmpty()) {
            throw new IllegalArgumentException("El motor no puede estar vacío.");
        }
        if (modelo == null || modelo.trim().isEmpty()) {
            throw new IllegalArgumentException("El modelo no puede estar vacío.");
        }
        if (cantPuertas == null || cantPuertas.trim().isEmpty()) {
            throw new IllegalArgumentException("La cantidad de puertas no puede estar vacía.");
        }
    }

    // Obtener todos los Automoviles
    public List<Automovil> traerAutomovil() {
        return controladoraPersistencia.traerAutomovil();
    }

    // Buscar Automoviles por parámetros
    public void buscar(String modelo, String marca, String color, Catalogo ui) {
        List<Automovil> resultados = controladoraPersistencia.buscar(modelo, marca, color);
        ui.actualizarTabla(resultados);
    }
}
