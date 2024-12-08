package com.saturn.machines.hr.dl.dao;
import com.saturn.machines.hr.dl.dto.*;


import com.saturn.machines.hr.dl.exceptions.*;
import com.saturn.machines.hr.dl.interfaces.dao.*;
import com.saturn.machines.hr.dl.interfaces.dto.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;


@Service
public class DesignationDAO implements DesignationDAOInterface
{
//private static final String FILE_NAME = "designation.data";
private static final String FILE_NAME = "D:\\git\\emp-management-app\\back-end\\Employee-Management\\designation.data";


public void add(DesignationDTOInterface designationDTO) throws DAOException
{
if(designationDTO==null) throw new DAOException("Designation is null");
String title = designationDTO.getTitle();
if(title==null) throw new DAOException("Designation is null");
if(title.length()==0) throw new DAOException("Length of Designation is 0");
title = title.trim();
try{
File file = new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile = new RandomAccessFile(file,"rw");
int lastGeneratedCode = 0;
int recordCount = 0;
String lastGeneratedCodeString = "";
String recordCountString = "";
if(randomAccessFile.length()==0)
{
lastGeneratedCodeString = "0";
while(lastGeneratedCodeString.length()<10) lastGeneratedCodeString+=" ";
recordCountString = "0";
while(recordCountString.length()<10) recordCountString+=" ";
randomAccessFile.writeBytes(lastGeneratedCodeString);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(recordCountString);
randomAccessFile.writeBytes("\n");
}
else
{
lastGeneratedCode=Integer.parseInt(randomAccessFile.readLine().trim());
recordCount=Integer.parseInt(randomAccessFile.readLine().trim());
}
int fCode;
String fTitle;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fCode=Integer.parseInt(randomAccessFile.readLine());
fTitle=randomAccessFile.readLine();
if(fTitle.equalsIgnoreCase(title))
{
randomAccessFile.close();
throw new DAOException("Title : " + title + " exists...");
}
}
int code = lastGeneratedCode+1;
randomAccessFile.writeBytes(String.valueOf(code));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(title);
randomAccessFile.writeBytes("\n");
designationDTO.setCode(code);
lastGeneratedCode++;
recordCount++;
lastGeneratedCodeString = String.valueOf(lastGeneratedCode);
while(lastGeneratedCodeString.length()<10) lastGeneratedCodeString+=" ";
recordCountString = String.valueOf(recordCount);
while(recordCountString.length()<10) recordCountString+=" ";
randomAccessFile.seek(0);
randomAccessFile.writeBytes(lastGeneratedCodeString);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(recordCountString);
randomAccessFile.writeBytes("\n");
randomAccessFile.close();
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}


public void update(DesignationDTOInterface designationDTO) throws DAOException
{
if(designationDTO==null) throw new DAOException("Designation is null");
String title = designationDTO.getTitle();
if(title==null) throw new DAOException("Designation is null");
if(title.length()==0) throw new DAOException("Length of Designation is 0");
title = title.trim();
int code = designationDTO.getCode();
if(code <=0 ) throw new DAOException("Invalid code: "+code);
try
{
File file = new File(FILE_NAME);
if(file.exists()==false) throw new DAOException("Invalid code : " + code);
RandomAccessFile randomAccessFile;
randomAccessFile = new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid code : " + code);
}
String fTitle;
int fCode;
boolean found = false;
randomAccessFile.readLine();
randomAccessFile.readLine();
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fCode=Integer.parseInt(randomAccessFile.readLine());
if(fCode==code)
{
found=true;
break;
}
randomAccessFile.readLine();
}
if(found==false)
{
randomAccessFile.close();
throw new DAOException("Invalid code : " + code);
}
randomAccessFile.seek(0);
randomAccessFile.readLine();
randomAccessFile.readLine();
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fCode=Integer.parseInt(randomAccessFile.readLine());
fTitle = randomAccessFile.readLine();
if(fCode!=code && fTitle.equalsIgnoreCase(title))
{
randomAccessFile.close();
throw new DAOException("Title exists : " + title);
}
}
File tmpFile = new File("tmp.data");
if(tmpFile.exists()) tmpFile.delete();
RandomAccessFile tmpRandomAccessFile;
tmpRandomAccessFile = new RandomAccessFile(tmpFile,"rw");
randomAccessFile.seek(0);
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine());
tmpRandomAccessFile.writeBytes("\n");
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine());
tmpRandomAccessFile.writeBytes("\n");
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fCode=Integer.parseInt(randomAccessFile.readLine());
fTitle = randomAccessFile.readLine();
if(fCode!=code)
{
tmpRandomAccessFile.writeBytes(String.valueOf(fCode));
tmpRandomAccessFile.writeBytes("\n");
tmpRandomAccessFile.writeBytes(fTitle);
tmpRandomAccessFile.writeBytes("\n");
}
else
{
tmpRandomAccessFile.writeBytes(String.valueOf(code));
tmpRandomAccessFile.writeBytes("\n");
tmpRandomAccessFile.writeBytes(title);
tmpRandomAccessFile.writeBytes("\n");
}
}
tmpRandomAccessFile.seek(0);
randomAccessFile.seek(0);
while(tmpRandomAccessFile.getFilePointer()<tmpRandomAccessFile.length())
{
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine()+"\n");
}
randomAccessFile.setLength(tmpRandomAccessFile.length());
randomAccessFile.close();
tmpRandomAccessFile.setLength(0);
tmpRandomAccessFile.close();
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}


