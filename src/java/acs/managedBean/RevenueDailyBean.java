package acs.managedBean;

import acs.ejbs.RptRevenueEJB;
import acs.ejbs.RptRevenueTotalDailyEJB;
import acs.entities.RptRevenue;
import acs.entities.RptRevenueTotalDaily;
import acs.utilities.Notification;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.text.DateFormatSymbols;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.log4j.Logger;

@Named("revenueDailyBean")
@ViewScoped
public class RevenueDailyBean implements Serializable {

    private List<RptRevenue> revenues = new ArrayList<>();

    private List<RptRevenueTotalDaily> totalrevenues = new ArrayList<>();

    private LocalDate startDate = getDefaultStartDate();

    private LocalDate endDate = LocalDate.now();

    private String startStringMonth;

    private String endStringMonth;

    private String stringYear;

    private static final Logger Log = Logger.getLogger("ACSReports");

    private LinkedHashMap<String, String> listMonths = new LinkedHashMap<>();

    private List<String> years = new ArrayList<>();

    @Inject
    private RptRevenueEJB rptRevenueEJB;

    @Inject
    private RptRevenueTotalDailyEJB rptRevenueTotalDailyEJB;

    public RevenueDailyBean() {
    }

    @PostConstruct
    public void init() {
        populateListmonths();
        populateListyears();
    }

    public void fingDailyRevenueRecords() {
        try {
            revenues = rptRevenueEJB.findByDatetime(startDate, endDate);
            System.out.println("SIZE IS " + revenues.size());
        } catch (Exception ex) {
            Log.error(ex.toString() + "|" + Notification.getStackString(ex));
        }
    }

    public void fingTotalDailyRevenueRecords() {
        try {
            totalrevenues = rptRevenueTotalDailyEJB.findByDatetime(startDate, endDate);
        } catch (Exception ex) {
            Log.error(ex.toString() + "|" + Notification.getStackString(ex));
        }
    }

    public void doSearchRevenuMonthly() {
        retreiveRevenuesMonthly();
    }

    public void populateListmonths() {
        String[] months = new DateFormatSymbols().getMonths();
        Integer i = 1;
        for (String month : months) {
            if (i < 13) {
                this.listMonths.put(month, String.valueOf(i));
            }
            i++;
        }
    }

    public void populateListyears() {
        for (int i = 2010; i <= 2050; i++) {
            years.add(String.valueOf(i));
        }
    }

    public void retreiveRevenuesMonthly() {

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

    public String getStringYear() {
        return stringYear;
    }

    public void setStringYear(String stringYear) {
        this.stringYear = stringYear;
    }

    public LocalDate getDefaultStartDate() {
        return LocalDate.now().minusDays(7);
    }

    public List<String> getYears() {
        return years;
    }

    public void setYears(List<String> years) {
        this.years = years;
    }

    public String getStartStringMonth() {
        return startStringMonth;
    }

    public void setStartStringMonth(String startStringMonth) {
        this.startStringMonth = startStringMonth;
    }

    public String getEndStringMonth() {
        return endStringMonth;
    }

    public void setEndStringMonth(String endStringMonth) {
        this.endStringMonth = endStringMonth;
    }

    public LinkedHashMap<String, String> getListMonths() {
        return listMonths;
    }

    public void setListMonths(LinkedHashMap<String, String> listMonths) {
        this.listMonths = listMonths;
    }

    public List<RptRevenue> getRevenues() {
        return revenues;
    }

    public void setRevenues(List<RptRevenue> revenues) {
        this.revenues = revenues;
    }

    public List<RptRevenueTotalDaily> getTotalrevenues() {
        return totalrevenues;
    }

    public void setTotalrevenues(List<RptRevenueTotalDaily> totalrevenues) {
        this.totalrevenues = totalrevenues;
    }
    
    

}
