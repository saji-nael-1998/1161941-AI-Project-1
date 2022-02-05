package application;

import java.util.Stack;

public class Point {
	private Stack<City> route;	
	private double value;
	private City city;
	
	
	public Point(Stack<City> route, double value, City city) {
		super();
		this.route = route;
		this.value = value;
		this.city = city;
	}


	public double getValue() {
		return value;
	}


	public void setValue(double value) {
		this.value = value;
	}


	public City getCity() {
		return city;
	}


	public void setCity(City city) {
		this.city = city;
	}


	public Stack<City> getRoute() {
		return route;
	}


}
