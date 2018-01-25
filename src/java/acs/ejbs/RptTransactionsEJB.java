/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acs.ejbs;

import acs.entities.RptTransactions;
import acs.utilities.DateConverter;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
public class RptTransactionsEJB extends AbstractFacade<RptTransactions> {

    @PersistenceContext(unitName = "ReportsPU")
    private EntityManager em;

    private final DateConverter converter = new DateConverter();

    private static final Logger logger = Logger.getLogger("ACSReports");

    private RptTransactions rptTransaction;

    @Override
    protected EntityManager getEntityManager() {
        return em; //To change body of generated methods, choose Tools | Templates.
    }

    public RptTransactionsEJB() {
        super(RptTransactions.class);
    }

    @Override
    public void create(RptTransactions entity) {
        super.create(entity);
    }

    @Override
    public void edit(RptTransactions entity) {
        super.edit(entity);
    }

    @Override
    public RptTransactions find(Object id) {
        return super.find(id);
    }


    public List<RptTransactions> findPerformanceByDatetime(LocalDate startDate, LocalDate endDate) {
        TypedQuery query = em.createNamedQuery("RptTransactions.findDailyUsageByDatetime", RptTransactions.class);
        query.setParameter(1, startDate);
        query.setParameter(2, endDate);
        return query.getResultList();
    }

    public List<RptTransactions> findByLikeRptDatetime(RptTransactions entity) {
        Query query = em.createNativeQuery("select SQL_NO_CACHE * from rpt_transactions rt where rpt_datetime like ?", RptTransactions.class);
        query.setParameter(1, converter.dateToString(entity.getRptDatetime()) + "%");
        query.setMaxResults(1);
        return query.getResultList();
    }

}
