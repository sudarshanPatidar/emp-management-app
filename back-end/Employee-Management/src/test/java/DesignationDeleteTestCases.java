import com.saturn.machines.hr.dl.dao.*;
import com.saturn.machines.hr.dl.dto.*;
import com.saturn.machines.hr.dl.interfaces.dao.*;
import com.saturn.machines.hr.dl.interfaces.dto.*;
import com.saturn.machines.hr.dl.exceptions.*;
public class DesignationDeleteTestCases
{
public static void main(String arg[])
{
int code =Integer.parseInt(arg[0]);
try{
DesignationDAOInterface designationDAO;
designationDAO = new DesignationDAO();
designationDAO.delete(code);
System.out.println("Designation with code : "+ code +" Deleted");
}catch(DAOException e)
{
System.out.println(e.getMessage());
}
}
}



