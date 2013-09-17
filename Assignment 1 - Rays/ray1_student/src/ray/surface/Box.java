
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
	  //d.normalize();
	  
	 // p + td
	  double txmin = (minPt.x - p.x) / d.x;
	  double tymin = (minPt.y - p.y) / d.y;
	  double tzmin = (minPt.z - p.z) / d.z;
	  
	  double txmax = (maxPt.x - p.x) / d.x;
	  double tymax = (maxPt.y - p.y) / d.y;
	  double tzmax = (maxPt.z - p.z) / d.z;
	  
	  if (d.x >= 0){
	  txmin = (minPt.x - p.x)/d.x;
	  txmax = (maxPt.x - p.x)/d.x;
	  }else{
	  txmin = (maxPt.x - p.x)/d.x;
	  txmax = (minPt.x - p.x)/d.x;
	  }
	  if (d.y >= 0){
		  tymin = (minPt.y - p.y)/d.y;
		  tymax = (maxPt.y - p.y)/d.y;
      }else{
		  tymin = (maxPt.y - p.y)/d.y;
		  tymax = (minPt.y - p.y)/d.y;
	  }
	  if (d.z >= 0){
		  tzmin = (minPt.z - p.z)/d.z;
		  tzmax = (maxPt.z - p.z)/d.z;
	  }else{
		  tzmin = (maxPt.z - p.z)/d.z;
		  tzmax = (minPt.z - p.z)/d.z;
	  }
	  
	  
	
	  double txenter = Math.min(txmin, txmax);
	  double txexit = Math.max(txmin, txmax);
	  double tyenter = Math.min(tymin, tymax);
	  double tyexit = Math.max(tymin, tymax);
	  double tzenter = Math.min(tzmin, tzmax);
	  double tzexit = Math.max(tzmin, tzmax);
	  
	  double tenter = Math.max(Math.max(txenter, tyenter),tzenter);
	  double texit = Math.min(Math.min(txexit, tyexit),tzexit);

	
	  
	  Vector3 d2 = new Vector3(d);
	  Point3 pointenter = new Point3(p);
	  d2.scale(tenter);
	  pointenter.add(d2);
	  
	  Vector3 d3 = new Vector3(d);
	  Point3 pointexit = new Point3(p);
	  d3.scale(texit);
	  pointexit.add(d3);


	  
//	  if (pointmin.x >= minPt.x && pointmin.x <= maxPt.x &&
//		  pointmin.y >= minPt.y && pointmin.y <= maxPt.y &&
//	      pointmin.z >= minPt.z && pointmin.z <= maxPt.z) {
//	  
	 if (!((txmin > tymax) || (tymin > txmax) || (tzmin > tymax) || 
	     (tzmin > txmax) || (txmin > tzmax) || tymin > tzmax)){ 

		 if (tenter < rayIn.start || tenter > rayIn.end) {
			 System.out.println("inside box");
			  return false;
		 }
		 
		  if (pointenter.x == minPt.x) outRecord.normal.set(new Vector3(-1,0,0));
		  if (pointenter.x == maxPt.x) outRecord.normal.set(new Vector3(1,0,0));
		  if (pointenter.y == minPt.y) outRecord.normal.set(new Vector3(0,-1,0));
	      if (pointenter.y == maxPt.y) outRecord.normal.set(new Vector3(0,1,0));
		  if (pointenter.z == minPt.z) outRecord.normal.set(new Vector3(0,0,-1));
		  if (pointenter.z == maxPt.z) outRecord.normal.set(new Vector3(0,0,1));
		  
		  if(outRecord.normal.x == 0.0 && outRecord.normal.y == 0.0 && outRecord.normal.z == 0.0){
		  }
		  
		  outRecord.t = tenter;
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