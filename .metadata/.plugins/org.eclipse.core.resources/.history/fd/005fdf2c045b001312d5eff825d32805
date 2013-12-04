package cs4620.scene;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.media.opengl.GL2;
import javax.vecmath.Vector3f;

public class LightNodeKeyframeable extends LightNode implements Keyframeable {
	private static final long serialVersionUID = 7302614078550499605L;
	
	protected TreeMap<Integer, LightNode> keyframes = new TreeMap<Integer, LightNode>();
	
	public LightNodeKeyframeable()
	{
		super();
		keyframes.put(0, new LightNode());
	}

	public LightNodeKeyframeable(String name)
	{
		super(name);
		keyframes.put(0, new LightNode(name));
	}
	
	public LightNodeKeyframeable(String name, int [] frames)
	{
		super(name);
		for(int f : frames)
		{
			keyframes.put(f, new LightNode(name));
		}
	}
	
	public void setToLightNode(LightNode node)
	{
		setIntensity(node.intensity[0], node.intensity[1], node.intensity[2]);
		translation.set(node.translation);
	}

	@Override
	public int[] getFrameNumbers() {
		Integer [] integerArray = keyframes.keySet().toArray(new Integer[0]);
		int [] outArray = new int[integerArray.length];
		for(int i = 0; i < outArray.length; i++)
			outArray[i] = integerArray[i];
		return outArray;
	}

	@Override
	public void addAsKeyframe(int frame) {
		LightNode keyframeNode = new LightNode(getName());
		
		keyframeNode.setIntensity(intensity[0], intensity[1], intensity[2]);
		keyframeNode.translation.set(translation);
		
		keyframes.put(frame, keyframeNode);
	}
	
	@Override
	public void applyToAllKeyframes()
	{
		int [] keyframeNumbers = getFrameNumbers();
		
		for(int f : keyframeNumbers)
		{
			addAsKeyframe(f);
		}
	}

	@Override
	public void deleteKeyframe(int frame) {
		keyframes.remove(new Integer(frame));
	}

	@Override
	public void linearInterpolateTo(int frame) {
		// TODO (Animation P1): Set the state of this light to the specified frame by
		// linearly interpolating the states of the appropriate keyframes.
		
		/*if (keyframes.containsKey(frame)){
			translation.set(keyframes.get(frame).translation);
		}else{
		LightNode f1 = keyframes.get(frame-1);
		LightNode f0 = keyframes.get(frame-2);
		LightNode f2 = keyframes.get(frame+1);
		LightNode f3 = keyframes.get(frame+2);
		
		// TODO (Animation P1): Set the state of this light to the specified frame by 
		// interpolating the states of the appropriate keyframes using Catmull-Rom splines.
		KeyframeAnimation.catmullRomInterpolation(f1.translation, f0.translation, f2.translation, f3.translation, frame, this.translation);*/
		
	}

	@Override
	public void catmullRomInterpolateTo(int frame) {
		if (keyframes.containsKey(frame)){
			setToLightNode(keyframes.get(frame));
		}else{
		int lastkeyframe= keyframes.lastKey();
		if (frame > lastkeyframe) setToLightNode(keyframes.get(lastkeyframe));
		else{
			
			
		Map.Entry<Integer,LightNode> mf1 = keyframes.floorEntry(frame);
		Map.Entry<Integer,LightNode> mf0 = keyframes.floorEntry(mf1.getKey()-1);
		Map.Entry<Integer,LightNode> mf2 = keyframes.higherEntry(frame);
		Map.Entry<Integer,LightNode> mf3 = keyframes.higherEntry(mf2.getKey());
		
		if (mf0 == null) mf0 = mf1;
		if (mf3 == null) mf3 = mf2;
			
		LightNode f1 = mf1.getValue();
		LightNode f0 = mf0.getValue();
		LightNode f2 = mf2.getValue();
		LightNode f3 = mf3.getValue();
		
//		System.out.println(f1.translation);
//		System.out.println(f0.translation);
//		System.out.println(f2.translation);
//		System.out.println(f3.translation);
		// TODO (Animation P1): Set the state of this light to the specified frame by 
		// interpolating the states of the appropriate keyframes using Catmull-Rom splines.
	//	System.out.println("Before Translation: " + this.translation);
		float t1 = mf2.getKey() - mf1.getKey();
		float t2 = frame - mf1.getKey();
		float t = t2 /t1;
		
		//KeyframeAnimation.catmullRomInterpolate4Float(f0.intensity, f1.intensity, f2.intensity, f3.intensity, t, this.intensity);
		KeyframeAnimation.catmullRomInterpolation(f0.translation, f1.translation, f2.translation, f3.translation, t, this.translation);
		KeyframeAnimation.catmullRomRotationInterpolation(f0.rotation, f1.rotation, f2.rotation, f3.rotation, t, this.rotation);
		KeyframeAnimation.catmullRomInterpolation(f0.scaling, f1.scaling, f2.scaling, f3.scaling, t, this.scaling);
			}
		}
		
	}
	
	@Override
	public void setName(String name) {
		this.name = name;
		if(keyframes != null)
		{
			for(Entry<Integer, LightNode> entry : keyframes.entrySet())
			{
				entry.getValue().setName(name);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Object getYamlObjectRepresentation()
	{
		Map<String, Object> result = (Map<String, Object>)super.getYamlObjectRepresentation();
		result.put("type", "LightNodeKeyframeable");
		
		Map<Object, Object> framesMap = new HashMap<Object, Object>();
		for (Entry<Integer, LightNode> entry: keyframes.entrySet())
		{
			framesMap.put(entry.getKey(), entry.getValue().getYamlObjectRepresentation());
		}
		result.put("frames", framesMap);
		
		return result;
	}
	
	public void extractFramesFromYamlObject(GL2 gl, Object yamlObject)
	{
		if (!(yamlObject instanceof Map))
			throw new RuntimeException("yamlObject not a Map");
		Map<?,?> yamlMap = (Map<?,?>)yamlObject;
		
		Map<Object, Object> framesMap = (Map) yamlMap.get("frames");
		
		for (Entry<Object, Object> entry: framesMap.entrySet())
		{
			int frameIndex = Integer.parseInt(entry.getKey().toString());
			LightNode node = (LightNode) LightNode.fromYamlObject(gl, entry.getValue());
			keyframes.put(frameIndex, node);
		}
	}
	
	public static SceneNode fromYamlObject(GL2 gl, Object yamlObject)
	{
		if (!(yamlObject instanceof Map))
			throw new RuntimeException("yamlObject not a Map");
		Map<?,?> yamlMap = (Map<?,?>)yamlObject;

		LightNodeKeyframeable result = new LightNodeKeyframeable((String)yamlMap.get("name"));
		result.setName((String)yamlMap.get("name"));
		result.addChildrenFromYamlObject(gl, yamlObject);
		result.extractTransformationFromYamlObject(yamlObject);
		result.extractLightFromYamlObject(yamlObject);
		result.extractFramesFromYamlObject(gl, yamlObject);

		return result;
	}

}
