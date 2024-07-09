package com.saturn.machines.hr.bl.managers;
import com.saturn.machines.hr.bl.interfaces.managers.*;
import com.saturn.machines.hr.bl.exceptions.*;
import com.saturn.machines.hr.bl.interfaces.pojo.*;
import com.saturn.machines.hr.bl.pojo.*;
import com.saturn.machines.hr.dl.interfaces.dao.*;
import com.saturn.machines.hr.dl.interfaces.dto.*;
import com.saturn.machines.hr.dl.exceptions.*;
import com.saturn.machines.hr.dl.dao.*;
import com.saturn.machines.hr.dl.dto.*;
import java.util.*;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DesignationManager implements DesignationManagerInterface
{
	private Map<Integer,DesignationInterface> codeWiseDesignationMap;
	private Map<String,DesignationInterface> titleWiseDesignationMap;
	private Set<DesignationInterface> designationSet;
	private static DesignationManager designationManager = null;
	private DesignationManager() throws BLException 
	{
		populateDataStructures();
	}
	private void populateDataStructures() throws BLException
	{
		this.codeWiseDesignationMap = new HashMap<>();
		this.titleWiseDesignationMap = new HashMap<>();
		this.designationSet = new TreeSet<>();
		try
		{
			Set<DesignationDTOInterface> dlDesignations;
			dlDesignations = new DesignationDAO().getAll();
			DesignationInterface designation;
			for(DesignationDTOInterface dlDesignation:dlDesignations)
			{
				designation = new Designation(); 
				designation.setCode(dlDesignation.getCode());
				designation.setTitle(dlDesignation.getTitle());
				codeWiseDesignationMap.put(designation.getCode(),designation);
				titleWiseDesignationMap.put(designation.getTitle().toUpperCase(),designation);
				designationSet.add(designation);
			}
		}catch(DAOException daoException)
		{
			BLException blException = new BLException();
			blException.setGenericException(daoException.getMessage());
			throw blException;
		}
	}
	@Bean
	public static DesignationManagerInterface getDesignationManager() throws BLException
	{
		if(designationManager==null) designationManager = new DesignationManager();
		return designationManager;
	}




	public DesignationInterface addDesignation(DesignationInterface designation) throws BLException
	{
		BLException blException = new BLException();
		if(designation==null)
		{
			blException.setGenericException("Designation Required");
			throw blException;
		}
//		int code = designation.getCode();
		String title = designation.getTitle();
//		if(code!=0)
//		{
//			blException.addException("code","Code must be zero (0)");
//		}
		if(title==null)
		{
			blException.addException("title","Title is null");
			title="";
		}
		else{
			title=title.trim();
			if(title.length()==0)
			{
				blException.addException("title","Title required");
			}
		}
		if(title.length()>0)
		{
			if(this.titleWiseDesignationMap.containsKey(title.toUpperCase()))
			{
				blException.addException("title","Designation : "+title+ " exists.");
			}
		}
		if(blException.hasException())
		{
			throw blException;
		}
		try{
			DesignationDTOInterface designationDTO;
			designationDTO = new DesignationDTO();
			designationDTO.setTitle(title);
			DesignationDAOInterface designationDAO;
			designationDAO = new DesignationDAO();
			designationDAO.add(designationDTO);
			int code = designationDTO.getCode();
			designation.setCode(code);
			DesignationInterface dsDesignation; //this is changed 
			dsDesignation = new Designation();
			dsDesignation.setCode(code);
			dsDesignation.setTitle(title);
			codeWiseDesignationMap.put(code,dsDesignation);
			titleWiseDesignationMap.put(title.toUpperCase(),dsDesignation);
			designationSet.add(dsDesignation);
			DesignationInterface dd = new Designation();
			dd.setTitle(dsDesignation.getTitle());
			dd.setCode(dsDesignation.getCode());
			return dd;
		}catch(DAOException daoException)
		{
			blException.setGenericException(daoException.getMessage());
			throw blException;
		}
	}



	public DesignationInterface updateDesignation(DesignationInterface designation) throws BLException
	{
		BLException blException = new BLException();
		if(designation==null)
		{
			blException.setGenericException("Designation Required");
			throw blException;
		}
		int code = designation.getCode();
		String title = designation.getTitle();
		if(code<=0)
		{
			blException.addException("code","Invalid code : " + code);
		}
		if(code>0)
		{
			if(this.codeWiseDesignationMap.containsKey(code)==false)
			{
				blException.addException("code","Invalid code : " + code);
				throw blException;
			}
		}
		if(title==null)
		{
			blException.addException("title","Title is null");
			title="";
		}
		else{
			title=title.trim();
			if(title.length()==0)
			{
				blException.addException("title","Title required");
			}
		}
		if(title.length()>0)
		{
			DesignationInterface d;
			d = titleWiseDesignationMap.get(title.toUpperCase());
			if(d!=null && d.getCode()!=code)
			{
				blException.addException("title","Designation : "+title+ " exists.");
			}
		}
		if(blException.hasException())
		{
			throw blException;
		}
		try{
			DesignationInterface dsDesignation = codeWiseDesignationMap.get(code);
			DesignationDTOInterface designationDTO = new DesignationDTO();
			designationDTO.setCode(code);
			designationDTO.setTitle(title);
			new DesignationDAO().update(designationDTO);
			//remove old one from the ds
			codeWiseDesignationMap.remove(code);
			titleWiseDesignationMap.remove(dsDesignation.getTitle().toUpperCase());
			designationSet.remove(dsDesignation);
			//update the dsDesignation
			dsDesignation.setTitle(title);
			//update the ds
			codeWiseDesignationMap.put(dsDesignation.getCode(),dsDesignation);
			titleWiseDesignationMap.put(dsDesignation.getTitle().toUpperCase(),dsDesignation);
			designationSet.add(dsDesignation);
			DesignationInterface dd = new Designation();
			dd.setTitle(dsDesignation.getTitle());
			dd.setCode(dsDesignation.getCode());
			return dd;
		}catch(DAOException daoException)
		{
			blException.setGenericException(daoException.getMessage());
			throw blException;
		}
	}



	public void removeDesignation(int code) throws BLException
	{
		BLException blException = new BLException();
		if(code<=0)
		{
			blException.addException("code","Invalid code : " + code);
			throw blException;
		}
		if(code>0)
		{
			if(this.codeWiseDesignationMap.containsKey(code)==false)
			{
				blException.addException("code","Invalid code : " + code);
				throw blException;
			}
		}
		try{
			DesignationInterface dsDesignation = codeWiseDesignationMap.get(code);
			new DesignationDAO().delete(code);
			//remove old one from the ds
			codeWiseDesignationMap.remove(code);
			titleWiseDesignationMap.remove(dsDesignation.getTitle().toUpperCase());
			designationSet.remove(dsDesignation);
		}catch(DAOException daoException)
		{
			blException.setGenericException(daoException.getMessage());
			throw blException;
		}
	}



	public DesignationInterface getDesignationByCode(int code) throws BLException
	{
		DesignationInterface designation;
		designation = codeWiseDesignationMap.get(code);
		if(designation==null)
		{
			BLException blException = new BLException();
			blException.addException("code","Invalid code : " + code);
			throw blException;
		}
		DesignationInterface designationClone = new Designation();
		designationClone.setCode(designation.getCode());            
		designationClone.setTitle(designation.getTitle());
		return designationClone;
	}

	//for internal use only
	DesignationInterface getDSDesignationByCode(int code) throws BLException 
	{
		DesignationInterface designation;
		designation = codeWiseDesignationMap.get(code);
		return designation;
	}



	public DesignationInterface getDesignationByTitle(String title) throws BLException
	{
		DesignationInterface designation;
		designation = titleWiseDesignationMap.get(title.toUpperCase());
		if(designation==null)
		{
			BLException blException = new BLException();
			blException.addException("title","Invalid title : " + title);
			throw blException;
		}
		DesignationInterface designationClone = new Designation();
		designationClone.setCode(designation.getCode());            
		designationClone.setTitle(designation.getTitle());
		return designationClone;
	}



	public Set<DesignationInterface> getDesignations() throws BLException
	{
		Set<DesignationInterface> designations = new TreeSet<>();
		designationSet.forEach((designation)->{
			DesignationInterface dsDesignation = new Designation();
			dsDesignation.setTitle(designation.getTitle());
			dsDesignation.setCode(designation.getCode());
			designations.add(dsDesignation);
		});
		return designations;
	}



	public int getDesignationCount() throws BLException
	{
		return designationSet.size();
	}



	public boolean designationCodeExists(int code) throws BLException
	{
		return codeWiseDesignationMap.containsKey(code);
	}



	public boolean designationTitleExists(String title) throws BLException
	{
		return titleWiseDesignationMap.containsKey(title.toUpperCase());
	}



}