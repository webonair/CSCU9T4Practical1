// This class holds information about a single training session
package com.stir.cscu9t4practical1;

public class SwimEntry extends Entry {
  //Venue: outdoors or pool?
  private String venue;
  
  public SwimEntry (String n, int d, int m, int y, int h, int min, int s, float dist, String ven) {
      
    super(n,d,m,y,h,min,s,dist);

    venue = ven;
    
  } //constructor

  public String getVenue() {
    return venue;
  } //getVenue
  
  @Override
  public String getEntry () {
   String result = getName()+" swam " + getDistance() + " km " + venue + " in "
             +getHour()+":"+getMin()+":"+ getSec() + " on "
             +getDay()+"/"+getMonth()+"/"+getYear()+ "\n";
   return result;
  } //getEntry
  
// For reference, JUnit assertion:
// "Alice swam 3.0 km outdoors in 0:16:7 on 1/2/2003\n"
   
} // EntrySwim