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

uniform sampler2D un_NoiseTexture;
uniform sampler2D un_FireTexture;
uniform float un_Time;

uniform vec3 un_ScrollSpeeds;

// TODO (Shaders 2 P3): Declare varying variables here
varying vec3 ex_Vertex;
varying vec2 ex_TexCoord;
varying float noise;

void main() {
	// TODO (Shaders 2 P3): Implement the fire fragment shader here
    vec2 set1 = (ex_TexCoord);
    vec2 set2 = (ex_TexCoord * 2);
    vec2 set3 = (ex_TexCoord * 3);
    
    set1.y = set1.y + un_Time * un_ScrollSpeeds.x;
    set2.y = set2.y + un_Time * un_ScrollSpeeds.y;
    set3.y = set3.y + un_Time * un_ScrollSpeeds.z;
    
    set1.x = mod(set1.x , 1.0);
    set2.x = mod(set2.x , 1.0);
    set3.x = mod(set3.x , 1.0);
    set1.y = mod(set1.y , 1.0);
    set2.y = mod(set2.y , 1.0);
    set3.y = mod(set3.y , 1.0);

    
    vec4 colorRGB1 = texture2D(un_NoiseTexture, set1);
    vec4 colorRGB2 = texture2D(un_NoiseTexture, set2);
    vec4 colorRGB3 = texture2D(un_NoiseTexture, set3);
    
    vec4 colorRGB = (colorRGB1/3 + colorRGB2/3 + colorRGB3/3);
    
    colorRGB = texture2D(un_FireTexture, vec2(colorRGB.r, colorRGB.g));
    
	gl_FragColor = vec4(colorRGB.rgb,1);
    
}