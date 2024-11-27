package com.mycompany.concesionaria.persistencia;

import com.mycompany.concesionaria.models.Automovil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.util.List;

public class AutomovilJpaController implements Serializable {

    private EntityManagerFactory emf; // Factory para crear EntityManagers

    public AutomovilJpaController() {
        this.emf = Persistence.createEntityManagerFactory("CencesionariaPU"); // Configura la unidad de persistencia
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager(); // Crea un nuevo EntityManager
    }

    // CREATE: Crear un nuevo Automovil
    public void create(Automovil automovil) {
        EntityManager em = getEntityManager();
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
        } finally {
            em.close();
        }
    }

    // READ: Buscar un Automovil por su ID
    public Automovil find(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Automovil.class, id);
        } finally {
            em.close();
        }
    }

    // READ: Obtener todos los Automoviles
    public List<Automovil> findAll() {
        EntityManager em = getEntityManager();
        try {
            List<Automovil> automoviles = em.createQuery("SELECT a FROM Automovil a", Automovil.class).getResultList();
            for (Automovil auto : automoviles) {
                System.out.println("Motor del Automovil: " + auto.getMotor());
            }
            return automoviles;
        } finally {
            em.close();
        }
    }

    // UPDATE: Actualizar un Automovil existente
    public void edit(Automovil automovil) {
        EntityManager em = getEntityManager();
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
        } finally {
            em.close();
        }
    }

    // DELETE: Eliminar un Automovil por su ID
    public void delete(int id) {
        EntityManager em = getEntityManager();
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
        } finally {
            em.close();
        }
    }

    // Verificar si el Automovil existe por ID
    public boolean exists(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Automovil.class, id) != null;
        } finally {
            em.close();
        }
    }
}
