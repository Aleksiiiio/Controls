package pl.gornik.controls;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.*;

public class Controller implements Initializable {

    @FXML
    private Slider mySlider;

    @FXML
    private Rectangle myRectangle;

    @FXML
    private TextField txtSlider;

    @FXML
    private ScrollBar myScroll;

    @FXML
    private Ellipse myEllipse;

    @FXML
    private Label lblScroll;

    @FXML
    private CheckBox chbRmf;

    @FXML
    private CheckBox chbLel;

    @FXML
    private CheckBox chbEsk;

    @FXML
    private CheckBox chbMar;

    @FXML
    private TextArea myArea;

    @FXML
    private ListView<String> myList;

    @FXML
    private ChoiceBox<String> myChoice;

    @FXML
    private ComboBox<String> myCombo;

    @FXML
    private Label lblChoice;

    @FXML
    private Label lblCombo;

    private List<String> radio;

    @FXML
    private Spinner<Integer> spiMin;

    @FXML
    private Spinner<Integer> spiMax;

    @FXML
    private Label lblInfo;

    @FXML
    private Label lblNumber;

    @FXML
    private Button btnRandom;
    @FXML
    private Spinner<Integer> spiNum;

    @FXML
    private Button btnRandom1;

    @FXML
    private Label lblInfo1;

    @FXML
    private Label lblNumber1;

    @FXML
    private ColorPicker cpColor;

    @FXML
    private Label lblColor;

    @FXML
    private Rectangle colorRec;
    Random random;

    public int min = 1,max = 101;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Slider
        mySlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                txtSlider.setText(String.valueOf((int) mySlider.getValue())+"%");
                myRectangle.setHeight(4 * mySlider.getValue());
                myRectangle.setWidth(6 * mySlider.getValue());
            }
        });

        txtSlider.setOnAction(actionEvent -> {
            mySlider.setValue(Double.parseDouble(txtSlider.getText()));
        });


        //Scrollbar
        myScroll.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                lblScroll.setText(String.valueOf((int) myScroll.getValue())+ "px");
                myEllipse.setLayoutY(80 + myScroll.getValue());
            }
        });

        //CheckBox i ListView
        chbMar.setOnAction(actionEvent -> addValueToArea(chbMar));
        chbRmf.setOnAction(actionEvent -> addValueToArea(chbRmf));
        chbLel.setOnAction(actionEvent -> addValueToArea(chbLel));
        chbEsk.setOnAction(actionEvent -> addValueToArea(chbEsk));

        radio = Arrays.asList(chbMar.getText(),chbRmf.getText(),chbLel.getText(),chbEsk.getText());
//        myChoice.getItems().add(chbMar.getText());
        myChoice.getItems().addAll(radio);
        myCombo.getItems().addAll(radio);
        myChoice.setOnAction(actionEvent -> lblChoice.setText(myChoice.getValue()));
        myCombo.setOnAction(actionEvent -> lblCombo.setText(myCombo.getValue()));

        //Spinner

        SpinnerValueFactory minValue = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,100,1);
        spiMin.setValueFactory(minValue);
        SpinnerValueFactory maxValue = new SpinnerValueFactory.IntegerSpinnerValueFactory(101,500,101);
        spiMax.setValueFactory(maxValue);

        min = spiMin.getValue();
        max = spiMax.getValue();

        spiMin.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                min = spiMin.getValue();
                lblInfo.setText("Generowanie liczb z zakresu od "+min+" do "+max);
            }
        });
        spiMax.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                max = spiMax.getValue();
                lblInfo.setText("Generowanie liczb z zakresu od "+min+" do "+max);
            }
        });

        btnRandom.setOnAction(actionEvent -> {
            lblInfo.setText("Generowanie liczb z zakresu od "+spiMin.getValue()+" do "+spiMax.getValue());
            lblNumber.setText(String.valueOf(generateRandomNumber(spiMin.getValue(),spiMax.getValue())));
        });

        SpinnerValueFactory numValue = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 50,1);
        spiNum.setValueFactory(numValue);

        btnRandom1.setOnAction(actionEvent -> {
            ArrayList<Integer> randomNumber = new ArrayList<>();
            lblInfo1.setText("Generowanie liczb z zakresu od "+spiMin.getValue()+" do "+spiMax.getValue());
            for (int i = 0; i < spiNum.getValue();i++){
                randomNumber.add(generateRandomNumber(spiMin.getValue(),spiMax.getValue()));
            }
            lblNumber1.setText(String.valueOf(randomNumber));
        });


        //Color picker
        cpColor.setOnAction(actionEvent -> {
            Color color = cpColor.getValue();
            colorRec.setFill(color);
            colorRec.setStroke(color.invert());
            char[] chars = String.valueOf(cpColor.getValue()).substring(2,8).toCharArray();
            lblColor.setText("#" + String.valueOf(chars));
        });

    }

        public int generateRandomNumber(int min, int max){
        Random random = new Random();
        return random.nextInt(min,max);
        }

        public void addValueToArea(CheckBox chb){
            if(chb.isSelected()) {
                myArea.setText(myArea.getText() + chb.getText() + "\n");
                myList.getItems().add(chb.getText());
            }
            else  myList.getItems().remove(chb.getText());
        }
}
