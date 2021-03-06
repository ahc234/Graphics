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
		Vector3f p0 =  new Vector3f(sNode.translation);
		Vector3f p1 =  new Vector3f(eNode.translation);
		
		Vector3f p2 = new Vector3f(sNode.scaling);
		Vector3f p3 = new Vector3f(eNode.scaling);
		
		Vector3f p4 = new Vector3f(sNode.rotation);
		Vector3f p5 = new Vector3f(eNode.rotation);
		
		p0.scale(1-t);
		p1.scale(t);
		p1.add(p0);
		
		p2.scale(1-t);
		p3.scale(t);
		p3.add(p2);

		System.out.println("Translation: " + p1);
		System.out.println("Scale: " + p3);
		
		iNode.setTranslation(p1.x, p1.y, p1.z);
		iNode.setScaling(p3.x, p3.y, p3.z);
		catmullRomRotationInterpolation(p4,p4,p5,p5,t,iNode.rotation);
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
		
		Vector4f p0 = new Vector4f(sFloat[0], sFloat[1], sFloat[2], sFloat[3]);
		Vector4f p1 = new Vector4f(eFloat[0], eFloat[1], eFloat[2], eFloat[3]);
		
		p1.sub(p0);
		p1.scale(t);
		p1.add(p0);
		
		iFloat[0] = p1.x;
		iFloat[1] = p1.y;
		iFloat[2] = p1.z;
		iFloat[3] = p1.w;
		
		
	}
	
	public static void catmullRomInterpolate4Float(float [] p0, float [] p1, float [] p2, float [] p3,
			float t, float [] iNode)
	{
		// TODO (Animation P1): Implement if you find useful -- evaluate a Catmull-Rom spline on four
		// four-element-array "control points" and write result to iNode.
		
		float a = p0[0];
		float b = p0[1];
		float c = p0[2];
		float d = p0[3];
		
		float e = p1[0];
		float f = p1[1];
		float g = p1[2];
		float h = p1[3];
		
		float i = p2[0];
		float j = p2[1];
		float k = p2[2];
		float l = p2[3];
		
		float m = p3[0];
		float n = p3[1];
		float o = p3[2];
		float p = p3[3];
		
		float t2 = t*t;
		float t3 = t*t*t;
		
		float x = .5f*(-a*t3 + 3*e*t3 - 3*i*t3 + m*t3 + 2*a*t2 - 5*e*t2 + 4*i*t2 - m*t2 - a*t + i*t + 2*e);
		float y = .5f*(-b*t3 + 3*f*t3 - 3*j*t3 + n*t3 + 2*b*t2 - 5*f*t2 + 4*j*t2 - n*t2 - b*t + j*t + 2*f);
		float z = .5f*(-c*t3 + 3*g*t3 - 3*k*t3 + o*t3 + 2*c*t2 - 5*g*t2 + 4*k*t2 - o*t2 - c*t + k*t + 2*g);
		float w = .5f*(-d*t3 + 3*h*t3 - 3*l*t3 + p*t3 + 2*d*t2 - 5*h*t2 + 4*l*t2 - p*t2 - d*t + l*t + 2*h);
		
		iNode[0] = x;
		iNode[1] = y;
		iNode[2] = z;
		iNode[3] = w;
	}
	
	public static void catmullRomRotationInterpolation(Vector3f p0, Vector3f p1,
	        Vector3f p2, Vector3f p3, float t, Vector3f iNode)
	{
		// TODO (Animation P1): Implement if you find useful -- evaluate a Catmull-Rom spline
		// using quaternions with the given four control points, and write result to iNode.
		Quat4f q1 = getQuaternionFromEulerAngles(p1);
		Quat4f q2 = getQuaternionFromEulerAngles(p2);
		
		q1 = slerp(q1, q2, t);
		//q1.interpolate(q1, q2, t);
		
		iNode.set(getEulerAnglesFromQuaternion(q1));

	}
	
	public static void catmullRomInterpolation(Vector3f p0, Vector3f p1,
			Vector3f p2, Vector3f p3, float t, Vector3f iNode)
	{
		// TODO (Animation P1): Implement if you find useful -- evaluate a Catmull-Rom spline
		// with the given four control points, and write result to iNode.
		float a = p0.x;
		float b = p0.y;
		float c = p0.z;
		float d = p1.x;
		float e = p1.y;
		float f = p1.z;
		float g = p2.x;
		float h = p2.y;
		float i = p2.z;
		float j = p3.x;
		float k = p3.y;
		float l = p3.z;
		float t2 = t*t;
		float t3 = t*t*t;
		
		float x = (.5f)*(-a*t3 + 3*d*t3 - 3*g*t3 + j*t3 + 2*a*t2 - 5*d*t2 + 4*g*t2 - j*t2 - a*t + g*t + 2*d);
		float y = (.5f)*(-b*t3 + 3*e*t3 - 3*h*t3 + k*t3 + 2*b*t2 - 5*e*t2 + 4*h*t2 - k*t2 - b*t + h*t + 2*e);
		float z = (.5f)*(-c*t3 + 3*f*t3 - 3*i*t3 + l*t3 + 2*c*t2 - 5*f*t2 + 4*i*t2 - l*t2 - c*t + i*t + 2*f);
		
		iNode.set(new Vector3f(x, y, z));

	}
	
	public static Quat4f slerp(Quat4f i1, Quat4f i2, float t)
	{
		// TODO (Animation P1): Implement slerp.
		
		Vector4f vecQ1 = new Vector4f(i1);
		Vector4f vecQ2 = new Vector4f(i2);
		
		float dotProd = vecQ1.dot(vecQ2);
		
		if (dotProd < 0) {
			dotProd = -dotProd;
			i2.scale(-1f);
		}
		
		if (1.0 - Math.abs(dotProd) > 0.001f) {
			float psi = (float)Math.acos(dotProd);
			Quat4f q = new Quat4f(i1);
			q.scale((float)Math.sin((1-t)*psi));
			Quat4f i2Deep = new Quat4f(i2);
			i2Deep.scale((float)Math.sin(t*psi));
			q.add(i2Deep);
			q.scale(1f/(float)Math.sin(psi));
				
			return q;
		} else {
			i2.sub(i1);
			i2.scale(t);
			i2.add(i1);
			
			return i2;
		} 
		
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
