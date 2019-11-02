

PImage texture; 
ArrayList<Ball> balls= new ArrayList();


//ball class
class Ball{
    float x, y, z;
    float vx, vy, vz;
    PShape ballShape;
    //constructor
    public Ball(float x,float y){
      
      ballShape = createShape(SPHERE, 20);
      ballShape.setStroke(false);
      //position
      this.x = x;
      this.y = y;
      this.z = 0;
      //speed
      this.vx = random(-20, 20);
      this.vy = random(-20, 20);
      this.vz = random(-40, -1);
      //skin
      int k = int(random(0, 3));
      if(k == 0){
        texture = loadImage("./t1.jpg");
    }else if(k == 1){
      texture = loadImage("./t2.jpg");
    }else{
      texture = loadImage("./t3.jpg");
    }
    ballShape.setTexture(texture);
    //shape
    pushMatrix();
    translate(x, y, z);
    shape(this.ballShape);
    popMatrix();
    
    println(x + " " + y + " " + z);
    }
    
    //update the ball stat
    void update(){ 
      
          x=x+vx;
          y=y+vy;
          z=z+vz;
          
      //apply friction to ball, friction is 0,99
          vx = vx * 0.99;
          vy = vy * 0.99;
          vz = vz * 0.98;
          
      //apply gravity to ball
          
          vy = vy +0.05;
          
          //check if the ball is stopped
          if(vx < 0.0001 && vy < 0.04 && vz < 0.0001){
            //vx = 0;
            vy = 0;
            //vz = 0;
          }
          
      //shape ball
      pushMatrix();
      translate(x,y,z);
      shape(this.ballShape);
      popMatrix();
          
    }
    
    
    //ball bounce with wall
    void bounce(){
          //collision with wall detection: box is 0 - 600, ball is 20
          if (x>580 || x<20) {
            x=x-vx;
            vx=-vx;
          }
        
          if (y>580 || y<20) {
            y=y-vy;
            vy=-vy;
          }
        
          if (z>20 || z<-580) {
            z=z-vz;
            vz=-vz;
          }
    }
      //collision with other balls detection: 
      void collide(Ball other){
          float d = dist(x, y, z, other.x, other.y, other.z);
          
          if(d < 40){//r = 20, d<40 == collide
            vx = -vx;
            vy = -vy;
            vz = -vz;
            other.vx = -other.vx;
            other.vy = -other.vy;
            other.vz = -other.vz;
          }
      }
          
}


void setup() {
  size(600, 600, P3D);
  background(0);
  noStroke();
}

void draw(){
  background(0);
  lights();
  ambientLight(5, 5, 5);
  //draw the cube frame
  pushMatrix();
  translate(width/2, height/2);
 
  stroke(128);
  strokeWeight(5);
  noFill();
  translate(0, 0, 0);
  box(600, 600, 600);

  popMatrix();
  
  //loop through ball list and check
  for(Ball ball: balls){
    println(ball.x, ball.y, ball.z, ball.vx, ball.vy, ball.vz);
      ball.bounce();
      ball.update();
      //ball.bounce();
      for(Ball ball2 : balls){
        if(ball != ball2){
          ball.collide(ball2);
        }
      }
  }
  
   
}

void mouseClicked(){
   balls.add(new Ball(mouseX, mouseY));
}
