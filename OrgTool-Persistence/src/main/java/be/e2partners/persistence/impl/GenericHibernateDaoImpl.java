package be.e2partners.persistence.impl;

/**
 * Created by IntelliJ IDEA.
 * User: user
 * Date: 16/11/11
 * Time: 10:11
 *
 */


import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import be.e2partners.domain.Persoon;
import be.e2partners.persistence.GenericDao;
import org.apache.commons.lang.Validate;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.transaction.annotation.Transactional;


/**
 * JPA implementation of the GenericDAO. Note that this implementation also
 * expects Hibernate as JPA implementation.
 *
 * @param <T>
 *            The persistent type
 * @param <ID>
 *            The primary key type
 */
public class GenericHibernateDaoImpl<T extends Serializable, ID extends Serializable> implements GenericDao<T, ID> {

    /** The persistent class. */
    private final Class<T> persistentClass;

    /** The entity manager. */
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Instantiates a new generic jpa dao impl.
     */
    @SuppressWarnings("unchecked")
    public GenericHibernateDaoImpl() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    /**
     * Instantiates a new generic jpa dao impl.
     *
     * @param persistentClass
     *            the persistent class
     */
    public GenericHibernateDaoImpl(final Class<T> persistentClass) {
        super();
        this.persistentClass = persistentClass;
    }

    public T findById(final ID id) {
        Validate.notNull(id, "Programming Error: The id cannot be null");
        return getEntityManager().find(persistentClass, id);
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return getEntityManager().createQuery("Select t from " + persistentClass.getSimpleName() + " t")
                .getResultList();
    }

    public int countAll() {
        Session session = (Session) getEntityManager().getDelegate();
        Criteria crit = session.createCriteria(persistentClass);
        crit.setProjection(Projections.rowCount());
        return (Integer) crit.uniqueResult();
    }

    /**
     * Use this inside subclasses as a convenience method.
     *
     * @param maxResults
     *            the max results
     * @param criteria
     *            the criteria
     * @return the list
     */
    @SuppressWarnings("unchecked")
    protected List<T> findByCriteria(final DetachedCriteria criteria, final Integer maxResults) {
        Validate.notNull(criteria, "Programming Error: Calling findByCriteria cannot be done without criteria.");
        Session session = (Session) getEntityManager().getDelegate();
        Criteria crit = criteria.getExecutableCriteria(session);
        if (maxResults != null && maxResults > 0) {
            crit.setMaxResults(maxResults);
        }
        return crit.list();
    }

    /**
     * Creates a Criteria object from a DetachedCriteria and returns it.
     *
     * @param criteria
     *            the criteria
     * @return the criteria
     */
    protected Criteria createCriteria(final DetachedCriteria criteria) {
        Session session = (Session) getEntityManager().getDelegate();
        return criteria.getExecutableCriteria(session);
    }

    public T create(final T entity) {
        Validate.notNull(entity, "Programming Error: The object cannot be null");
        getEntityManager().persist(entity);
        return entity;
    }

    public T update(final T entity) {
        Validate.notNull(entity, "Programming Error: The object cannot be null");
        return getEntityManager().merge(entity);
    }

    public void delete(final T entity) {
        Validate.notNull(entity, "Programming Error: The object cannot be null");

        getEntityManager().remove(getEntityManager().merge(entity));
    }

    /**
     * Returns the entityManager.
     *
     * @return the entityManager
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * Sets the entityManager.
     *
     * @param someEntityManager
     *            the entityManager to set
     */
    public void setEntityManager(final EntityManager someEntityManager) {
        entityManager = someEntityManager;
    }

}
