/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acs.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author aimable
 */
@Entity
@Table(name = "rpt_sms_kpi")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RptSmsKpi.findAll", query = "SELECT r FROM RptSmsKpi r")
    , @NamedQuery(name = "RptSmsKpi.findByKpiDatetime", query = "SELECT r FROM RptSmsKpi r WHERE r.kpiDatetime = :kpiDatetime")
    , @NamedQuery(name = "RptSmsKpi.findByAcsAdvance", query = "SELECT r FROM RptSmsKpi r WHERE r.acsAdvance = :acsAdvance")
    , @NamedQuery(name = "RptSmsKpi.findByBundleAdvance", query = "SELECT r FROM RptSmsKpi r WHERE r.bundleAdvance = :bundleAdvance")
    , @NamedQuery(name = "RptSmsKpi.findByTotalAdvance", query = "SELECT r FROM RptSmsKpi r WHERE r.totalAdvance = :totalAdvance")
    , @NamedQuery(name = "RptSmsKpi.findByTotalPaid", query = "SELECT r FROM RptSmsKpi r WHERE r.totalPaid = :totalPaid")
    , @NamedQuery(name = "RptSmsKpi.findByTotalFee", query = "SELECT r FROM RptSmsKpi r WHERE r.totalFee = :totalFee")
    , @NamedQuery(name = "RptSmsKpi.findByKpiSms", query = "SELECT r FROM RptSmsKpi r WHERE r.kpiSms = :kpiSms")
    , @NamedQuery(name = "RptSmsKpi.findByKpiLatest", query = "SELECT r FROM RptSmsKpi r order by r.kpiDatetime desc")
    , @NamedQuery(name = "RptSmsKpi.findByRangeKpiDatetime", query = "SELECT r FROM RptSmsKpi r WHERE r.kpiDatetime between ?1 and ?2")})
public class RptSmsKpi implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "kpi_datetime")
    private String kpiDatetime;

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "acs_advance")
    private BigDecimal acsAdvance;

    @Basic(optional = false)
    @NotNull
    @Column(name = "bundle_advance")
    private BigDecimal bundleAdvance;

    @Basic(optional = false)
    @NotNull
    @Column(name = "total_advance")
    private BigDecimal totalAdvance;

    @Basic(optional = false)
    @NotNull
    @Column(name = "total_paid")
    private BigDecimal totalPaid;

    @Basic(optional = false)
    @NotNull
    @Column(name = "total_fee")
    private BigDecimal totalFee;

    @Size(max = 200)
    @Column(name = "kpi_sms")
    private String kpiSms;

    public RptSmsKpi() {
    }

    public RptSmsKpi(String kpiDatetime) {
        this.kpiDatetime = kpiDatetime;
    }

    public RptSmsKpi(String kpiDatetime, BigDecimal acsAdvance, BigDecimal bundleAdvance, BigDecimal totalAdvance, BigDecimal totalPaid, BigDecimal totalFee) {
        this.kpiDatetime = kpiDatetime;
        this.acsAdvance = acsAdvance;
        this.bundleAdvance = bundleAdvance;
        this.totalAdvance = totalAdvance;
        this.totalPaid = totalPaid;
        this.totalFee = totalFee;
    }

    public String getKpiDatetime() {
        return kpiDatetime;
    }

    public void setKpiDatetime(String kpiDatetime) {
        this.kpiDatetime = kpiDatetime;
    }

    public BigDecimal getAcsAdvance() {
        return acsAdvance;
    }

    public void setAcsAdvance(BigDecimal acsAdvance) {
        this.acsAdvance = acsAdvance;
    }

    public BigDecimal getBundleAdvance() {
        return bundleAdvance;
    }

    public void setBundleAdvance(BigDecimal bundleAdvance) {
        this.bundleAdvance = bundleAdvance;
    }

    public BigDecimal getTotalAdvance() {
        return totalAdvance;
    }

    public void setTotalAdvance(BigDecimal totalAdvance) {
        this.totalAdvance = totalAdvance;
    }

    public BigDecimal getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(BigDecimal totalPaid) {
        this.totalPaid = totalPaid;
    }

    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    public String getKpiSms() {
        return kpiSms;
    }

    public void setKpiSms(String kpiSms) {
        this.kpiSms = kpiSms;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kpiDatetime != null ? kpiDatetime.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RptSmsKpi)) {
            return false;
        }
        RptSmsKpi other = (RptSmsKpi) object;
        if ((this.kpiDatetime == null && other.kpiDatetime != null) || (this.kpiDatetime != null && !this.kpiDatetime.equals(other.kpiDatetime))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "acs.entities.RptSmsKpi[ kpiDatetime=" + kpiDatetime + " ]";
    }

}
