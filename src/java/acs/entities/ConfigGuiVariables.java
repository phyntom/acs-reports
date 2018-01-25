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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author aimable
 */
@Entity
@Table(name = "config_gui_variables")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConfigGuiVariables.findAll", query = "SELECT c FROM ConfigGuiVariables c")
    , @NamedQuery(name = "ConfigGuiVariables.findByGuiVariableNum", query = "SELECT c FROM ConfigGuiVariables c WHERE c.guiVariableNum = :guiVariableNum")
    , @NamedQuery(name = "ConfigGuiVariables.findByGuiVariableName", query = "SELECT c FROM ConfigGuiVariables c WHERE c.guiVariableName = :guiVariableName")
    , @NamedQuery(name = "ConfigGuiVariables.findByGuiVariableDescription", query = "SELECT c FROM ConfigGuiVariables c WHERE c.guiVariableDescription = :guiVariableDescription")
    , @NamedQuery(name = "ConfigGuiVariables.findByGuiVariableValue", query = "SELECT c FROM ConfigGuiVariables c WHERE c.guiVariableValue = :guiVariableValue")
    , @NamedQuery(name = "ConfigGuiVariables.findByGuiNum", query = "SELECT c FROM ConfigGuiVariables c WHERE c.guiNum = :guiNum")
    , @NamedQuery(name = "ConfigGuiVariables.findByGuiVariableStatus", query = "SELECT c FROM ConfigGuiVariables c WHERE c.guiVariableStatus = :guiVariableStatus")})
public class ConfigGuiVariables implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "gui_variable_num")
    private Integer guiVariableNum;

    @Size(max = 25)
    @Column(name = "gui_variable_name")
    private String guiVariableName;

    @Size(max = 45)
    @Column(name = "gui_variable_description")
    private String guiVariableDescription;

    @Size(max = 65)
    @Column(name = "gui_variable_value")
    private String guiVariableValue;

    @Column(name = "gui_num")
    private Integer guiNum;

    @Column(name = "gui_variable_status")
    private Integer guiVariableStatus;


    public ConfigGuiVariables() {
    }

    public ConfigGuiVariables(Integer guiVariableNum) {
        this.guiVariableNum = guiVariableNum;
    }

    public Integer getGuiVariableNum() {
        return guiVariableNum;
    }

    public void setGuiVariableNum(Integer guiVariableNum) {
        this.guiVariableNum = guiVariableNum;
    }

    public String getGuiVariableName() {
        return guiVariableName;
    }

    public void setGuiVariableName(String guiVariableName) {
        this.guiVariableName = guiVariableName;
    }

    public String getGuiVariableDescription() {
        return guiVariableDescription;
    }

    public void setGuiVariableDescription(String guiVariableDescription) {
        this.guiVariableDescription = guiVariableDescription;
    }

    public String getGuiVariableValue() {
        return guiVariableValue;
    }

    public void setGuiVariableValue(String guiVariableValue) {
        this.guiVariableValue = guiVariableValue;
    }

    public Integer getGuiNum() {
        return guiNum;
    }

    public void setGuiNum(Integer guiNum) {
        this.guiNum = guiNum;
    }

    public Integer getGuiVariableStatus() {
        return guiVariableStatus;
    }

    public void setGuiVariableStatus(Integer guiVariableStatus) {
        this.guiVariableStatus = guiVariableStatus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (guiVariableNum != null ? guiVariableNum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConfigGuiVariables)) {
            return false;
        }
        ConfigGuiVariables other = (ConfigGuiVariables) object;
        if ((this.guiVariableNum == null && other.guiVariableNum != null) || (this.guiVariableNum != null && !this.guiVariableNum.equals(other.guiVariableNum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "acs.entities.ConfigGuiVariables[ guiVariableNum=" + guiVariableNum + " ]";
    }

}
