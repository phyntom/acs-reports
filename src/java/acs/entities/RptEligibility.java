/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acs.entities;

import acs.utilities.DateConverter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
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
@Table(name = "rpt_eligibility")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RptEligibility.findAll", query = "SELECT r FROM RptEligibility r")
    ,
    @NamedQuery(name = "RptEligibility.findByRptDatetime", query = "SELECT r FROM RptEligibility r WHERE r.rptDatetime = :rptDatetime")
    ,
    @NamedQuery(name = "RptEligibility.findByEligibilityCode", query = "SELECT r FROM RptEligibility r WHERE r.eligibilityCode = :eligibilityCode")
    ,
    @NamedQuery(name = "RptEligibility.findByRequestTotalCount", query = "SELECT r FROM RptEligibility r WHERE r.requestTotalCount = :requestTotalCount")
    ,
    @NamedQuery(name = "RptEligibility.findByRequestTotalCount", query = "SELECT r FROM RptEligibility r WHERE r.requestTotalCount = :requestTotalCount")
    ,
    @NamedQuery(name = "RptEligibility.findByRangeRptDatetime", query = "SELECT r FROM RptEligibility r WHERE r.rptDatetime between ?1 and ?2")
    ,
    @NamedQuery(name = "RptEligibility.findByRptDatetimeStatus", query = "SELECT r FROM RptEligibility r WHERE r.rptDatetime between ?1 and ?2 and r.eligibilityCode = ?3")})
public class RptEligibility implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "rpt_datetime")
    @Convert(converter = LocalDateConverter.class)
    private LocalDate rptDatetime;

    @Id
    @ManyToOne
    @JoinColumn(name = "eligibility_code")
    private ConfigEligibilityStatus eligibilityCode;

    @Basic(optional = false)
    @NotNull
    @Column(name = "request_total_count")
    private Long requestTotalCount;

    @Transient
    private String dateString;

    public RptEligibility() {
    }

    public LocalDate getRptDatetime() {
        return rptDatetime;
    }

    public void setRptDatetime(LocalDate rptDatetime) {
        this.rptDatetime = rptDatetime;
    }

    public ConfigEligibilityStatus getEligibilityCode() {
        return eligibilityCode;
    }

    public void setEligibilityCode(ConfigEligibilityStatus eligibilityCode) {
        this.eligibilityCode = eligibilityCode;
    }

    public Long getRequestTotalCount() {
        return requestTotalCount;
    }

    public void setRequestTotalCount(Long requestTotalCount) {
        this.requestTotalCount = requestTotalCount;
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
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.rptDatetime);
        hash = 37 * hash + Objects.hashCode(this.eligibilityCode);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RptEligibility other = (RptEligibility) obj;
        if (!Objects.equals(this.rptDatetime, other.rptDatetime)) {
            return false;
        }
        if (!Objects.equals(this.eligibilityCode, other.eligibilityCode)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RptEligibility{" + "rptDatetime=" + rptDatetime + ", eligibilityCode=" + eligibilityCode + '}';
    }

}
