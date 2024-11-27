package com.mycompany.concesionaria.persistencia;

import com.mycompany.concesionaria.models.Automovil;
import java.util.List;

public class ControladoraPersistencia {

    AutomovilJpaController jpaController = new AutomovilJpaController();

    public void guardar(Automovil automovil) {
        jpaController.create(automovil);
    }

    public List<Automovil> traerAutomovil() {
        return jpaController.findAll();
    }

}
