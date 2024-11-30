package com.mycompany.concesionaria.persistencia;

import com.mycompany.concesionaria.models.Automovil;
import javax.persistence.*;
import java.util.List;

public class AutomovilJpaController {

    private EntityManagerFactory emf;

    public AutomovilJpaController() {
        emf = Persistence.createEntityManagerFactory("CencesionariaPU");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Crear un nuevo Automovil
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

    // Buscar un Automovil por ID
    public Automovil find(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Automovil.class, id);
        } finally {
            em.close();
        }
    }

    // Obtener todos los Automoviles
    public List<Automovil> findAll() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT a FROM Automovil a", Automovil.class).getResultList();
        } finally {
            em.close();
        }
    }

    // Editar un Automovil
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

    // Eliminar un Automovil por ID
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

    // Buscar Automoviles por parámetros
    public List<Automovil> buscarPorParametros(String modelo, String marca, String color) {
        StringBuilder query = new StringBuilder("SELECT a FROM Automovil a WHERE 1=1");

        if (modelo != null && !modelo.isEmpty()) {
            query.append(" AND a.modelo LIKE :modelo");
        }
        if (marca != null && !marca.isEmpty()) {
            query.append(" AND a.marca LIKE :marca");
        }
        if (color != null && !color.isEmpty()) {
            query.append(" AND a.color LIKE :color");
        }

        EntityManager em = getEntityManager();
        TypedQuery<Automovil> typedQuery = em.createQuery(query.toString(), Automovil.class);

        if (modelo != null && !modelo.isEmpty()) {
            typedQuery.setParameter("modelo", "%" + modelo + "%");
        }
        if (marca != null && !marca.isEmpty()) {
            typedQuery.setParameter("marca", "%" + marca + "%");
        }
        if (color != null && !color.isEmpty()) {
            typedQuery.setParameter("color", "%" + color + "%");
        }

        return typedQuery.getResultList();
    }
}
