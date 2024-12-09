import com.saturn.machines.hr.dl.dao.*;
import com.saturn.machines.hr.dl.dto.*;
import com.saturn.machines.hr.dl.interfaces.dao.*;
import com.saturn.machines.hr.dl.interfaces.dto.*;
import com.saturn.machines.hr.dl.exceptions.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.lang.*;
public class EmployeeGetCountTestCases
{
public static void main(String arg[])
{
try{
EmployeeDAOInterface eDAO;
eDAO = new EmployeeDAO();
System.out.println("Total no of employees : " + eDAO.getCount());
}catch(DAOException e)
{
System.out.println(e.getMessage());
}
}
}



