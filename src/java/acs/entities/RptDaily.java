/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acs.entities;

import acs.utilities.DateConverter;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Basic;
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
@Table(name = "rpt_daily")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RptDaily.findAll", query = "SELECT r FROM RptDaily r"),
    @NamedQuery(name = "RptDaily.findByRptDatetime", query = "SELECT r FROM RptDaily r WHERE r.rptDatetime = :rptDatetime"),
    @NamedQuery(name = "RptDaily.findByBarredSubsCount", query = "SELECT r FROM RptDaily r WHERE r.barredSubsCount = :barredSubsCount"),
    @NamedQuery(name = "RptDaily.findByWarnedSubsCount", query = "SELECT r FROM RptDaily r WHERE r.warnedSubsCount = :warnedSubsCount"),
    @NamedQuery(name = "RptDaily.findByUniqueSubsCount", query = "SELECT r FROM RptDaily r WHERE r.uniqueSubsCount = :uniqueSubsCount"),
    @NamedQuery(name = "RptDaily.findByAvgPaymentTime", query = "SELECT r FROM RptDaily r WHERE r.avgPaymentTime = :avgPaymentTime"),
    @NamedQuery(name = "RptDaily.findDailyUsageByDatetime", query = "SELECT r FROM RptDaily r WHERE r.rptDatetime between ?1 and ?2"),
    @NamedQuery(name = "RptDaily.findByLikeRptDatetime", query = "SELECT r FROM RptDaily r WHERE cast(r.rptDatetime char(10)) like :rptDatetime")})
public class RptDaily implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Column(name = "rpt_datetime")
    @Convert(converter = LocalDateConverter.class)
    private LocalDate rptDatetime;

    @Basic(optional = false)
    @NotNull
    @Column(name = "barred_subs_count")
    private Long barredSubsCount;

    @Basic(optional = false)
    @NotNull
    @Column(name = "warned_subs_count")
    private Long warnedSubsCount;

    @Basic(optional = false)
    @NotNull
    @Column(name = "unique_subs_count")
    private Long uniqueSubsCount;

    @Basic(optional = false)
    @NotNull
    @Column(name = "avg_payment_time")
    private Long avgPaymentTime;

    @Basic(optional = false)
    @NotNull
    @Column(name = "firsttime_subs_count")
    private Long firstTimeSubsCount;

    @Transient
    private String dateString;

    public RptDaily() {
    }

    public RptDaily(LocalDate rptDatetime) {
        this.rptDatetime = rptDatetime;
    }

    public RptDaily(LocalDate rptDatetime, Long barredSubsCount, Long warnedSubsCount, Long uniqueSubsCount, Long avgPaymentTime, Long firstTimeSubsCount) {
        this.rptDatetime = rptDatetime;
        this.barredSubsCount = barredSubsCount;
        this.warnedSubsCount = warnedSubsCount;
        this.uniqueSubsCount = uniqueSubsCount;
        this.avgPaymentTime = avgPaymentTime;
        this.firstTimeSubsCount = firstTimeSubsCount;
    }

    public LocalDate getRptDatetime() {
        return rptDatetime;
    }

    public void setRptDatetime(LocalDate rptDatetime) {
        this.rptDatetime = rptDatetime;
    }

    public Long getBarredSubsCount() {
        return barredSubsCount;
    }

    public void setBarredSubsCount(Long barredSubsCount) {
        this.barredSubsCount = barredSubsCount;
    }

    public Long getWarnedSubsCount() {
        return warnedSubsCount;
    }

    public void setWarnedSubsCount(Long warnedSubsCount) {
        this.warnedSubsCount = warnedSubsCount;
    }

    public Long getUniqueSubsCount() {
        return uniqueSubsCount;
    }

    public void setUniqueSubsCount(Long uniqueSubsCount) {
        this.uniqueSubsCount = uniqueSubsCount;
    }

    public Long getAvgPaymentTime() {
        return avgPaymentTime;
    }

    public void setAvgPaymentTime(Long avgPaymentTime) {
        this.avgPaymentTime = avgPaymentTime;
    }

    public Long getFirstTimeSubsCount() {
        return firstTimeSubsCount;
    }

    public void setFirstTimeSubsCount(Long firstTimeSubsCount) {
        this.firstTimeSubsCount = firstTimeSubsCount;
    }

    public String getDateString() {
        dateString = new DateConverter().dateToString(this.getRptDatetime());
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
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
        if (!(object instanceof RptDaily)) {
            return false;
        }
        RptDaily other = (RptDaily) object;
        if ((this.rptDatetime == null && other.rptDatetime != null) || (this.rptDatetime != null && !this.rptDatetime.equals(other.rptDatetime))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "acs.entities.RptDaily[ rptDatetime=" + rptDatetime + " ]";
    }
    
    
}
