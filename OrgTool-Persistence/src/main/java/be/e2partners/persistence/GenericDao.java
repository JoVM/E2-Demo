package be.e2partners.persistence;

/**
 * Created by IntelliJ IDEA.
 * User: user
 * Date: 16/11/11
 * Time: 10:05
 *
 */

import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Generic DAO, providing basic operations supported by the application.
 *
 * @param <T>
 *            the entity type
 * @param <ID>
 *            the primary key type
 */
public interface GenericDao<T extends Serializable, ID extends Serializable> {

    /**
     * Find an entity by its primary key.
     *
     * @param id
     *            the primary key
     * @return the entity
     */
    T findById(final ID id);

    /**
     * Load all entities.
     *
     * @return the list of entities
     */
    List<T> findAll();

    /**
     * Creates an entity and returns the persisted object.
     *
     * @param entity
     *            the entity to save
     * @return the create entity
     */
    T create(final T entity);

    /**
     * Update an entity. <u>Refetch the object to reflect changes.</u>
     *
     * @param entity
     *            the entity to update
     */
    T update(T entity);

    /**
     * Count all entities.
     *
     * @return the number of entities
     */
    int countAll();

    /**
     * mag natuurlijk ook rechtstreeks boolean zijn
     * @param entity
     * @return
     */
    void delete(T entity);
}