attribute vec3 position;

varying vec3 color;

void main(void){

    gl_Position = vec4(position, 1.0);
    color = vec3(position.x+0.5, 1.0, position.y+0.5);

}