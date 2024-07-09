package com.saturn.machines.hr.bl.managers;
import com.saturn.machines.hr.bl.exceptions.*;
import com.saturn.machines.hr.bl.interfaces.pojo.*;
import com.saturn.machines.hr.bl.pojo.*;
import com.saturn.machines.hr.bl.interfaces.managers.*;
import com.saturn.machines.hr.dl.interfaces.dao.*;
import com.saturn.machines.hr.dl.interfaces.dto.*;
import com.saturn.machines.hr.dl.exceptions.*;
import com.saturn.machines.hr.dl.dao.*;
import com.saturn.machines.hr.dl.dto.*;
import com.saturn.machines.enums.*;
import java.util.*;

import org.springframework.stereotype.Component;

import java.text.*;
import java.math.*;

@Component
public class EmployeeManager implements EmployeeManagerInterface
{
	private Map<String,EmployeeInterface> employeeIdWiseEmployeesMap;
	private Map<String,EmployeeInterface> panNumberWiseEmployeesMap;
	private Map<String,EmployeeInterface> aadharCardNumberWiseEmployeesMap;
	private Set<EmployeeInterface> employeesSet;
	private static EmployeeManager employeeManager = null;
	private EmployeeManager() throws BLException
	{
		populateDataStructures();
	}
	private void populateDataStructures() throws BLException
	{
		this.employeeIdWiseEmployeesMap = new HashMap<>();
		this.panNumberWiseEmployeesMap = new HashMap<>();
		this.aadharCardNumberWiseEmployeesMap = new HashMap<>();
		this.employeesSet = new TreeSet<>();
		try
		{
			Set<EmployeeDTOInterface> dlEmployees;
			dlEmployees = new EmployeeDAO().getAll();
			EmployeeInterface employee;
			DesignationManagerInterface designationManager = DesignationManager.getDesignationManager();
			// DesignationInterface designation;
			for(EmployeeDTOInterface dlEmployee:dlEmployees)
			{
				employee = new Employee(); 
				employee.setEmployeeId(dlEmployee.getEmployeeId());
				employee.setName(dlEmployee.getName());
				// designation = designationManager.getDesignationByCode(dlEmployee.getDesignationCode());
				employee.setDateOfBirth(dlEmployee.getDateOfBirth());
				// employee.setDesignation(designation);
				employee.setDesignationCode(dlEmployee.getDesignationCode());
				
				employee.setBasicSalary(dlEmployee.getBasicSalary());
				employee.setPANNumber(dlEmployee.getPANNumber());
				employee.setAadharCardNumber(dlEmployee.getAadharCardNumber());
				employee.setIsIndian(dlEmployee.getIsIndian());
				employee.setGender((dlEmployee.getGender()=='M')?GENDER.MALE:GENDER.FEMALE);
				this.employeeIdWiseEmployeesMap.put(employee.getEmployeeId().toUpperCase(),employee);
				this.panNumberWiseEmployeesMap.put(employee.getPANNumber().toUpperCase(),employee);
				this.aadharCardNumberWiseEmployeesMap.put(employee.getAadharCardNumber().toUpperCase(),employee);
				employeesSet.add(employee);
			}
	}
		catch(DAOException daoException)
		{
			BLException blException = new BLException();
			blException.setGenericException(daoException.getMessage());
			throw blException;
		}
	}
	
	

	
	public static EmployeeManagerInterface getEmployeeManager() throws BLException
	{
		if(employeeManager==null) employeeManager = new EmployeeManager();
		return employeeManager;
	}
	
	
	public Map<String,EmployeeInterface> myMethod()
	{
		return this.employeeIdWiseEmployeesMap;
	}
	
