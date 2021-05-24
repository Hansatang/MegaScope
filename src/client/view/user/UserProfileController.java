package client.view.user;

import client.view.ViewHandler;
import client.viewmodel.user.UserProfileViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import shared.User;
import shared.util.EventType;

import java.beans.PropertyChangeEvent;
import java.io.File;

public class UserProfileController {
    private ViewHandler viewHandler;
    private UserProfileViewModel userProfileViewModel;
    private User userLoggedIn;

    @FXML
    private Label userCurrentFirstNameLabel;
    @FXML
    private Label userCurrentLastNameLabel;
    @FXML
    private Label userCurrentPhoneLabel;
    @FXML
    private Label userCurrentUsernameLabel;
    @FXML
    private Label userCurrentUsertypeLabel;
    @FXML
    private TextField firstnameTextField;
    @FXML
    private TextField lastnameTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private TextField setPasswordField;
    @FXML
    private TextField confirmPasswordField;
    @FXML
    private CheckBox vipCheckBox;
    @FXML
    private Label saveInfoLabel;
    @FXML
    private ImageView logoView;
    @FXML
    private ImageView identityCard;
    @FXML
    private ImageView changeInfo;


    public void init(UserProfileViewModel userProfileViewModel,
                     ViewHandler viewHandler, User userLoggedIn) {
        userProfileViewModel.clearMessages();
        this.viewHandler = viewHandler;
        this.userProfileViewModel = userProfileViewModel;
        this.userLoggedIn = userLoggedIn;

        vipCheckBox.setVisible(!(userLoggedIn.getUserType().equals("ADMIN")));

        userCurrentUsernameLabel.textProperty()
                .bindBidirectional(userProfileViewModel.currentUsernameProperty());
        userCurrentFirstNameLabel.textProperty()
                .bindBidirectional(userProfileViewModel.currentFirstnameProperty());
        userCurrentLastNameLabel.textProperty()
                .bindBidirectional(userProfileViewModel.currentLastnameProperty());
        userCurrentPhoneLabel.textProperty()
                .bindBidirectional(userProfileViewModel.currentPhoneNumberProperty());
        userCurrentUsertypeLabel.textProperty()
                .bindBidirectional(userProfileViewModel.currentUsertypeProperty());
        firstnameTextField.textProperty()
                .bindBidirectional(userProfileViewModel.newFirstNameProperty());
        lastnameTextField.textProperty()
                .bindBidirectional(userProfileViewModel.newLastNameProperty());
        phoneTextField.textProperty()
                .bindBidirectional(userProfileViewModel.newPhoneNumberProperty());
        usernameTextField.textProperty()
                .bindBidirectional(userProfileViewModel.newUsernameProperty());
        setPasswordField.textProperty()
                .bindBidirectional(userProfileViewModel.newPasswordProperty());
        confirmPasswordField.textProperty()
                .bindBidirectional(userProfileViewModel.confirmPasswordProperty());
        vipCheckBox.selectedProperty().bindBidirectional(userProfileViewModel.vipCheckProperty());
        saveInfoLabel.textProperty().bindBidirectional(userProfileViewModel.saveInfoLabelProperty());
        userProfileViewModel.updateCurrentInfo(userLoggedIn);
        try {
            File logoFile = new File("images/logo.png");
            Image logo = new Image(logoFile.toURI().toString());
            logoView.setImage(logo);
            File idCardFile = new File("images/idBackground.jpg");
            Image idCard = new Image(idCardFile.toURI().toString());
            identityCard.setImage(idCard);
            File infoFile = new File("images/changeInfo.jpg");
            Image infoChange = new Image(infoFile.toURI().toString());
            changeInfo.setImage(infoChange);
        } catch (NullPointerException e) {
            System.out.println("image problem");
        }
        userProfileViewModel.addPropertyChangeListener(EventType.SAVENEWINFO_RESULT.toString(),
                this::newSavedInfo);
    }

    private void newSavedInfo(PropertyChangeEvent event) {
        System.out.println("hello");
        User temp = (User) event.getNewValue();
        if (temp != null) {
            System.out.println("Every " + temp);
            this.userLoggedIn = temp;
            userProfileViewModel.updateCurrentInfo(userLoggedIn);
        }
    }

    public void saveButtonOnAction() {
        userProfileViewModel.clearMessages();
        userProfileViewModel.saveAccount(userLoggedIn);

    }



    public void closeOnAction() {
        viewHandler.showFrontPage(userLoggedIn);
    }
}
