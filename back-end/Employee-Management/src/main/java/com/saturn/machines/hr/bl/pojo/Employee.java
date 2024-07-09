package com.saturn.machines.hr.bl.pojo;
import com.saturn.machines.hr.bl.interfaces.pojo.*;
import com.saturn.machines.enums.*;
import java.util.*;
import java.math.*;
public class Employee implements EmployeeInterface
{
private String employeeId;
private String name;
private int designationCode;
private Date dateOfBirth;
private boolean isIndian;
private char gender;
private BigDecimal basicSalary; 
private String panNumber;
private String aadharCardNumber;
public Employee()
{
this.employeeId="";
this.name="";
this.designationCode=0;
this.dateOfBirth=null;
this.isIndian=false;
this.gender=' ';
this.basicSalary=null; 
this.panNumber="";
this.aadharCardNumber="";
}
public void setEmployeeId(java.lang.String employeeId)
{
this.employeeId = employeeId;
}
public java.lang.String getEmployeeId()
{
return this.employeeId;
}
public void setName(java.lang.String name)
{
this.name = name;
}
public java.lang.String getName()
{
return this.name;
}
public void setDesignationCode(int designationCode)
{
this.designationCode = designationCode;
}
public int getDesignationCode()
{
return this.designationCode;
}
// public void setDesignation(DesignationInterface designation)
// {
// this.designation = designation;
// }
// public DesignationInterface getDesignation()
// {
// return this.designation;
// }
public void setDateOfBirth(java.util.Date dateOfBirth)
{
this.dateOfBirth = dateOfBirth;
}
public java.util.Date getDateOfBirth()
{
return this.dateOfBirth;
}
public void setIsIndian(boolean isIndian) 
{
this.isIndian = isIndian;
}
public boolean getIsIndian()
{
return this.isIndian;
}
public void setGender(GENDER gender)
{
if(gender==GENDER.MALE) this.gender = 'M';
else this.gender = 'F';
}
public char getGender()
{
return this.gender;
}
public void setBasicSalary(java.math.BigDecimal basicSalary)
{
this.basicSalary = basicSalary;
}
public java.math.BigDecimal getBasicSalary()
{
return this.basicSalary;
}
public void setPANNumber(java.lang.String panNumber)
{
this.panNumber = panNumber;
}
public java.lang.String getPANNumber()
{
return this.panNumber;
}
public void setAadharCardNumber(java.lang.String aadharCardNumber)
{
this.aadharCardNumber = aadharCardNumber;
}
public java.lang.String getAadharCardNumber()
{
return this.aadharCardNumber;
}
public boolean equals(Object other)
{
if(!(other instanceof EmployeeInterface)) return false;
EmployeeInterface employee;
employee = (EmployeeInterface)other;
return this.employeeId.equalsIgnoreCase(employee.getEmployeeId());
}
public int compareTo(EmployeeInterface other)
{
return this.employeeId.compareToIgnoreCase(other.getEmployeeId());
}
public int hashCode()
{
return this.employeeId.toUpperCase().hashCode();
}
}