import com.saturn.machines.hr.bl.exceptions.*;
import com.saturn.machines.hr.bl.pojo.*;
import com.saturn.machines.hr.bl.managers.*;
import com.saturn.machines.hr.bl.interfaces.pojo.*;
import com.saturn.machines.hr.bl.interfaces.managers.*;
import com.saturn.machines.enums.*;
import java.util.*;
import java.math.*;
public class EmployeeManagerRemoveTestCase
{
public static void main(String gg[])
{
String employeeId = gg[0];
try
{
EmployeeManagerInterface employeeManager = EmployeeManager.getEmployeeManager();
employeeManager.removeEmployee(employeeId);
System.out.println("Employee with employee Id. "+employeeId+" removed.");
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