import com.saturn.machines.hr.dl.dao.*;
import com.saturn.machines.hr.dl.interfaces.dao.*;
import com.saturn.machines.hr.dl.exceptions.*;
public class DAOCodeExistsTestCases
{
public static void main(String arg[])
{
int code = Integer.parseInt(arg[0]);
try{
DesignationDAOInterface designationDAO;
designationDAO = new DesignationDAO();
boolean x = designationDAO.codeExists(code);
System.out.println("Code : "+ code +" exists : " + x);
}catch(DAOException e)
{
System.out.println(e.getMessage());
}
}
}



