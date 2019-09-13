 
import processing.sound.*;
SoundFile song;

void setup() {
  size(500, 500);
 
    
  // Load a soundfile from the /data folder of the sketch and play it back
  song = new SoundFile(this, "../../W6LabData/audio_sample.wav");
  song.play();
  song.loop();
}      

void draw() {
  
   background(0);
  
}

void mouseClicked() {
  // add your code here.
  
  
}
