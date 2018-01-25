/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acs.ejbs;

import acs.entities.RptDaily;
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
public class RptDailyEJB extends AbstractFacade<RptDaily> {

    @PersistenceContext(unitName = "ReportsPU")
    private EntityManager em;

    private final DateConverter converter = new DateConverter();

    private RptDaily rptDaily;

    private static final Logger logger = Logger.getLogger("ACSReport");

    public RptDailyEJB() {
        super(RptDaily.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void create(RptDaily entity) {
        super.create(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(RptDaily entity) {
        super.edit(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RptDaily find(Object id) {
        return super.find(id); //To change body of generated methods, choose Tools | Templates.
    }

    public List<RptDaily> findDailyUsageSubscByDatetime(LocalDate startDate, LocalDate endDate) {
        TypedQuery<RptDaily> query = em.createNamedQuery("RptDaily.findDailyUsageByDatetime", RptDaily.class);
        query.setParameter(1, startDate);
        query.setParameter(2, endDate);
        return query.getResultList();
    }

    public List<RptDaily> findByRptDatetime(RptDaily entity) {
        Query query = em.createNativeQuery("select SQL_NO_CACHE * from rpt_daily where rpt_datetime like ?", RptDaily.class);
        query.setParameter(1, converter.dateToString(entity.getRptDatetime()) + "%");
        query.setMaxResults(1);
        return query.getResultList();
    }

}
