/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acs.ejbs;

/**
 *
 * @author Aimable
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author Aimable
 */
public abstract class AbstractFacade<T> {
    private Class<T> entityClass;
    
    private CriteriaBuilder cb;
    private CriteriaQuery<T> cq;
    private Root<T> root;
    private TypedQuery<T> q;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(getEntityManager().merge(entity));
    }

    public void remove(T entity) {
        getEntityManager().remove(entity);
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        cb=getEntityManager().getCriteriaBuilder();
        cq = cb.createQuery(entityClass);
        root=cq.from(entityClass);
        cq.select(root);
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        
        cb=getEntityManager().getCriteriaBuilder();
        cq = cb.createQuery(entityClass);
        root=cq.from(entityClass);
        cq.select(root);
        q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        cb=getEntityManager().getCriteriaBuilder();
        cq =cb.createQuery(entityClass);
        root = cq.from(entityClass);
//        cq.select(cb.count());
        q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    public List<T> findByAttributes(String filter,Map<String,Object> attributes) {
        
        List<T> results;
        //set up the Criteria query
        cb = getEntityManager().getCriteriaBuilder();
        cq = cb.createQuery(entityClass);
        root = cq.from(entityClass);
 
        List<Predicate> predicates = new ArrayList<Predicate>();
        if (filter.equals("contains")) {
            for (String s : attributes.keySet()) {
                if (root.get(s) != null) {
                    predicates.add(cb.like(root.<String>get(s), "%" + attributes.get(s) + "%"));
                }
            }
        }
        if (filter.equals("startby")) {
            for (String s : attributes.keySet()) {
                if (root.get(s) != null) {
                    predicates.add(cb.like(root.<String>get(s), attributes.get(s) + "%"));
                }
            }
        }
        if (filter.equals("equals")) {
            for (String s : attributes.keySet()) {
                if (root.get(s) != null) {
                    predicates.add(cb.equal(root.<Object>get(s), attributes.get(s)));
                }
            }
        }
        cq.where(predicates.toArray(new Predicate[]{}));
//        cq.orderBy(cb.desc(root.get("datecreation")));
        q = getEntityManager().createQuery(cq);
        results = q.getResultList();
        return results;
    }
    
}
