/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acs.restwebservice;

import acs.ejbs.ConfigEligibilityStatusEJB;
import acs.ejbs.MonitorEJB;
import acs.ejbs.RptDailyEJB;
import acs.ejbs.RptEligibilityEJB;
import acs.ejbs.RptRevenueEJB;
import acs.ejbs.RptSmsKpiEJB;
import acs.ejbs.RptTransactionsEJB;
import acs.entities.ConfigEligibilityStatus;
import acs.entities.RptDaily;
import acs.entities.RptEligibility;
import acs.entities.RptRevenue;
import acs.entities.RptSmsKpi;
import acs.entities.RptTransactions;
import acs.utilities.DateConverter;
import java.io.StringReader;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;

/**
 * REST Web Service
 *
 * @author Aimable
 */
@Path("/report")
@Stateless
public class ReportResource {

    @EJB
    private RptRevenueEJB rptRevenueEJB;

    @EJB
    private RptTransactionsEJB rptTransactionEJB;

    @EJB
    private RptDailyEJB rptDailyEJB;

    @EJB
    private RptEligibilityEJB rptEligibilityEJB;

    @Inject
    private ConfigEligibilityStatusEJB configEligibilityStatusEJB;

    @EJB
    private MonitorEJB monitorEJB;

    @EJB
    private RptSmsKpiEJB rptSmsKpiEJB;

    DateConverter converter = new DateConverter();

    private static final Logger Log = org.apache.log4j.Logger.getLogger("ACSReports");

    /**
     * Creates a new instance of GenericResource
     */
    public ReportResource() {

    }

    /**
     *
     * @return
     */
    @GET
    @Path("/latestnotification")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public RptSmsKpi getLatestNotification() {
        try {
            return rptSmsKpiEJB.findLatestNotificationByDatetime();
        } catch (Exception ex) {
            Log.error("ERROR" + ex);
            return null;
        }
    }

    /**
     *
     * @param requestData
     * @return
     */
    @POST
    @Path("/getnotifications")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response getNotificationByDate(String requestData) {
        try {
            Response response = null;
            StringReader reader = new StringReader(requestData);
            JsonParser parser = Json.createParser(reader);
            String startDate = "";
            String endDate = "";
            List<RptSmsKpi> notifications;
            while (parser.hasNext()) {
                JsonParser.Event e = parser.next();
                if (e == JsonParser.Event.KEY_NAME) {
                    switch (parser.getString()) {
                        case "startDate":
                            parser.next();
                            startDate = parser.getString();
                            break;
                        case "endDate":
                            parser.next();
                            endDate = parser.getString();
                            break;
                        default:
                            response = Response.noContent().build();
                            break;
                    }
                }
            }
            if (!startDate.isEmpty() && !endDate.isEmpty()) {
                notifications = rptSmsKpiEJB.findNotificationsByDatetime(startDate, endDate);
                if (notifications.isEmpty()) {
                    response = Response.noContent().build();
                }
                else {
                    response = Response.ok(notifications, MediaType.APPLICATION_JSON).build();
                }
            }
            return response;
        } catch (Exception ex) {
            Log.error("ERROR" + ex);
            return Response.serverError().build();
        }
    }

    /**
     *
     *
     * @param startDate
     * @param endDate
     * @return an instance of List<RptRevenue>
     */
    @GET
    @Path("/revenues")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<RptRevenue> getRevenuesByDatetime(@QueryParam("startDate") String startDate, @QueryParam("endDate") String endDate) {
        try {
            LocalDate stDate = converter.stringToDate(startDate);
            LocalDate enDate = converter.stringToDate(endDate);
            return rptRevenueEJB.findByDatetime(stDate, enDate);
        } catch (ParseException ex) {
            Log.error("ERROR" + ex);
            return null;
        }

    }

    @GET
    @Path("/monthlyrevenues")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Object[]> getRevenuesByMonth(@QueryParam("startMonth") String startMonth, @QueryParam("endMonth") String endMonth, @QueryParam("year") String styear) {
        try {
            Integer stMonth = Integer.parseInt(startMonth);
            Integer enMonth = Integer.parseInt(endMonth);
            Integer year = Integer.parseInt(styear);
            return rptRevenueEJB.findByMonthDatetime(stMonth, enMonth, year);
        } catch (NumberFormatException ex) {
            Log.error("ERROR" + ex);
            return null;
        }

    }

    @GET
    @Path("/usage")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<RptTransactions> getUsageDaily(@QueryParam("startDate") String startDate, @QueryParam("endDate") String endDate) {
        try {
            LocalDate stDate = converter.stringToDate(startDate);
            LocalDate enDate = converter.stringToDate(endDate);
            return rptTransactionEJB.findPerformanceByDatetime(stDate, enDate);
        } catch (ParseException ex) {
            Log.error("ERROR" + ex);
            return null;
        }

    }

