package application;

import java.util.ArrayList;

public class City {
	private String cityName;
	private int xCoordinate;
	private int yCoordinate;
	private ArrayList<Edge> edges;

	public City() {
		super();
	}

	public City(String cityName, int xCoordinate, int yCoordinate) {
		super();
		this.cityName = cityName;
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		edges = new ArrayList<>();

	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public int getxCoordinate() {
		return xCoordinate;
	}

	public void setxCoordinate(int xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public int getyCoordinate() {
		return yCoordinate;
	}

	public void setyCoordinate(int yCoordinate) {
		this.yCoordinate = yCoordinate;
	}
	public void addEdge(Edge edge) {
		this.edges.add(edge);
	}
	public ArrayList<Edge> getEdge() {
		return edges;
	}

	public double getDistance(City city) {
		for(Edge edge : edges) {
			if(edge.getCity().getCityName().equals(city.getCityName())) {
				return edge.getDistance();
			}
		}
		return -1.0;
	}

	@Override
	public String toString() {
		return "City [cityName=" + cityName + "]";
	}
	
}
