/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acs.ejbs;

import acs.entities.ConfigEligibilityStatus;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Aimable
 */
@Stateless
public class ConfigEligibilityStatusEJB extends AbstractFacade<ConfigEligibilityStatus> {

    @PersistenceContext(unitName = "ReportsPU")
    private EntityManager em;

    public ConfigEligibilityStatusEJB() {
        super(ConfigEligibilityStatus.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public List<ConfigEligibilityStatus> findAll() {
        return super.findAll(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ConfigEligibilityStatus find(Object id) {
        return super.find(id); //To change body of generated methods, choose Tools | Templates.
    }

}
