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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "rpt_revenue")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RptRevenue.findAll", query = "SELECT r FROM RptRevenue r")
    ,
    @NamedQuery(name = "RptRevenue.findByRptDatetime", query = "SELECT r FROM RptRevenue r WHERE r.rptDatetime = :rptDatetime")
    ,
    @NamedQuery(name = "RptRevenue.findByAdvanceAmount", query = "SELECT r FROM RptRevenue r WHERE r.advanceAmount = :advanceAmount")
    ,
    @NamedQuery(name = "RptRevenue.findByCommissionAmount", query = "SELECT r FROM RptRevenue r WHERE r.commissionAmount = :commissionAmount")
    ,
    @NamedQuery(name = "RptRevenue.findByTotalAdvanceCount", query = "SELECT r FROM RptRevenue r WHERE r.totalAdvanceCount = :totalAdvanceCount")
    ,
    @NamedQuery(name = "RptRevenue.findByTotalAdvance", query = "SELECT r FROM RptRevenue r WHERE r.totalAdvance = :totalAdvance")
    ,
    @NamedQuery(name = "RptRevenue.findByTotalCommission", query = "SELECT r FROM RptRevenue r WHERE r.totalCommission = :totalCommission")
    ,
    @NamedQuery(name = "RptRevenue.findByTotalPaymentCount", query = "SELECT r FROM RptRevenue r WHERE r.totalPaymentCount = :totalPaymentCount")
    ,
    @NamedQuery(name = "RptRevenue.findByTotalPayment", query = "SELECT r FROM RptRevenue r WHERE r.totalPayment = :totalPayment")
    ,
    @NamedQuery(name = "RptRevenue.findByRangeRptDatetime", query = "SELECT r FROM RptRevenue r WHERE r.rptDatetime between ?1 and ?2")
    ,
    @NamedQuery(name = "RptRevenue.findByRangeMonthRptDatetime", query = "SELECT FUNCTION('date',r.rptDatetime),r.advanceAmount,SUM(r.totalAdvanceCount),SUM(r.totalAdvance),SUM(r.totalCommission),SUM(r.totalPaymentCount),SUM(r.totalPayment) FROM RptRevenue r WHERE FUNCTION('month',r.rptDatetime) between ?1 and ?2 and FUNCTION('year',r.rptDatetime)=?3 group by r.advanceAmount,FUNCTION('month',r.rptDatetime) order by FUNCTION('month',r.rptDatetime),r.advanceAmount")})
public class RptRevenue implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "rpt_date")
    @Convert(converter = LocalDateConverter.class)
    private LocalDate rptDatetime;

    @Id
    @ManyToOne
    @JoinColumn(name = "service_id")
    private ConfigServices serviceId;

    @NotNull
    @Column(name = "advance_amount")
    private Double advanceAmount;

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "commission_amount")
    private Double commissionAmount;

    @Basic(optional = false)
    @NotNull
    @Column(name = "total_advance_count")
    private Long totalAdvanceCount;

    @Basic(optional = false)
    @NotNull
    @Column(name = "total_advance")
    private Double totalAdvance;

    @Basic(optional = false)
    @NotNull
    @Column(name = "total_commission")
    private Double totalCommission;

    @Basic(optional = false)
    @NotNull
    @Column(name = "total_payment_count")
    private Long totalPaymentCount;

    @Basic(optional = false)
    @NotNull
    @Column(name = "total_payment")
    private Double totalPayment;

    @Basic(optional = false)
    @NotNull
    @Column(name = "total_partial_payment_count")
    private Long totalPartialPaymentCount;

    @Basic(optional = false)
    @NotNull
    @Column(name = "total_partial_payment")
    private Double totalPartialPayment;

    @Transient
    private String dateString;

    public RptRevenue() {
    }

    public RptRevenue(LocalDate rptDatetime, ConfigServices serviceId, Double advanceAmount, Double commissionAmount, Long totalAdvanceCount, Double totalAdvance, Double totalCommission, Long totalPaymentCount, Double totalPayment, Long totalPartialPaymentCount, Double totalPartialPayment, String dateString) {
        this.rptDatetime = rptDatetime;
        this.serviceId = serviceId;
        this.advanceAmount = advanceAmount;
        this.commissionAmount = commissionAmount;
        this.totalAdvanceCount = totalAdvanceCount;
        this.totalAdvance = totalAdvance;
        this.totalCommission = totalCommission;
        this.totalPaymentCount = totalPaymentCount;
        this.totalPayment = totalPayment;
        this.totalPartialPaymentCount = totalPartialPaymentCount;
        this.totalPartialPayment = totalPartialPayment;
        this.dateString = dateString;
    }


    public LocalDate getRptDatetime() {
        return rptDatetime;
    }

    public void setRptDatetime(LocalDate rptDatetime) {
        this.rptDatetime = rptDatetime;
    }

    public Double getAdvanceAmount() {
        return advanceAmount;
    }

    public void setAdvanceAmount(Double advanceAmount) {
        this.advanceAmount = advanceAmount;
    }

    public Double getCommissionAmount() {
        return commissionAmount;
    }

    public void setCommissionAmount(Double commissionAmount) {
        this.commissionAmount = commissionAmount;
    }

    public Long getTotalAdvanceCount() {
        return totalAdvanceCount;
    }

    public void setTotalAdvanceCount(Long totalAdvanceCount) {
        this.totalAdvanceCount = totalAdvanceCount;
    }

    public Double getTotalAdvance() {
        return totalAdvance;
    }

    public void setTotalAdvance(Double totalAdvance) {
        this.totalAdvance = totalAdvance;
    }

    public Double getTotalCommission() {
        return totalCommission;
    }

    public void setTotalCommission(Double totalCommission) {
        this.totalCommission = totalCommission;
    }

    public Long getTotalPaymentCount() {
        return totalPaymentCount;
    }

    public void setTotalPaymentCount(Long totalPaymentCount) {
        this.totalPaymentCount = totalPaymentCount;
    }

    public Double getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(Double totalPayment) {
        this.totalPayment = totalPayment;
    }

    public String getDateString() {
        dateString = new DateConverter().dateToString(this.getRptDatetime());
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public ConfigServices getServiceId() {
        return serviceId;
    }

    public void setServiceId(ConfigServices serviceId) {
        this.serviceId = serviceId;
    }

    public Long getTotalPartialPaymentCount() {
        return totalPartialPaymentCount;
    }

    public void setTotalPartialPaymentCount(Long totalPartialPaymentCount) {
        this.totalPartialPaymentCount = totalPartialPaymentCount;
    }

    public Double getTotalPartialPayment() {
        return totalPartialPayment;
    }

    public void setTotalPartialPayment(Double totalPartialPayment) {
        this.totalPartialPayment = totalPartialPayment;
    }
    
    

    @Override
    public String toString() {
        return "RptRevenue{" + "rptDatetime=" + rptDatetime + ", advanceAmount=" + advanceAmount + '}';
    }

}
