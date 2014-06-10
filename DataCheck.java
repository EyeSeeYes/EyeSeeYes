public class DataCheck 
{
  public static boolean checkName (String name)
  {
    if (name.equals (null) || name.equals (""))
      return false;
    else
    {
      for (int x = 0; x <= name.length () - 1; x++)
      {
        if (name.charAt (x) < 64 || name.charAt (x) > 91)
        {
          if (name.charAt (x) < 97 || name.charAt (x) > 123)
            return false;
        }        
      }
    }
    return true;
  }
  public static boolean checkTime (String time)
  {
    if (time.equals (null) || time.equals (""))
      return false;
    if (time.length () < 6 || time.length () > 8)
      return false;
    if (time.length () == 6)
    {
      if (Character.isDigit (time.charAt (0)) && Character.isDigit (time.charAt (1)) && Character.isDigit (time.charAt (2)) && Character.isDigit (time.charAt (3)) && time.charAt (5) == 'M')
      return true;
    }
    if (time.length() == 7)
    {
      if (Character.isDigit (time.charAt (0)) && Character.isDigit (time.charAt (1)))
      {
        if (time.charAt (2) == ':' && Character.isDigit (time.charAt (3)) && Character.isDigit (time.charAt (4)) && time.charAt (6) == 'M')
          return true;
        if (Character.isDigit (time.charAt (0)) && Character.isDigit (time.charAt (1)) && Character.isDigit (time.charAt (2)) && Character.isDigit (time.charAt (3)) && time.charAt (4) == ' ' &&time.charAt (6) == 'M')
          return true;
      return false;
    }
      return false;
  } 
    if (time.length () == 8)
    {
       if (Character.isDigit (time.charAt (0)) && Character.isDigit (time.charAt (1)) && time.charAt (2) == ':' && Character.isDigit (time.charAt (3)) && Character.isDigit (time.charAt (4))  && time.charAt (5) == ' ' && time.charAt (7) == 'M')
         return true;
       return false;
    }
    return false;
}
}
