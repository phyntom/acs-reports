/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acs.ejbs;

import acs.utilities.DateConverter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author aimable
 */
@Stateless
public class MonitorEJB {

    @PersistenceContext(unitName = "ReportsPU")
    private EntityManager em;

    private final DateConverter converter = new DateConverter();

    public MonitorEJB() {

    }

    public List<Object[]> getHourlySuccessTransactions(Long mxOddRequestId, Long mxEvenRequestId) {
        LocalDate currentTime = LocalDate.now();
        Query query = null;
        String sqlQuery = "select date(advance_datetime),hour(advance_datetime),count(request_id) from subscriber_advance where ";
        if (mxOddRequestId != 0 && mxEvenRequestId != 0) {
            sqlQuery += "((request_id > ? and MOD(request_id,2)<>0) or (request_id > ? and MOD(request_id,2)= 0))";
            sqlQuery += " and advance_datetime between ? and ? and hour(current_timestamp)-1=hour(advance_datetime)";
            query = em.createNativeQuery(sqlQuery);
            query.setParameter(1, mxOddRequestId);
            query.setParameter(2, mxEvenRequestId);
            query.setParameter(3, converter.dateToString(currentTime) + " 00:00:00");
            query.setParameter(4, converter.dateToString(currentTime) + " 23:59:59");
            System.out.println(sqlQuery);
        }
        else if (mxOddRequestId != 0 && mxEvenRequestId == 0) {
            sqlQuery += "(request_id > ? and MOD(request_id,2) <>0) and advance_datetime between ? and ? and hour(current_timestamp)-1=hour(advance_datetime)";
            query = em.createNativeQuery(sqlQuery);
            query.setParameter(1, mxOddRequestId);
            query.setParameter(2, converter.dateToString(currentTime) + " 00:00:00");
            query.setParameter(3, converter.dateToString(currentTime) + " 23:59:59");
        }
        else if (mxEvenRequestId != 0 && mxOddRequestId == 0) {
            sqlQuery += "(request_id > ? and MOD(request_id,2) = 0) and advance_datetime between ? and ? and hour(current_timestamp)-1=hour(advance_datetime)";
            query = em.createNativeQuery(sqlQuery);
            query.setParameter(1, mxEvenRequestId);
            query.setParameter(2, converter.dateToString(currentTime) + " 00:00:00");
            query.setParameter(3, converter.dateToString(currentTime) + " 23:59:59");
        }

        query.setHint("javax.persistence.cache.retreiveMode", CacheRetrieveMode.BYPASS);
        return query.getResultList();
    }

    public List<Object[]> getHourlyUnSuccessfullTransactions() {
        LocalDate currentTime = LocalDate.now();
        Query query = em.createNativeQuery("select date(request_datetime),hour(request_datetime),count(request_id) \n"
                + "from request_transaction where request_datetime between ? and ? and hour(current_time)-1=hour(request_datetime)and eligibility_status !=1");
        query.setParameter(1, converter.dateToString(currentTime) + " 00:00:00");
        query.setParameter(2, converter.dateToString(currentTime) + " 23:59:59");
        query.setHint("javax.persistence.cache.retreiveMode", CacheRetrieveMode.BYPASS);
        return query.getResultList();
    }

    public List<Object[]> getMonthlyAverage(Long requestId, Long averageCountTransas) {
        LocalDate startTime = LocalDate.now();
        startTime.minusDays(1);
        LocalDate endTime = LocalDate.now();
        endTime.minusDays(30);
        Query query = em.createNativeQuery("select date(advance_datetime),min(request_id)  from subscriber_advance \n"
                + "where request_id >=? and advance_datetime between ? and ? \n"
                + "group by date(advance_datetime) having count(request_id) >= ? order by advance_datetime  asc limit 30");
        query.setParameter(1, requestId);
        query.setParameter(2, converter.dateToString(startTime) + " 00:00:00");
        query.setParameter(3, converter.dateToString(endTime) + " 23:59:59");
        query.setParameter(4, averageCountTransas);
        query.setMaxResults(30);
        List<Object[]> correctData = query.getResultList();
        int position = 0;
        String sqlQuery = "select sum(cnt)/?,hours from (select count(request_id) as cnt,\n"
                + "hour(advance_datetime) as hours,date(advance_datetime) as dates from subscriber_advance where \n"
                + "date(advance_datetime) in(";
        for (Object[] crdata : correctData) {
            if (position == 0) {
                sqlQuery += "'" + crdata[0] + "'";
            }
            else {
                sqlQuery += ",'" + crdata[0] + "'";
            }
            position++;
        }
        sqlQuery += ") and request_id > ? group by hour(advance_datetime)) as tcnt group by hours";
        query = em.createNativeQuery(sqlQuery);
        query.setParameter(1, correctData.size());
        query.setParameter(2, requestId);
        query.setHint("javax.persistence.cache.retreiveMode", CacheRetrieveMode.BYPASS);
        return query.getResultList();
    }

    public Long getRequestIdAvg(Long requestId, Long averageCountTransas) {
        LocalDate startTime = LocalDate.now();
        startTime.minusDays(1);
        LocalDate endTime = LocalDate.now();
        endTime.minusDays(30);
        Query query = em.createNativeQuery("select date(advance_datetime),min(request_id)  from subscriber_advance \n"
                + "where request_id >=? and advance_datetime between ? and ? \n"
                + "group by date(advance_datetime) having count(request_id) >= ? order by advance_datetime  asc limit 1");
        query.setParameter(1, requestId);
        query.setParameter(2, converter.dateToString(startTime) + " 00:00:00");
        query.setParameter(3, converter.dateToString(endTime) + " 23:59:59");
        query.setParameter(4, averageCountTransas);
        query.setMaxResults(1);
        List<Object[]> correctData = query.getResultList();
        return (Long) correctData.get(0)[1];
    }

    public Long getMaxOddRequestId(Long requestId) {
        LocalDate previousTime = LocalDate.now();
        previousTime.minusDays(1);
        Query query = em.createNativeQuery("select max(request_id) from subscriber_advance where MOD(request_id,2)<> 0\n"
                + "and advance_datetime between ? and ? and request_id > ?");
        query.setParameter(1, converter.dateToString(previousTime) + " 00:00:00");
        query.setParameter(2, converter.dateToString(previousTime) + " 23:59:59");
        query.setParameter(3, requestId);
        return (Long) query.getResultList().get(0);
    }

    public Long getMaxEvenRequestId(Long requestId) {
        LocalDate previousTime = LocalDate.now();
        previousTime.minusDays(1);
        Query query = em.createNativeQuery("select max(request_id) from subscriber_advance where MOD(request_id,2)= 0\n"
                + "and advance_datetime between ? and ? and request_id > ?");
        query.setParameter(1, converter.dateToString(previousTime) + " 00:00:00");
        query.setParameter(2, converter.dateToString(previousTime) + " 23:59:59");
        query.setParameter(3, requestId);
        return (Long) query.getResultList().get(0);
    }

}
