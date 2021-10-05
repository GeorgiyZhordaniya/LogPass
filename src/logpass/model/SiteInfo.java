package logpass.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SiteInfo {
    private StringProperty siteName;
    private StringProperty siteURL;
    private StringProperty email;
    private StringProperty login;
    private StringProperty password;

    public SiteInfo(){
        siteName = new SimpleStringProperty("");
        siteURL = new SimpleStringProperty("");
        email = new SimpleStringProperty("");
        login = new SimpleStringProperty("");
        password = new SimpleStringProperty("");
    }

    public String getSiteName() {
        return siteName.get();
    }

    public StringProperty SiteNameProperty() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName.set(siteName);
    }

    public String getSiteURL() {
        return siteURL.get();
    }

    public StringProperty SiteURLProperty() {
        return siteURL;
    }

    public void setSiteURL(String siteURL) {
        this.siteURL.set(siteURL);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty EmailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getLogin() {
        return login.get();
    }

    public StringProperty LoginProperty() {
        return login;
    }

    public void setLogin(String login) {
        this.login.set(login);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty PasswordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

}
