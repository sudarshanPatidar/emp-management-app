import com.saturn.machines.enums.*;
import com.saturn.machines.hr.bl.interfaces.pojo.EmployeeInterface;
import com.saturn.machines.hr.dl.dao.*;
import com.saturn.machines.hr.dl.dto.*;
import com.saturn.machines.hr.dl.interfaces.dao.*;
import com.saturn.machines.hr.dl.interfaces.dto.*;
import com.saturn.machines.hr.dl.exceptions.*;
import java.util.*;
import java.math.*;
import java.text.*; 
public class EmployeeAddTestCases
{
public static void main(String arg[])
{

String name = arg[0];
int designationCode = Integer.parseInt(arg[1]);
SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
Date dateOfBirth=null;
try
{
dateOfBirth=sdf.parse(arg[2]);
}catch(ParseException pe)
{
System.out.println(pe.getMessage());
return;
}
char gender = arg[3].charAt(0);
boolean isIndian = Boolean.parseBoolean(arg[4]);
BigDecimal basicSalary = new BigDecimal(arg[5]);
String panNumber = arg[6];
String aadharCardNumber = arg[7];
try
{
EmployeeDTOInterface eDTO;
eDTO = new EmployeeDTO();
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
eDAO.add(eDTO);
String employeeId = eDTO.getEmployeeId();
System.out.println("Employee with employee ID : "+employeeId + " added");
}catch(DAOException e)
{
System.out.println(e.getMessage());
}
}
}



