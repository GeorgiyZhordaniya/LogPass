package logpass.view;

import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.stage.WindowEvent;
import logpass.LogPassMain;
import logpass.model.SiteInfo;

import javax.xml.bind.JAXBException;
import java.util.Optional;

public class SiteInfoController {

    private LogPassMain logPassMain;

    @FXML
    private TableView<SiteInfo> siteTable;

    @FXML
    private TableColumn<SiteInfo, String> siteNameCol;

    @FXML
    private TableColumn<SiteInfo, String> loginCol;

    @FXML
    private TextField siteNameField;
    @FXML
    private TextField siteURLField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField loginField;
    @FXML
    private TextField passwordField;

    @FXML
    private void initialize() {
        siteNameCol.setCellValueFactory(cellData -> cellData.getValue().SiteNameProperty());
        loginCol.setCellValueFactory(cellData -> cellData.getValue().LoginProperty());

        showSiteInfo(null);

        siteTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldSite, newSite) -> showSiteInfo(newSite));
    }

    public void setMain(LogPassMain logPassMain) {
        this.logPassMain = logPassMain;

        this.logPassMain.getPrimaryStage().getScene().getWindow().addEventFilter
                (WindowEvent.WINDOW_CLOSE_REQUEST, this::closeWindowEvent);

        siteTable.setItems(logPassMain.getSites());
    }

    private void showSiteInfo(SiteInfo siteInfo) {
        if (siteInfo != null) {
            siteNameField.setText(siteInfo.getSiteName());
            siteURLField.setText(siteInfo.getSiteURL());
            emailField.setText(siteInfo.getEmail());
            loginField.setText(siteInfo.getLogin());
            passwordField.setText(siteInfo.getPassword());
        }
        else {
            siteNameField.setText("");
            siteURLField.setText("");
            emailField.setText("");
            loginField.setText("");
            passwordField.setText("");
        }
    }

    @FXML
    private void deleteButtonClicked() {
       int selectedSiteIndex = siteTable.getSelectionModel().getSelectedIndex();
       if (selectedSiteIndex != -1) {
           siteTable.getItems().remove(selectedSiteIndex);
           //if (logPassMain.getDataChanged() == false)
           logPassMain.setDataChanged(true);
       }
    }

    @FXML
    private void addButtonClicked() {
        SiteInfo siteInfo = new SiteInfo();
        logPassMain.addSiteWindow(siteInfo, true);
        //siteInfo = null;
    }

    @FXML
    private void editButtonClicked() {
        SiteInfo selectedSite = siteTable.getSelectionModel().getSelectedItem();
        if (selectedSite != null) {
            logPassMain.addSiteWindow(selectedSite, false);
            showSiteInfo(selectedSite);
        }
        //selectedSite = null;
    }

    ButtonType dontSave = new ButtonType("Don't save", ButtonBar.ButtonData.NO);
    ButtonType save = new ButtonType("Save", ButtonBar.ButtonData.YES);

    public void closeWindowEvent(WindowEvent event) {
        if (logPassMain.getDataChanged()) {

            Alert alert = new Alert(Alert.AlertType.NONE,
                    "Close without saving?", dontSave, save, ButtonType.CANCEL);
            alert.setTitle("Exit");
            alert.initOwner(logPassMain.getPrimaryStage().getOwner());
            Optional<ButtonType> button = alert.showAndWait();

            if (button.isPresent()) {
                if (button.get().equals(ButtonType.CANCEL))
                    event.consume();

                if (button.get().equals(save)){
                    logPassMain.saveInformation();
                }
            }
        }
    }
}
