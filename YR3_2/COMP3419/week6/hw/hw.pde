//import the library required: minim
import ddf.minim.*;
 
Minim minim;
AudioPlayer song;
 
void setup()
{
  size(500, 500);
 
  minim = new Minim(this);
 
  // this loads mysong.wav from the data folder
  song = minim.loadFile("../W6LabData/audio_drum.wav");
  song.play();
  song.loop(); //comment it if you do not want to make it loop
}
 
void draw()
{
  background(0); 
}

void mouseClicked() {
  // Submission Info - replace them with your name/unikey
  String studentname = "Shuwei Zhang";
  String Unikey = "470425437";
  
  // Enter your code here
  if(mouseButton == LEFT){
    song.pause();
  }
  else if(mouseButton == RIGHT){
    song.play();
  }
  else if(mouseButton == CENTER){
    song.rewind();
  }
  
  
  // Useful page: https://processing.org/reference/mouseButton.html
  
  // Some preseved variable you may want to use: [mouseButton], [LEFT], [RIGHT]
  // Some function that you may want to use: [xx.pause()], [xx.rewind()], [xx.play()]
}
