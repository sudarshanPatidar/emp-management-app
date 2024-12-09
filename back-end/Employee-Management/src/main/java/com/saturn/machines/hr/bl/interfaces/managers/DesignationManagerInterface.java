package com.saturn.machines.hr.bl.interfaces.managers;
import com.saturn.machines.hr.bl.exceptions.*;
import com.saturn.machines.hr.bl.interfaces.pojo.*;
import java.util.*;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
@Component
@Primary
public interface DesignationManagerInterface
{
public DesignationInterface addDesignation(DesignationInterface designation) throws BLException;
public DesignationInterface updateDesignation(DesignationInterface designation) throws BLException;
public void removeDesignation(int code) throws BLException;
public DesignationInterface getDesignationByCode(int code) throws BLException;
public DesignationInterface getDesignationByTitle(String title) throws BLException;
public Set<DesignationInterface> getDesignations() throws BLException;
public int getDesignationCount() throws BLException;
public boolean designationCodeExists(int code) throws BLException;
public boolean designationTitleExists(String title) throws BLException;
}