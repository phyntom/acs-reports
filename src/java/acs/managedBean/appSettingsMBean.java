package acs.managedBean;

import acs.ejbs.ConfigGuiVariablesEJB;
import acs.entities.ConfigGuiVariables;
import java.io.Serializable;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.log4j.Logger;

@Named(value = "appSettingsMBean")
@ApplicationScoped
public class appSettingsMBean implements Serializable {

    private String applicationTitle = "Default";

    private String headerTitle = "Default";

    private String footerText = "Default";

    private String logoPath = "";

    @Inject
    private ConfigGuiVariablesEJB configGuiEJB;

    private static final Logger logger = Logger.getLogger("ACSReports");

    public appSettingsMBean() {

    }

    @PostConstruct
    private void init() {
        try {
            List<ConfigGuiVariables> guiconfigs = configGuiEJB.findAll();
            headerTitle=(guiconfigs.stream().filter(item -> item.getGuiVariableNum() == 1).findAny().orElse(new ConfigGuiVariables()).getGuiVariableValue());
            setFooterText(guiconfigs.stream().filter(item -> item.getGuiVariableNum() == 7).findAny().orElse(new ConfigGuiVariables()).getGuiVariableValue());
            applicationTitle=(guiconfigs.stream().filter(item -> item.getGuiVariableNum() == 1).findAny().orElse(new ConfigGuiVariables()).getGuiVariableValue());
            logoPath=(guiconfigs.stream().filter(item -> item.getGuiVariableNum() == 13).findAny().orElse(new ConfigGuiVariables()).getGuiVariableValue());
        } catch (Exception ex) {
            logger.error(ex.toString() + "|");
        }
    }

    public String getApplicationTitle() {
        return applicationTitle;
    }

    public void setApplicationTitle(String applicationTitle) {
        this.applicationTitle = applicationTitle;
    }

    public String getHeaderTitle() {
        return headerTitle;
    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle = headerTitle;
    }

    public String getFooterText() {
        return footerText;
    }

    public void setFooterText(String footerText) {
        this.footerText = footerText;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

}
