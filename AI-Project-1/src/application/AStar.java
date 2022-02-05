package application;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;

public class AStar {

	public Point findPath(City start, City goal) {
		// if start equal to goal or not
		if (goal.getCityName().equals(start.getCityName())) {
			return null;
		} else {
			
			ValueComparator comparator = new ValueComparator();
			PriorityQueue<Point> queue = new PriorityQueue<>(comparator);
			// set stack form the start to goal
			Stack<City> path = new Stack<>();
			path.add(start);
			//set start as cuurent point
			Point currentCity = new Point(path, 0, start);
			//add to queu
			queue.add(currentCity);

			while (!queue.isEmpty()) {
			
				LinkedList<City> visitedCities = new LinkedList<>();
				//poll point from queue
				currentCity = queue.poll();
				//get current path
				path = currentCity.getRoute();
				//check if we reach the goal
				if (currentCity.getCity().getCityName().equals(goal.getCityName())) {
					currentCity.setValue(roundValue(currentCity.getValue()));
					return currentCity;

				}
				//set the visited cities/points
				for (int x = 0; x < currentCity.getRoute().size(); x++) {
					visitedCities.add(currentCity.getRoute().get(x));
				}
				//get the edegs of the current city
				ArrayList<Edge> edge = currentCity.getCity().getEdge();
				for (int x = 0; x < edge.size(); x++) {
					City city = edge.get(x).getCity();
					//check if the edge has been visited or not
					if (isVisited(city, visitedCities)) {
						continue;
					} else {
						//set the new path
						Stack<City> newPath = new Stack<>();
						for (int y = 0; y < path.size(); y++) {
							newPath.add(path.get(y));
						}
						newPath.add(city);
						double value = findValue(newPath, goal);
						//add a new point to queue
						queue.add(new Point(newPath, value, city));
					}
				}

			}

		}
		return null;
	}

	public double findValue(Stack<City> path, City goal) {
		//calculate the value 
		double value = 0;
		for (int y = 1; y < path.size(); y++) {
			value += path.get(y - 1).getDistance(path.get(y));
		}
		value += findHeuristicValue(path.peek(), goal);
		return value;
	}

	public double roundValue(double value) {
		value = value * 100;
		value = (int) value;
		value = value / 100;
		return value;
	}

	public double findHeuristicValue(City city, City goal) {
		//calculate the heuristic value 
		int xDiff = goal.getxCoordinate() - city.getxCoordinate();
		xDiff = xDiff * xDiff;
		int yDiff = goal.getyCoordinate() - city.getyCoordinate();
		yDiff = yDiff * yDiff;
		int sum = xDiff + yDiff;
		double heuristicValue = Math.sqrt(sum);
		return heuristicValue;
	}

	public boolean isVisited(City city, LinkedList<City> visitedCities) {
		//check city is already visited or not
		boolean isVisited = false;
		if (visitedCities.isEmpty()) {
			return isVisited;
		} else {
			for (City c : visitedCities) {
				if (city.getCityName().equals(c.getCityName())) {
					isVisited = true;
					return isVisited;
				}
			}
		}

		return isVisited;
	}

}
