package acs.managedBean;

import acs.ejbs.RptTransactionsEJB;
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

@Named
@ViewScoped
public class PerformanceBean implements Serializable {
    
    private LocalDate startDate = getDefaultStartDate();
    
    private LocalDate endDate=LocalDate.now();
    
    private static final Logger Log = Logger.getLogger("ACSReports");
    
    private List<RptTransactions> transactions;
    
    @Inject
    private RptTransactionsEJB rptTransactionEJB;
    
    public PerformanceBean() {
    }
    
    public void findTransactionsRecords() {
        try {
            transactions = new ArrayList<>();
            transactions = rptTransactionEJB.findPerformanceByDatetime(startDate, endDate);
            System.out.println("The size is "+transactions);
        } catch (Exception ex) {
            Log.error(ex.toString() + "|" + Notification.getStackString(ex));
        }
    }
    
    public void doSearchProcessigRecord() {
//        retreiveProcessingRecords();
//        processRecordsChart();
    }
    
    public LocalDate getStartDate() {
        return startDate;
    }
    
    public void setStartDate(LocalDate date) {
        startDate = date;
    }
    
    public LocalDate getEndDate() {
        return endDate;
    }
    
    public void setEndDate(LocalDate date) {
        endDate = date;
    }
    
    public LocalDate getDefaultStartDate() {
        return LocalDate.now().minusDays(7);
    }
    
    public void retreiveHourlyRecords() {
        
    }
    
    public void retreiveProcessingRecords() {
        
    }
    
    public List<RptTransactions> getTransactions() {
        return transactions;
    }
    
    public void setTransactions(List<RptTransactions> transactions) {
        this.transactions = transactions;
    }
    
}
