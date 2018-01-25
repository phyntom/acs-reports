package acs.managedBean;

import acs.ejbs.RptDailyEJB;
import acs.ejbs.RptTransactionsEJB;
import acs.entities.RptDaily;
import acs.entities.RptTransactions;
import acs.utilities.Notification;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.log4j.Logger;

@Named("usageDailyBean")
@ViewScoped
public class UsageDailyBean implements Serializable {

    private LocalDate startDate = getDefaultDate();

    private LocalDate endDate;

    private static final Logger Log = Logger.getLogger("ACSReports");

    @Inject
    private RptDailyEJB rptDailyEJB;

    @Inject
    private RptTransactionsEJB rptTransactionEJB;

    private List<RptTransactions> transactions = new ArrayList<>();

    private List<RptDaily> subscriberUsageRecords = new ArrayList<>();

    public UsageDailyBean() {
    }

    public void findUsageRecords() {
        try {
            transactions = rptTransactionEJB.findPerformanceByDatetime(startDate, endDate);
        } catch (Exception ex) {
            Log.error(ex.toString() + "|" + Notification.getStackString(ex));
        }
    }

    public void findSubscriberDailyUsage() {
        try {
            subscriberUsageRecords = rptDailyEJB.findDailyUsageSubscByDatetime(startDate, endDate);
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

    public List<RptTransactions> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<RptTransactions> transactions) {
        this.transactions = transactions;
    }

    public List<RptDaily> getSubscriberUsageRecords() {
        return subscriberUsageRecords;
    }

    public void setSubscriberUsageRecords(List<RptDaily> subscriberUsageRecords) {
        this.subscriberUsageRecords = subscriberUsageRecords;
    }

    public LocalDate getDefaultDate() {
        return LocalDate.now().minusDays(7);
    }

}
