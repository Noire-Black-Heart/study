// ball location
float x=0, y=0, z=0;
// ball speeds
float xs=0, ys=0, zs=1;
// rotation of scene (viewpoint)
float rotz=0, rotx=0, roty=0;

float gravity = 0.2; 

void setup() {
  fullScreen(P3D);
  frameRate(60);
  x=0;
  y=-height/8;
  z=0;
  xs = 1;
}

void draw() {
  background(0);

  // Make 0,0,0 center of scene, rotate all axises
  translate(width/2, height/2, 0);
  rotateX(rotx);
  rotateY(roty);
  rotateZ(rotz);
  
  // Wire frame box in which the ball bounces
  pushMatrix();
  stroke(128);
  strokeWeight(5);
  noFill();
  translate(0, 0, 0);
  box(width/4, height/4, width/4);

  rotateX(PI/2);
  translate(0, 0, -height/8);
  
  popMatrix();

  // Bouncing ball
  pushMatrix();
  translate(x, y, z);
  noStroke();
  fill(128);
  sphere(20);
  popMatrix();

  // Update ball position, bounce if reach box edge (ball radius is 20)
  x=x+xs;
  if (x>(width/8) || x<-(width/8)) {
    x=x-xs;
    xs=-xs;
  }

  y=y+ys;
  if (y>(-20+height/8) || y<(-20-height/8)) {
    y=y-ys;
    ys=-ys;
  }

  z=z+zs;
  if (z>(width/8) || z<-(width/8)) {
    z=z-zs;
    zs=-zs;
  }

  // Add speed dependant on how much the plan is leaning 
  ys = ys + gravity;
  //xs = 0.2;
  
  // Rotate scene
  //roty+=0.009;

  
}
