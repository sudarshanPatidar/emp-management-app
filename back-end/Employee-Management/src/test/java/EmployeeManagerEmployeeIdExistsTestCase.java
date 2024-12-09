import com.saturn.machines.hr.bl.interfaces.managers.*;
import com.saturn.machines.hr.bl.exceptions.*;
import com.saturn.machines.hr.bl.interfaces.pojo.*;
import com.saturn.machines.hr.bl.pojo.*;
import com.saturn.machines.hr.bl.managers.*;
import java.util.*;
import java.math.*;
import java.text.*;
class EmployeeManagerEmployeeIdExistsTestCase
{
public static void main(String arg[])
{
String employeeId = arg[0];
try{
EmployeeManagerInterface employeeManager;
employeeManager = EmployeeManager.getEmployeeManager();
boolean exists = employeeManager.employeeIdExists(employeeId);
System.out.println("Employees Id. "+employeeId+" exists : "+exists);
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







