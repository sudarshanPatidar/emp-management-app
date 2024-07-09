import com.saturn.machines.hr.dl.dao.*;
import com.saturn.machines.hr.dl.interfaces.dao.*;
import com.saturn.machines.hr.dl.exceptions.*;
public class DAOTitleExistsTestCases
{
public static void main(String arg[])
{
String title = arg[0];
try{
DesignationDAOInterface designationDAO;
designationDAO = new DesignationDAO();
boolean x = designationDAO.titleExists(title);
System.out.println("Title : "+ title +" exists : " + x);
}catch(DAOException e)
{
System.out.println(e.getMessage());
}
}
}



