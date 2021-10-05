package logpass;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import logpass.model.SiteInfoXML;
import logpass.view.AddSiteController;
import logpass.view.SiteInfoController;
import logpass.model.SiteInfo;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;


public class LogPassMain extends Application {

    private Stage primaryStage;

    private ObservableList<SiteInfo> siteList= FXCollections.observableArrayList();

    private File saveFile = new File(System.getProperty("user.dir") + "/save.xml");

    private boolean dataChanged = false;

    public ObservableList<SiteInfo> getSites() {
        return siteList;
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            if (!saveFile.exists()){
                saveInformation();
            }
            else {
                loadInformation();
            }

            this.primaryStage = primaryStage;
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(LogPassMain.class.getResource("view/MainWindow.fxml"));
            Parent root = loader.load();
            primaryStage.setTitle("LogPass");
            primaryStage.setScene(new Scene(root));

            SiteInfoController controller = loader.getController();
            controller.setMain(this);

            primaryStage.show();
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void addSiteWindow(SiteInfo siteInfo, boolean newSite) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(LogPassMain.class.getResource("view/AddSiteWindow.fxml"));
            Pane addPane = loader.load();

            Stage addStage = new Stage();
            addStage.setTitle("Add/Edit site info");
            addStage.initOwner(primaryStage);
            addStage.initModality(Modality.WINDOW_MODAL);
            Scene addScene = new Scene(addPane);
            addStage.setScene(addScene);

            AddSiteController controller = loader.getController();
            controller.setAddSiteStage(addStage);
            controller.setMain(this);
            controller.setNewSite(newSite);
            controller.setSiteInfo(siteInfo);

            addStage.showAndWait();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadInformation() throws JAXBException {
        JAXBContext jabx = JAXBContext.newInstance(SiteInfoXML.class);
        Unmarshaller unmarshaller = jabx.createUnmarshaller();
        SiteInfoXML siteInfoXML = (SiteInfoXML) unmarshaller.unmarshal(saveFile);

        if(siteInfoXML.getSiteList() != null) {
            siteList.clear();
            siteList.addAll(siteInfoXML.getSiteList());
        }
    }

    public void saveInformation() {
        try {
            JAXBContext jabx = JAXBContext.newInstance(SiteInfoXML.class);
            Marshaller marshaller = jabx.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            SiteInfoXML siteInfoXML = new SiteInfoXML();
            siteInfoXML.setSiteList(siteList);

            marshaller.marshal(siteInfoXML, saveFile);
        }
        catch (Exception e) {
            System.out.println("Error while saving");
        }
    }

    public boolean getDataChanged() {
        return dataChanged;
    }

    public void setDataChanged(boolean dataChanged) {
        this.dataChanged = dataChanged;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
