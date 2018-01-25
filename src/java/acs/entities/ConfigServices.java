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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "config_services")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConfigServices.findAll", query = "SELECT c FROM ConfigServices c")
    , @NamedQuery(name = "ConfigServices.findByServiceId", query = "SELECT c FROM ConfigServices c WHERE c.serviceId = :serviceId")
    , @NamedQuery(name = "ConfigServices.findByServiceName", query = "SELECT c FROM ConfigServices c WHERE c.serviceName = :serviceName")
    , @NamedQuery(name = "ConfigServices.findByServiceDesc", query = "SELECT c FROM ConfigServices c WHERE c.serviceDesc = :serviceDesc")
    , @NamedQuery(name = "ConfigServices.findByServicePlateform", query = "SELECT c FROM ConfigServices c WHERE c.servicePlateform = :servicePlateform")
    , @NamedQuery(name = "ConfigServices.findByServiceValue", query = "SELECT c FROM ConfigServices c WHERE c.serviceValue = :serviceValue")
    , @NamedQuery(name = "ConfigServices.findByServiceKeyword", query = "SELECT c FROM ConfigServices c WHERE c.serviceKeyword = :serviceKeyword")
    , @NamedQuery(name = "ConfigServices.findByAmount", query = "SELECT c FROM ConfigServices c WHERE c.amount = :amount")
    , @NamedQuery(name = "ConfigServices.findByServiceFee", query = "SELECT c FROM ConfigServices c WHERE c.serviceFee = :serviceFee")
    , @NamedQuery(name = "ConfigServices.findByEventIds", query = "SELECT c FROM ConfigServices c WHERE c.eventIds = :eventIds")
    , @NamedQuery(name = "ConfigServices.findByValidityDays", query = "SELECT c FROM ConfigServices c WHERE c.validityDays = :validityDays")
    , @NamedQuery(name = "ConfigServices.findByServiceTypeId", query = "SELECT c FROM ConfigServices c WHERE c.serviceTypeId = :serviceTypeId")
    , @NamedQuery(name = "ConfigServices.findByServiceStatus", query = "SELECT c FROM ConfigServices c WHERE c.serviceStatus = :serviceStatus")
    , @NamedQuery(name = "ConfigServices.findByServiceFlag", query = "SELECT c FROM ConfigServices c WHERE c.serviceFlag = :serviceFlag")})
public class ConfigServices implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "service_id")
    private Integer serviceId;

    @Size(max = 15)
    @Column(name = "service_name")
    private String serviceName;

    @Size(max = 35)
    @Column(name = "service_desc")
    private String serviceDesc;

    @Size(max = 25)
    @Column(name = "service_plateform")
    private String servicePlateform;

    @Size(max = 25)
    @Column(name = "service_value")
    private String serviceValue;

    @Size(max = 25)
    @Column(name = "service_keyword")
    private String serviceKeyword;

    @Column(name = "amount")
    private Short amount;

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "service_fee")
    private Double serviceFee;

    @Size(max = 10)
    @Column(name = "event_ids")
    private String eventIds;

    @Column(name = "validity_days")
    private Integer validityDays;

    @Column(name = "service_type_id")
    private Integer serviceTypeId;

    @Basic(optional = false)
    @NotNull
    @Column(name = "service_status")
    private boolean serviceStatus;

    @Size(max = 15)
    @Column(name = "service_flag")
    private String serviceFlag;

    public ConfigServices() {
    }

    public ConfigServices(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public ConfigServices(Integer serviceId, boolean serviceStatus) {
        this.serviceId = serviceId;
        this.serviceStatus = serviceStatus;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceDesc() {
        return serviceDesc;
    }

    public void setServiceDesc(String serviceDesc) {
        this.serviceDesc = serviceDesc;
    }

    public String getServicePlateform() {
        return servicePlateform;
    }

    public void setServicePlateform(String servicePlateform) {
        this.servicePlateform = servicePlateform;
    }

    public String getServiceValue() {
        return serviceValue;
    }

    public void setServiceValue(String serviceValue) {
        this.serviceValue = serviceValue;
    }

    public String getServiceKeyword() {
        return serviceKeyword;
    }

    public void setServiceKeyword(String serviceKeyword) {
        this.serviceKeyword = serviceKeyword;
    }

    public Short getAmount() {
        return amount;
    }

    public void setAmount(Short amount) {
        this.amount = amount;
    }

    public Double getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(Double serviceFee) {
        this.serviceFee = serviceFee;
    }

    public String getEventIds() {
        return eventIds;
    }

    public void setEventIds(String eventIds) {
        this.eventIds = eventIds;
    }

    public Integer getValidityDays() {
        return validityDays;
    }

    public void setValidityDays(Integer validityDays) {
        this.validityDays = validityDays;
    }

    public Integer getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(Integer serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public boolean getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(boolean serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    public String getServiceFlag() {
        return serviceFlag;
    }

    public void setServiceFlag(String serviceFlag) {
        this.serviceFlag = serviceFlag;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (serviceId != null ? serviceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConfigServices)) {
            return false;
        }
        ConfigServices other = (ConfigServices) object;
        if ((this.serviceId == null && other.serviceId != null) || (this.serviceId != null && !this.serviceId.equals(other.serviceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "acs.entities.ConfigServices[ serviceId=" + serviceId + " ]";
    }
    
}
