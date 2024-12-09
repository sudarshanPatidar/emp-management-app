import com.saturn.machines.hr.bl.interfaces.managers.*;
import com.saturn.machines.hr.bl.exceptions.*;
import com.saturn.machines.hr.bl.interfaces.pojo.*;
import com.saturn.machines.hr.bl.pojo.*;
import com.saturn.machines.hr.bl.managers.*;
import java.util.*;
import java.math.*;
import java.text.*;
class EmployeeManagerGetEmployeeByAadharCardNumberTestCase
{
public static void main(String arg[])
{
String aadharCardNumber = arg[0];
SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
try{
EmployeeManagerInterface employeeManager;
employeeManager = EmployeeManager.getEmployeeManager();
EmployeeInterface dsEmployee = employeeManager.getEmployeeByAadharCardNumber(aadharCardNumber);
System.out.println("Employee id : "+dsEmployee.getEmployeeId());
System.out.println("Name of the employee: "+dsEmployee.getName());
DesignationInterface designation = new Designation();
//designation = dsEmployee.getDesignation();
 int designationCode = dsEmployee.getDesignationCode();
System.out.println("Designation code : "+designationCode);
System.out.println("Designation title : "+designation.getTitle());
System.out.println("Date of Birth : "+sdf.format(dsEmployee.getDateOfBirth()));
System.out.println("Gender : "+dsEmployee.getGender());
System.out.println("Is Indian : "+dsEmployee.getIsIndian());
System.out.println("Basic Salary : "+dsEmployee.getBasicSalary().toPlainString());
System.out.println("Pan number : "+dsEmployee.getPANNumber());
System.out.println("Aadhar Number : "+dsEmployee.getAadharCardNumber());
}catch(BLException blException)
{
if(blException.hasGenericException()) System.out.println(blException.getGenericException());
List<String> properties = blException.getProperties();
for(String property:properties)
{
System.out.println(blException.getException(property));
}
}
}
}







