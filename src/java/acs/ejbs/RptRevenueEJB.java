/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acs.ejbs;

import acs.entities.RptRevenue;
import acs.utilities.DateConverter;
import java.time.LocalDate;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.apache.log4j.Logger;

/**
 *
 * @author Aimable
 */
@Stateless
public class RptRevenueEJB extends AbstractFacade<RptRevenue> {

    @PersistenceContext(unitName = "ReportsPU")
    private EntityManager em;

    private DateConverter converter = new DateConverter();

    private static final Logger logger = Logger.getLogger("ACSReports");

    public RptRevenueEJB() {
        super(RptRevenue.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void create(RptRevenue entity) {
        super.create(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(RptRevenue entity) {
        super.edit(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RptRevenue find(Object id) {
        return super.find(id); //To change body of generated methods, choose Tools | Templates.
    }

    public List<RptRevenue> findByDatetime(LocalDate startDate, LocalDate endDate) {
        TypedQuery<RptRevenue> query = em.createNamedQuery("RptRevenue.findByRangeRptDatetime", RptRevenue.class);
        query.setParameter(1, startDate);
        query.setParameter(2, endDate);
        return query.getResultList();
    }

    public List<Object[]> findByMonthDatetime(Integer startMonth, Integer endMonth, Integer year) {
        TypedQuery<Object[]> query = em.createNamedQuery("RptRevenue.findByRangeMonthRptDatetime", Object[].class);
        query.setParameter(1, startMonth);
        query.setParameter(2, endMonth);
        query.setParameter(3, year);
        return query.getResultList();
    }

    public List<RptRevenue> findByDatetimeAndAdvanceAmount(RptRevenue entity) {
        Query query = em.createNativeQuery("select SQL_NO_CACHE * from rpt_revenue where rpt_datetime like ? and advance_amount=?", RptRevenue.class);
        query.setParameter(1, converter.dateToString(entity.getRptDatetime()) + "%");
        query.setParameter(2, entity.getAdvanceAmount());
        query.setMaxResults(1);
        return query.getResultList();
    }

}
