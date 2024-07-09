import com.saturn.machines.hr.dl.dao.*;
import com.saturn.machines.hr.dl.dto.*;
import com.saturn.machines.hr.dl.interfaces.dao.*;
import com.saturn.machines.hr.dl.interfaces.dto.*;
import com.saturn.machines.hr.dl.exceptions.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.lang.*;
public class EmployeeIsDesignationAllotedTestCases  
{
public static void main(String arg[])
{
int designationCode = Integer.parseInt(arg[0]);
try{
EmployeeDAOInterface employeeDAO;
employeeDAO = new EmployeeDAO();
System.out.println("Designation code "+designationCode+" exists : "+employeeDAO.isDesignationAlloted(designationCode));
}catch(DAOException e)
{
System.out.println(e.getMessage());
}
}
}



