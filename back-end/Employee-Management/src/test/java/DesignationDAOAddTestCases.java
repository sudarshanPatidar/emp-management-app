import com.saturn.machines.hr.dl.dao.*;
import com.saturn.machines.hr.dl.dto.*;
import com.saturn.machines.hr.dl.interfaces.dao.*;
import com.saturn.machines.hr.dl.interfaces.dto.*;
import com.saturn.machines.hr.dl.exceptions.*;
public class DesignationDAOAddTestCases
{
public static void main(String arg[])
{
String title = arg[0];
try{
DesignationDTOInterface designationDTO;
designationDTO = new DesignationDTO();
designationDTO.setTitle(title);
DesignationDAOInterface designationDAO;
designationDAO = new DesignationDAO();
designationDAO.add(designationDTO);
System.out.println("Designation " +title+" added with code : "+ designationDTO.getCode());
}catch(DAOException e)
{
System.out.println(e.getMessage());
}
}
}



