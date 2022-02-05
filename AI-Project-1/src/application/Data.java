package application;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Data {
	private ArrayList<City> cities;

	Data(ArrayList<City> cities) {
		this.cities = cities;
	}

	public void readData() {
		try {
			// create file object
			File file = new File("files\\towns.csv");
			// create scanner object
			Scanner scannner = new Scanner(file);
			while (scannner.hasNextLine()) {
				// read linve
				String line = scannner.nextLine();
				line = line.substring(1, line.length() - 1);
			
				// split data
				String split[] = line.trim().split(",");
				// create city object
				City city = new City(split[0].trim(), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
				// add to arraylist
				cities.add(city);

			}
			readEdges();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void readEdges() {
		try {

			// create file object
			File file = new File("files\\roads.csv");
			// create scanner object
			Scanner scannner = new Scanner(file);
			while (scannner.hasNextLine()) {
				// read linve
				String line = scannner.nextLine();
				// split data
				String split[] = line.split("-");
				
				String c1Name = split[0].trim();
				String c2Name = split[1].trim();

				double distance = Double.parseDouble(split[2]);
				// add city2-> city 1 edges
				Edge e1 = new Edge(getCity(c2Name), distance);

				// add city1-> city 2 edges
				Edge e2 = new Edge(getCity(c1Name), distance);

				// add edge for each city
				cities.get(getCityIndex(c1Name)).addEdge(e1);

				cities.get(getCityIndex(c2Name)).addEdge(e2);

			}
		
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public int getCityIndex(String cityName) {
		for (int x = 0; x < cities.size(); x++) {
			if (cities.get(x).getCityName().equalsIgnoreCase(cityName)) {
				return x;
			}
		}
		return -1;
	}

	public City getCity(String cityName) {
		for (int x = 0; x < cities.size(); x++) {
			if (cities.get(x).getCityName().equalsIgnoreCase(cityName)) {
				return cities.get(x);
			}
		}
		return null;
	}

	public boolean hasEdges(City target) {
		if (target.getEdge().size() == 0) {
			return checkExistanceAsEdge(target);
		}
		return true;
	}

	private boolean checkExistanceAsEdge(City target) {
		for (City c : cities) {
			for (Edge edge : c.getEdge()) {
				if (edge.getCity().getCityName().equals(target.getCityName())) {
					return true;
				}
			}
		}
		return false;
	}

}
