
package ray.surface;

import ray.IntersectionRecord;
import ray.Ray;
import ray.math.Point3;
import ray.math.Vector3;

/**
 * Represents a sphere as a center and a radius.
 *
 * @author ags
 */
public class Sphere extends Surface {
  
  /** The center of the sphere. */
  protected final Point3 center = new Point3();
  public void setCenter(Point3 center) { this.center.set(center); }
  
  /** The radius of the sphere. */
  protected double radius = 1.0;
  public void setRadius(double radius) { this.radius = radius; }
  
  public Sphere() { }
  
  /**
   * Tests this surface for intersection with ray. If an intersection is found
   * record is filled out with the information about the intersection and the
   * method returns true. It returns false otherwise and the information in
   * outRecord is not modified.
   *
   * @param outRecord the output IntersectionRecord
   * @param ray the ray to intersect
   * @return true if the surface intersects the ray
   */
  public boolean intersect(IntersectionRecord outRecord, Ray rayIn) {
    // TODO: fill in this function.
	  Point3 e = new Point3(rayIn.origin);
	  Vector3 d = new Vector3(rayIn.direction);
	  Point3 c = new Point3(center);
	  
	  double t1;
	  double t2;
	  
	  Vector3 EminC = new Vector3();
	  EminC.sub(e, c);
	  double B = d.dot(EminC);
	  double A = (d.dot(d));
	  double C = (EminC).dot(EminC) - (radius*radius);
	  
	  double discriminant= (B*2) - A*C;
	  
	  if (discriminant < 0 ) return false;
	  if (discriminant == 0 ){
		  t1 = (B*-1.0)/A;
		  Vector3 d2 = new Vector3(d);
		  d2.scale(t1);
		  Point3 location= new Point3(e);
		  location.add(d2);
		  outRecord.location = location;
		  outRecord.t = t1;
		  outRecord.surface = this;
		  //outRecordnormal
		  return true;
	  }
	  else {
		  t1 = ((B*-1.0) + discriminant )/A;
		  t2 = ((B*-1.0) - discriminant )/A;
		if (t1 < 0) t1 = t2;
		if (t1 >= 0 && t2 >= 0) t1 = Math.min(t1, t2);
		
		  t1 = (B*-1.0)/A;
		  Vector3 d2 = new Vector3(d);
		  d2.scale(t1);
		  Point3 location= new Point3(e);
		  location.add(d2);
		  outRecord.location = location;
		  outRecord.t = t1;
		  outRecord.surface = this;
		  //outRecordnormal
	  }

	  
	  
	 
        
    return true;

  }
  
  /**
   * @see Object#toString()
   */
  public String toString() {
    return "sphere " + center + " " + radius + " " + shader + " end";
  }

}