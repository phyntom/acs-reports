/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acs.ejbs;

import acs.entities.ConfigGuiVariables;
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
public class ConfigGuiVariablesEJB extends AbstractFacade<ConfigGuiVariables> {

    @PersistenceContext(unitName = "ReportsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConfigGuiVariablesEJB() {
        super(ConfigGuiVariables.class);
    }

    public List<ConfigGuiVariables> getAllConfigGuiVariables() {
        TypedQuery query = em.createNamedQuery("ConfigGuiVariables.findAll", ConfigGuiVariables.class);
        return query.getResultList();
    }

}
