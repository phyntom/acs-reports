package acs.managedBean;

import acs.ejbs.ConfigEligibilityStatusEJB;
import acs.ejbs.RptEligibilityEJB;
import acs.entities.ConfigEligibilityStatus;
import acs.entities.RptEligibility;
import acs.utilities.Notification;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.log4j.Logger;

@Named()
@RequestScoped
public class EligibilityBean {

    private LocalDate startDate=LocalDate.now().minusDays(7);

    private LocalDate endDate=LocalDate.now();

    @Inject
    private RptEligibilityEJB rptEligibilityEJB;

    @Inject
    private ConfigEligibilityStatusEJB configEligibilityStatusEJB;

    private List<RptEligibility> eligibilityRecords = new ArrayList<>();

    private List<ConfigEligibilityStatus> configEligibilities = new ArrayList<>();

    private ConfigEligibilityStatus eligibilityStatus;

    private static final Logger Log = Logger.getLogger("ACSReports");

    public EligibilityBean() {
    }

    @PostConstruct
    public void init() {
        configEligibilities = configEligibilityStatusEJB.findAll();
    }

    public void findElibigibiltyRecordsByStatus() {
        try {
            if (eligibilityStatus.getEligibilityStatusId() == 0) {
                eligibilityRecords = rptEligibilityEJB.findEligibilityByDatetime(startDate, endDate);
            }
            else {
                eligibilityRecords = rptEligibilityEJB.findEligibilityByStatus(startDate, endDate, eligibilityStatus);
            }
        } catch (Exception ex) {
            Log.error(ex.toString() + "|" + Notification.getStackString(ex));
        }
    }

    public void findElibigibiltyRecords() {
        try {
            eligibilityRecords = rptEligibilityEJB.findEligibilityByDatetime(startDate, endDate);
        } catch (Exception ex) {
            Log.error(ex.toString() + "|" + Notification.getStackString(ex));
        }
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public RptEligibilityEJB getRptEligibilityEJB() {
        return rptEligibilityEJB;
    }

    public void setRptEligibilityEJB(RptEligibilityEJB rptEligibilityEJB) {
        this.rptEligibilityEJB = rptEligibilityEJB;
    }

    public List<RptEligibility> getEligibilityRecords() {
        return eligibilityRecords;
    }

    public void setEligibilityRecords(List<RptEligibility> eligibilityRecords) {
        this.eligibilityRecords = eligibilityRecords;
    }

    public List<ConfigEligibilityStatus> getConfigEligibilities() {
        return configEligibilities;
    }

    public void setConfigEligibilities(List<ConfigEligibilityStatus> configEligibilities) {
        this.configEligibilities = configEligibilities;
    }

    public ConfigEligibilityStatus getEligibilityStatus() {
        return eligibilityStatus;
    }

    public void setEligibilityStatus(ConfigEligibilityStatus eligibilityStatus) {
        this.eligibilityStatus = eligibilityStatus;
    }
}
