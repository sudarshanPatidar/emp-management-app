package com.saturn.machines.hr.dl.dao;
import com.saturn.machines.hr.dl.interfaces.dao.*;
import com.saturn.machines.hr.dl.dto.*;
import com.saturn.machines.hr.dl.interfaces.dto.*;
import com.saturn.machines.hr.dl.dao.*;
import com.saturn.machines.hr.dl.exceptions.*;
import com.saturn.machines.enums.*;
import java.util.*;
import java.io.*;
import java.text.*;
import java.math.*;
import java.lang.*;
public class EmployeeDAO implements EmployeeDAOInterface
{
//private static final String FILE_NAME="employee.data";
private static final String FILE_NAME = "D:\\Sudarshan\\back\\Employee-Management\\employee.data";

public void add(EmployeeDTOInterface employeeDTO) throws DAOException
{
if(employeeDTO==null) throw new DAOException("Employee is null");
String employeeId;
String name = employeeDTO.getName();
if(name==null) throw new DAOException("Name is null");
name = name.trim();
if(name.length()==0) throw new DAOException("Length of name is 0");
int designationCode = employeeDTO.getDesignationCode();
if(designationCode<=0) throw new DAOException("Designation code is invalid");
DesignationDAOInterface designationDAO;
designationDAO = new DesignationDAO();
boolean isDesignationCodeValid = false;
isDesignationCodeValid = designationDAO.codeExists(designationCode);
if(isDesignationCodeValid==false) throw new DAOException("Designation code is invalid"); 
Date dateOfBirth = employeeDTO.getDateOfBirth();
if(dateOfBirth==null) throw new DAOException("Date is null");
char gender = employeeDTO.getGender();
if(gender == ' ') throw new DAOException("Invalid gender must be (MALE/FEMALE)");
boolean isIndian = employeeDTO.getIsIndian();
BigDecimal basicSalary = employeeDTO.getBasicSalary();
if(basicSalary==null) throw new DAOException("Salary is null");
if(basicSalary.signum()==-1) throw new DAOException("Salary can't be negative");
String panNumber = employeeDTO.getPANNumber();
if(panNumber == null) throw new DAOException("Pan number is null");
panNumber = panNumber.trim();
if(panNumber.length()==0) throw new DAOException("Length of pan number is 0");
String aadharCardNumber = employeeDTO.getAadharCardNumber();
if(aadharCardNumber == null) throw new DAOException("Aadhar card number is null");
aadharCardNumber = aadharCardNumber.trim();
if(aadharCardNumber.length()==0) throw new DAOException("Length of aadhar card number is 0");
try
{
int lastGeneratedEmployeeId=10000000;
String lastGeneratedEmployeeIdString="";
int recordCount=0;
String recordCountString="";
File file = new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile = new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
lastGeneratedEmployeeIdString=String.format("%-10s","10000000");
randomAccessFile.writeBytes(lastGeneratedEmployeeIdString+"\n");
recordCountString = String.format("%-10s","0");
randomAccessFile.writeBytes(recordCountString+"\n");
}
else
{
lastGeneratedEmployeeIdString = randomAccessFile.readLine().trim();
lastGeneratedEmployeeId=Integer.parseInt(lastGeneratedEmployeeIdString);
recordCountString = randomAccessFile.readLine().trim(); 
recordCount = Integer.parseInt(recordCountString);
}
boolean panNumberExists=false;
boolean aadharCardNumberExists=false;
String fPANNumber;
String fAadharCardNumber; 
int x;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
for(x=0;x<7;x++) randomAccessFile.readLine();
fPANNumber = randomAccessFile.readLine(); 
fAadharCardNumber = randomAccessFile.readLine(); 
if(panNumberExists==false && fPANNumber.equalsIgnoreCase(panNumber))
{
panNumberExists=true;
}
if(aadharCardNumberExists==false && fAadharCardNumber.equalsIgnoreCase(aadharCardNumber))
{
aadharCardNumberExists=true;
}
if(panNumberExists==true && aadharCardNumberExists==true) break;
}
if(panNumberExists && aadharCardNumberExists)
{
randomAccessFile.close();
throw new DAOException("Pan and Aadhar card number exists");
}
if(panNumberExists)
{
randomAccessFile.close();
throw new DAOException("Pan number exists");
}
if(aadharCardNumberExists)
{
randomAccessFile.close();
throw new DAOException("Aadhar card number exists");
}
lastGeneratedEmployeeId++;
lastGeneratedEmployeeIdString = String.format("%-10d",lastGeneratedEmployeeId);
recordCount++;
recordCountString= String.format("%-10d",recordCount);
employeeId = "A"+lastGeneratedEmployeeId;
randomAccessFile.writeBytes(employeeId+"\n");
randomAccessFile.writeBytes(name+"\n");
randomAccessFile.writeBytes(designationCode+"\n");
SimpleDateFormat simpleDateFormat;
simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
randomAccessFile.writeBytes(simpleDateFormat.format(dateOfBirth)+"\n");
randomAccessFile.writeBytes(gender+"\n");
randomAccessFile.writeBytes(isIndian+"\n");
randomAccessFile.writeBytes(basicSalary.toPlainString()+"\n");
randomAccessFile.writeBytes(panNumber+"\n");
randomAccessFile.writeBytes(aadharCardNumber+"\n");
randomAccessFile.seek(0);
randomAccessFile.writeBytes(lastGeneratedEmployeeIdString+"\n");
randomAccessFile.writeBytes(recordCountString+"\n");
employeeDTO.setEmployeeId(employeeId);
randomAccessFile.close();
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}


