import com.saturn.machines.enums.*;
import com.saturn.machines.hr.dl.dao.*;
import com.saturn.machines.hr.dl.dto.*;
import com.saturn.machines.hr.dl.interfaces.dao.*;
import com.saturn.machines.hr.dl.interfaces.dto.*;
import com.saturn.machines.hr.dl.exceptions.*;
import java.util.*;
import java.math.*;
import java.text.*; 
public class EmployeeUpdateTestCases
{
public static void main(String arg[])
{
String employeeId = arg[0]; 
String name = arg[1];
int designationCode = Integer.parseInt(arg[2]);
SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
Date dateOfBirth=null;
try
{
dateOfBirth=sdf.parse(arg[3]);
}catch(ParseException pe)
{
System.out.println(pe.getMessage());
return;
}
char gender = arg[4].charAt(0);
boolean isIndian = Boolean.parseBoolean(arg[5]);
BigDecimal basicSalary = new BigDecimal(arg[6]);
String panNumber = arg[7];
String aadharCardNumber = arg[8];
try
{
EmployeeDTOInterface eDTO;
eDTO = new EmployeeDTO();
eDTO.setEmployeeId(employeeId);
eDTO.setName(name);
eDTO.setDesignationCode(designationCode);
eDTO.setDateOfBirth(dateOfBirth);
if(gender == 'M')
{
eDTO.setGender(GENDER.MALE);
}
if(gender == 'F')
{
eDTO.setGender(GENDER.FEMALE);
}

eDTO.setIsIndian(isIndian);
eDTO.setBasicSalary(basicSalary);
eDTO.setPANNumber(panNumber);
eDTO.setAadharCardNumber(aadharCardNumber);
EmployeeDAOInterface eDAO;
eDAO = new EmployeeDAO();
eDAO.update(eDTO);
System.out.println("Employee updated");
}catch(DAOException e)
{
System.out.println(e.getMessage());
}
}
}



