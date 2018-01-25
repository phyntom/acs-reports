/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acs.ejbs;

import acs.utilities.DateConverter;
import java.time.LocalDate;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import org.apache.log4j.Logger;

/**
 *
 * @author aimable
 */
@Stateless
public class RptManagerEJB {

    @PersistenceContext(unitName = "ReportsPU")
    private EntityManager em;

    private static final Logger log = Logger.getLogger("ACSReports");

    protected EntityManager getEntityManager() {
        return em;
    }

    @Schedule(hour = "0", minute = "5", second = "0")
    public void dailyAdvancesReport() {
        try {
            LocalDate previousDate = LocalDate.now().minusDays(1);
            String reportDate = new DateConverter().dateToString(previousDate);

            StoredProcedureQuery query = em.createStoredProcedureQuery("rptAdvancesDailyService");
            query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            query.setParameter(1, reportDate + " 00:00:00");
            query.setParameter(2, reportDate + " 23:59:59");
            query.execute();
            log.info("Report on advance by service executed successfully");

            query = em.createStoredProcedureQuery("rptAdvancesDailyTotal");
            query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            query.setParameter(1, reportDate + " 00:00:00");
            query.setParameter(2, reportDate + " 23:59:59");
            query.execute();
            log.info("Report on advance total executed successfully");

            query = em.createStoredProcedureQuery("rptIncidents");
            query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            query.setParameter(1, reportDate + " 00:00:00");
            query.setParameter(2, reportDate + " 23:59:59");
            query.execute();
            log.info("Report on incidents executed successfully");

        } catch (Exception ex) {
            log.error("Error occurs on processing advances", ex);
        }
    }
    
    @Schedule(hour = "0", minute = "15", second = "0")
    public void dailyPaymentReport() {
        try {
            LocalDate previousDate = LocalDate.now().minusDays(1);
            String reportDate = new DateConverter().dateToString(previousDate);

            StoredProcedureQuery query = em.createStoredProcedureQuery("rptPaymentsDailyService");
            query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            query.setParameter(1, reportDate + " 00:00:00");
            query.setParameter(2, reportDate + " 23:59:59");
            query.execute();
            log.info("Report on payments by brand executed successfully");

            query = em.createStoredProcedureQuery("rptPaymentsDailyTotal");
            query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            query.setParameter(1, reportDate + " 00:00:00");
            query.setParameter(2, reportDate + " 23:59:59");
            query.execute();
            log.info("Report on payments total executed successfully");
            
            query = em.createStoredProcedureQuery("rptPartialPaymentsDailyService");
            query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            query.setParameter(1, reportDate + " 00:00:00");
            query.setParameter(2, reportDate + " 23:59:59");
            query.execute();
            log.info("Report on partial payments by service executed successfully");
            
            query = em.createStoredProcedureQuery("rptPartialPaymentsDailyTotal");
            query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            query.setParameter(1, reportDate + " 00:00:00");
            query.setParameter(2, reportDate + " 23:59:59");
            query.execute();
            log.info("Report on partial payments total executed successfully");

            query = em.createStoredProcedureQuery("rptTransactions");
            query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            query.setParameter(1, reportDate + " 00:00:00");
            query.setParameter(2, reportDate + " 23:59:59");
            query.execute();
            log.info("Report on subscriber transactions executed successfully");

        } catch (Exception ex) {
            log.error("Error occurs on processing payments", ex);
        }
    }
    
    @Schedule(hour = "0", minute = "30", second = "0")
    public void dailySubscriber() {
        try {
            LocalDate previousDate = LocalDate.now().minusDays(1);
            String reportDate = new DateConverter().dateToString(previousDate);

            StoredProcedureQuery query = em.createStoredProcedureQuery("rptDaily");
            query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            query.setParameter(1, reportDate + " 00:00:00");
            query.setParameter(2, reportDate + " 23:59:59");
            query.execute();
            log.info("Report on subscriber details executed successfully");

        } catch (Exception ex) {
            log.error("Error occurs on processing payments", ex);
        }
    }
}
