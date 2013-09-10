
package ray.surface;

import ray.IntersectionRecord;
import ray.Ray;
import ray.math.Point3;
import ray.math.Vector3;

public class Box extends Surface {
  
  /* The corner of the box with the smallest x, y, and z components. */
  protected final Point3 minPt = new Point3();
  public void setMinPt(Point3 minPt) { this.minPt.set(minPt); }
  
  /* The corner of the box with the largest x, y, and z components. */
  protected final Point3 maxPt = new Point3();
  public void setMaxPt(Point3 maxPt) { this.maxPt.set(maxPt); }
  
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
	  Point3 p = new Point3(rayIn.origin);

	  Vector3 d = new Vector3(rayIn.direction);
	    
	  
	 // p + td
	  double txmin = (minPt.x - p.x) / d.x;
	  double tymin = (minPt.y - p.y) / d.y;
	  double tzmin = (minPt.z - p.z) / d.z;
	  
	  double txmax = (maxPt.x - p.x) / d.x;
	  double tymax = (maxPt.y - p.y) / d.y;
	  double tzmax = (maxPt.z - p.z) / d.z;
	  
	  double tmin = Math.max(Math.max(txmin, tymin),tzmin);
	  double tmax = Math.min(Math.min(txmax, tymax),tzmax);
	  
	  Vector3 d2 = new Vector3(d);
	  Vector3 location = new Vector3(d);
	  d2.scale(tmin);
	  location.add(d);

    
    return true;

  }
  
  /**
   * @see Object#toString()
   */
  public String toString() {
    return "Box ";
  }
}