public void update(EmployeeDTOInterface employeeDTO) throws DAOException
{
if(employeeDTO==null) throw new DAOException("Employee is null");
String employeeId = employeeDTO.getEmployeeId();
if(employeeId == null) throw new DAOException("Employee id is null");
if(employeeId.length()==0) throw new DAOException("Length of Employee id is null" + employeeId);
String name = employeeDTO.getName();
if(name==null) throw new DAOException("Name is null");
name = name.trim();
if(name.length()==0) throw new DAOException("Length of name is 0");
int designationCode = employeeDTO.getDesignationCode();
if(designationCode<=0) throw new DAOException("Designation code is invalid");
DesignationDAOInterface designationDAO;
designationDAO = new DesignationDAO();
boolean isDesignationCodeValid = false;
isDesignationCodeValid = designationDAO.codeExists(designationCode);
if(isDesignationCodeValid==false) throw new DAOException("Designation code is invalid"); 
Date dateOfBirth = employeeDTO.getDateOfBirth();
if(dateOfBirth==null) throw new DAOException("Date is null");
char gender = employeeDTO.getGender();
if(gender == ' ') throw new DAOException("Invalid gender must be (MALE/FEMALE)");
boolean isIndian = employeeDTO.getIsIndian();
BigDecimal basicSalary = employeeDTO.getBasicSalary();
if(basicSalary==null) throw new DAOException("Salary is null");
if(basicSalary.signum()==-1) throw new DAOException("Salary can't be negative");
String panNumber = employeeDTO.getPANNumber();
if(panNumber == null) throw new DAOException("Pan number is null");
panNumber = panNumber.trim();
if(panNumber.length()==0) throw new DAOException("Length of pan number is 0");
String aadharCardNumber = employeeDTO.getAadharCardNumber();
if(aadharCardNumber == null) throw new DAOException("Aadhar card number is null");
aadharCardNumber = aadharCardNumber.trim();
if(aadharCardNumber.length()==0) throw new DAOException("Length of aadhar card number is 0");
try
{
File file = new File(FILE_NAME);
if(file.exists()==false) throw new DAOException("Invalid employee id : " + employeeId);
RandomAccessFile randomAccessFile;
randomAccessFile = new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid employee id : " + employeeId);
}
randomAccessFile.readLine();
int recordCount=Integer.parseInt(randomAccessFile.readLine().trim());
SimpleDateFormat simpleDateFormat;
simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
String fEmployeeId;
String fPANNumber; 
String fAadharCardNumber;
int x;
boolean employeeIdFound = false;
boolean panNumberFound = false;
boolean aadharCardNumberFound = false;
String panFoundAgainstEmployeeId = "";
String aadharFoundAgainstEmployeeId = "";
long foundAt = 0;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
if(employeeIdFound==false) foundAt = randomAccessFile.getFilePointer(); 
fEmployeeId = randomAccessFile.readLine();
for(x=1;x<=6;x++) randomAccessFile.readLine();
fPANNumber = randomAccessFile.readLine();
fAadharCardNumber = randomAccessFile.readLine();
if(employeeIdFound==false && fEmployeeId.equalsIgnoreCase(employeeId))
{
employeeIdFound=true;
}
if(panNumberFound == false && fPANNumber.equalsIgnoreCase(panNumber))
{
panNumberFound = true;
panFoundAgainstEmployeeId = fEmployeeId;
}
if(aadharCardNumberFound == false && fAadharCardNumber.equalsIgnoreCase(aadharCardNumber))
{
aadharCardNumberFound = true;
aadharFoundAgainstEmployeeId = fEmployeeId;
}
if(employeeIdFound && panNumberFound && aadharCardNumberFound) break;
}
if(employeeIdFound==false)
{
randomAccessFile.close();
throw new DAOException("Invalid employee id : " + employeeId);
}
boolean panNumberExists = false;
if(panNumberFound && panFoundAgainstEmployeeId.equalsIgnoreCase(employeeId)==false)
{
panNumberExists = true;
}
boolean aadharCardNumberExists = false;
if(aadharCardNumberFound && aadharFoundAgainstEmployeeId.equalsIgnoreCase(employeeId)==false)
{
aadharCardNumberExists = true;
}
if(panNumberExists && aadharCardNumberExists)
{
randomAccessFile.close();
throw new DAOException("Pan number : " + panNumber + " and aadhar card number : "+ aadharCardNumber + " exists...");
}
if(panNumberExists)
{
randomAccessFile.close();
throw new DAOException("Pan number : " + panNumber +" exists...");
}
if(aadharCardNumberExists)
{
randomAccessFile.close();
throw new DAOException("Aadhar card number : "+ aadharCardNumber + " exists...");
}
randomAccessFile.seek(foundAt);
for(x=1;x<=9;x++) randomAccessFile.readLine();
File tmpFile = new File("tmp.tmp");
if(tmpFile.exists()) tmpFile.delete();
RandomAccessFile tmpRandomAccessFile;
tmpRandomAccessFile = new RandomAccessFile(tmpFile,"rw");
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine() + "\n");
}
randomAccessFile.seek(foundAt);
randomAccessFile.writeBytes(employeeId + "\n");
randomAccessFile.writeBytes(name + "\n");
randomAccessFile.writeBytes(designationCode + "\n");
randomAccessFile.writeBytes(simpleDateFormat.format(dateOfBirth) + "\n");
randomAccessFile.writeBytes(gender + "\n");
randomAccessFile.writeBytes(isIndian + "\n");
randomAccessFile.writeBytes(basicSalary.toPlainString() + "\n");
randomAccessFile.writeBytes(panNumber + "\n");
randomAccessFile.writeBytes(aadharCardNumber + "\n");
tmpRandomAccessFile.seek(0);
while(tmpRandomAccessFile.getFilePointer()<tmpRandomAccessFile.length())
{
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine() + "\n");
}
randomAccessFile.setLength(randomAccessFile.getFilePointer());
randomAccessFile.close();
tmpRandomAccessFile.setLength(0);
tmpRandomAccessFile.close();
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}


