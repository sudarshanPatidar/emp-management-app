import com.saturn.machines.hr.bl.exceptions.*;
import com.saturn.machines.hr.bl.pojo.*;
import com.saturn.machines.hr.bl.managers.*;
import com.saturn.machines.hr.bl.interfaces.pojo.*;
import com.saturn.machines.hr.bl.interfaces.managers.*;
import com.saturn.machines.enums.*;
import java.util.*;
import java.math.*;
public class EmployeeManagerUpdateTestCase
{
public static void main(String args[])
{
try
{
EmployeeInterface employee = new Employee();
employee.setEmployeeId("  A10000003");
employee.setName("Suresh Sharma");
//DesignationInterface designation = new Designation();
employee.setDesignationCode(3);
//designation.setCode(4);
employee.setAadharCardNumber("a123456");
employee.setPANNumber("p123456");
employee.setDateOfBirth(new Date());
employee.setIsIndian(false);
employee.setBasicSalary(new BigDecimal(800000));
employee.setGender(GENDER.MALE);
EmployeeManagerInterface employeeManager = EmployeeManager.getEmployeeManager();
employeeManager.updateEmployee(employee);
System.out.println("Employee Updated ");
}
catch(BLException blException)
{
if(blException.hasGenericException())
{
System.out.println(blException.getMessage());
}
List<String> properties = blException.getProperties();
for(String property:properties)
{
System.out.println(blException.getException(property));
}
}
}
}