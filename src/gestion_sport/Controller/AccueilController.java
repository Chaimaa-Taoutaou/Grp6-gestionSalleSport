package gestion_sport.Controller;

import gestion_sport.Model.connecter;
import java.net.URL;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Label;



public class AccueilController implements Initializable {
	@FXML
	private PieChart pieChart;

	@FXML
	private Label Tactivity;

	@FXML
	private Label Tadherent;

	@FXML
	private Label Tcoach;

	@FXML
	private Label Tsalle;

	/*     @FXML
     private BarChart<String, Integer> barChart;
     @FXML
     private CategoryAxis x;
     @FXML
     private NumberAxis y;*/
	connecter c = new connecter();
	ResultSet Ta;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Activity();
		Adherent();
		Salle();
		Coach();
		pieChart1();
		//chart();




	}

	public void Activity() {

		Ta=c.TotalActivity();
		try {
			if(Ta.next()) {
				Tactivity.setText(String.valueOf(Ta.getInt(1)));}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void Adherent() {


		Ta=c.TotalAdherent();
		try {
			if(Ta.next()) {
				Tadherent.setText(String.valueOf(Ta.getInt(1)));}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void Coach() {

		Ta=c.TotalCoach();
		try {
			if(Ta.next()) {
				Tcoach.setText(String.valueOf(Ta.getInt(1)));}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void Salle() {

		Ta=c.TotalSalle();
		try {
			if(Ta.next()) {
				Tsalle.setText(String.valueOf(Ta.getInt(1)));}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/*p
	@SuppressWarnings("unchecked")
	ublic void chart() {
		ResultSet rq;
		rq=c.chartM();
		XYChart.Series<String, Integer> series1 = new XYChart.Series<>();
		series1.setName("Nombre des adhrents");

	try {
		while(rq.next()) {
			try {
				series1.getData().add(new XYChart.Data<String, Integer>(rq.getString(1),rq.getInt(2)));
				barChart.getData().add(series1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	}

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}


	}*/

	public void pieChart1() {
		ResultSet pr;
		pr=c.pieChart();
		try {
			while(pr.next()) {
				try {
					ObservableList<PieChart.Data> pieChartData =
							FXCollections.observableArrayList(
									new PieChart.Data(pr.getString(1), pr.getInt(2)));


					pieChartData.forEach(data -> data.nameProperty().bind(
									Bindings.concat(
											data.getName()," ", data.pieValueProperty(),"% "
									)
							)

					);
					pieChart.getData().addAll(pieChartData);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}