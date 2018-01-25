/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acs.ejbs;


import acs.entities.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author aimable
 */
@Stateless
public class UsersEJB extends AbstractFacade<User> {

    @PersistenceContext(unitName = "ReportsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersEJB() {
        super(User.class);
    }

    public List<User> findByUserNameAndPassword(String username, String password) {
        TypedQuery<User> query = em.createNamedQuery("User.findByUserNameAndPassword", User.class);
        query.setParameter("userName", username);
        query.setParameter("password", password);
        query.setMaxResults(1);
        return query.getResultList();
    }

    @Override
    public List<User> findAll() {
        TypedQuery query = em.createNamedQuery("User.findAll", User.class);
        return query.getResultList();
    }

}
