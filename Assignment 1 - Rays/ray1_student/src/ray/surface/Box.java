
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
	  d.normalize();
	    
	  
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
	  Point3 pointmin = new Point3(p);
	  d2.scale(tmin);
	  pointmin.add(d);
	  
	  Vector3 d3 = new Vector3(d);
	  Point3 pointmax = new Point3(p);
	  d3.scale(tmax);
	  pointmax.add(d);

	  
	  if (pointmin.x >= minPt.x && pointmin.x <= maxPt.x &&
		  pointmin.y >= minPt.y && pointmin.y <= maxPt.y &&
	      pointmin.z >= minPt.z && pointmin.z <= maxPt.z) {
		  
		  outRecord.location = pointmin;
		  if (pointmin.x == minPt.x) outRecord.normal = new Vector3(0,-1,0);
		  if (pointmax.x == maxPt.x) outRecord.normal = new Vector3(0,1,0);
		  if (pointmin.y == minPt.y) outRecord.normal = new Vector3(0,0,-1);
	      if (pointmax.y == maxPt.y) outRecord.normal = new Vector3(0,0,1);
		  if (pointmin.z == minPt.z) outRecord.normal = new Vector3(-1,0,0);
		  if (pointmax.z == minPt.z) outRecord.normal = new Vector3(1,0,0);
		  System.out.println(outRecord.normal);
		  outRecord.t = minPt.x;
		  outRecord.surface = this;
		  return true;
	  }
	  
    return false;

  }
  
  /**
   * @see Object#toString()
   */
  public String toString() {
    return "Box ";
  }
}