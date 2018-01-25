/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acs.utilities;

import acs.ejbs.ConfigEligibilityStatusEJB;
import acs.entities.ConfigEligibilityStatus;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author root
 */
@FacesConverter(value = "EligibilityStatusConverter", forClass = ConfigEligibilityStatus.class)
public class EligibilityStatusConverter implements Converter {

    @EJB
    private final ConfigEligibilityStatusEJB configEligibilityEJB = lookupConfigEligibilityStatusEJBBean();

    private InitialContext initialContext;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null) {
            FacesMessage message;
            message = new FacesMessage("Conversion error");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ConverterException(message);
        }
        return configEligibilityEJB.find(Long.valueOf(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        return ((ConfigEligibilityStatus) o).getEligibilityStatusId().toString();
    }

    private ConfigEligibilityStatusEJB lookupConfigEligibilityStatusEJBBean() {
        try {
            initialContext = new InitialContext();
            return (ConfigEligibilityStatusEJB) initialContext.lookup("java:global/Reports/ConfigEligibilityStatusEJB");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