    @GET
    @Path("/usageSubscriber")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<RptDaily> getUsageSubscriberDaily(@QueryParam("startDate") String startDate, @QueryParam("endDate") String endDate) {
        try {
            LocalDate stDate = converter.stringToDate(startDate);
            LocalDate enDate = converter.stringToDate(endDate);
            return rptDailyEJB.findDailyUsageSubscByDatetime(stDate, enDate);
        } catch (ParseException ex) {
            Log.error("ERROR" + ex);
            return null;
        }
    }

    @GET
    @Path("/performance")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<RptTransactions> getPerformance(@QueryParam("startDate") String startDate, @QueryParam("endDate") String endDate) {
        try {
            LocalDate stDate = converter.stringToDate(startDate);
            LocalDate enDate = converter.stringToDate(endDate);
            return rptTransactionEJB.findPerformanceByDatetime(stDate, enDate);
        } catch (ParseException ex) {
            Log.error("ERROR" + ex);
            return null;
        }
    }

    @GET
    @Path("/eligibillity")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<RptEligibility> getEligibility(@QueryParam("startDate") String startDate, @QueryParam("endDate") String endDate) {
        try {
            LocalDate stDate = converter.stringToDate(startDate);
            LocalDate enDate = converter.stringToDate(endDate);
            return rptEligibilityEJB.findEligibilityByDatetime(stDate, enDate);
        } catch (ParseException ex) {
            Log.error("ERROR" + ex);
            return null;
        }
    }

    @GET
    @Path("/eligibillityStatus")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<RptEligibility> getEligibilityByStatus(@QueryParam("startDate") String startDate, @QueryParam("endDate") String endDate, @QueryParam("statusCode") String statusCode) {
        try {
            LocalDate stDate = converter.stringToDate(startDate);
            LocalDate enDate = converter.stringToDate(endDate);
            ConfigEligibilityStatus configEligibilityStatus = configEligibilityStatusEJB.find(Long.valueOf(statusCode));
            return rptEligibilityEJB.findEligibilityByStatus(stDate, enDate, configEligibilityStatus);
        } catch (ParseException ex) {
            Log.error("ERROR" + ex);
            return null;
        }
    }

    @GET
    @Path("/hourlysuccesstrans")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Object[]> getHourlySuccessTrans(@QueryParam("oddrequestid") Long maxOddRequestId, @QueryParam("evenrequestid") Long maxEvenRequestId) {
        try {
            return monitorEJB.getHourlySuccessTransactions(maxOddRequestId, maxEvenRequestId);
        } catch (NumberFormatException ex) {
            Log.error("ERROR" + ex);
            return null;
        }

    }

    @GET
    @Path("/monthlyavgtrans")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Object[]> getMonthlyAverageTrans(@QueryParam("requestid") Long requestId, @QueryParam("averagetrans") Long averageTrans) {
        try {
            return monitorEJB.getMonthlyAverage(requestId, averageTrans);
        } catch (NumberFormatException ex) {
            Log.error("ERROR" + ex);
            return null;
        }

    }

    @GET
    @Path("/requestidavg")
    @Produces({MediaType.APPLICATION_JSON})
    public Long getRequestIdAvg(@QueryParam("requestid") Long requestId, @QueryParam("averagetrans") Long averageTrans) {
        try {
            return monitorEJB.getRequestIdAvg(requestId, averageTrans);
        } catch (NumberFormatException ex) {
            Log.error("ERROR" + ex);
            return null;
        }

    }

    @GET
    @Path("/oddrequestid")
    @Produces({MediaType.APPLICATION_JSON})
    public Long getOddRequestId(@QueryParam("orequestid") Long requestId) {
        try {
            Long maxOddRequestId = monitorEJB.getMaxOddRequestId(requestId);
            if (maxOddRequestId == null) {
                maxOddRequestId = new Long(0);
            }
            return maxOddRequestId;
        } catch (NumberFormatException ex) {
            Log.error("ERROR" + ex);
            return null;
        }

    }

    @GET
    @Path("/evenrequestid")
    @Produces({MediaType.APPLICATION_JSON})
    public Long getEvenRequestId(@QueryParam("erequestid") Long requestId) {
        try {
            Long maxEvenRequestId = monitorEJB.getMaxEvenRequestId(requestId);
            if (maxEvenRequestId == null) {
                maxEvenRequestId = new Long(0);
            }
            return maxEvenRequestId;
        } catch (NumberFormatException ex) {
            Log.error("ERROR" + ex);
            return null;
        }

    }

}