	public EmployeeInterface addEmployee(EmployeeInterface employee) throws BLException
	{
		BLException blException = new BLException();
		String employeeId;
		// DesignationInterface designation = employee.getDesignation();
		int designationCode = employee.getDesignationCode();
		boolean isIndian = employee.getIsIndian();
		String name = employee.getName();
		Date dateOfBirth = employee.getDateOfBirth();
		BigDecimal basicSalary = employee.getBasicSalary();
		String panNumber = employee.getPANNumber();
		String aadharCardNumber = employee.getAadharCardNumber();
		char gender = employee.getGender();
//		if(employeeId!=null)
//		{
//			employeeId = employeeId.toUpperCase();
//			if(employeeId.length()>0) blException.addException("employeeId","Employee Id. must be nil/empty");
//		}
		if(name==null)
		{
			blException.addException("name","Name required.");
		}
		else
		{
			name = name.trim();
			if(name.length()==0) blException.addException("name","Name required.");
		}
		DesignationManagerInterface designationManager = DesignationManager.getDesignationManager();
		// if(designation==null)
		// {
		// blException.addException("designation","Designation required.");
		// }
		// else
		// {
		// designationCode = designation.getCode();
		if(designationManager.designationCodeExists(designationCode)==false) blException.addException("designationCode","Invalid designation code");
		if(dateOfBirth==null) blException.addException("dateOfBirth","Date of birth required.");
		if(gender==' ') blException.addException("gender","Gender required.");
		if(basicSalary==null)
		{
			blException.addException("basicSalary","Basic salary required.");
		}
		else
		{
			if(basicSalary.signum()==-1)
			{
				blException.addException("basicSalary","Basic salary cannot be negative");
			}
		}
		if(panNumber==null)
		{
			blException.addException("panNumber","Pan card number required");
		}
		else 
		{
			panNumber=panNumber.trim();
			if(panNumber.length()==0) blException.addException("panNumber","Pan card number required");
		}
		if(aadharCardNumber==null)
		{
			blException.addException("aadharCardNumber","Aadhar card number required");
		}
		else 
		{
			aadharCardNumber=aadharCardNumber.trim();
			if(aadharCardNumber.length()==0) blException.addException("aadharCardNumber","Aadhar card number required");
		}
		if(panNumber!=null && panNumber.length()>0)
		{
			if(panNumberWiseEmployeesMap.containsKey(panNumber.toUpperCase())==true)
			{
				blException.addException("panNumber","Pan number " + panNumber + " exists.");
			}
		}
		if(aadharCardNumber!=null && aadharCardNumber.length()>0)
		{
			if(aadharCardNumberWiseEmployeesMap.containsKey(aadharCardNumber.toUpperCase())==true)
			{
				blException.addException("aadharCardNumber","Aadhar card number "+ aadharCardNumber+ " exists.");
			}
		}
		if(blException.hasException()) throw blException;
		try
		{
			EmployeeDAOInterface employeeDAO = new EmployeeDAO();
			EmployeeDTOInterface dlEmployee = new EmployeeDTO();
			dlEmployee.setName(name);
			dlEmployee.setDesignationCode(designationCode);
			dlEmployee.setDateOfBirth(dateOfBirth);
			dlEmployee.setGender((gender=='M')?GENDER.MALE:GENDER.FEMALE);
			dlEmployee.setIsIndian(isIndian);
			dlEmployee.setPANNumber(panNumber);
			dlEmployee.setBasicSalary(basicSalary);
			dlEmployee.setAadharCardNumber(aadharCardNumber);
			employeeDAO.add(dlEmployee);
			employee.setEmployeeId(dlEmployee.getEmployeeId());
			
			employeeId = dlEmployee.getEmployeeId();
			EmployeeInterface dsEmployee = new Employee();
			dsEmployee.setEmployeeId(dlEmployee.getEmployeeId());
			dsEmployee.setDesignationCode(designationCode);
			// dsEmployee.setDesignation(((DesignationManager)designationManager).getDSDesignationByCode(designation.getCode()));
			
			dsEmployee.setName(name);
			dsEmployee.setAadharCardNumber(aadharCardNumber);
			dsEmployee.setBasicSalary(basicSalary);
			dsEmployee.setIsIndian(isIndian);
			dsEmployee.setPANNumber(panNumber);
			dsEmployee.setGender((gender=='M')?GENDER.MALE:GENDER.FEMALE);
			dsEmployee.setDateOfBirth(dateOfBirth);
			employeesSet.add(dsEmployee);
			panNumberWiseEmployeesMap.put(panNumber.toUpperCase(),dsEmployee);
			aadharCardNumberWiseEmployeesMap.put(aadharCardNumber.toUpperCase(),dsEmployee);
			employeeIdWiseEmployeesMap.put(employeeId.toUpperCase(),dsEmployee);
			return dsEmployee;
		}
		catch(DAOException daoException)
		{
			blException.setGenericException(daoException.getMessage());
			throw blException;
		}
	}
	
	
	public EmployeeInterface updateEmployee(EmployeeInterface employee) throws BLException
	{
		BLException blException = new BLException();
		String employeeId=employee.getEmployeeId();
		// DesignationInterface designation = employee.getDesignation();
		// int designationCode = 0;
		int designationCode = employee.getDesignationCode();
		boolean isIndian = employee.getIsIndian();
		String name = employee.getName();
		Date dateOfBirth = employee.getDateOfBirth();
		BigDecimal basicSalary = employee.getBasicSalary();
		String panNumber = employee.getPANNumber();
		String aadharCardNumber = employee.getAadharCardNumber();
		char gender = employee.getGender();
		if(employeeId==null)
		{
			blException.addException("employeeId","Employee Id. required");
			throw blException;
		}
		else{
			employeeId = employeeId.trim();
			if(employeeId.length()==0)
			{
				blException.addException("employeeId","Employee Id. required");
				throw blException;
			}
			else
			{
				if(employeeIdWiseEmployeesMap.containsKey(employeeId)==false)
				{
					blException.addException("employeeId","Invalid employee id. "+employeeId);
					throw blException;
				}
			}
		}
		if(name==null)
		{
			blException.addException("name","Name required.");
		}
		else
		{
			name = name.trim();
			if(name.length()==0) blException.addException("name","Name required.");
		}
		DesignationManagerInterface designationManager = DesignationManager.getDesignationManager();
		// if(designation==null)
		// {
		// blException.addException("designation","Designation required.");
		// }
		// else
		// {
		// designationCode = designation.getCode();
		if(designationManager.designationCodeExists(designationCode)==false) blException.addException("designationCode","Invalid designation code");
		if(dateOfBirth==null) blException.addException("dateOfBirth","Date of birth required.");
		if(gender==' ') blException.addException("gender","Gender required.");
		if(basicSalary==null)
		{
			blException.addException("basicSalary","Basic salary required.");
		}
		else
		{
			if(basicSalary.signum()==-1)
			{
				blException.addException("basicSalary","Basic salary cannot be negative");
			}
		}
		if(panNumber==null)
		{
			blException.addException("panNumber","Pan card number required");
		}
		else 
		{
			panNumber=panNumber.trim();
			if(panNumber.length()==0) blException.addException("panNumber","Pan card number required");
		}
		if(aadharCardNumber==null)
		{
			blException.addException("aadharCardNumber","Aadhar card number required");
		}
		else 
		{
			aadharCardNumber=aadharCardNumber.trim();
			if(aadharCardNumber.length()==0) blException.addException("aadharCardNumber","Aadhar card number required");
		}
		
		//if pan exists but with the same employeeId as input
		if(panNumber!=null && panNumber.length()>0)
		{
			EmployeeInterface ee = panNumberWiseEmployeesMap.get(panNumber.toUpperCase());
			if(ee!=null && ee.getEmployeeId().equalsIgnoreCase(employeeId)==false)
			{
				blException.addException("panNumber","Pan number " + panNumber + " exists.");
			}
		}
		if(aadharCardNumber!=null && aadharCardNumber.length()>0)
		{
			EmployeeInterface ee = aadharCardNumberWiseEmployeesMap.get(aadharCardNumber.toUpperCase());
			if(ee!=null && ee.getEmployeeId().equalsIgnoreCase(employeeId)==false)
			{
				blException.addException("aadharCardNumber","Aadhar card number "+ aadharCardNumber+ " exists.");
			}
		}
		if(blException.hasException()) throw blException;
		try
		{
			EmployeeInterface dsEmployee = employeeIdWiseEmployeesMap.get(employeeId.toUpperCase());
			String oldPANNumber = dsEmployee.getPANNumber();
			String oldAadharCardNumber = dsEmployee.getAadharCardNumber();
			EmployeeDAOInterface employeeDAO = new EmployeeDAO();
			EmployeeDTOInterface dlEmployee = new EmployeeDTO();
			dlEmployee.setEmployeeId(dsEmployee.getEmployeeId());
			dlEmployee.setName(name);
			dlEmployee.setDesignationCode(designationCode);
			dlEmployee.setDateOfBirth(dateOfBirth);
			dlEmployee.setGender((gender=='M')?GENDER.MALE:GENDER.FEMALE);
			dlEmployee.setIsIndian(isIndian);
			dlEmployee.setPANNumber(panNumber);
			dlEmployee.setBasicSalary(basicSalary);
			dlEmployee.setAadharCardNumber(aadharCardNumber);
			employeeDAO.update(dlEmployee);
			
			
			dsEmployee.setEmployeeId(dlEmployee.getEmployeeId());
			// dsEmployee.setDesignation(((DesignationManager)designationManager).getDSDesignationByCode(designation.getCode()));
			
			dsEmployee.setDesignationCode(designationCode);
			dsEmployee.setName(name);
			dsEmployee.setAadharCardNumber(aadharCardNumber);
			dsEmployee.setBasicSalary(basicSalary);
			dsEmployee.setIsIndian(isIndian);
			dsEmployee.setPANNumber(panNumber);
			dsEmployee.setGender((gender=='M')?GENDER.MALE:GENDER.FEMALE);
			dsEmployee.setDateOfBirth(dateOfBirth);
			//remove the old one
			employeesSet.remove(dsEmployee);
			panNumberWiseEmployeesMap.remove(oldPANNumber.toUpperCase());
			aadharCardNumberWiseEmployeesMap.remove(oldAadharCardNumber.toUpperCase());
			employeeIdWiseEmployeesMap.remove(employeeId.toUpperCase());
			//update the with new 
			employeesSet.add(dsEmployee);
			panNumberWiseEmployeesMap.put(panNumber.toUpperCase(),dsEmployee);
			aadharCardNumberWiseEmployeesMap.put(aadharCardNumber.toUpperCase(),dsEmployee);
			employeeIdWiseEmployeesMap.put(dsEmployee.getEmployeeId().toUpperCase(),dsEmployee);
			return dsEmployee;
		}
		catch(DAOException daoException)
		{
			blException.setGenericException(daoException.getMessage());
			throw blException;
		}
	}
	
	
	public void removeEmployee(String employeeId) throws BLException
	{
		if(employeeId==null)
		{
			BLException blException = new BLException();
			blException.addException("employeeId","Employee Id. required");
			throw blException;
		}
		else
		{
			employeeId = employeeId.trim();
			if(employeeId.length()==0)
			{
				BLException blException = new BLException();
				blException.addException("employeeId","Employee Id. required");
				throw blException;
			}
			else
			{
				if(employeeIdWiseEmployeesMap.containsKey(employeeId.toUpperCase())==false)
				{
					BLException blException = new BLException();
					blException.addException("employeeId","Invalid employee id.  1    "+employeeId);
					throw blException;
				}
			}
		}
		try
		{
			EmployeeInterface dsEmployee = employeeIdWiseEmployeesMap.get(employeeId.toUpperCase());
			EmployeeDAOInterface employeeDAO = new EmployeeDAO();
			employeeDAO.delete(dsEmployee.getEmployeeId());
			//remove from ds
			employeesSet.remove(dsEmployee);
			panNumberWiseEmployeesMap.remove(dsEmployee.getPANNumber().toUpperCase());
			aadharCardNumberWiseEmployeesMap.remove(dsEmployee.getAadharCardNumber().toUpperCase());
			employeeIdWiseEmployeesMap.remove(employeeId.toUpperCase());
		}
		catch(DAOException daoException)
		{
			BLException blException = new BLException();
			blException.setGenericException(daoException.getMessage());
			throw blException;
		}
	}
	
	
	public EmployeeInterface getEmployeeByEmployeeId(String employeeId) throws BLException
	{
	EmployeeInterface employee = employeeIdWiseEmployeesMap.get(employeeId.toUpperCase());
	if(employee==null)
	{
		BLException blException = new BLException();
		blException.setGenericException("Invalid employee Id. 1   " + employeeId);
		throw blException;
	}
	//return the copy
	EmployeeInterface eClone = new Employee();
	eClone.setName(employee.getName());
	eClone.setGender((employee.getGender()=='M')?GENDER.MALE:GENDER.FEMALE);
	eClone.setEmployeeId(employee.getEmployeeId());
	// DesignationInterface dsDesignation = employee.getDesignation();
	// DesignationInterface designation = new Designation();
	// designation.setCode(dsDesignation.getCode());
	// designation.setTitle(dsDesignation.getTitle());
	
	
	eClone.setDesignationCode(employee.getDesignationCode());
	eClone.setBasicSalary(employee.getBasicSalary());
	eClone.setIsIndian(employee.getIsIndian());
	eClone.setDateOfBirth(employee.getDateOfBirth());
	eClone.setAadharCardNumber(employee.getAadharCardNumber());
	eClone.setPANNumber(employee.getPANNumber());
	return eClone;
	}
	
	
	public EmployeeInterface getEmployeeByPANNumber(String panNumber) throws BLException
	{
	EmployeeInterface employee = panNumberWiseEmployeesMap.get(panNumber.toUpperCase());
	if(employee==null)
	{
	BLException blException = new BLException();
	blException.setGenericException("Invalid Pan number " + panNumber);
	throw blException;
	}
	EmployeeInterface eClone = new Employee();
	eClone.setName(employee.getName());
	eClone.setGender((employee.getGender()=='M')?GENDER.MALE:GENDER.FEMALE);
	eClone.setEmployeeId(employee.getEmployeeId());
	
	// DesignationInterface dsDesignation = employee.getDesignation();
	// DesignationInterface designation = new Designation();
	// designation.setCode(dsDesignation.getCode());
	// designation.setTitle(dsDesignation.getTitle());
	// eClone.setDesignation(designation);
	
	eClone.setDesignationCode(employee.getDesignationCode());
	eClone.setBasicSalary(employee.getBasicSalary());
	eClone.setIsIndian(employee.getIsIndian());
	eClone.setDateOfBirth(employee.getDateOfBirth());
	eClone.setAadharCardNumber(employee.getAadharCardNumber());
	eClone.setPANNumber(employee.getPANNumber());
	return eClone;
	}
	
	
	public EmployeeInterface getEmployeeByAadharCardNumber(String aadharCardNumber) throws BLException
	{
	EmployeeInterface employee = aadharCardNumberWiseEmployeesMap.get(aadharCardNumber.toUpperCase());
	if(employee==null)
	{
	BLException blException = new BLException();
	blException.setGenericException("Invalid Aadhar card number " + aadharCardNumber);
	throw blException;
	}
	EmployeeInterface eClone = new Employee();
	eClone.setName(employee.getName());
	eClone.setGender((employee.getGender()=='M')?GENDER.MALE:GENDER.FEMALE);
	eClone.setEmployeeId(employee.getEmployeeId());
	
	// DesignationInterface dsDesignation = employee.getDesignation();
	// DesignationInterface designation = new Designation();
	// designation.setCode(dsDesignation.getCode());
	// designation.setTitle(dsDesignation.getTitle());
	// eClone.setDesignation(designation);
	
	eClone.setDesignationCode(employee.getDesignationCode());
	eClone.setBasicSalary(employee.getBasicSalary());
	eClone.setIsIndian(employee.getIsIndian());
	eClone.setDateOfBirth(employee.getDateOfBirth());
	eClone.setAadharCardNumber(employee.getAadharCardNumber());
	eClone.setPANNumber(employee.getPANNumber());
	return eClone;
	}
	
	
	public int getEmployeeCount()
	{
	return employeesSet.size();
	}
	
	
	public boolean employeeIdExists(String employeeId)
	{
	return employeeIdWiseEmployeesMap.containsKey(employeeId.toUpperCase());
	}
	
	
	public boolean employeePANNumberExists(String panNumber)
	{
	return panNumberWiseEmployeesMap.containsKey(panNumber.toUpperCase());
	}
	
	
	public boolean employeeAadharCardNumberExists(String aadharCardNumber)
	{
	return aadharCardNumberWiseEmployeesMap.containsKey(aadharCardNumber.toUpperCase());
	}
	
	
	public Set<EmployeeInterface> getEmployees() 
	{
	Set<EmployeeInterface> employees = new TreeSet<>();
	employeesSet.forEach((employee)->{
	EmployeeInterface dsEmployee = new Employee();
	dsEmployee.setGender((employee.getGender()=='M')?GENDER.MALE:GENDER.FEMALE);
	dsEmployee.setEmployeeId(employee.getEmployeeId());
	dsEmployee.setName(employee.getName());
	
	// DesignationInterface dsDesignation = employee.getDesignation();
	// DesignationInterface designation = new Designation();
	// designation.setCode(dsDesignation.getCode());
	// designation.setTitle(dsDesignation.getTitle());
	// dsEmployee.setDesignation(designation);
	
	dsEmployee.setDesignationCode(employee.getDesignationCode());
	dsEmployee.setBasicSalary(employee.getBasicSalary());
	dsEmployee.setIsIndian(employee.getIsIndian());
	dsEmployee.setDateOfBirth(employee.getDateOfBirth());
	dsEmployee.setAadharCardNumber(employee.getAadharCardNumber());
	dsEmployee.setPANNumber(employee.getPANNumber());
	employees.add(dsEmployee);
	});
	return employees;
	}
	
	
	public Set<EmployeeInterface> getEmployeesByDesignationCode(int designationCode) throws BLException 
	{
	BLException blException = new BLException();
	blException.setGenericException("Not yet implemented");
	throw blException;
	}
	
	
	public int getEmployeeCountByDesignationCode(int designationCode) throws BLException   
	{
	BLException blException = new BLException();
	blException.setGenericException("Not yet implemented");
	throw blException;
	}
	
	
	public boolean designationAlloted(int designationCode) throws BLException
	{
	BLException blException = new BLException();
	blException.setGenericException("Not yet implemented");
	throw blException;
	}

}