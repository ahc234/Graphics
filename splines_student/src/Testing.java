import java.util.ArrayList;
import static java.util.Arrays.asList;
import javax.vecmath.Vector2f;

public class Testing {

    public static void main(String[] args) {
    	ArrayList<Vector2f> pointStart = new ArrayList<Vector2f>(asList(
    			new Vector2f(-10, 0), new Vector2f(-9, 0), new Vector2f(-8, 0), new Vector2f(-7, 0), new Vector2f(-6, 0), new Vector2f(-5, 0),
    			new Vector2f(-4, 0), new Vector2f(-3, 0), new Vector2f(-2, 0), new Vector2f(-1, 0), new Vector2f(0, 1),
    			new Vector2f(1, 0), new Vector2f(2, 0), new Vector2f(3, 0), new Vector2f(4, 0), new Vector2f(5, 0),
    			new Vector2f(6, 0), new Vector2f(7, 0), new Vector2f(8, 0), new Vector2f(9, 0), new Vector2f(10, 0)));
    	
//    	ArrayList<Float> evenMask = new ArrayList<Float>(asList(1f));
//    	ArrayList<Float> oddMask = new ArrayList<Float>(asList((0.5f), (0.5f)));
    	
//    	ArrayList<Float> evenMask = new ArrayList<Float>(asList(1f/8, 6f/8, 1f/8));
//    	ArrayList<Float> oddMask = new ArrayList<Float>(asList((0.5f), (0.5f)));
    	
    	ArrayList<Float> evenMask = new ArrayList<Float>(asList(1f));
    	ArrayList<Float> oddMask = new ArrayList<Float>(asList(-1f/16, 9f/16, 9f/16, -1f/16));
    	
        subdivision(pointStart, evenMask, oddMask, 0);
    }
    
    public static void subdivision(ArrayList<Vector2f> points, ArrayList<Float> evenMask, ArrayList<Float> oddMask, int counter) {
    	if (counter == 6) {
    		String s = "";
    		for (Vector2f p : points) {
    			//s = s + "{" + p.x + ", " + p.y + "}, ";
    			System.out.println(p.y);
    		}
    	} else {
	    	ArrayList<Vector2f> newPoints = new ArrayList<Vector2f>();
    		newPoints.add(new Vector2f(points.get(0).x, points.get(0).y));
    		
	    	for (int i = 0; i < points.size()-1; i++) {
	    		
	    		ArrayList<Vector2f> oddInfluence = new ArrayList<Vector2f>();
	    		
	    		int oddLength = oddMask.size();
	    		for (int j = 0; j < oddMask.size(); j++) {
	    			int index = i-oddLength/2 + j + 1;
	    			if (index < 0) {
	    				index = 0;
	    			} else if (index > points.size()-1) {
	    				index = points.size()-1;
	    			}
	    			Vector2f p = points.get(index);
	    			oddInfluence.add(new Vector2f(p.x*oddMask.get(j),p.y*oddMask.get(j)));
	    		}
	    		Vector2f oddAggregate = new Vector2f(0f, 0f);
	    		for (Vector2f p : oddInfluence) {
	    			oddAggregate = new Vector2f(oddAggregate.x + p.x, oddAggregate.y + p.y);
	    		}
	    		newPoints.add(oddAggregate);
	    		
	    		ArrayList<Vector2f> evenInfluence = new ArrayList<Vector2f>();
	    		int evenLength = evenMask.size();
	    		
	    		if (evenLength == 3) {
    				Vector2f prevPoint = new Vector2f(points.get(i).x * evenMask.get(0), points.get(i).y * evenMask.get(0));
    				evenInfluence.add(prevPoint);
    				
    				int index = Math.max(0, Math.min(points.size()-1, i+1));
    				Vector2f currPoint = new Vector2f(points.get(index).x * evenMask.get(1), points.get(index).y * evenMask.get(1));
	    			evenInfluence.add(currPoint);
	    			
	    			index = Math.max(0, Math.min(points.size()-1, i+2));
					Vector2f nextPoint = new Vector2f(points.get(index).x * evenMask.get(2), points.get(index).y * evenMask.get(2));
	    			evenInfluence.add(nextPoint);
	    			
	    		} else {
	    			int index = Math.max(0, Math.min(points.size()-1, i+1));
	    			evenInfluence.add(new Vector2f(points.get(index).x, points.get(index).y));
	    		}
	    	
	    		Vector2f evenAggregate = new Vector2f(0f, 0f);
	    		for (Vector2f p : evenInfluence) {
	    			evenAggregate = new Vector2f(evenAggregate.x + p.x, evenAggregate.y + p.y);
	    		}
	    		if (i < points.size() - 2) {
	    			newPoints.add(evenAggregate);
	    		}
	    		
	    	}
	    	newPoints.add(points.get(points.size()-1));
	    	
	    	subdivision(newPoints, evenMask, oddMask, counter+1);
    	}
    }
}
