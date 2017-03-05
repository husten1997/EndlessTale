uniform float scale;
uniform vec4 pos;
attribute vec4 vPosition;

varying vec2 v_TexCoordinate; //This will be passed into the fragment shader.

void main()
{
    // Pass through the texture coordinate.
    v_TexCoordinate = vPosition.xy;

    // multiply the vertex by the matrix to get the normalized screen coordinates.
    gl_Position = scale * vPosition + pos;
}