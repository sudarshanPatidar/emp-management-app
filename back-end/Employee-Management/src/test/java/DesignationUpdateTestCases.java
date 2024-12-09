import com.saturn.machines.hr.dl.dao.*;
import com.saturn.machines.hr.dl.dto.*;
import com.saturn.machines.hr.dl.interfaces.dao.*;
import com.saturn.machines.hr.dl.interfaces.dto.*;
import com.saturn.machines.hr.dl.exceptions.*;
public class DesignationUpdateTestCases
{
public static void main(String arg[])
{
int code = Integer.parseInt(arg[0]);
String title = arg[1];
try{
DesignationDTOInterface designationDTO;
designationDTO = new DesignationDTO();
designationDTO.setTitle(title);
designationDTO.setCode(code);
DesignationDAOInterface designationDAO;
designationDAO = new DesignationDAO();
designationDAO.update(designationDTO);
System.out.println("Designation : " +title+" updated with code : "+ designationDTO.getCode());
}catch(DAOException e)
{
System.out.println(e.getMessage());
}
}
}



