/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acs.entities;

import java.io.Serializable;
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
 * @author Aimable
 */
@Entity
@Table(name = "config_eligibility_status")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConfigEligibilityStatus.findAll", query = "SELECT c FROM ConfigEligibilityStatus c"),
    @NamedQuery(name = "ConfigEligibilityStatus.findByEligibilityStatusId", query = "SELECT c FROM ConfigEligibilityStatus c WHERE c.eligibilityStatusId = :eligibilityStatusId"),
    @NamedQuery(name = "ConfigEligibilityStatus.findByEligibilityStatusDesc", query = "SELECT c FROM ConfigEligibilityStatus c WHERE c.eligibilityStatusDesc = :eligibilityStatusDesc")})
public class ConfigEligibilityStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "eligibility_status_id")
    private Long eligibilityStatusId;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "eligibility_status_desc")
    private String eligibilityStatusDesc;

    public ConfigEligibilityStatus() {
    }

    public ConfigEligibilityStatus(Long eligibilityStatusId) {
        this.eligibilityStatusId = eligibilityStatusId;
    }

    public ConfigEligibilityStatus(Long eligibilityStatusId, String eligibilityStatusDesc) {
        this.eligibilityStatusId = eligibilityStatusId;
        this.eligibilityStatusDesc = eligibilityStatusDesc;
    }

    public Long getEligibilityStatusId() {
        return eligibilityStatusId;
    }

    public void setEligibilityStatusId(Long eligibilityStatusId) {
        this.eligibilityStatusId = eligibilityStatusId;
    }

    public String getEligibilityStatusDesc() {
        if (eligibilityStatusDesc == null) {
            eligibilityStatusDesc = "Unknown";
        }
        return eligibilityStatusDesc;
    }

    public void setEligibilityStatusDesc(String eligibilityStatusDesc) {
        this.eligibilityStatusDesc = eligibilityStatusDesc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (eligibilityStatusId != null ? eligibilityStatusId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConfigEligibilityStatus)) {
            return false;
        }
        ConfigEligibilityStatus other = (ConfigEligibilityStatus) object;
        if ((this.eligibilityStatusId == null && other.eligibilityStatusId != null) || (this.eligibilityStatusId != null && !this.eligibilityStatusId.equals(other.eligibilityStatusId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "" + this.eligibilityStatusDesc;
    }

}
