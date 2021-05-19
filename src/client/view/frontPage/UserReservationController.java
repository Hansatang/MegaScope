package client.view.frontPage;

import client.view.CustomTextFieldTableCell;
import client.view.ViewHandler;
import client.viewmodel.frontPage.UserReservationViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import shared.User;
import shared.UserReservationInfo;

import java.util.ArrayList;

public class UserReservationController {

    @FXML
    private Label usernameLabel;
    @FXML
    private Button cancelReservationButton;
    @FXML
    private Button goBackButton;

    @FXML
    private TableView<UserReservationInfo> reservationTableView;
    @FXML
    private TableColumn<Object, String> reservationIdCol;
    @FXML
    private TableColumn<Object, String> movieTitleCol;
    @FXML
    private TableColumn<Object, String> timeCol;
    @FXML
    private TableColumn<Object, String> dateCol;
    @FXML
    private TableColumn<Object, String> seatCol;

    private UserReservationViewModel userReservationViewModel;
    private ViewHandler viewHandler;
    private User userLoggedIn;
    private UserReservationInfo selectedInfo;

    public void init(UserReservationViewModel userReservationViewModel, ViewHandler viewHandler, User userLoggedIn){
        this.userReservationViewModel = userReservationViewModel;
        userReservationViewModel.getUserReservations(userLoggedIn);

        this.viewHandler = viewHandler;

        reservationTableView.itemsProperty()
                .bindBidirectional(userReservationViewModel.observableItemsProperty());
        reservationIdCol.setCellValueFactory(new PropertyValueFactory<>("reservation_id"));
        movieTitleCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time_show"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date_show"));
        seatCol.setCellValueFactory(new PropertyValueFactory<>("seat_id"));

        this.userLoggedIn = userLoggedIn;
        usernameLabel.setText("Logged in as " + userLoggedIn.getUsername());
    }



    public void onCancelReservation(ActionEvent actionEvent) {

        if(selectedInfo != null) {
            userReservationViewModel.cancelReservation(selectedInfo);
        }
    }

    public void onGoBack(ActionEvent actionEvent) {
        viewHandler.showFrontPage(userLoggedIn);
    }

    public void setSelected(MouseEvent mouseEvent) {
        if(reservationTableView.getSelectionModel().getSelectedItem() != null){
            int index = reservationTableView.getSelectionModel().getSelectedIndex();
            selectedInfo = reservationTableView.getItems().get(index);
            System.out.println(selectedInfo);
        }
    }
}