package com.mycompany.concesionaria.persistencia;

import com.mycompany.concesionaria.models.Automovil;

public class ControladoraPersistencia {

    AutomovilJpaController jpaController = new AutomovilJpaController();
    
    public void guardar(Automovil automovil) {
        jpaController.create(automovil);
    }
    
}
