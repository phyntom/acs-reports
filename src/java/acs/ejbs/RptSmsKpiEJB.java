/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acs.ejbs;

import acs.entities.RptSmsKpi;
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
public class RptSmsKpiEJB extends AbstractFacade<RptSmsKpi> {

    @PersistenceContext(unitName = "ReportsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RptSmsKpiEJB() {
        super(RptSmsKpi.class);
    }

    /**
     * method to return the latest notification sent
     *
     * @return
     */
    public RptSmsKpi findLatestNotificationByDatetime() {
        TypedQuery<RptSmsKpi> query = em.createNamedQuery("RptSmsKpi.findByKpiLatest", RptSmsKpi.class);
        query.setMaxResults(1);
        return query.getSingleResult();
    }

    public List<RptSmsKpi> findNotificationsByDatetime(String startDate, String endDate) {
        TypedQuery<RptSmsKpi> query = em.createNamedQuery("RptSmsKpi.findByRangeKpiDatetime", RptSmsKpi.class);
        query.setParameter(1, startDate);
        query.setParameter(2, endDate);
        return query.getResultList();
    }

}
