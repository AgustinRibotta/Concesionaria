package com.mycompany.concesionaria.persistencia;

import com.mycompany.concesionaria.models.Automovil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public void modificarAuto(Automovil automovil) {
        try {
            jpaController.edit(automovil);
        } catch (Exception e) {
           Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void borrarAuto(int num_auto) {
    
        try {
            jpaController.delete(num_auto);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
