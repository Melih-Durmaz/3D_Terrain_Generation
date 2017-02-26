import processing.core.PApplet;

public class MainProgram extends PApplet {
	int cols,rows;
	int scale = 20;//Scale of each triangle strip
	int shapeWidth = 1200;
	int shapeHeight = 1600;
	float flyingSpeed = 0;
	float[][] vertexHeights;
	float heightColor;

	public static void main(String[] args) {
		PApplet.main("MainProgram");
	}
	
	public void setup(){
		fill(90);
		cols = shapeWidth/scale;
		rows = shapeHeight/scale;
		vertexHeights = new float[cols][rows];
		
		
	}
	
	public void settings() {
		size(600,600,P3D);
	}
	
	public void draw() {
		flyingSpeed -= 0.08;
		float yOffset = flyingSpeed;
		for(int i = 0; i < rows;i++){
			float xOffset = 0;
			for(int j = 0;j < cols;j++){
				//Map random "heights" with Perlin noise.
				vertexHeights[j][i] =  map(noise(xOffset,yOffset),0,1,-150,105);
				xOffset += 0.15;
			}
			yOffset += 0.15;
		}
		background(0);
		
		//Centers the terrain
		translate(width/2, height/2 + 50);
		
		//Rotates the terrain over the X axis
		rotateX(PI/(float)2.5);
		
		translate(-shapeWidth/2, -shapeHeight/2);
		
		//Create the terrain and with random heights for each vertex
		for(int i = 0; i < rows-1;i++){
			beginShape(TRIANGLE_STRIP);
			for(int j = 0;j < cols;j++){
				heightColor = vertexHeights[j][i] + 150;
				//Add color to the terrain based on the height
				fill(heightColor,255-heightColor,255-heightColor);
				stroke(heightColor,255-heightColor,heightColor);
				//Draw the vertexes.
				vertex(j*scale,i*scale,vertexHeights[j][i]);
				vertex(j*scale,(i+1)*scale,vertexHeights[j][i+1]);				
			}
			endShape();
		}		
	}
}