public void delete(String employeeId) throws DAOException
{
if(employeeId == null) throw new DAOException("Employee id is null");
if(employeeId.length()==0) throw new DAOException("Length of Employee id is null" + employeeId);
try
{
File file = new File(FILE_NAME);
if(file.exists()==false) throw new DAOException("Invalid employee id 1: " + employeeId);
RandomAccessFile randomAccessFile;
randomAccessFile = new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid employee id 2: " + employeeId);
}
randomAccessFile.readLine();
int recordCount=Integer.parseInt(randomAccessFile.readLine().trim());
String fEmployeeId;
int x;
boolean employeeIdFound = false;
long foundAt = 0;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
foundAt = randomAccessFile.getFilePointer(); 
fEmployeeId = randomAccessFile.readLine();
for(x=1;x<=8;x++) randomAccessFile.readLine();
if(fEmployeeId.equalsIgnoreCase(employeeId))
{
employeeIdFound=true;
break;
}
}
if(employeeIdFound==false)
{
randomAccessFile.close();
throw new DAOException("Invalid employee id 3: " + employeeId);
}
File tmpFile = new File("tmp.tmp");
if(tmpFile.exists()) tmpFile.delete();
RandomAccessFile tmpRandomAccessFile;
tmpRandomAccessFile = new RandomAccessFile(tmpFile,"rw");
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine() + "\n");
}
randomAccessFile.seek(foundAt);
tmpRandomAccessFile.seek(0);
while(tmpRandomAccessFile.getFilePointer()<tmpRandomAccessFile.length())
{
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine() + "\n");
}
randomAccessFile.setLength(randomAccessFile.getFilePointer());
recordCount--;
String recordCountString = String.format("%-10d", recordCount);
randomAccessFile.seek(0);
randomAccessFile.readLine();
randomAccessFile.writeBytes(recordCountString+"\n");
randomAccessFile.close();
tmpRandomAccessFile.setLength(0);
tmpRandomAccessFile.close();
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}


