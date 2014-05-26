import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.JToolBar;
import javax.swing.JFrame;
import javax.swing.table.*;
import java.util.*;
import java.io.*;

public class FileIO extends JPanel implements ActionListener
{

  /*This method saves any changes to the file being edited
   */
  protected void saveFile()
  {
     
    if (fileName == null || fileName.equals ("") || fileName.length () > 255)
    {
      saveAs();
    }
    else
    {
      save();   
    }
    isSaved = true;
    setPersons();
  }
  /*This method saves the current file as a new file
   * @param filter  ExampleFilter used to create a fileFilter
   * @param e  ActionEvent used
   * @param result  used to open a fileChooser
   * @param fileChooser  JFileChooser used
   */
  protected void saveAs()
  {
    ExampleFileFilter filter = new ExampleFileFilter ( );
    filter.addExtension ( "bum" );
    filter.setDescription ( "AddressBook Lists" );  
    JFileChooser fileChooser = new JFileChooser (".\\Documents");   
    fileChooser.setFileFilter (filter);   
    fileChooser.setFileSelectionMode (JFileChooser.FILES_ONLY);  
    int result = fileChooser.showSaveDialog (this);
    try
    {
      checkFile = fileChooser.getSelectedFile ();
      fileName = fileChooser.getSelectedFile ().toString();
    }
    catch (Exception e)
    { 
    }
     
    if (fileName == null || fileName.equals ("") || fileName.length () > 255)
    {
      //JOptionPane.showMessageDialog (this, "Invalid File Name", "Invalid File Name", JOptionPane.ERROR_MESSAGE);
      fileName = null;
    }
     
    else if (checkFile.exists() && !checkFile.isDirectory())
    {
      int reply = JOptionPane.showConfirmDialog(null, "That file already exists. Would you like to overwrite it?", "File Already Exists", JOptionPane.YES_NO_OPTION);
      if (reply == JOptionPane.NO_OPTION)
      {
        fileChooser.cancelSelection();
      }
      else
      {
        save();
      }
       
    } 
    else
    {
      save(); 
    }
  }
   
  /*This method is used to save files, it writes the information into the file and is called in other methods
   * @param output  PRintWriter used to output into a new file
   */
  private void save ()
  {
    isSaved = true;
    if (!fileName.endsWith (".bum"))
      fileName += ".bum";
    PrintWriter output;   
    try
    {
      output = new PrintWriter (new FileWriter (fileName));
      output.println ("SWAGMONEY");
      for (int x = 0; x < persons.size();x++)
      {
        PersonRecord pr = persons.get (x);
        output.println (pr.getFirstName());
        output.println (pr.getLastName());
        output.println (pr.getPhoneNumber());
        output.println (pr.getEmail());
      }
      output.close();
    }
    catch (Exception e)
    {
    }
    setPersons();
  }
  /**
   * This method uses JFileChooser to open a file chooser and allow
   * the user to select the file they wish to open (as long as it 
   * fits the filters set).
   * 
   *@param  filter  the instance of ExampleFileFilter to allow for filtering of shown files
   *@param  fileName  the name of the file to be selected and stored
   *@param  fileChooser  the instance of JFileChooser being used in this program
   *@param  result  integer used in the selection of files
   *@param  e  used to check for exceptions while reading the contents of the selected file
   */
  protected void openFile ()
  {
    if (!isSaved)
    {
      int reply = JOptionPane.showConfirmDialog(null, "Your current file has not been saved. Would you like to save it before continuing?", "File Has Not Been Saved", JOptionPane.YES_NO_OPTION);
      if (reply == JOptionPane.NO_OPTION)
      {
        open();
      }
      else
      { 
        saveFile();
        open ();
      }
    }
    else
      open();
  }
   
  /**
   * This method uses JFileChooser to open a file chooser and allow
   * the user to select the file they wish to open (as long as it 
   * fits the filters set).
   * 
   *@param  filter  the instance of ExampleFileFilter to allow for filtering of shown files
   *@param  fileName  the name of the file to be selected and stored
   *@param  fileChooser  the instance of JFileChooser being used in this program
   *@param  result  integer used in the selection of files
   *@param  e  used to check for exceptions while reading the contents of the selected file
   */
  private void open ()
  {
    isSaved = true;
    firstNameLine = "";
    ExampleFileFilter filter = new ExampleFileFilter ( );
    filter.addExtension ( "bum" );
    filter.setDescription ( "AddressBook Lists" );    
    JFileChooser fileChooser = new JFileChooser (".\\Documents");   
    fileChooser.setFileFilter (filter);   
    fileChooser.setFileSelectionMode (JFileChooser.FILES_ONLY);  
    int result = fileChooser.showOpenDialog (this);
     
    try
    {
      fileName = fileChooser.getSelectedFile ().toString();
    }
    catch (Exception e)
    {
    }
    if (fileName == null || fileName.equals ("") || fileName.length () > 255)
    {
      //JOptionPane.showMessageDialog (this, "Invalid File Name", "Invalid File Name", JOptionPane.ERROR_MESSAGE);
      fileName = "";
    }
    else
    {
      try
      {
        BufferedReader input = new BufferedReader (new FileReader (fileName));
        headerLine = input.readLine ();        
      }
      catch (Exception e)
      {
        if (fileName != null && !fileName.equals (""))
          JOptionPane.showMessageDialog (this, "Invalid File Name", "Invalid File Name", JOptionPane.ERROR_MESSAGE);
      }
      if (!headerLine.equals ("SWAGMONEY"))
      {
        JOptionPane.showMessageDialog (this, "Invalid File Header", "Invalid File Header", JOptionPane.ERROR_MESSAGE);
        headerLine = "";
        openFile();
      }
      else
      {
        persons.removeAll(persons);
        try
        {
          BufferedReader input = new BufferedReader (new FileReader (fileName));
          input.readLine ();
          while (firstNameLine != null)
          {
            firstNameLine = input.readLine ();
            lastNameLine = input.readLine ();
            phoneNumberLine = input.readLine ();
            eMailLine = input.readLine ();
            PersonRecord pr = new PersonRecord (firstNameLine, lastNameLine, phoneNumberLine, eMailLine);
            persons.add (pr);
          }
        }
        catch (Exception e)
        {
        } 
      }
    }
    setPersons();
  }
  /**
   * This method erases the contents of the personRecord arraylist and shows a blank template as if the user had just opened the program
   */
  protected void newFile()
  {
    fileName = null;
    isSaved = true;
    persons.removeAll(persons);
    firstNameField.setText ("");
    lastNameField.setText ("");
    phoneNumberField.setText ("");
    eMailField.setText ("");
    recordNumber = 0;
    setPersons();
  }
   



   
}