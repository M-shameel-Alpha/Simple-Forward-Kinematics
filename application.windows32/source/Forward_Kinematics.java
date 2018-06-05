import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Forward_Kinematics extends PApplet {

// M-Shameel-Alpha
// 
// Forward Kinematics
// 
// Just Doing Crazy Stuff With Kinematics Visualizations.
//
Arm[] arms;
int size = 10;
float angle = 0;
int armlength = 20;

public void setup()
{
  
  
  arms = new Arm[size];
  
  for(int i=0;i<arms.length;i++)
  {
    if(i > 0)
    {
      arms[i] = new Arm(0,0,armlength,10,arms[i-1]);
    }else
    {
      arms[i] = new Arm(width/2,height/2,armlength,0);
    }
  }
  
}

public void draw()
{
  background(255);
  
  for(int i=0;i<arms.length;i++)
  {
    if(i > 0)
    {
      PVector startpos = arms[i-1].getEndPos();
      arms[i].setStartPos(startpos.x, startpos.y);
      arms[i].addAngle(sin(angle*i)); // Also Try sin( angle*i ), noise(angle)
      arms[i].calcEndPos();
      
      arms[i].display();
    }else
    {
      arms[i].setAngle(sin(angle)); // Also Try sin( angle*i ), noise(angle)
      arms[i].calcEndPos();
      arms[i].display();
    }
  }
  
  angle += 0.01f;
  
  stroke(255,0,0);
  strokeWeight(10);
  point(width/2,height/2);
}
// M-Shameel-Alpha
//
// Arm Class
//
class Arm
{
  PVector startPos;
  PVector endPos;
  int length;
  float angle;
  Arm parent = null;
  
  Arm(float x, float y, int _length, float _angle , Arm _parent)
  {
    //Initializing Variables
    startPos = new PVector(x,y);
    endPos = new PVector(0,0);
    length = _length;
    angle = _angle;
    parent = _parent;
  }
  
  Arm(float x, float y, int _length, float _angle)
  {
    //Initializing Variables  
    startPos = new PVector(x,y);
    endPos = new PVector(0,0);
    length = _length;
    angle = _angle;
  }
  
  public void calcEndPos()
  {
    //Calculating End Position With cos() and sin()
    float x;
    float y;
    if(parent == null){
      x = startPos.x + (cos(angle) * length);
      y = startPos.y + (sin(angle) * length);
    }else
    {
      float newAngle  = angle + parent.angle;
      x = startPos.x + (cos(newAngle) * length);
      y = startPos.y + (sin(newAngle) * length);
      
      //Just To Make Things Crazy
      angle = newAngle/2;
    }
    endPos.x = x;
    endPos.y = y;
  }
  
  public PVector getEndPos()
  {
    return endPos;
  }
  
  public void setStartPos(float x, float y)
  {
    startPos.x = x;
    startPos.y = y;
  }
  
  public void setAngle(float a)
  {
    angle = a;
  }
  
  public void addAngle(float a)
  {
    angle += a;
  }
  
  public void display()
  {
    println("start pos:" + startPos.x, ", " + startPos.y,"end pos:" + endPos.x,", " + endPos.y);
    
    //Drawing Line From startPos to endPos 
    stroke(255,0,0);
    strokeWeight(4);
    point(endPos.x,endPos.y);

    stroke(0);
    strokeWeight(5);
    line(startPos.x,startPos.y,endPos.x,endPos.y);
    

  }
}
  public void settings() {  size(500,500); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Forward_Kinematics" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
