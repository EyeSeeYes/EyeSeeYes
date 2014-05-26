//The "StudentRecord" class.

/**
 * The class that stores and formats all of the records.
 * @author Ms.Dyke, edited by Angela Jeong
 * @version 2.1, Mar 20, 2014
 * //NATHAN IS SUPER COOL
 */
public class StudentRecord
{
  /**
   * The strings that store the first name, last name, attendance and phone number.
   */
  private String first, last, time, reason;
  private int attendance;
  /**
   * The integer that stores the total number of records.
   */
  static int total = 0;
  
  /**
   * A class constructor - stores first name, last name, attendance, and phone number.
   * @param first String
   * @param last String
   * @param attendance int
   * @param phone String
   */
  public StudentRecord (String first, String last, String time, String reason)
  {
    this.first = first;
    this.last = last;
   // this.attendance = attendance;
    this.time = time;
    this.reason = reason;
    total++;
  }
  
  public void setFirst (String newName)
  {
    first = formatName(newName);
  }
  
  public void setLast (String newName)
  {
    last = formatName(newName);
  }
  
  public void setAttendance (String attendance)
  {
    attendance = attendance;
  }
  
  public void setTime (String newTime)
  {
    time = formatTime(newTime);
  }
  
  public String getFirst()
  {
    return first;
  }
  
  public String getLast()
  {
    return last;
  }
  
  public int getAttendance()
  {
    return attendance;
  }
  
  public String getTime()
  {
    return time;
  }
  
  public String getReason()
  {
    return reason;
  }
  
  private String formatName (String name)
  {
    String newName = "";
    String splitName[] = name.split(" ");
    
    for (int i=0; i<splitName.length; i++)
      newName += splitName[i].substring(0,1).toUpperCase() + splitName[i].substring(1).toLowerCase() + " ";
    
    return newName.substring(0,newName.length()-1);
  }
  
  private String formatTime (String time)
  {
    time = time.replaceAll(" ","")+time.replaceAll(".","");
    return time.substring(0,2) + ":" + time.substring(2);
  }
  
}
