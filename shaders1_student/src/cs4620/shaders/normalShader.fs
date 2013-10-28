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

// TODO: (Shaders 1 Problem 1) Declare any varying variables here
varying vec3 ex_Normal;

void main()
{
	// TODO: (Shaders 1 Problem 1) Implement the fragment shader for
	// the normal shader here
    
    vec3 colorRGB = vec3(0.0,0.0,0.0);
	colorRGB.x = (ex_Normal.x + 1 )/2;
    colorRGB.y = (ex_Normal.y + 1 )/2;
    colorRGB.z = (ex_Normal.z + 1 )/2;

    gl_FragColor = vec4(colorRGB, 1);
}
