package application;
import java.util.Comparator;

public class ValueComparator  implements Comparator<Point>  {

	@Override
	public int compare(Point p1, Point p2) {
	
		if (p1.getValue() ==  p2.getValue()) {
			
			if (p1.getCity().getCityName().compareTo(p2.getCity().getCityName()) < 0) {
				return 1;
			} else if (p1.getCity().getCityName().compareTo(p2.getCity().getCityName()) > 0) {
				return -1;
			}
		} else {
			if (p1.getValue() > p2.getValue()) {
				return 1;
			} else if (p1.getValue() < p2.getValue()) {
				return -1;
			}
		}
		return 0;
	}
	
}
