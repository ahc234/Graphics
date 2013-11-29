package cs4620.scene;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;

public class KeyframeAnimation {
	public static void linearInterpolateTransformation(SceneNode sNode, SceneNode eNode, float t, SceneNode iNode)
	{
		// TODO (Animation P1): Implement if you find useful -- set the state of SceneNode iNode
		// (which can be a superclass of SceneNode, such as SceneNodeKeyframeable) to be
		// the linear interpolation of the states of nodes sNode and eNode.
		
	}
	
	public static void catmullRomInterpolateTransformation(SceneNode p0, SceneNode p1,
			SceneNode p2, SceneNode p3, float t, SceneNode iNode)
	{
		// TODO (Animation P1): Implement if you find useful -- set the state of SceneNode iNode
		// (which can be a superclass of SceneNode, such as SceneNodeKeyframeable) to be the
		// result of evaluating the Catmull-Rom spline whose "control points" are the states
		// of the four SceneNode objects.
		
	}
	
	public static void linearInterpolate4Float(float [] sFloat, float [] eFloat, float t, float [] iFloat)
	{
		// TODO (Animation P1): Implement if you find useful -- interpolate linearly between two size-four
		// arrays and write result to iFloat.
		
	}
	
	public static void catmullRomInterpolate4Float(float [] p0, float [] p1, float [] p2, float [] p3,
			float t, float [] iNode)
	{
		// TODO (Animation P1): Implement if you find useful -- evaluate a Catmull-Rom spline on four
		// four-element-array "control points" and write result to iNode.
		
	}
	
	public static void catmullRomRotationInterpolation(Vector3f p0, Vector3f p1,
	        Vector3f p2, Vector3f p3, float t, Vector3f iNode)
	{
		// TODO (Animation P1): Implement if you find useful -- evaluate a Catmull-Rom spline
		// using quaternions with the given four control points, and write result to iNode.
		
	}
	
	public static void catmullRomInterpolation(Vector3f p0, Vector3f p1,
			Vector3f p2, Vector3f p3, float t, Vector3f iNode)
	{
		// TODO (Animation P1): Implement if you find useful -- evaluate a Catmull-Rom spline
		// with the given four control points, and write result to iNode.
				
	}
	
	public static Quat4f slerp(Quat4f i1, Quat4f i2, float t)
	{
		// TODO (Animation P1): Implement slerp.
		Vector4f vecQ1 = new Vector4f(i1.w, i1.x, i1.y, i1.z);
		Vector4f vecQ2 = new Vector4f(i2.w, i2.x, i2.y, i2.z);
		float psi = (float)Math.acos((vecQ1.dot(vecQ2)));
		
		Quat4f q = new Quat4f(i1.x, i1.y, i1.z, i1.w);
		q.scale((float)Math.sin(1-t)*psi);
		Quat4f i2Deep = new Quat4f(i2.x, i2.y, i2.z, i2.w);
		i2Deep.scale((float)Math.sin(t)*psi);
		q.add(i2Deep);
		q.scale(1f/(float)Math.sin(psi));
		
		return q;
		
	}
	
	public static Vector3f getEulerAnglesFromQuaternion(Quat4f quat)
	{
		// TODO (Animation P1): Convert the given quaternion into a vector of 
		// three Euler angles that encode the same rotation.
		
		float a;
		float b;
		float c;
		
		float w = quat.w;
		float x = quat.x;
		float y = quat.y;
		float z = quat.z;
		
		float test = 2*(w*y - z*x);
		if (Math.abs(test) > 0.9999) {
			if (test > 0) {
				a = 2*(float)Math.atan2(x, w);
				b = (float)Math.PI/2;
				c = 0;
			} else {
				a = -2*(float)Math.atan2(x, w);
				b = -1*(float)Math.PI/2;
				c = 0;
			}
		} else {
			a = (float)Math.atan2(2*(w*x + y*z), 1f - 2*(x*x + y*y));
			b = (float)Math.asin(2*(w*y - z*x));
			c = (float)Math.atan2(2*(w*z + x*y), 1f - 2*(y*y + z*z));
		}
	
		a = (float)Math.toDegrees(a);
		b = (float)Math.toDegrees(b);
		c = (float)Math.toDegrees(c);
		
		return new Vector3f(a, b, c);
	}
	
	public static Quat4f getQuaternionFromEulerAngles(Vector3f eulerAngles)
	{
		// TODO (Animation P1): Convert the given Euler angles into a quaternion
		// that encodes the same rotation.
		float a = (float)Math.toRadians(eulerAngles.x);
		float b = (float)Math.toRadians(eulerAngles.y);
		float c = (float)Math.toRadians(eulerAngles.z);
		
		Vector3f x = new Vector3f(1, 0, 0);
		x.scale((float)Math.sin(a/2));
		Vector3f y = new Vector3f(0, 1, 0);
		y.scale((float)Math.sin(b/2));
		Vector3f z = new Vector3f(0, 0, 1);
		z.scale((float)Math.sin(c/2));
		
		Quat4f qx = new Quat4f(x.x, x.y, x.z, (float)Math.cos(a/2));
		Quat4f qy = new Quat4f(y.x, y.y, y.z, (float)Math.cos(b/2));
		Quat4f qz = new Quat4f(z.x, z.y, z.z, (float)Math.cos(c/2));
		
		qy.mul(qx);
		qz.mul(qy);
		return qz;
		
	}
}
