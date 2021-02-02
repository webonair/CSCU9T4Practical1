// This class holds information about a single training session
package com.stir.cscu9t4practical1;

public class CycleEntry extends Entry {
  private String surface;
  private String tempo;
  
  public CycleEntry (String n, int d, int m, int y, int h, int min, int s, float dist, String sce, String tpo) {
      
    super(n,d,m,y,h,min,s,dist);
    
    surface = sce;
    tempo = tpo;
    
  } //constructor

  public String getSurface() {
    return surface;
  } //getSurface
  
  public String getTempo() {
    return tempo;
  } //getTempo

  @Override
  public String getEntry () {
   String result = getName()+" ran " + getDistance() + " km in "
             +getHour()+":"+getMin()+":"+ getSec() + " on "
             +getDay()+"/"+getMonth()+"/"+getYear()+ " on " 
             + surface + " at " + tempo + " tempo\n";
   return result;
  } //getEntry
  
// For reference, JUnit assertion:
// "Alice cycled 3.0 km in 0:16:7 on 1/2/2003 on asphalt at moderate tempo\n"
   
} // EntryCycle