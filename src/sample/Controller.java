package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller {

    public Automata A = new Automata(0);

    private ObservableList<Price> menu = FXCollections.observableArrayList();

    @FXML
    private Label state;

    @FXML
    private Label choosenDrink;

    @FXML
    private TableView<Price> tvMenu;

    @FXML
    private TableColumn<Price, String> tcDrink;

    @FXML
    private TableColumn<Price, Integer> tcPrice;

    @FXML
    private TextField payCash;

    @FXML
    private TextField totalCash;

    @FXML
    private Label debug;

    @FXML
    public void initialize() {
        state.setText(A.getState().toString());
        tcDrink.setCellValueFactory(new PropertyValueFactory<Price, String>("drink"));
        tcPrice.setCellValueFactory(new PropertyValueFactory<Price, Integer>("price"));
        tvMenu.setItems(menu);
        state.setText(A.getState().toString());
        //totalCash.setText(A.getCash().toString());
        totalCash.setText(A.getCash().toString());
    }

    @FXML
    public void on() {
        A.addToMenu("tea", 10);
        A.addToMenu("coffee", 15);
        A.addToMenu("beer", 50);
        A.addToMenu("vodka", 45);
        A.printMenu().forEach((drink, price) -> {
            menu.add(new Price(drink, price));
        });
        A.on();
        state.setText(A.getState().toString());
        totalCash.setText(A.getCash().toString());
    }

    @FXML
    public void chooseDrink(){
        Price chDrink=tvMenu.getSelectionModel().getSelectedItem();
        choosenDrink.setText(chDrink.getDrink());

    }

    @FXML
    public void putMoney(){
        A.coin(Integer.parseInt(payCash.getText()));
        state.setText(A.getState().toString());
        totalCash.setText(A.getCash().toString());

    }

    @FXML
    public void cook(){
        Integer test=A.choice(choosenDrink.getText());
        if (test<0){
            debug.setText("!!"+state.getText()+"  !!"+choosenDrink.getText());
            return;
        }
        state.setText(A.getState().toString());
        totalCash.setText(A.getCash().toString());

    }

}
