package application;

public class Edge {
	private City city;
	private double distance;
	public Edge(City city, double distance) {
		super();
		this.city = city;
		this.distance = distance;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}

	

}
