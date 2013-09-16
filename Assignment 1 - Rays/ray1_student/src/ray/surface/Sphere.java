
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
	  
	  //System.out.println("A: " + A +"; "+ "B: " + B + "; "+ "C: " + C);
	  
	  double discriminant= (B*B) - A*C;
	  
	  if (discriminant < 0 ) {
		  return false;
	  }
	  else if (discriminant == 0 ){
		  t1 = (-B)/A;
		  if (t1 < rayIn.start || t1 > rayIn.end) {
			  return false;
		  }
		  Vector3 d2 = new Vector3(d);
		  d2.scale(t1);
		  Point3 location= new Point3(e);
		  location.add(d2);
		  outRecord.location.set(location);
		  outRecord.t = t1;
		  outRecord.surface = this;
		  outRecord.normal.sub(location, center);   //outRecordnormal
		  outRecord.normal.normalize();
		  return true;
	  }
	  else {
		  t1 = ((-B) + Math.sqrt(discriminant))/A;
		  t2 = ((-B) - Math.sqrt(discriminant))/A;
		  if (t1 < rayIn.start || t1 > rayIn.end) {
			  	t1 = t2;
		  }
		  if (t1 < rayIn.start || t1 > rayIn.end) {
			  return false;
		  }
			  
		  t1 = Math.min(t1, t2);
			
		  Vector3 d2 = new Vector3(d);
		  d2.scale(t1);
		  Point3 location= new Point3(e);
		  location.add(d2);
		  outRecord.location.set(location);
		  outRecord.t = t1;
		  outRecord.surface = this;
		  outRecord.normal.sub(location, center);  //outRecordnormal
		  outRecord.normal.normalize();
		  return true;
	  }

  }
  
  /**
   * @see Object#toString()
   */
  public String toString() {
    return "sphere " + center + " " + radius + " " + shader + " end";
  }

}