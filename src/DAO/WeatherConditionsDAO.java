package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Model.ClimateCondition.*;

public class WeatherConditionsDAO {
	
	private static ArrayList<ClimateCondition> weatherConditions = new ArrayList<>();

	 public static List<ClimateCondition> getAllWeatherConditions() throws SQLException {
	        String queryclimatecond = "SELECT* FROM weatherConditions";

	        try{
	        	 Connection connection = DBConnection.getConnection();
	             Statement statement = connection.createStatement();
	             ResultSet rs = statement.executeQuery(queryclimatecond);
	             
	             
	        	
	            while (rs.next()) {
	                int id = rs.getInt("idconditions");
	                String description = rs.getString("description");
	                String measureUnit = rs.getString("measureunit");
	                double lowerMark = rs.getDouble("lowermark");
	                double upperMark = rs.getDouble("uppermark");
	                double swimmingWear = rs.getDouble("wearswimming");
	                double cyclingWear = rs.getDouble("wearcycling");
	                double runningWear = rs.getDouble("wearunning");
	                
	                UnitMeasure unitMeasure = new UnitMeasure(measureUnit);

	                ClimateCondition condition = new ClimateCondition(id, description, unitMeasure, lowerMark, upperMark,swimmingWear,cyclingWear,runningWear);
	                weatherConditions.add(condition);
	            }
	        }catch (SQLException e) {
	            e.printStackTrace(); 
	       }
	       
	        return weatherConditions;
	}
}

