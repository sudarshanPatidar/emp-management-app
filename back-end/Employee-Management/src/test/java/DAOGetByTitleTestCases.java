import com.saturn.machines.hr.dl.dao.*;
import com.saturn.machines.hr.dl.dto.*;
import com.saturn.machines.hr.dl.interfaces.dao.*;
import com.saturn.machines.hr.dl.interfaces.dto.*;
import com.saturn.machines.hr.dl.exceptions.*;
public class DAOGetByTitleTestCases
{
public static void main(String arg[])
{
String title = arg[0];
try{
DesignationDAOInterface designationDAO;
designationDAO = new DesignationDAO();
DesignationDTOInterface designationDTO;
designationDTO = designationDAO.getByTitle(title);
System.out.println("Designation " +designationDTO.getTitle()+" code : "+ designationDTO.getCode());
}catch(DAOException e)
{
System.out.println(e.getMessage());
}
}
}



