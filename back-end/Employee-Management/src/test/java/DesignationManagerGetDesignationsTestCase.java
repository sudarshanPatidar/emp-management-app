import com.saturn.machines.hr.bl.interfaces.managers.*;
import com.saturn.machines.hr.bl.exceptions.*;
import com.saturn.machines.hr.bl.interfaces.pojo.*;
import com.saturn.machines.hr.bl.pojo.*;
import com.saturn.machines.hr.bl.managers.*;
import java.util.*;
class DesignationManagerGetDesignationsTestCase
{
public static void main(String arg[])
{
try{
DesignationManagerInterface designationManager;
designationManager = DesignationManager.getDesignationManager();
Set<DesignationInterface> designations = designationManager.getDesignations();
designations.forEach((designation)->{
System.out.printf("Title : %s  Code : %d \n",designation.getTitle(),designation.getCode());
});
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