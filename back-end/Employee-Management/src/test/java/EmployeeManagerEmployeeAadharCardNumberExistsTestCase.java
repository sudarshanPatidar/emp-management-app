import com.saturn.machines.hr.bl.interfaces.managers.*;
import com.saturn.machines.hr.bl.exceptions.*;
import com.saturn.machines.hr.bl.interfaces.pojo.*;
import com.saturn.machines.hr.bl.pojo.*;
import com.saturn.machines.hr.bl.managers.*;
import java.util.*;
import java.math.*;
import java.text.*;
class EmployeeManagerEmployeeAadharCardNumberExistsTestCase
{
public static void main(String arg[])
{
String aadharCardNumber = arg[0];
try{
EmployeeManagerInterface employeeManager;
employeeManager = EmployeeManager.getEmployeeManager();
boolean exists = employeeManager.employeeAadharCardNumberExists(aadharCardNumber);
System.out.println("Employee with aadhar card number "+aadharCardNumber+" exists : "+exists);
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







