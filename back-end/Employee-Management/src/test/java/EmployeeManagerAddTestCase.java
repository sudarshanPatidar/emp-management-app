import com.saturn.machines.hr.bl.exceptions.*;
import com.saturn.machines.hr.bl.pojo.*;
import com.saturn.machines.hr.bl.managers.*;
import com.saturn.machines.hr.bl.interfaces.pojo.*;
import com.saturn.machines.hr.bl.interfaces.managers.*;
import com.saturn.machines.enums.*;
import java.util.*;
import java.math.*;
public class EmployeeManagerAddTestCase
{
public static void main(String args[])
{
try
{
EmployeeInterface employee = new Employee();
employee.setName("Rakhi Sawant");
DesignationInterface designation = new Designation();

DesignationManagerInterface designationManager = DesignationManager.getDesignationManager();
// employee.setDesignation(designation);
// designation.setCode(2);

designation =  designationManager.getDesignationByCode(1);

employee.setDesignationCode(1);
employee.setAadharCardNumber("A1234567");
employee.setPANNumber("P1234567");
employee.setDateOfBirth(new Date());
employee.setIsIndian(true);
employee.setBasicSalary(new BigDecimal(1200000));
employee.setGender(GENDER.FEMALE);
EmployeeManagerInterface employeeManager = EmployeeManager.getEmployeeManager();
employeeManager.addEmployee(employee);
System.out.println("Employee added with emmployee Id. " + employee.getEmployeeId());
System.out.println("Title is  " + designation.getTitle());
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