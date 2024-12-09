import com.saturn.machines.hr.dl.dao.*;
import com.saturn.machines.hr.dl.dto.*;
import com.saturn.machines.hr.dl.interfaces.dao.*;
import com.saturn.machines.hr.dl.interfaces.dto.*;
import com.saturn.machines.hr.dl.exceptions.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.lang.*;
public class EmployeeGetAllTestCases  
{
public static void main(String arg[])
{
try{
EmployeeDAOInterface employeeDAO;
employeeDAO = new EmployeeDAO();
Set<EmployeeDTOInterface> employees;
employees = new TreeSet<>();
employees = employeeDAO.getAll();
SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

for(EmployeeDTOInterface employeeDTO:employees)
{
System.out.println("Employee id : "+employeeDTO.getEmployeeId());
System.out.println("Name of the employee: "+employeeDTO.getName());
System.out.println("Designation code : "+employeeDTO.getDesignationCode());
System.out.println("Date of Birth : "+sdf.format(employeeDTO.getDateOfBirth()));
System.out.println("Gender : "+employeeDTO.getGender());
System.out.println("Is Indian : "+employeeDTO.getIsIndian());
System.out.println("Basic Salary : "+employeeDTO.getBasicSalary().toPlainString());
System.out.println("Pan number : "+employeeDTO.getPANNumber());
System.out.println("Aadhar Number : "+employeeDTO.getAadharCardNumber());
System.out.println("************************************");
}
}catch(DAOException e)
{
System.out.println(e.getMessage());
}
}
}



