//The "StudentRecord" class.

/**
 * The class that stores and formats all of the records.
 * @author Angela Jeong
 * @version 2.0, June 5, 2014
 */
public class StudentRecord
{
  /**
   * The strings that store the first name, last name, attendance and phone number.
   */
  private String first, last, attendance, time, reason;
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
  public StudentRecord (String first, String last, String attendance, String time, String reason)
  {
    this.first = first;
    this.last = last;
    this.attendance = attendance;
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
  
  public void setAttendance (String newAttendance)
  {
    attendance = newAttendance;
  }
  
  public void setTime (String newTime)
  {
    time = formatTime(newTime);
  }
  
  public void setReason (String newReason)
  {
    reason = newReason;
  }
  
  public String getFirst()
  {
    return first;
  }
  
  public String getLast()
  {
    return last;
  }
  
  public String getAttendance()
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
  
  private String formatAttendance (String attendance)
  {
    if (attendance.equals("0") || attendance.toLowerCase().contains("absen"))
      attendance = "Absent";
    else if (attendance.equals("1") || attendance.toLowerCase().contains("in"))
      attendance = "Sign In";
    else
      attendance = "Sign Out";
    return attendance;
  }
  
  private String formatTime (String time)
  {
    time = time.replaceAll(" ","")+time.replaceAll(".","")+time.toUpperCase();
    if (time.length()==6)
    {
        return time.substring(0,2) + ":" + time.substring(2);
    }
    if (time.length()==7)
    {
      if (time.charAt (2) == ':')
        return time.substring (0, 4) + " " + time.substring (4);
      return time.substring(0,2) + ":" + time.substring(2);
    }
    else
      return time;
  }
}
