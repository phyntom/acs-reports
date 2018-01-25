package acs.managedBean;

import acs.ejbs.UsersEJB;
import acs.entities.User;
import acs.utilities.EncryptDecryptSecret;
import acs.utilities.Notification;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

@Named(value = "user")
@SessionScoped
public class userMBean implements Serializable {

    private User user = new User();

    private User loggedInUser;

    private String userName;

    private String password;

    private boolean loggedIn = false;

    private static final Logger log = Logger.getLogger("ACSReports");

    @Inject
    private UsersEJB userEJB;

    private FacesContext context;

    public String login() {
        try {
            if ((userName == null || userName.equals("")) || password == null || password.equals("")) {
                Notification.addErrorMessage("Username or Password cannot be null");
                return "Login";
            }
            else {
                List<User> searchedUsers = userEJB.findByUserNameAndPassword(userName, EncryptDecryptSecret.encrypt(password));
                if (searchedUsers.isEmpty()) {
                    Notification.addErrorMessage("Invalid username or password");
                    return "Login";
                }
                else {
                    loggedInUser = searchedUsers.get(0);
                    setLoggedIn(true);
                    context = FacesContext.getCurrentInstance();
                    context.getExternalContext().getSessionMap().put("user", loggedInUser);
                    log.info("user " + loggedInUser + " successfull logged in at " + new Date());
                    return "RevenueDaily?faces-redirect=true";
                }
            }
        } catch (Exception e) {
            Notification.addErrorMessage("Cannot Login. Error " + e.getMessage());
            log.error("Cannot Login. The error occurs |", e);
            return "Login";
        }

    }

    public void checkIfuserIsAuthenticated() {
        try {
            context = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
            if ((User) session.getAttribute("user") == null) {
                String viewId = context.getViewRoot().getViewId();
                if (viewId.equals("/RevenueDaily.xhtml")) {
                    context.getExternalContext().redirect("Login.xhtml?faces-redirect=true");
                    context.responseComplete();
                }
                else if (!viewId.equals("/RevenueDaily.xhtml")) {
                    context.getExternalContext().redirect("Login.xhtml?faces-redirect=true");
                    context.responseComplete();
                }
            }
        } // end of try // end of try
        catch (Exception ex) {
            log.error("Cannot check if user is authenticated", ex.getCause());
        }
    }

    public void logout() {
        try {
            context = FacesContext.getCurrentInstance();
            HttpSession currentSession = (HttpSession) context.getExternalContext().getSession(true);
            String viewId = context.getViewRoot().getViewId();
            if (!viewId.equals("/Login.xhtml")) {
                context.getExternalContext().redirect("Login.xhtml?faces-redirect=true");
                context.responseComplete();
            }
            currentSession.invalidate();
            setLoggedIn(false);
        } catch (IOException ex) {
            log.error("The error ", ex);
        }
    }

    public String changePassword() {
        return null;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

}
