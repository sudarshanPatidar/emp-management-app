package com.saturn.machines.hr.dl.interfaces.dto;
import com.saturn.machines.enums.*;
import java.util.*;
import java.math.*;
public interface EmployeeDTOInterface extends Comparable<EmployeeDTOInterface>,java.io.Serializable
{
public void setEmployeeId(String employeeId);
public String getEmployeeId();
public void setName(String name);
public String getName();
public void setGender(GENDER gender);
public char getGender();
public void setDesignationCode(int designationCode);
public int getDesignationCode();
public void setDateOfBirth(Date dateOfBirth);
public Date getDateOfBirth();
public void setPANNumber(String panNumber);
public String getPANNumber();
public void setAadharCardNumber(String aadharCardNumber);
public String getAadharCardNumber();
public void setIsIndian(boolean isIndian);
public boolean getIsIndian();
public void setBasicSalary(BigDecimal basicSalary);
public BigDecimal getBasicSalary();
} 