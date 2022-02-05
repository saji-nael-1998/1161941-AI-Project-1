package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class IndexController implements Initializable {

	// set nodes
	@FXML
	private AnchorPane container;
	@FXML
	private ComboBox<String> starComboBox;
	@FXML
	private ComboBox<String> goalComboBox;
	@FXML
	private TextField distanceTF;
	@FXML
	private TextArea pathTA;

	ArrayList<City> cities = new ArrayList<City>();
	Data data;
	boolean isClear = true;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// load map
		loadMap();
	}

	public void loadMap() {
		try {
			container.getChildren().clear();
			// load map on screen
			AnchorPane map = FXMLLoader.load(getClass().getResource("palestine_map.fxml"));
			container.getChildren().add(map);
			data = new Data(cities);
			data.readData();
			loadCitiesOnScreen();
			
			loadCitiesInComboBox();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

	private void loadCitiesInComboBox() {
		for (City city : cities) {
			// fill combobox with cities name
			starComboBox.getItems().add(city.getCityName());
			goalComboBox.getItems().add(city.getCityName());
		}
		// set action when i click o
		starComboBox.getSelectionModel().select(0);
		goalComboBox.getSelectionModel().select(0);
	}

	public void loadCitiesOnScreen() {
		for (City city : cities) {
			Circle circle = new Circle(5);
			circle.setFill(Color.BLACK);
			circle.setTranslateX(city.getxCoordinate());
			circle.setTranslateY(city.getyCoordinate());
			Tooltip.install(circle, new Tooltip(city.getCityName()));
			container.getChildren().add(circle);
		}
	}

	public void findPathBtn(ActionEvent e) {

		if (isClear == true) {
			//fetch the cities from each combo box
			City start = data.getCity(starComboBox.getSelectionModel().getSelectedItem());
			City goal = data.getCity(goalComboBox.getSelectionModel().getSelectedItem());
			//check if start/goal is an edge or has edge(s)
			if (data.hasEdges(start) && data.hasEdges(goal)) {
				AStar aStar = new AStar();
				Point result = aStar.findPath(start, goal);
				if (result != null) {
					isClear = false;
					//print the result of searching
					printPathOnScreen(result);
				} else {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setContentText("the same city!!");
					alert.show();
				}
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setContentText("there is no route!!");
				alert.show();
			}

		} else {

			Alert alert = new Alert(AlertType.WARNING);
			alert.setContentText("You must clear the screen!!");
			alert.show();
		}

	}

	public void printPathOnScreen(Point result) {
		distanceTF.setText(String.valueOf(result.getValue()) + " KM");
		for (int x = 1; x < result.getRoute().size(); x++) {
			City start = result.getRoute().get(x - 1);
			City end = result.getRoute().get(x);
			Line line = new Line(start.getxCoordinate(), start.getyCoordinate(), end.getxCoordinate(),
					end.getyCoordinate());
			container.getChildren().add(line);

		}
		String path = "";
		for (int x = 1; x < result.getRoute().size(); x++) {
			City start = result.getRoute().get(x - 1);
			City end = result.getRoute().get(x);
			path += start.getCityName() + "->" + end.getCityName() + "\n";
		}
		pathTA.setText(path);
	}

	public void clearBtn(ActionEvent e) {
		// clear screen
		distanceTF.clear();
		pathTA.clear();
		isClear = true;
		loadMap();
	}

}
