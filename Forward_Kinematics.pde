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

void setup()
{
  size(500,500);
  
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

void draw()
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
  
  angle += 0.01;
  
  stroke(255,0,0);
  strokeWeight(10);
  point(width/2,height/2);
}
