/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acs.ejbs;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Schedule;
import javax.ejb.Schedules;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import org.apache.log4j.Logger;

/**
 *
 * @author Aimable
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Lock(LockType.READ)
public class ReportManagerEJB {

    @PersistenceContext(unitName = "ReportsPU", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;


    private static final Logger logger = Logger.getLogger("ACSReports");



    @Schedules({
        @Schedule(minute = "0", hour = "5", dayOfWeek = "*")
    })
    public void processFlush() {
        List<Long> listIds = new ArrayList<>();
        Query query = em.createNativeQuery("select id from information_schema.processlist where user = ?", Long.class);
        query.setParameter(1, "reportuser");
        listIds = query.getResultList();
        logger.debug("Going to kill " + listIds.size() + " processes.");
        if (!listIds.isEmpty()) {
            for (Long id : listIds) {
                query = em.createNativeQuery("kill ?");
                query.setParameter(1, id);
            }
        }
        em.flush();
        em.clear();
        logger.debug("Finished to kill " + listIds.size() + " processes.");
    }

}