public void delete(int code) throws DAOException
{
if(code <=0 ) throw new DAOException("Invalid code: "+code);
try
{
String fTitle="";
File file = new File(FILE_NAME);
if(file.exists()==false) throw new DAOException("Invalid code : " + code);
RandomAccessFile randomAccessFile;
randomAccessFile = new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid code : " + code);
}
int fCode;
boolean found = false;
randomAccessFile.readLine();
int recordCount = Integer.parseInt(randomAccessFile.readLine().trim());
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fCode=Integer.parseInt(randomAccessFile.readLine());
fTitle = randomAccessFile.readLine();
if(fCode==code)
{
found=true;
break;
}
}
if(found==false)
{
randomAccessFile.close();
throw new DAOException("Invalid code : " + code);
}
if(new EmployeeDAO().isDesignationAlloted(code))
{
randomAccessFile.close();
throw new DAOException("Employee exists with designation : " + fTitle);
}
File tmpFile = new File("tmp.data");
if(tmpFile.exists()) tmpFile.delete();
RandomAccessFile tmpRandomAccessFile;
tmpRandomAccessFile = new RandomAccessFile(tmpFile,"rw");
randomAccessFile.seek(0);
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine());
tmpRandomAccessFile.writeBytes("\n");
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine());
tmpRandomAccessFile.writeBytes("\n");
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fCode=Integer.parseInt(randomAccessFile.readLine());
fTitle = randomAccessFile.readLine();
if(fCode!=code)
{
tmpRandomAccessFile.writeBytes(String.valueOf(fCode));
tmpRandomAccessFile.writeBytes("\n");
tmpRandomAccessFile.writeBytes(fTitle);
tmpRandomAccessFile.writeBytes("\n");
}
}
tmpRandomAccessFile.seek(0);
randomAccessFile.seek(0);
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine());
randomAccessFile.writeBytes("\n");
tmpRandomAccessFile.readLine();
String recordCountString = String.valueOf(recordCount - 1);
while(recordCountString.length()<10) recordCountString+=" ";
randomAccessFile.writeBytes(recordCountString);
randomAccessFile.writeBytes("\n");
while(tmpRandomAccessFile.getFilePointer()<tmpRandomAccessFile.length())
{
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine()+"\n");
}
randomAccessFile.setLength(tmpRandomAccessFile.length());
randomAccessFile.close();
tmpRandomAccessFile.setLength(0);
tmpRandomAccessFile.close();
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}


