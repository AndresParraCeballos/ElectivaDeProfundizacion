/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorJPA;

import ControladorJPA.exceptions.IllegalOrphanException;
import ControladorJPA.exceptions.NonexistentEntityException;
import ControladorJPA.exceptions.RollbackFailureException;
import Entidades.Factura;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Formaspagos;
import Entidades.Usuario;
import Entidades.ProductosDeFactura;
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
public class FacturaJpaController implements Serializable {

    public FacturaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Factura factura) throws RollbackFailureException, Exception {
        if (factura.getProductosDeFacturaCollection() == null) {
            factura.setProductosDeFacturaCollection(new ArrayList<ProductosDeFactura>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Formaspagos formaspagosIdformaspagos = factura.getFormaspagosIdformaspagos();
            if (formaspagosIdformaspagos != null) {
                formaspagosIdformaspagos = em.getReference(formaspagosIdformaspagos.getClass(), formaspagosIdformaspagos.getIdformaspagos());
                factura.setFormaspagosIdformaspagos(formaspagosIdformaspagos);
            }
            Usuario idUsuariovendedor = factura.getIdUsuariovendedor();
            if (idUsuariovendedor != null) {
                idUsuariovendedor = em.getReference(idUsuariovendedor.getClass(), idUsuariovendedor.getIdusuario());
                factura.setIdUsuariovendedor(idUsuariovendedor);
            }
            Usuario idUsuariocomprador = factura.getIdUsuariocomprador();
            if (idUsuariocomprador != null) {
                idUsuariocomprador = em.getReference(idUsuariocomprador.getClass(), idUsuariocomprador.getIdusuario());
                factura.setIdUsuariocomprador(idUsuariocomprador);
            }
            Collection<ProductosDeFactura> attachedProductosDeFacturaCollection = new ArrayList<ProductosDeFactura>();
            for (ProductosDeFactura productosDeFacturaCollectionProductosDeFacturaToAttach : factura.getProductosDeFacturaCollection()) {
                productosDeFacturaCollectionProductosDeFacturaToAttach = em.getReference(productosDeFacturaCollectionProductosDeFacturaToAttach.getClass(), productosDeFacturaCollectionProductosDeFacturaToAttach.getIdproductosDeFactura());
                attachedProductosDeFacturaCollection.add(productosDeFacturaCollectionProductosDeFacturaToAttach);
            }
            factura.setProductosDeFacturaCollection(attachedProductosDeFacturaCollection);
            em.persist(factura);
            if (formaspagosIdformaspagos != null) {
                formaspagosIdformaspagos.getFacturaCollection().add(factura);
                formaspagosIdformaspagos = em.merge(formaspagosIdformaspagos);
            }
            if (idUsuariovendedor != null) {
                idUsuariovendedor.getFacturaCollection().add(factura);
                idUsuariovendedor = em.merge(idUsuariovendedor);
            }
            if (idUsuariocomprador != null) {
                idUsuariocomprador.getFacturaCollection().add(factura);
                idUsuariocomprador = em.merge(idUsuariocomprador);
            }
            for (ProductosDeFactura productosDeFacturaCollectionProductosDeFactura : factura.getProductosDeFacturaCollection()) {
                Factura oldFacturaIdfacturaOfProductosDeFacturaCollectionProductosDeFactura = productosDeFacturaCollectionProductosDeFactura.getFacturaIdfactura();
                productosDeFacturaCollectionProductosDeFactura.setFacturaIdfactura(factura);
                productosDeFacturaCollectionProductosDeFactura = em.merge(productosDeFacturaCollectionProductosDeFactura);
                if (oldFacturaIdfacturaOfProductosDeFacturaCollectionProductosDeFactura != null) {
                    oldFacturaIdfacturaOfProductosDeFacturaCollectionProductosDeFactura.getProductosDeFacturaCollection().remove(productosDeFacturaCollectionProductosDeFactura);
                    oldFacturaIdfacturaOfProductosDeFacturaCollectionProductosDeFactura = em.merge(oldFacturaIdfacturaOfProductosDeFacturaCollectionProductosDeFactura);
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

    public void edit(Factura factura) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Factura persistentFactura = em.find(Factura.class, factura.getIdfactura());
            Formaspagos formaspagosIdformaspagosOld = persistentFactura.getFormaspagosIdformaspagos();
            Formaspagos formaspagosIdformaspagosNew = factura.getFormaspagosIdformaspagos();
            Usuario idUsuariovendedorOld = persistentFactura.getIdUsuariovendedor();
            Usuario idUsuariovendedorNew = factura.getIdUsuariovendedor();
            Usuario idUsuariocompradorOld = persistentFactura.getIdUsuariocomprador();
            Usuario idUsuariocompradorNew = factura.getIdUsuariocomprador();
            Collection<ProductosDeFactura> productosDeFacturaCollectionOld = persistentFactura.getProductosDeFacturaCollection();
            Collection<ProductosDeFactura> productosDeFacturaCollectionNew = factura.getProductosDeFacturaCollection();
            List<String> illegalOrphanMessages = null;
            for (ProductosDeFactura productosDeFacturaCollectionOldProductosDeFactura : productosDeFacturaCollectionOld) {
                if (!productosDeFacturaCollectionNew.contains(productosDeFacturaCollectionOldProductosDeFactura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ProductosDeFactura " + productosDeFacturaCollectionOldProductosDeFactura + " since its facturaIdfactura field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (formaspagosIdformaspagosNew != null) {
                formaspagosIdformaspagosNew = em.getReference(formaspagosIdformaspagosNew.getClass(), formaspagosIdformaspagosNew.getIdformaspagos());
                factura.setFormaspagosIdformaspagos(formaspagosIdformaspagosNew);
            }
            if (idUsuariovendedorNew != null) {
                idUsuariovendedorNew = em.getReference(idUsuariovendedorNew.getClass(), idUsuariovendedorNew.getIdusuario());
                factura.setIdUsuariovendedor(idUsuariovendedorNew);
            }
            if (idUsuariocompradorNew != null) {
                idUsuariocompradorNew = em.getReference(idUsuariocompradorNew.getClass(), idUsuariocompradorNew.getIdusuario());
                factura.setIdUsuariocomprador(idUsuariocompradorNew);
            }
            Collection<ProductosDeFactura> attachedProductosDeFacturaCollectionNew = new ArrayList<ProductosDeFactura>();
            for (ProductosDeFactura productosDeFacturaCollectionNewProductosDeFacturaToAttach : productosDeFacturaCollectionNew) {
                productosDeFacturaCollectionNewProductosDeFacturaToAttach = em.getReference(productosDeFacturaCollectionNewProductosDeFacturaToAttach.getClass(), productosDeFacturaCollectionNewProductosDeFacturaToAttach.getIdproductosDeFactura());
                attachedProductosDeFacturaCollectionNew.add(productosDeFacturaCollectionNewProductosDeFacturaToAttach);
            }
            productosDeFacturaCollectionNew = attachedProductosDeFacturaCollectionNew;
            factura.setProductosDeFacturaCollection(productosDeFacturaCollectionNew);
            factura = em.merge(factura);
            if (formaspagosIdformaspagosOld != null && !formaspagosIdformaspagosOld.equals(formaspagosIdformaspagosNew)) {
                formaspagosIdformaspagosOld.getFacturaCollection().remove(factura);
                formaspagosIdformaspagosOld = em.merge(formaspagosIdformaspagosOld);
            }
            if (formaspagosIdformaspagosNew != null && !formaspagosIdformaspagosNew.equals(formaspagosIdformaspagosOld)) {
                formaspagosIdformaspagosNew.getFacturaCollection().add(factura);
                formaspagosIdformaspagosNew = em.merge(formaspagosIdformaspagosNew);
            }
            if (idUsuariovendedorOld != null && !idUsuariovendedorOld.equals(idUsuariovendedorNew)) {
                idUsuariovendedorOld.getFacturaCollection().remove(factura);
                idUsuariovendedorOld = em.merge(idUsuariovendedorOld);
            }
            if (idUsuariovendedorNew != null && !idUsuariovendedorNew.equals(idUsuariovendedorOld)) {
                idUsuariovendedorNew.getFacturaCollection().add(factura);
                idUsuariovendedorNew = em.merge(idUsuariovendedorNew);
            }
            if (idUsuariocompradorOld != null && !idUsuariocompradorOld.equals(idUsuariocompradorNew)) {
                idUsuariocompradorOld.getFacturaCollection().remove(factura);
                idUsuariocompradorOld = em.merge(idUsuariocompradorOld);
            }
            if (idUsuariocompradorNew != null && !idUsuariocompradorNew.equals(idUsuariocompradorOld)) {
                idUsuariocompradorNew.getFacturaCollection().add(factura);
                idUsuariocompradorNew = em.merge(idUsuariocompradorNew);
            }
            for (ProductosDeFactura productosDeFacturaCollectionNewProductosDeFactura : productosDeFacturaCollectionNew) {
                if (!productosDeFacturaCollectionOld.contains(productosDeFacturaCollectionNewProductosDeFactura)) {
                    Factura oldFacturaIdfacturaOfProductosDeFacturaCollectionNewProductosDeFactura = productosDeFacturaCollectionNewProductosDeFactura.getFacturaIdfactura();
                    productosDeFacturaCollectionNewProductosDeFactura.setFacturaIdfactura(factura);
                    productosDeFacturaCollectionNewProductosDeFactura = em.merge(productosDeFacturaCollectionNewProductosDeFactura);
                    if (oldFacturaIdfacturaOfProductosDeFacturaCollectionNewProductosDeFactura != null && !oldFacturaIdfacturaOfProductosDeFacturaCollectionNewProductosDeFactura.equals(factura)) {
                        oldFacturaIdfacturaOfProductosDeFacturaCollectionNewProductosDeFactura.getProductosDeFacturaCollection().remove(productosDeFacturaCollectionNewProductosDeFactura);
                        oldFacturaIdfacturaOfProductosDeFacturaCollectionNewProductosDeFactura = em.merge(oldFacturaIdfacturaOfProductosDeFacturaCollectionNewProductosDeFactura);
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
                Integer id = factura.getIdfactura();
                if (findFactura(id) == null) {
                    throw new NonexistentEntityException("The factura with id " + id + " no longer exists.");
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
            Factura factura;
            try {
                factura = em.getReference(Factura.class, id);
                factura.getIdfactura();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The factura with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<ProductosDeFactura> productosDeFacturaCollectionOrphanCheck = factura.getProductosDeFacturaCollection();
            for (ProductosDeFactura productosDeFacturaCollectionOrphanCheckProductosDeFactura : productosDeFacturaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Factura (" + factura + ") cannot be destroyed since the ProductosDeFactura " + productosDeFacturaCollectionOrphanCheckProductosDeFactura + " in its productosDeFacturaCollection field has a non-nullable facturaIdfactura field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Formaspagos formaspagosIdformaspagos = factura.getFormaspagosIdformaspagos();
            if (formaspagosIdformaspagos != null) {
                formaspagosIdformaspagos.getFacturaCollection().remove(factura);
                formaspagosIdformaspagos = em.merge(formaspagosIdformaspagos);
            }
            Usuario idUsuariovendedor = factura.getIdUsuariovendedor();
            if (idUsuariovendedor != null) {
                idUsuariovendedor.getFacturaCollection().remove(factura);
                idUsuariovendedor = em.merge(idUsuariovendedor);
            }
            Usuario idUsuariocomprador = factura.getIdUsuariocomprador();
            if (idUsuariocomprador != null) {
                idUsuariocomprador.getFacturaCollection().remove(factura);
                idUsuariocomprador = em.merge(idUsuariocomprador);
            }
            em.remove(factura);
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

    public List<Factura> findFacturaEntities() {
        return findFacturaEntities(true, -1, -1);
    }

    public List<Factura> findFacturaEntities(int maxResults, int firstResult) {
        return findFacturaEntities(false, maxResults, firstResult);
    }

    private List<Factura> findFacturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Factura.class));
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

    public Factura findFactura(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Factura.class, id);
        } finally {
            em.close();
        }
    }

    public int getFacturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Factura> rt = cq.from(Factura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
