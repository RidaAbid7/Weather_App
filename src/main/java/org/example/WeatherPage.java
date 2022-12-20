package org.example;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
public class WeatherPage {
    public static String city_name;
    public WeatherPage(Stage stage, Scene login_scene) throws FileNotFoundException {

        BackgroundImage bg = new BackgroundImage(new Image("http://openweathermap.org/img/wn/01d@2x.png", 100, 100,false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

        Text temp = new Text("Temperature:");
        Text temp_text = new Text();
        Text feels_like = new Text("Feels Like:");
        Text fl_text = new Text("     ");
        Text minimum_temperature = new Text("Minimum Temperature");
        Text min_text = new Text("     ");
        Text maximum_temperature = new Text("Maximum Temperature");
        Text max_text = new Text("     ");
        Text pressure = new Text("Pressure");
        Text pressure_text = new Text("     ");
        Text humidity = new Text("Humidity");
        Text humidity_text = new Text("     ");

        HBox inner_hbox = new HBox();
        inner_hbox.setMinHeight(30);
        inner_hbox.setMinWidth(30);
        inner_hbox.setMaxSize(100,100);
        inner_hbox.setBackground(new Background(bg));

        Button btn = new Button("Previous");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.setScene(login_scene);
            }
        });

        DecimalFormat obj = new DecimalFormat("0.00");
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        temp_text.setText(obj.format(((Double)(((Double)(Main.data.get("temp"))) - 273.16))).toString() + " °C");
                        fl_text.setText(obj.format(((Double)(((Double)(Main.data.get("feels_like"))) - 273.16))).toString()+ " °C");
                        min_text.setText(obj.format(((Double)(((Double)(Main.data.get("temp_min"))) - 273.16))).toString()+ " °C");
                        max_text.setText(obj.format(((Double)(((Double)(Main.data.get("temp_max"))) - 273.16))).toString()+ " °C");
                        pressure_text.setText(Main.data.get("pressure").toString() + " hPa");
                        //1 hPa = 100 Pascals = 1 mb.
                        humidity_text.setText(obj.format(((Double)(((Double)(Main.data.get("humidity"))) / 100.0))).toString()+ " %");
                    }
                });


        temp.setFont(new Font("Times New Roman" ,30));
        temp_text.setFont(new Font(30));

        GridPane gp = new GridPane();
        gp.setAlignment(Pos.CENTER);
        gp.setVgap(20);
        gp.setHgap(20);
        gp.add(inner_hbox, 4,1,3,3);
        gp.addColumn(0, new Text());
        gp.add(temp, 0,0,2,1);
        gp.add(temp_text,3,0,2,1);
        gp.addRow(1, new Text());
        gp.addRow(2,new Text());
        gp.addRow(3, new Text());
        gp.add(feels_like,0,4);
        gp.add(fl_text,1,4);
        gp.add(minimum_temperature,0,5);
        gp.add(min_text,1,5);
        gp.add(maximum_temperature,0,6);
        gp.add(max_text,1,6);
        gp.add(pressure,0,7);
        gp.add(pressure_text,1,7);
        gp.add(humidity,0,8);
        gp.add(humidity_text,1,8);

        BorderPane bp = new BorderPane();
        bp.setCenter(gp);
        bp.setBottom(btn);
        Scene weatherscene = new Scene(bp , 800 ,500);
        stage.setScene(weatherscene);
    }
}
