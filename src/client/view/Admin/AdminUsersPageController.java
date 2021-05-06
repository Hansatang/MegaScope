package client.view.Admin;

import client.model.UserModel;
import client.view.CustomTextFieldTableCell;
import client.view.ViewHandler;

import client.viewmodel.admin.AdminViewModelUsers;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import shared.NewRegisteredUser;

import java.beans.PropertyChangeEvent;

public class AdminUsersPageController {
    private AdminViewModelUsers adminViewModelUsers;
    private UserModel userModel;
    private ViewHandler viewHandler;
    @FXML
    public TextField searchBar;
    @FXML
    private TableView<NewRegisteredUser> userTableView;
    @FXML
    private TableColumn<Object, String> usernameCol;
    @FXML
    private TableColumn<Object, String> firstNameCol;
    @FXML
    private TableColumn<Object, String> lastNameCol;
    @FXML
    private TableColumn<Object, String> phoneNoCol;

    private NewRegisteredUser userLoggedIn;


    public void init(AdminViewModelUsers adminViewModelUsers, ViewHandler viewHandler, NewRegisteredUser userLoggedIn) {
        this.adminViewModelUsers = adminViewModelUsers;
        this.viewHandler = viewHandler;
        this.userLoggedIn = userLoggedIn;
        adminViewModelUsers.getUsers();
        userTableView.itemsProperty().bindBidirectional(adminViewModelUsers.observableItemsProperty());
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        phoneNoCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        searchBar.textProperty()
                .bindBidirectional(adminViewModelUsers.searchPhraseProperty());

        adminViewModelUsers.addPropertyChangeListener("Update", this::update);
        userTableView.setItems(adminViewModelUsers.getItems());
        setSelectedUser();

    }

    private void update(PropertyChangeEvent event) {
        System.out.println("Upadate Users");
        userTableView.setItems(adminViewModelUsers.getItems());
    }


    public void onBanAction(javafx.event.ActionEvent event) {
    }

    public void onBackAction(javafx.event.ActionEvent event) {
        viewHandler.showFrontPage(userLoggedIn);
    }

    private void setSelectedUser() {
        userTableView.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener() {
                    public void changed(ObservableValue observableValue, Object oldValue,
                                        Object newValue) {
                        if (userTableView.getSelectionModel().getSelectedItem() != null) {
                            int index = userTableView.getSelectionModel().getSelectedIndex();

                            System.out.println(userTableView.getItems().get(index));
                        }
                    }
                });
    }

    public void Search(ActionEvent event) {
        adminViewModelUsers.search();
    }
}