public Set<EmployeeDTOInterface> getAll() throws DAOException
{
Set<EmployeeDTOInterface> employees;
employees = new TreeSet<>();
try{
File file = new File(FILE_NAME);
if(file.exists()==false) return employees;
RandomAccessFile randomAccessFile;
randomAccessFile = new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return employees;
}
randomAccessFile.readLine();
int recordCount=Integer.parseInt(randomAccessFile.readLine().trim());
if(recordCount==0)
{
randomAccessFile.close();
return employees;
}
EmployeeDTOInterface employeeDTO;
SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
char fGender;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
employeeDTO = new EmployeeDTO();
employeeDTO.setEmployeeId(randomAccessFile.readLine());
employeeDTO.setName(randomAccessFile.readLine());
employeeDTO.setDesignationCode(Integer.parseInt(randomAccessFile.readLine()));
try{
employeeDTO.setDateOfBirth(simpleDateFormat.parse(randomAccessFile.readLine()));
}catch(ParseException pe)
{
throw new DAOException(pe.getMessage());
}
fGender = randomAccessFile.readLine().charAt(0);
if(fGender == 'M')
{
employeeDTO.setGender(GENDER.MALE);
}
if(fGender == 'F')
{
employeeDTO.setGender(GENDER.FEMALE);
}
employeeDTO.setIsIndian(Boolean.parseBoolean(randomAccessFile.readLine()));
employeeDTO.setBasicSalary(new BigDecimal(randomAccessFile.readLine()));
employeeDTO.setPANNumber(randomAccessFile.readLine());
employeeDTO.setAadharCardNumber(randomAccessFile.readLine());
employees.add(employeeDTO);
}
randomAccessFile.close();
return employees;
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}


