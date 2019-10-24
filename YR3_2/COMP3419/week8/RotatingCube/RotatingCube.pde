int framectr = 0;
void setup(){
  size(640, 360, P3D);
}

void put_a_cube_at_here(){
  beginShape();
  //vertex(100, 100, -100);
  //vertex(-100, 100, -100);
  //vertex(-100, 100, 100);
  //vertex(100, 100, 100);
  
  vertex(100, -100, 100);
  vertex(-100, -100, 100);
  vertex(-100, -100, -100);
  vertex(100, -100, -100);
  vertex(-100, -100, -100);
  vertex(-100, -100, 100);
  vertex(100, -100, 100);
  
  vertex(100, 100, 100);
  vertex(-100, 100, 100);
  vertex(-100, -100, 100);
  vertex(100, -100, 100);
  vertex(-100, -100, 100);
  vertex(-100, 100, 100);
  vertex(100, 100, 100);
  vertex(100, -100, 100);
  vertex(-100, -100, 100);
  vertex(-100, -100, -100);
  
  vertex(100, -100, -100);
  vertex(-100, -100, -100);
  vertex(-100, 100, -100);
  vertex(100, 100, -100);
  vertex(-100, 100, -100);
  vertex(-100, -100, -100);
  vertex(-100, -100, 100);
  vertex(100, -100, 100);
  vertex(100, 100, 100);
  
  vertex(-100, 100, 100);
  vertex(-100, 100, -100);
  vertex(-100, -100, -100);
  vertex(100, -100, -100);
  vertex(100, 100, -100);
  vertex(100, 100, 100);
  vertex(100, -100, 100);
  vertex(100, -100, -100);
  
  endShape();
}

void draw(){
  background(0);
  translate(width/2, height/2, 0);
  stroke(255);
  // Submission Info - for student to fill in
  String title_name = "%s(%s)'s rotating cube";
  String student_name = "Shuwei Zhang"; // replace it with your full name
  String unikey = "szha3901"; // replace it with your Unikey
  frame.setTitle(String.format(title_name, student_name, unikey));
  
  // TODO-start: Given this template code, complete the following section to make it rotate
  
  // tip: Some variable you may want to use: [framectr]
  // tip: Some preserved variable you may want to use: [PI]
  
  rotateY(PI * framectr /180);
  //rotateX(PI * framectr / 180) ;

  // TODO-end
  
  put_a_cube_at_here(); 
  framectr ++;
}
