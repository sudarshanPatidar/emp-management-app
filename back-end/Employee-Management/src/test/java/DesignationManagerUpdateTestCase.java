import com.saturn.machines.hr.bl.interfaces.managers.*;
import com.saturn.machines.hr.bl.exceptions.*;
import com.saturn.machines.hr.bl.interfaces.pojo.*;
import com.saturn.machines.hr.bl.pojo.*;
import com.saturn.machines.hr.bl.managers.*;
import java.util.*;
class DesignationManagerUpdateTestCase
{
public static void main(String arg[])
{
DesignationInterface designation = new Designation();
designation.setCode(2);
designation.setTitle("Lawyer");
try{
DesignationManagerInterface designationManager;
designationManager = DesignationManager.getDesignationManager();
designationManager.updateDesignation(designation);
System.out.println("Designation updated");
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