public Set<EmployeeDTOInterface> getByDesignationCode(int designationCode) throws DAOException
{
if(!(new DesignationDAO().codeExists(designationCode))) 
{
throw new DAOException("Invalid Code : "+ designationCode);
}
Set<EmployeeDTOInterface> employees;
employees = new TreeSet<>();
try{
File file = new File(FILE_NAME);
if(file.exists()==false) return employees;
RandomAccessFile randomAccessFile;
randomAccessFile = new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return employees;
}
randomAccessFile.readLine();
int recordCount=Integer.parseInt(randomAccessFile.readLine().trim());
if(recordCount==0)
{
randomAccessFile.close();
return employees;
}
String fEmployeeId;
String fName;
int fDesignationCode;
EmployeeDTOInterface employeeDTO;
SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
char fGender;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fEmployeeId = randomAccessFile.readLine();
fName = randomAccessFile.readLine();
fDesignationCode = Integer.parseInt(randomAccessFile.readLine());
if(fDesignationCode!= designationCode)
{
for(int x=0;x<6;x++) randomAccessFile.readLine();
continue;
}
employeeDTO = new EmployeeDTO();
employeeDTO.setEmployeeId(fEmployeeId);
employeeDTO.setName(fName);
employeeDTO.setDesignationCode(fDesignationCode);
try{
employeeDTO.setDateOfBirth(simpleDateFormat.parse(randomAccessFile.readLine()));
}catch(ParseException pe)
{
throw new DAOException(pe.getMessage());
}
fGender = randomAccessFile.readLine().charAt(0);
if(fGender == 'M')
{
employeeDTO.setGender(GENDER.MALE);
}
if(fGender == 'F')
{
employeeDTO.setGender(GENDER.FEMALE);
}
employeeDTO.setIsIndian(Boolean.parseBoolean(randomAccessFile.readLine()));
employeeDTO.setBasicSalary(new BigDecimal(randomAccessFile.readLine()));
employeeDTO.setPANNumber(randomAccessFile.readLine());
employeeDTO.setAadharCardNumber(randomAccessFile.readLine());
employees.add(employeeDTO);
}
randomAccessFile.close();
return employees;
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}


public boolean isDesignationAlloted(int designationCode) throws DAOException
{
if(!(new DesignationDAO().codeExists(designationCode))) 
{
throw new DAOException("Invalid Code : "+ designationCode);
}
try{
File file = new File(FILE_NAME);
if(file.exists()==false) return false;
RandomAccessFile randomAccessFile;
randomAccessFile = new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return false;
}
randomAccessFile.readLine();
int recordCount=Integer.parseInt(randomAccessFile.readLine().trim());
if(recordCount==0)
{
randomAccessFile.close();
return false;
}
int fDesignationCode;
SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
randomAccessFile.readLine();
randomAccessFile.readLine();
fDesignationCode = Integer.parseInt(randomAccessFile.readLine());
if(fDesignationCode == designationCode)
{
randomAccessFile.close();
return true;
}
for(int x=0;x<6;x++) randomAccessFile.readLine();
}
randomAccessFile.close();
return false;
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}


public EmployeeDTOInterface getByEmployeeId(String employeeId) throws DAOException
{
if(employeeId==null) throw new DAOException("Employee ID is null");
employeeId=employeeId.trim();
if(employeeId.length()==0) throw new DAOException("Length of Employee ID is 0"); 
try{
File file = new File(FILE_NAME);
if(file.exists()==false) throw new DAOException("Invalid Employee ID" + employeeId);
RandomAccessFile randomAccessFile;
randomAccessFile = new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid Employee ID" + employeeId);
}
randomAccessFile.readLine();
randomAccessFile.readLine();
EmployeeDTOInterface employeeDTO;
String fEmployeeId;
int x;
SimpleDateFormat simpleDateFormat;
simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fEmployeeId = randomAccessFile.readLine();
if(fEmployeeId.equalsIgnoreCase(employeeId))
{
employeeDTO = new EmployeeDTO();
employeeDTO.setEmployeeId(fEmployeeId);
employeeDTO.setName(randomAccessFile.readLine());
employeeDTO.setDesignationCode(Integer.parseInt(randomAccessFile.readLine()));
try
{
employeeDTO.setDateOfBirth(simpleDateFormat.parse(randomAccessFile.readLine()));
}catch(ParseException pe)
{
throw new DAOException(pe.getMessage());
}
employeeDTO.setGender((randomAccessFile.readLine().charAt(0)=='M')?GENDER.MALE:GENDER.FEMALE);
employeeDTO.setIsIndian(Boolean.parseBoolean(randomAccessFile.readLine()));
employeeDTO.setBasicSalary(new BigDecimal(randomAccessFile.readLine()));
employeeDTO.setPANNumber(randomAccessFile.readLine());
employeeDTO.setAadharCardNumber(randomAccessFile.readLine());
randomAccessFile.close();
return employeeDTO;
}
for(x=1;x<=8;x++) randomAccessFile.readLine();
}
randomAccessFile.close();
throw new DAOException("Invalid Employee ID " + employeeId);
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}


