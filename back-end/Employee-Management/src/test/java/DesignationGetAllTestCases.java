import com.saturn.machines.hr.dl.dao.*;
import com.saturn.machines.hr.dl.dto.*;
import com.saturn.machines.hr.dl.interfaces.dao.*;
import com.saturn.machines.hr.dl.interfaces.dto.*;
import com.saturn.machines.hr.dl.exceptions.*;
import java.util.*;
public class DesignationGetAllTestCases  
{
public static void main(String arg[])
{
try{
DesignationDAOInterface designationDAO;
designationDAO = new DesignationDAO();
Set<DesignationDTOInterface> designations;
designations = new TreeSet<>();
designations = designationDAO.getAll();
designations.forEach((designationDTO) -> {
System.out.printf("Code : %d ,Designation : %s \n",designationDTO.getCode(),designationDTO.getTitle());
});
System.out.println("************************************");
Iterator<DesignationDTOInterface> it;
DesignationDTOInterface designationDTO;
designationDTO = new DesignationDTO();
it=designations.iterator();
while(it.hasNext())
{
designationDTO = it.next();
System.out.printf("Code : %d ,Designation : %s \n",designationDTO.getCode(),designationDTO.getTitle());
}
}catch(DAOException e)
{
System.out.println(e.getMessage());
}
}
}



