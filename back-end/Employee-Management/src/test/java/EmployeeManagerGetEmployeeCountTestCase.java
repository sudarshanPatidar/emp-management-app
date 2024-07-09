import com.saturn.machines.hr.bl.interfaces.managers.*;
import com.saturn.machines.hr.bl.exceptions.*;
import com.saturn.machines.hr.bl.interfaces.pojo.*;
import com.saturn.machines.hr.bl.pojo.*;
import com.saturn.machines.hr.bl.managers.*;
import java.util.*;
import java.math.*;
import java.text.*;
class EmployeeManagerGetEmployeeCountTestCase
{
public static void main(String arg[])
{
try{
EmployeeManagerInterface employeeManager;
employeeManager = EmployeeManager.getEmployeeManager();
int employeeCount = employeeManager.getEmployeeCount();
System.out.println("Total count of employees is : "+employeeCount);
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




