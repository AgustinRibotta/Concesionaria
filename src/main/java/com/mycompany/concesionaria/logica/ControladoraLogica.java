package com.mycompany.concesionaria.logica;

import com.mycompany.concesionaria.models.Automovil;
import com.mycompany.concesionaria.persistencia.ControladoraPersistencia;
import java.util.List;

public class ControladoraLogica {

    ControladoraPersistencia controladoraPersistencia = new ControladoraPersistencia();

    public void guardar(String cantPuertas, String color, String marca, String motor, String modelo, String patente) {
        try {
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

    public List<Automovil> traerAutomovil() {

        return controladoraPersistencia.traerAutomovil();
    }

 
   public void buscar(String bucar) {

        controladoraPersistencia.buscar(bucar);
    }

}
