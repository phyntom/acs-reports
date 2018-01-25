/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acs.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author aimable
 */
@Entity
@Table(name = "rpt_revenue_total_daily")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RptRevenueTotalDaily.findAll", query = "SELECT r FROM RptRevenueTotalDaily r")
    , @NamedQuery(name = "RptRevenueTotalDaily.findByRptDate", query = "SELECT r FROM RptRevenueTotalDaily r WHERE r.rptDate = :rptDate")
    , @NamedQuery(name = "RptRevenueTotalDaily.findByTotalAdvanceCount", query = "SELECT r FROM RptRevenueTotalDaily r WHERE r.totalAdvanceCount = :totalAdvanceCount")
    , @NamedQuery(name = "RptRevenueTotalDaily.findByTotalAdvance", query = "SELECT r FROM RptRevenueTotalDaily r WHERE r.totalAdvance = :totalAdvance")
    , @NamedQuery(name = "RptRevenueTotalDaily.findByTotalCommission", query = "SELECT r FROM RptRevenueTotalDaily r WHERE r.totalCommission = :totalCommission")
    , @NamedQuery(name = "RptRevenueTotalDaily.findByTotalPaymentCount", query = "SELECT r FROM RptRevenueTotalDaily r WHERE r.totalPaymentCount = :totalPaymentCount")
    , @NamedQuery(name = "RptRevenueTotalDaily.findByTotalPayment", query = "SELECT r FROM RptRevenueTotalDaily r WHERE r.totalPayment = :totalPayment")
    , @NamedQuery(name = "RptRevenueTotalDaily.findByRangeRptDatetime", query = "SELECT r FROM RptRevenueTotalDaily r WHERE r.rptDate between ?1 and ?2")
    , @NamedQuery(name = "RptRevenueTotalDaily.findByUniqueUsers", query = "SELECT r FROM RptRevenueTotalDaily r WHERE r.uniqueUsers = :uniqueUsers")})
public class RptRevenueTotalDaily implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "rpt_date")
    @Convert(converter = LocalDateConverter.class)
    private LocalDate rptDate;

    @Basic(optional = false)
    @NotNull
    @Column(name = "total_advance_count")
    private int totalAdvanceCount;

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "total_advance")
    private BigDecimal totalAdvance;

    @Basic(optional = false)
    @NotNull
    @Column(name = "total_commission")
    private BigDecimal totalCommission;

    @Basic(optional = false)
    @NotNull
    @Column(name = "total_payment_count")
    private int totalPaymentCount;

    @Basic(optional = false)
    @NotNull
    @Column(name = "total_payment")
    private BigDecimal totalPayment;

    @Column(name = "unique_users")
    private Integer uniqueUsers;

    @Basic(optional = false)
    @NotNull
    @Column(name = "total_partial_payment_count")
    private Long totalPartialPaymentCount;

    @Basic(optional = false)
    @NotNull
    @Column(name = "total_partial_payment")
    private Double totalPartialPayment;

    public RptRevenueTotalDaily() {
    }

    public RptRevenueTotalDaily(LocalDate rptDate) {
        this.rptDate = rptDate;
    }

    public RptRevenueTotalDaily(LocalDate rptDate, int totalAdvanceCount, BigDecimal totalAdvance, BigDecimal totalCommission, int totalPaymentCount, BigDecimal totalPayment) {
        this.rptDate = rptDate;
        this.totalAdvanceCount = totalAdvanceCount;
        this.totalAdvance = totalAdvance;
        this.totalCommission = totalCommission;
        this.totalPaymentCount = totalPaymentCount;
        this.totalPayment = totalPayment;
    }

    public LocalDate getRptDate() {
        return rptDate;
    }

    public void setRptDate(LocalDate rptDate) {
        this.rptDate = rptDate;
    }

    public int getTotalAdvanceCount() {
        return totalAdvanceCount;
    }

    public void setTotalAdvanceCount(int totalAdvanceCount) {
        this.totalAdvanceCount = totalAdvanceCount;
    }

    public BigDecimal getTotalAdvance() {
        return totalAdvance;
    }

    public void setTotalAdvance(BigDecimal totalAdvance) {
        this.totalAdvance = totalAdvance;
    }

    public BigDecimal getTotalCommission() {
        return totalCommission;
    }

    public void setTotalCommission(BigDecimal totalCommission) {
        this.totalCommission = totalCommission;
    }

    public int getTotalPaymentCount() {
        return totalPaymentCount;
    }

    public void setTotalPaymentCount(int totalPaymentCount) {
        this.totalPaymentCount = totalPaymentCount;
    }

    public BigDecimal getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(BigDecimal totalPayment) {
        this.totalPayment = totalPayment;
    }

    public Integer getUniqueUsers() {
        return uniqueUsers;
    }

    public void setUniqueUsers(Integer uniqueUsers) {
        this.uniqueUsers = uniqueUsers;
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
    public int hashCode() {
        int hash = 0;
        hash += (rptDate != null ? rptDate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RptRevenueTotalDaily)) {
            return false;
        }
        RptRevenueTotalDaily other = (RptRevenueTotalDaily) object;
        if ((this.rptDate == null && other.rptDate != null) || (this.rptDate != null && !this.rptDate.equals(other.rptDate))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "acs.entities.RptRevenueTotalDaily[ rptDate=" + rptDate + " ]";
    }

}
