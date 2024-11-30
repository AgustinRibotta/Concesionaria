package com.mycompany.concesionaria.persistencia;

import com.mycompany.concesionaria.models.Automovil;
import java.util.List;

public class ControladoraPersistencia {

    private AutomovilJpaController jpaController = new AutomovilJpaController();

    // Guardar un Automovil
    public void guardar(Automovil automovil) {
        jpaController.create(automovil);
    }

    // Obtener todos los Automoviles
    public List<Automovil> traerAutomovil() {
        return jpaController.findAll();
    }

    // Buscar Automoviles por parámetros
    public List<Automovil> buscar(String modelo, String marca, String color) {
        return jpaController.buscarPorParametros(modelo, marca, color);
    }

    public Automovil traerUnAutomovil(int num_auto) {
        return  jpaController.find(num_auto);
    }
}
