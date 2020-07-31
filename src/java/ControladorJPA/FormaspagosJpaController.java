/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorJPA;

import ControladorJPA.exceptions.IllegalOrphanException;
import ControladorJPA.exceptions.NonexistentEntityException;
import ControladorJPA.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Factura;
import Entidades.Formaspagos;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Andre
 */
public class FormaspagosJpaController implements Serializable {

    public FormaspagosJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Formaspagos formaspagos) throws RollbackFailureException, Exception {
        if (formaspagos.getFacturaCollection() == null) {
            formaspagos.setFacturaCollection(new ArrayList<Factura>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Factura> attachedFacturaCollection = new ArrayList<Factura>();
            for (Factura facturaCollectionFacturaToAttach : formaspagos.getFacturaCollection()) {
                facturaCollectionFacturaToAttach = em.getReference(facturaCollectionFacturaToAttach.getClass(), facturaCollectionFacturaToAttach.getIdfactura());
                attachedFacturaCollection.add(facturaCollectionFacturaToAttach);
            }
            formaspagos.setFacturaCollection(attachedFacturaCollection);
            em.persist(formaspagos);
            for (Factura facturaCollectionFactura : formaspagos.getFacturaCollection()) {
                Formaspagos oldFormaspagosIdformaspagosOfFacturaCollectionFactura = facturaCollectionFactura.getFormaspagosIdformaspagos();
                facturaCollectionFactura.setFormaspagosIdformaspagos(formaspagos);
                facturaCollectionFactura = em.merge(facturaCollectionFactura);
                if (oldFormaspagosIdformaspagosOfFacturaCollectionFactura != null) {
                    oldFormaspagosIdformaspagosOfFacturaCollectionFactura.getFacturaCollection().remove(facturaCollectionFactura);
                    oldFormaspagosIdformaspagosOfFacturaCollectionFactura = em.merge(oldFormaspagosIdformaspagosOfFacturaCollectionFactura);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Formaspagos formaspagos) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Formaspagos persistentFormaspagos = em.find(Formaspagos.class, formaspagos.getIdformaspagos());
            Collection<Factura> facturaCollectionOld = persistentFormaspagos.getFacturaCollection();
            Collection<Factura> facturaCollectionNew = formaspagos.getFacturaCollection();
            List<String> illegalOrphanMessages = null;
            for (Factura facturaCollectionOldFactura : facturaCollectionOld) {
                if (!facturaCollectionNew.contains(facturaCollectionOldFactura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Factura " + facturaCollectionOldFactura + " since its formaspagosIdformaspagos field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Factura> attachedFacturaCollectionNew = new ArrayList<Factura>();
            for (Factura facturaCollectionNewFacturaToAttach : facturaCollectionNew) {
                facturaCollectionNewFacturaToAttach = em.getReference(facturaCollectionNewFacturaToAttach.getClass(), facturaCollectionNewFacturaToAttach.getIdfactura());
                attachedFacturaCollectionNew.add(facturaCollectionNewFacturaToAttach);
            }
            facturaCollectionNew = attachedFacturaCollectionNew;
            formaspagos.setFacturaCollection(facturaCollectionNew);
            formaspagos = em.merge(formaspagos);
            for (Factura facturaCollectionNewFactura : facturaCollectionNew) {
                if (!facturaCollectionOld.contains(facturaCollectionNewFactura)) {
                    Formaspagos oldFormaspagosIdformaspagosOfFacturaCollectionNewFactura = facturaCollectionNewFactura.getFormaspagosIdformaspagos();
                    facturaCollectionNewFactura.setFormaspagosIdformaspagos(formaspagos);
                    facturaCollectionNewFactura = em.merge(facturaCollectionNewFactura);
                    if (oldFormaspagosIdformaspagosOfFacturaCollectionNewFactura != null && !oldFormaspagosIdformaspagosOfFacturaCollectionNewFactura.equals(formaspagos)) {
                        oldFormaspagosIdformaspagosOfFacturaCollectionNewFactura.getFacturaCollection().remove(facturaCollectionNewFactura);
                        oldFormaspagosIdformaspagosOfFacturaCollectionNewFactura = em.merge(oldFormaspagosIdformaspagosOfFacturaCollectionNewFactura);
                    }
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = formaspagos.getIdformaspagos();
                if (findFormaspagos(id) == null) {
                    throw new NonexistentEntityException("The formaspagos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Formaspagos formaspagos;
            try {
                formaspagos = em.getReference(Formaspagos.class, id);
                formaspagos.getIdformaspagos();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The formaspagos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Factura> facturaCollectionOrphanCheck = formaspagos.getFacturaCollection();
            for (Factura facturaCollectionOrphanCheckFactura : facturaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Formaspagos (" + formaspagos + ") cannot be destroyed since the Factura " + facturaCollectionOrphanCheckFactura + " in its facturaCollection field has a non-nullable formaspagosIdformaspagos field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(formaspagos);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Formaspagos> findFormaspagosEntities() {
        return findFormaspagosEntities(true, -1, -1);
    }

    public List<Formaspagos> findFormaspagosEntities(int maxResults, int firstResult) {
        return findFormaspagosEntities(false, maxResults, firstResult);
    }

    private List<Formaspagos> findFormaspagosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Formaspagos.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Formaspagos findFormaspagos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Formaspagos.class, id);
        } finally {
            em.close();
        }
    }

    public int getFormaspagosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Formaspagos> rt = cq.from(Formaspagos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
