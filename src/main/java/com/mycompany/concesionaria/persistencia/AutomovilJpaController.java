package com.mycompany.concesionaria.persistencia;

import com.mycompany.concesionaria.models.Automovil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

public class AutomovilJpaController implements Serializable {

    @PersistenceContext(unitName = "CencesionariaPU")  
    private EntityManager em;

    // CREATE: Crear un nuevo Automovil
    public void create(Automovil automovil) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(automovil); 
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback(); 
            }
            throw e; 
        }
    }

    // READ: Buscar un Automovil por su ID
    public Automovil find(int id) {
        return em.find(Automovil.class, id); 
    }

    // READ: Obtener todos los Automoviles
    public List<Automovil> findAll() {
        return em.createQuery("SELECT a FROM Automovil a", Automovil.class).getResultList(); 
    }

    // UPDATE: Actualizar un Automovil existente
    public void edit(Automovil automovil) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(automovil); 
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();  
            }
            throw e; 
        }
    }

    // DELETE: Eliminar un Automovil por su ID
    public void delete(int id) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Automovil automovil = em.find(Automovil.class, id);  
            if (automovil != null) {
                em.remove(automovil);  
            }
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();  
            }
            throw e; 
        }
    }

    // Verificar si el Automovil existe por ID
    public boolean exists(int id) {
        return em.find(Automovil.class, id) != null;  
    }
}
