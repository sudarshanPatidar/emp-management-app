import com.saturn.machines.hr.bl.interfaces.managers.*;
import com.saturn.machines.hr.bl.exceptions.*;
import com.saturn.machines.hr.bl.interfaces.pojo.*;
import com.saturn.machines.hr.bl.pojo.*;
import com.saturn.machines.hr.bl.managers.*;
import java.util.*;
import java.math.*;
import java.text.*;
class EmployeeManagerEmployeePANNumberExistsTestCase
{
public static void main(String arg[])
{
String panNumber = arg[0];
try{
EmployeeManagerInterface employeeManager;
employeeManager = EmployeeManager.getEmployeeManager();
boolean exists = employeeManager.employeePANNumberExists(panNumber);
System.out.println("Employee with pan number "+panNumber+" exists : "+exists);
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







