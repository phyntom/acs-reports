/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acs.ejbs;

import acs.entities.RptRevenueTotalDaily;
import java.time.LocalDate;
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
public class RptRevenueTotalDailyEJB extends AbstractFacade<RptRevenueTotalDaily> {

    @PersistenceContext(unitName = "ReportsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RptRevenueTotalDailyEJB() {
        super(RptRevenueTotalDaily.class);
    }

    public List<RptRevenueTotalDaily> findByDatetime(LocalDate startDate, LocalDate endDate) {
        TypedQuery<RptRevenueTotalDaily> query = em.createNamedQuery("RptRevenueTotalDaily.findByRangeRptDatetime", RptRevenueTotalDaily.class);
        query.setParameter(1, startDate);
        query.setParameter(2, endDate);
        return query.getResultList();
    }

}
