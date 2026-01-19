package ies.sequeros.dam.ad.orm.infraestructure.repositories;

import ies.sequeros.dam.ad.orm.domain.IProductoRepository;
import ies.sequeros.dam.ad.orm.domain.Producto;
import ies.sequeros.dam.ad.orm.infraestructure.entities.ProductoJPA;
import ies.sequeros.dam.ad.orm.infraestructure.mappers.ProductoMapper;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class JPAProductoRepository implements IProductoRepository {
    private final EntityManagerFactory emf;
    public JPAProductoRepository(final EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public @NotNull List<@NotNull Producto> all() {
        EntityManager em = emf.createEntityManager();
        List<ProductoJPA> items;
        try{
            String jpql = "SELECT c FROM ProductoJPA c";
            items = em.createQuery(jpql,
                    ProductoJPA.class).getResultList();
            return items.stream().map(ProductoMapper::toDomain).toList();
        } finally {
            em.close();
        }
    }

    @Override
    public void create(@NotNull Producto item) {
        final EntityManager em = this.emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            ProductoJPA producto = ProductoMapper.toJpa(item);
            tx.begin();
            em.persist(producto);
            tx.commit();
        } catch (final RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public void update(final Producto item) {
        final EntityManager em = this.emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            final ProductoJPA producto = ProductoMapper.toJpa(item);
            tx.begin();
            em.merge(producto);
            tx.commit();
        } catch (final RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public void delete(final Producto item) {
        final EntityManager em = this.emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            final Producto elemento = em.merge(item);
            em.remove(elemento);
            tx.commit();
        } catch (final RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public void delete(final UUID id) {
        final EntityManager em = this.emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            ProductoJPA ref = em.getReference(ProductoJPA.class, id);
            em.remove(ref);
            tx.commit();
        } catch (final RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public @Nullable Producto findById(@Nullable UUID id) {
        final EntityManager em = this.emf.createEntityManager();
        try {
            final ProductoJPA p = em.find(ProductoJPA.class, id);
            if (p == null) {
                return null;
            }
            return ProductoMapper.toDomain(p);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public boolean existsById(@NotNull UUID id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(ProductoJPA.class, id) != null;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}
