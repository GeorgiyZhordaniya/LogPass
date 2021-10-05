package logpass.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logpass.LogPassMain;
import logpass.model.SiteInfo;

public class AddSiteController {

    @FXML
    private TextField siteNameField;

    @FXML
    private TextField siteURLField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField loginField;

    private LogPassMain logPassMain;
    private Stage addSiteStage;
    private SiteInfo siteInfo;
    private boolean newSite;

    @FXML
    private void initialize() {

    }

    public void setAddSiteStage(Stage addSiteStage) {
        this.addSiteStage = addSiteStage;
    }

    public void setMain(LogPassMain logPassMain){
        this.logPassMain = logPassMain;
    }

    public void setNewSite(boolean newSite) {
        this.newSite = newSite;
    }

    public void setSiteInfo(SiteInfo siteInfo) {
        this.siteInfo = siteInfo;

        siteNameField.setText(siteInfo.getSiteName());
        siteURLField.setText(siteInfo.getSiteURL());
        emailField.setText(siteInfo.getEmail());
        loginField.setText(siteInfo.getLogin());
        passwordField.setText(siteInfo.getPassword());
    }

    @FXML
    private void saveButtonClicked() {
        siteInfo.setSiteName(siteNameField.getText());
        siteInfo.setSiteURL(siteURLField.getText());
        siteInfo.setEmail(emailField.getText());
        siteInfo.setLogin(loginField.getText());
        siteInfo.setPassword(passwordField.getText());

        if (newSite)
            logPassMain.getSites().add(siteInfo);
        //if (logPassMain.getDataChanged() == false)
        logPassMain.setDataChanged(true);
        addSiteStage.close();
    }

    @FXML
    private void cancelButtonClick() {
        addSiteStage.close();
    }
}