public Set<DesignationDTOInterface> getAll() throws DAOException
{
Set<DesignationDTOInterface> designations;
designations = new TreeSet<>();
try{
File file = new File(FILE_NAME);
if(!(file.exists())) return designations;
RandomAccessFile randomAccessFile;
randomAccessFile = new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return designations;
}
DesignationDTOInterface designationDTO;
randomAccessFile.readLine();
randomAccessFile.readLine();
int fCode;
String fTitle;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fCode = Integer.parseInt(randomAccessFile.readLine());
fTitle = randomAccessFile.readLine();
designationDTO = new DesignationDTO();
designationDTO.setCode(fCode);
designationDTO.setTitle(fTitle);
designations.add(designationDTO);
}
randomAccessFile.close();
return designations;
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}


public DesignationDTOInterface getByCode(int code) throws DAOException
{
if(code <= 0 ) throw new DAOException("Invalid code : " + code);
try
{
File file = new File(FILE_NAME);
if(file.exists()==false) throw new DAOException("Invalid code : " + code);
RandomAccessFile randomAccessFile;
randomAccessFile = new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid code : " + code);
}
randomAccessFile.readLine();
int recordCount = Integer.parseInt(randomAccessFile.readLine().trim());
if(recordCount==0)
{
randomAccessFile.close();
throw new DAOException("Invalid code : " + code);
} 
int fCode;
String fTitle;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fCode=Integer.parseInt(randomAccessFile.readLine());
fTitle=randomAccessFile.readLine();
if(fCode==code)
{
randomAccessFile.close();
DesignationDTOInterface designationDTO;
designationDTO = new DesignationDTO();
designationDTO.setCode(code);
designationDTO.setTitle(fTitle);
return designationDTO;
}
}
randomAccessFile.close();
throw new DAOException("Invalid code : " + code);
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}


public DesignationDTOInterface getByTitle(String title) throws DAOException
{
if(title==null || title.trim().length()==0) throw new DAOException("Invalid title : " + title);
title = title.trim();
try
{
File file = new File(FILE_NAME);
if(file.exists()==false) throw new DAOException("Invalid title : " + title);
RandomAccessFile randomAccessFile;
randomAccessFile = new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid title : " + title);
}
randomAccessFile.readLine();
int recordCount = Integer.parseInt(randomAccessFile.readLine().trim());
if(recordCount==0)
{
randomAccessFile.close();
throw new DAOException("Invalid title : " + title);
} 
int fCode;
String fTitle;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fCode=Integer.parseInt(randomAccessFile.readLine());
fTitle=randomAccessFile.readLine();
if(fTitle.equalsIgnoreCase(title))
{
randomAccessFile.close();
DesignationDTOInterface designationDTO;
designationDTO = new DesignationDTO();
designationDTO.setCode(fCode);
designationDTO.setTitle(fTitle);
return designationDTO;
}
}
randomAccessFile.close();
throw new DAOException("Invalid title : " + title);
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public boolean codeExists(int code) throws DAOException
{
if(code <=0) return false;
try
{
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
int recordCount = Integer.parseInt(randomAccessFile.readLine().trim()); //trim krna mat bhulna warna numberformatexception
if(recordCount==0)
{
randomAccessFile.close();
return false;
} 
int fCode;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fCode = Integer.parseInt(randomAccessFile.readLine());
if(fCode==code)
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

public boolean titleExists(String title) throws DAOException
{
if(title==null || title.trim().length()==0) return false; //humsha pehle title null check kiya jayga
title = title.trim();
try
{
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
int recordCount = Integer.parseInt(randomAccessFile.readLine().trim());
if(recordCount==0)
{
randomAccessFile.close();
return false;
} 
String fTitle;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
randomAccessFile.readLine();
fTitle=randomAccessFile.readLine();
if(fTitle.equalsIgnoreCase(title))
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


}