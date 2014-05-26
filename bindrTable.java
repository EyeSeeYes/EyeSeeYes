public void JTableMine ()
{
  setLayout (new BorderLayout());
  
  String [] columnTitles = {"Period" , "Time" , "Name" , "In/Out/Absent" , "Reason"};
  
  Color lightBlue = new Color (175, 229, 240); //light blue color for the text
  Color lightPink = new Color (247, 190, 214); //light pink color for the background
  Color lightPurple = new Color (210, 175, 227); //light purple color for the grid lines
  
  model = new DefaultTableModel (dataValues , columnTitles){
    //@Override
    public boolean cellEdit ( int row , int column) {
      return column == 0? false:true;
    }};
  
  table = new JTable (model);
  
  table.setColumnSelectionAllowed (false);
  table.setCellSelectionEnabled (false);
  table.setRowSelectionAllowed (true);
  
  table.setRowHeight (20);  //works
  table.setShowVerticalLines (true); //works
  table.setShowHorizontalLines (true); //works
  
  table.setSelectionForeground (lightBlue);
  table.setSelectionBackground (lightPink);
  table.setGridColor (lightPurple);
  table.setVisible (true);
  
  table.getModel(). addTableModelListener (new TableModelListener() {
    //@Override
    public void tableChanged (TableModelEvent e)
    {
      TableModel model = (TableModel)e.getSource();
      int rowNum = table.getSelectedRow();
      
      //need to edit based on what needs to be datachecked.
      String first = (String)model.getValueAt(rowNum , 1);
      String last = (String)model.getValueAt(rowNum , 2);
      String phone = (String)model.getValueAt(rowNum , 3);
      
      PersonRecord p = new PersonRecord (first, last, phone, (String)model.getValueAt(rowNum , 4));
      persons.set(rowNum , p);
      
      if (!first.isEmpty())
      {
        p.setFirst(first);
      }
      if (!last.isEmpty())
      {
        p.setLast(last);
      }
      if (phone.isEmpty())
      {
        p.setPhone(phone);
      }
    }});
  
  scrollBar = new JScrollPane (table);
  add (scrollBar, BorderLayout.CENTER);
  
  currentLayout = false;
}

public void createData ()
{
  dataValues = new String [persons.size()][5];
  
  for (int y = 0; y < persons.size(); y++)
  {
    dataValues [y][0] = ""+ (y+1);
    dataValues [y][1] = persons.get (y).getFirstName();
    dataValues[y][2] = persons.get (y).getLastName();
    dataValues[y][3] = persons.get (y).getPhoneNum();
    dataValues[y][4] = persons.get (y).getEmail();
  }
  //return dataValues;
}