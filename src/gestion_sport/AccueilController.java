/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

package gestion_sport;

import java.net.URL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;


public class AccueilController implements Initializable {

	
	@FXML
	private BarChart<String, Integer> barChart;
	@FXML
	private CategoryAxis x;
	@FXML
	private NumberAxis y;
	
	
	@SuppressWarnings("unchecked")
	@Override
		public void initialize(URL location, ResourceBundle resources) {
		
			//for BarChart
			connecter c = new connecter();
			ResultSet rs,rq;
			rs=c.chart();
			rq=c.chartM();
			XYChart.Series series1 = new XYChart.Series<>();
			series1.setName("homme");
			XYChart.Series series2 = new XYChart.Series<>();
			series2.setName("femme");
		try {
			while(rs.next()) {
				try {
					series1.getData().add(new XYChart.Data<String, Integer>(rs.getString(1),rs.getInt(2)));
					//series2.getData().add(new XYChart.Data<String, Integer>(rs.getString(1),rq.getInt(2)));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			while(rq.next()) {
				try {
					series2.getData().add(new XYChart.Data<String, Integer>(rq.getString(1),rq.getInt(2)));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
			
			barChart.getData().addAll(series1,series2);
		}
}
	