public EmployeeDTOInterface getByPANNumber(String panNumber) throws DAOException
{
if(panNumber==null) throw new DAOException("PAN Number is null");
panNumber=panNumber.trim();
if(panNumber.length()==0) throw new DAOException("Length of PanNumber is 0"); 
try{
File file = new File(FILE_NAME);
if(file.exists()==false) throw new DAOException("Invalid Pan Number " + panNumber);
RandomAccessFile randomAccessFile;
randomAccessFile = new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid Pan Number " + panNumber);
}
randomAccessFile.readLine();
randomAccessFile.readLine();
EmployeeDTOInterface employeeDTO;
String fEmployeeId;
String fName;
int fDesignationCode;
Date fDateOfBirth=null;
char fGender;
boolean fIsIndian;
BigDecimal fBasicSalary;
String fPANNumber; 
String fAadharCardNumber;
int x;
SimpleDateFormat simpleDateFormat;
simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fEmployeeId = randomAccessFile.readLine();
fName = randomAccessFile.readLine();
fDesignationCode = Integer.parseInt(randomAccessFile.readLine());
try
{
fDateOfBirth = simpleDateFormat.parse(randomAccessFile.readLine());
}catch(ParseException pe)
{
throw new DAOException(pe.getMessage());
}
fGender = randomAccessFile.readLine().charAt(0);
fIsIndian = Boolean.parseBoolean(randomAccessFile.readLine());
fBasicSalary = new BigDecimal(randomAccessFile.readLine());
fPANNumber = randomAccessFile.readLine();
fAadharCardNumber = randomAccessFile.readLine();
if(fPANNumber.equalsIgnoreCase(panNumber))
{
employeeDTO = new EmployeeDTO();
employeeDTO.setEmployeeId(fEmployeeId);
employeeDTO.setName(fName);
employeeDTO.setDesignationCode(fDesignationCode);
employeeDTO.setDateOfBirth(fDateOfBirth);
employeeDTO.setGender((fGender=='M')?GENDER.MALE:GENDER.FEMALE);
employeeDTO.setIsIndian(fIsIndian);
employeeDTO.setBasicSalary(fBasicSalary);
employeeDTO.setPANNumber(fPANNumber);
employeeDTO.setAadharCardNumber(fAadharCardNumber);
randomAccessFile.close();
return employeeDTO;
}
}
randomAccessFile.close();
throw new DAOException("Invalid Pan Number " + panNumber);
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}


public EmployeeDTOInterface getByAadharCardNumber(String aadharCardNumber) throws DAOException
{
if(aadharCardNumber==null) throw new DAOException("Aadhar card number is null");
aadharCardNumber=aadharCardNumber.trim();
if(aadharCardNumber.length()==0) throw new DAOException("Length of aadhar card number is 0"); 
try{
File file = new File(FILE_NAME);
if(file.exists()==false) throw new DAOException("Invalid Aadhar Card Number " + aadharCardNumber);
RandomAccessFile randomAccessFile;
randomAccessFile = new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid Aadhar Card Number " + aadharCardNumber);
}
randomAccessFile.readLine();
randomAccessFile.readLine();
EmployeeDTOInterface employeeDTO;
String fEmployeeId;
String fName;
int fDesignationCode;
Date fDateOfBirth=null;
char fGender;
boolean fIsIndian;
BigDecimal fBasicSalary;
String fPANNumber; 
String fAadharCardNumber;
int x;
SimpleDateFormat simpleDateFormat;
simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fEmployeeId = randomAccessFile.readLine();
fName = randomAccessFile.readLine();
fDesignationCode = Integer.parseInt(randomAccessFile.readLine());
try
{
fDateOfBirth = simpleDateFormat.parse(randomAccessFile.readLine());
}catch(ParseException pe)
{
throw new DAOException(pe.getMessage());
}
fGender = randomAccessFile.readLine().charAt(0);
fIsIndian = Boolean.parseBoolean(randomAccessFile.readLine());
fBasicSalary = new BigDecimal(randomAccessFile.readLine());
fPANNumber = randomAccessFile.readLine();
fAadharCardNumber = randomAccessFile.readLine();
if(fAadharCardNumber.equalsIgnoreCase(aadharCardNumber))
{
employeeDTO = new EmployeeDTO();
employeeDTO.setEmployeeId(fEmployeeId);
employeeDTO.setName(fName);
employeeDTO.setDesignationCode(fDesignationCode);
employeeDTO.setDateOfBirth(fDateOfBirth);
employeeDTO.setGender((fGender=='M')?GENDER.MALE:GENDER.FEMALE);
employeeDTO.setIsIndian(fIsIndian);
employeeDTO.setBasicSalary(fBasicSalary);
employeeDTO.setPANNumber(fPANNumber);
employeeDTO.setAadharCardNumber(fAadharCardNumber);
randomAccessFile.close();
return employeeDTO;
}
}
randomAccessFile.close();
throw new DAOException("Invalid Aadhar Card Number " + aadharCardNumber);
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}


