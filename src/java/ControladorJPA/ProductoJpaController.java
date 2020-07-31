/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorJPA;

import ControladorJPA.exceptions.IllegalOrphanException;
import ControladorJPA.exceptions.NonexistentEntityException;
import ControladorJPA.exceptions.RollbackFailureException;
import Entidades.Producto;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
public class ProductoJpaController implements Serializable {

    public ProductoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Producto producto) throws RollbackFailureException, Exception {
        if (producto.getProductosDeFacturaCollection() == null) {
            producto.setProductosDeFacturaCollection(new ArrayList<ProductosDeFactura>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<ProductosDeFactura> attachedProductosDeFacturaCollection = new ArrayList<ProductosDeFactura>();
            for (ProductosDeFactura productosDeFacturaCollectionProductosDeFacturaToAttach : producto.getProductosDeFacturaCollection()) {
                productosDeFacturaCollectionProductosDeFacturaToAttach = em.getReference(productosDeFacturaCollectionProductosDeFacturaToAttach.getClass(), productosDeFacturaCollectionProductosDeFacturaToAttach.getIdproductosDeFactura());
                attachedProductosDeFacturaCollection.add(productosDeFacturaCollectionProductosDeFacturaToAttach);
            }
            producto.setProductosDeFacturaCollection(attachedProductosDeFacturaCollection);
            em.persist(producto);
            for (ProductosDeFactura productosDeFacturaCollectionProductosDeFactura : producto.getProductosDeFacturaCollection()) {
                Producto oldProductoIdproductoOfProductosDeFacturaCollectionProductosDeFactura = productosDeFacturaCollectionProductosDeFactura.getProductoIdproducto();
                productosDeFacturaCollectionProductosDeFactura.setProductoIdproducto(producto);
                productosDeFacturaCollectionProductosDeFactura = em.merge(productosDeFacturaCollectionProductosDeFactura);
                if (oldProductoIdproductoOfProductosDeFacturaCollectionProductosDeFactura != null) {
                    oldProductoIdproductoOfProductosDeFacturaCollectionProductosDeFactura.getProductosDeFacturaCollection().remove(productosDeFacturaCollectionProductosDeFactura);
                    oldProductoIdproductoOfProductosDeFacturaCollectionProductosDeFactura = em.merge(oldProductoIdproductoOfProductosDeFacturaCollectionProductosDeFactura);
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

    public void edit(Producto producto) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Producto persistentProducto = em.find(Producto.class, producto.getIdproducto());
            Collection<ProductosDeFactura> productosDeFacturaCollectionOld = persistentProducto.getProductosDeFacturaCollection();
            Collection<ProductosDeFactura> productosDeFacturaCollectionNew = producto.getProductosDeFacturaCollection();
            List<String> illegalOrphanMessages = null;
            for (ProductosDeFactura productosDeFacturaCollectionOldProductosDeFactura : productosDeFacturaCollectionOld) {
                if (!productosDeFacturaCollectionNew.contains(productosDeFacturaCollectionOldProductosDeFactura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ProductosDeFactura " + productosDeFacturaCollectionOldProductosDeFactura + " since its productoIdproducto field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<ProductosDeFactura> attachedProductosDeFacturaCollectionNew = new ArrayList<ProductosDeFactura>();
            for (ProductosDeFactura productosDeFacturaCollectionNewProductosDeFacturaToAttach : productosDeFacturaCollectionNew) {
                productosDeFacturaCollectionNewProductosDeFacturaToAttach = em.getReference(productosDeFacturaCollectionNewProductosDeFacturaToAttach.getClass(), productosDeFacturaCollectionNewProductosDeFacturaToAttach.getIdproductosDeFactura());
                attachedProductosDeFacturaCollectionNew.add(productosDeFacturaCollectionNewProductosDeFacturaToAttach);
            }
            productosDeFacturaCollectionNew = attachedProductosDeFacturaCollectionNew;
            producto.setProductosDeFacturaCollection(productosDeFacturaCollectionNew);
            producto = em.merge(producto);
            for (ProductosDeFactura productosDeFacturaCollectionNewProductosDeFactura : productosDeFacturaCollectionNew) {
                if (!productosDeFacturaCollectionOld.contains(productosDeFacturaCollectionNewProductosDeFactura)) {
                    Producto oldProductoIdproductoOfProductosDeFacturaCollectionNewProductosDeFactura = productosDeFacturaCollectionNewProductosDeFactura.getProductoIdproducto();
                    productosDeFacturaCollectionNewProductosDeFactura.setProductoIdproducto(producto);
                    productosDeFacturaCollectionNewProductosDeFactura = em.merge(productosDeFacturaCollectionNewProductosDeFactura);
                    if (oldProductoIdproductoOfProductosDeFacturaCollectionNewProductosDeFactura != null && !oldProductoIdproductoOfProductosDeFacturaCollectionNewProductosDeFactura.equals(producto)) {
                        oldProductoIdproductoOfProductosDeFacturaCollectionNewProductosDeFactura.getProductosDeFacturaCollection().remove(productosDeFacturaCollectionNewProductosDeFactura);
                        oldProductoIdproductoOfProductosDeFacturaCollectionNewProductosDeFactura = em.merge(oldProductoIdproductoOfProductosDeFacturaCollectionNewProductosDeFactura);
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
                Integer id = producto.getIdproducto();
                if (findProducto(id) == null) {
                    throw new NonexistentEntityException("The producto with id " + id + " no longer exists.");
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
            Producto producto;
            try {
                producto = em.getReference(Producto.class, id);
                producto.getIdproducto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The producto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<ProductosDeFactura> productosDeFacturaCollectionOrphanCheck = producto.getProductosDeFacturaCollection();
            for (ProductosDeFactura productosDeFacturaCollectionOrphanCheckProductosDeFactura : productosDeFacturaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Producto (" + producto + ") cannot be destroyed since the ProductosDeFactura " + productosDeFacturaCollectionOrphanCheckProductosDeFactura + " in its productosDeFacturaCollection field has a non-nullable productoIdproducto field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(producto);
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

    public List<Producto> findProductoEntities() {
        return findProductoEntities(true, -1, -1);
    }

    public List<Producto> findProductoEntities(int maxResults, int firstResult) {
        return findProductoEntities(false, maxResults, firstResult);
    }

    private List<Producto> findProductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Producto.class));
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

    public Producto findProducto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Producto.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Producto> rt = cq.from(Producto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
