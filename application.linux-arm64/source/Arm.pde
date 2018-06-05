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
  
  void calcEndPos()
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
  
  PVector getEndPos()
  {
    return endPos;
  }
  
  void setStartPos(float x, float y)
  {
    startPos.x = x;
    startPos.y = y;
  }
  
  void setAngle(float a)
  {
    angle = a;
  }
  
  void addAngle(float a)
  {
    angle += a;
  }
  
  void display()
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