public boolean employeeIdExists(String employeeId) throws DAOException
{
if(employeeId==null) return false;
employeeId=employeeId.trim();
if(employeeId.length()==0) return false;
try{
File file = new File(FILE_NAME);
if(file.exists()==false) return false;
RandomAccessFile randomAccessFile;
randomAccessFile = new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return false;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
String fEmployeeId;
int x;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fEmployeeId = randomAccessFile.readLine();
if(fEmployeeId.equalsIgnoreCase(employeeId))
{
randomAccessFile.close();
return true;
}
for(x=1;x<=8;x++) randomAccessFile.readLine();
}
randomAccessFile.close();
return false;
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}


public boolean panNumberExists(String panNumber) throws DAOException
{
if(panNumber==null) return false;
panNumber=panNumber.trim();
if(panNumber.length()==0) return false;
try{
File file = new File(FILE_NAME);
if(file.exists()==false) return false;
RandomAccessFile randomAccessFile;
randomAccessFile = new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return false;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
String fPANNumber;
int x;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
for(x=1;x<=7;x++) randomAccessFile.readLine();
fPANNumber = randomAccessFile.readLine();
if(fPANNumber.equalsIgnoreCase(panNumber))
{
randomAccessFile.close();
return true;
}
randomAccessFile.readLine();
}
randomAccessFile.close();
return false;
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}


public boolean aadharCardNumberExists(String aadharCardNumber) throws DAOException
{
if(aadharCardNumber==null) return false;
aadharCardNumber=aadharCardNumber.trim();
if(aadharCardNumber.length()==0) return false;
try{
File file = new File(FILE_NAME);
if(file.exists()==false) return false;
RandomAccessFile randomAccessFile;
randomAccessFile = new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return false;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
String fAadharCardNumber;
int x;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
for(x=1;x<=8;x++) randomAccessFile.readLine();
fAadharCardNumber = randomAccessFile.readLine();
if(fAadharCardNumber.equalsIgnoreCase(aadharCardNumber))
{
randomAccessFile.close();
return true;
}
}
randomAccessFile.close();
return false;
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}


public int getCount() throws DAOException
{
try
{
File file = new File(FILE_NAME);
if(file.exists()==false) return 0;
RandomAccessFile randomAccessFile;
randomAccessFile = new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return 0;
}
randomAccessFile.readLine();
int recordCount = Integer.parseInt(randomAccessFile.readLine().trim());
randomAccessFile.close();
return recordCount;
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}


public int getCountByDesignation(int designationCode) throws DAOException
{
try
{
if(new DesignationDAO().codeExists(designationCode)==false) throw new DAOException("Invalid Designation Code " + designationCode);
File file = new File(FILE_NAME);
if(file.exists()==false) return 0;
RandomAccessFile randomAccessFile;
randomAccessFile = new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return 0;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
int fDesignationCode;
int count = 0;
int x;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
randomAccessFile.readLine();
randomAccessFile.readLine();
fDesignationCode= Integer.parseInt(randomAccessFile.readLine());
if(fDesignationCode==designationCode) count++;
for(x=1;x<=6;x++) randomAccessFile.readLine();
}
randomAccessFile.close();
return count;
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}


}

