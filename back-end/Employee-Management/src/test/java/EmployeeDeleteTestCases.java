import com.saturn.machines.enums.*;
import com.saturn.machines.hr.dl.dao.*;
import com.saturn.machines.hr.dl.dto.*;
import com.saturn.machines.hr.dl.interfaces.dao.*;
import com.saturn.machines.hr.dl.interfaces.dto.*;
import com.saturn.machines.hr.dl.exceptions.*;
import java.util.*;
import java.math.*;
import java.text.*; 
public class EmployeeDeleteTestCases
{
public static void main(String arg[])
{
String employeeId =arg[0];
try{
EmployeeDAOInterface employeeDAO;
employeeDAO = new EmployeeDAO();
employeeDAO.delete(employeeId);
System.out.println("Employee deleted with employee id : " + employeeId);
}catch(DAOException e)
{
System.out.println(e.getMessage());
}
}
}



