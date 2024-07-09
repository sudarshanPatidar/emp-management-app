import com.saturn.machines.hr.dl.dao.*;
import com.saturn.machines.hr.dl.interfaces.dao.*;
import com.saturn.machines.hr.dl.exceptions.*;
public class DAOGetCountTestCases
{
public static void main(String arg[])
{
try{
DesignationDAOInterface designationDAO;
designationDAO = new DesignationDAO();
System.out.println("Record Count : " +  designationDAO.getCount());
}catch(DAOException e)
{
System.out.println(e.getMessage());
}
}
}



