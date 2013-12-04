
#version 120

// uniforms -- same value is used for every vertex in model
uniform mat4 un_Projection;
uniform mat4 un_ModelView;
uniform mat3 un_NormalMatrix;

uniform vec3 un_AmbientColor;
uniform vec3 un_DiffuseColor;
uniform vec3 un_SpecularColor;
uniform float un_Shininess;

uniform vec3 un_LightPositions[16];
uniform vec3 un_LightIntensities[16];
uniform vec3 un_LightAmbientIntensity;

// Transform between the object's local frame and
// the "yellow" light-aligned frame in Figures 5/6/7
uniform mat4 un_FrameToObj;
uniform mat4 un_ObjToFrame;

// TODO: (Shaders 1 Problem 2) Declare any additional uniform variables here
uniform float un_maxPhi;
uniform float un_maxRadius;
uniform float flowerHeight;

// TODO: (Shaders 1 Problem 2) Declare any varying variables here
varying vec4 ex_bentVert;
varying vec4 ex_bentNorm;

void main()
{
	// TODO: (Shaders 1 Problem 2) Implement the fragment shader for the flower shader here
	
	vec3 unitToLight = vec3(0.0,0.0,0.0);
    
	//vec4 bentNormalized = normalize(ex_bentNorm);
	vec3 unitNormal = ex_bentNorm.xyz;
	vec3 colorRGB = un_AmbientColor * un_LightAmbientIntensity;
    
    vec3 unitToEye = normalize(-ex_bentVert.xyz);
	vec3 unitHalfVec = vec3(0.0,0.0,0.0);
    
    unitToLight = normalize(un_LightPositions[0] - ex_bentVert.xyz);
        
        // if ndotL < 0 don't add
        if (dot(unitNormal, unitToLight) > 0){
            unitHalfVec = normalize(unitToLight + unitToEye);
            colorRGB = colorRGB + un_LightIntensities[0] * un_DiffuseColor * max(dot(unitNormal, unitToLight), 0.0);
            colorRGB = colorRGB + un_LightIntensities[0] * un_SpecularColor * pow(max(dot(unitNormal, unitHalfVec), 0.0), un_Shininess);

    }

	gl_FragColor = vec4(colorRGB, 1.0f);
}

