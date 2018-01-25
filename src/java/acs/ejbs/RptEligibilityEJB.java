/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acs.ejbs;

import acs.entities.ConfigEligibilityStatus;
import acs.entities.RptEligibility;
import acs.utilities.DateConverter;
import java.text.ParseException;
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
public class RptEligibilityEJB extends AbstractFacade<RptEligibility> {

    @PersistenceContext(unitName = "ReportsPU")
    private EntityManager em;

    private final DateConverter converter = new DateConverter();

    private static final Logger logger = Logger.getLogger("ACSReports");

    public RptEligibilityEJB() {
        super(RptEligibility.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void create(RptEligibility entity) {
        super.create(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(RptEligibility entity) {
        super.edit(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RptEligibility find(Object id) {
        return super.find(id); //To change body of generated methods, choose Tools | Templates.
    }

    public List<RptEligibility> findEligibilityByDatetime(LocalDate startDate, LocalDate endDate) {
        TypedQuery query = em.createNamedQuery("RptEligibility.findByRangeRptDatetime", RptEligibility.class);
        query.setParameter(1, startDate);
        query.setParameter(2, endDate);
        return query.getResultList();
    }

    public List<RptEligibility> findEligibilityByStatus(LocalDate startDate, LocalDate endDate, ConfigEligibilityStatus status) {
        TypedQuery query = em.createNamedQuery("RptEligibility.findByRptDatetimeStatus", RptEligibility.class);
        query.setParameter(1, startDate);
        query.setParameter(2, endDate);
        query.setParameter(3, status);
        return query.getResultList();
    }

    public List<RptEligibility> findByRptDatetime(RptEligibility entity) throws ParseException {
        Query query = em.createNativeQuery("select SQL_NO_CACHE * from rpt_eligibility where rpt_datetime like ? and eligibility_code=?", RptEligibility.class);
        query.setParameter(1, converter.dateToString(entity.getRptDatetime()) + "%");
        query.setParameter(2, entity.getEligibilityCode());
        query.setMaxResults(1);
        return query.getResultList();
    }

}
