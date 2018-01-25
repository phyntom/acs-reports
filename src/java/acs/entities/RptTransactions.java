/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acs.entities;

import acs.utilities.DateConverter;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Aimable
 */
@Entity
@Table(name = "rpt_transactions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RptTransactions.findAll", query = "SELECT r FROM RptTransactions r")
    ,
    @NamedQuery(name = "RptTransactions.findByRptDatetime", query = "SELECT r FROM RptTransactions r WHERE r.rptDatetime = :rptDatetime")
    ,
    @NamedQuery(name = "RptTransactions.findByRequestTotalCount", query = "SELECT r FROM RptTransactions r WHERE r.requestTotalCount = :requestTotalCount")
    ,
    @NamedQuery(name = "RptTransactions.findBySmppRequestTotalCount", query = "SELECT r FROM RptTransactions r WHERE r.smppRequestTotalCount = :smppRequestTotalCount")
    ,
    @NamedQuery(name = "RptTransactions.findByUssdRequestTotalCount", query = "SELECT r FROM RptTransactions r WHERE r.ussdRequestTotalCount = :ussdRequestTotalCount")
    ,
    @NamedQuery(name = "RptTransactions.findByGuiRequestTotalCount", query = "SELECT r FROM RptTransactions r WHERE r.guiRequestTotalCount = :guiRequestTotalCount")
    ,
    @NamedQuery(name = "RptTransactions.findByMinRequestProcTime", query = "SELECT r FROM RptTransactions r WHERE r.minRequestProcTime = :minRequestProcTime")
    ,
    @NamedQuery(name = "RptTransactions.findByAvgRequestProcTime", query = "SELECT r FROM RptTransactions r WHERE r.avgRequestProcTime = :avgRequestProcTime")
    ,
    @NamedQuery(name = "RptTransactions.findByMaxRequestProcTime", query = "SELECT r FROM RptTransactions r WHERE r.maxRequestProcTime = :maxRequestProcTime")
    ,
    @NamedQuery(name = "RptTransactions.findByRequestTotalSuccessful", query = "SELECT r FROM RptTransactions r WHERE r.requestTotalSuccessful = :requestTotalSuccessful")
    ,
    @NamedQuery(name = "RptTransactions.findByRequestTotalUnsuccessful", query = "SELECT r FROM RptTransactions r WHERE r.requestTotalUnsuccessful = :requestTotalUnsuccessful")
    ,
    @NamedQuery(name = "RptTransactions.findDailyUsageByDatetime", query = "SELECT r FROM RptTransactions r WHERE r.rptDatetime between ?1 and ?2 order by r.rptDatetime")})
public class RptTransactions implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Column(name = "rpt_datetime")
    @Convert(converter = LocalDateConverter.class)
    private LocalDate rptDatetime;

    @Column(name = "request_total_count")
    private Long requestTotalCount;

    @Column(name = "smpp_request_total_count")
    private Long smppRequestTotalCount;

    @Column(name = "ussd_request_total_count")
    private Long ussdRequestTotalCount;

    @Column(name = "gui_request_total_count")
    private Long guiRequestTotalCount;

    @Column(name = "min_request_proc_time")
    private Long minRequestProcTime;

    @Column(name = "avg_request_proc_time")
    private Long avgRequestProcTime;

    @Column(name = "max_request_proc_time")
    private Long maxRequestProcTime;

    @Column(name = "request_total_successful")
    private Long requestTotalSuccessful;

    @Column(name = "request_total_unsuccessful")
    private Long requestTotalUnsuccessful;
    
    @Transient
    private Long successRate;

    @Transient
    private String dateString;

    public RptTransactions() {
    }

    public RptTransactions(LocalDate rptDatetime) {
        this.rptDatetime = rptDatetime;
    }

    public LocalDate getRptDatetime() {
        return rptDatetime;
    }

    public void setRptDatetime(LocalDate rptDatetime) {
        this.rptDatetime = rptDatetime;
    }

    public Long getRequestTotalCount() {
        return requestTotalCount;
    }

    public void setRequestTotalCount(Long requestTotalCount) {
        this.requestTotalCount = requestTotalCount;
    }

    public Long getSmppRequestTotalCount() {
        return smppRequestTotalCount;
    }

    public void setSmppRequestTotalCount(Long smppRequestTotalCount) {
        this.smppRequestTotalCount = smppRequestTotalCount;
    }

    public Long getUssdRequestTotalCount() {
        return ussdRequestTotalCount;
    }

    public void setUssdRequestTotalCount(Long ussdRequestTotalCount) {
        this.ussdRequestTotalCount = ussdRequestTotalCount;
    }

    public Long getGuiRequestTotalCount() {
        return guiRequestTotalCount;
    }

    public void setGuiRequestTotalCount(Long guiRequestTotalCount) {
        this.guiRequestTotalCount = guiRequestTotalCount;
    }

    public Long getMinRequestProcTime() {
        return minRequestProcTime;
    }

    public void setMinRequestProcTime(Long minRequestProcTime) {
        this.minRequestProcTime = minRequestProcTime;
    }

    public Long getAvgRequestProcTime() {
        return avgRequestProcTime;
    }

    public void setAvgRequestProcTime(Long avgRequestProcTime) {
        this.avgRequestProcTime = avgRequestProcTime;
    }

    public Long getMaxRequestProcTime() {
        return maxRequestProcTime;
    }

    public void setMaxRequestProcTime(Long maxRequestProcTime) {
        this.maxRequestProcTime = maxRequestProcTime;
    }

    public Long getRequestTotalSuccessful() {
        return requestTotalSuccessful;
    }

    public void setRequestTotalSuccessful(Long requestTotalSuccessful) {
        this.requestTotalSuccessful = requestTotalSuccessful;
    }

    public Long getRequestTotalUnsuccessful() {
        return requestTotalUnsuccessful;
    }

    public void setRequestTotalUnsuccessful(Long requestTotalUnsuccessful) {
        this.requestTotalUnsuccessful = requestTotalUnsuccessful;
    }

    public String getDateString() {
        dateString = new DateConverter().dateToString(this.rptDatetime);
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public Long getSuccessRate() {
        successRate=(100 * requestTotalSuccessful)/requestTotalCount;
        return successRate;
    }

    public void setSuccessRate(Long successRate) {
        this.successRate = successRate;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rptDatetime != null ? rptDatetime.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RptTransactions)) {
            return false;
        }
        RptTransactions other = (RptTransactions) object;
        if ((this.rptDatetime == null && other.rptDatetime != null) || (this.rptDatetime != null && !this.rptDatetime.equals(other.rptDatetime))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "acs.entities.RptTransactions[ rptDatetime=" + rptDatetime + " ]";
    }

   
}
