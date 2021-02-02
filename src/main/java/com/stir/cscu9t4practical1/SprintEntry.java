// This class holds information about a single training session
package com.stir.cscu9t4practical1;

public class SprintEntry extends Entry {
  private int reps;
  private float metres;
  private int recovery;
  
  public SprintEntry (String n, int d, int m, int y, int h, int min, int s, float dist, int rp, int rec) {
      
    super(n,d,m,y,h,min,s,dist);
 
    metres = dist / 1000;
    reps = rp;
    recovery = rec;
    
  } //constructor
  
  public int getRep() {
    return reps;
  } //getReps
    
  public int getRecovery() {
    return recovery;
  } //getReps

  @Override
  public String getEntry () {
   String result = getName()+" sprinted " + reps + "x" + metres + "m in "
             +getHour()+":"+getMin()+":"+ getSec() + " with " + recovery + " minutes recovery on "
             +getDay()+"/"+getMonth()+"/"+getYear()+ "\n";
   return result;
  } //getEntry
  
// For reference, JUnit assertion:
// "Alice sprinted 4x300m in 0:16:7 with 2 minutes recovery on 1/2/2003\n"
   
} // EntrySprint