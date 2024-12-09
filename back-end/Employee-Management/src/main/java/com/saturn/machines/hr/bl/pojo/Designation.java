package com.saturn.machines.hr.bl.pojo;
import com.saturn.machines.hr.bl.interfaces.pojo.*;
public class Designation implements DesignationInterface
{
private int code;
private String title;
public Designation()
{
this.code = 0;
this.title = null;
}
public int getCode()
{
return this.code;
}
public void setCode(int code)
{
this.code = code;
}
public void setTitle(String title)
{
this.title = title;
}
public String getTitle()
{
return this.title;
}
public boolean equals(Object other)
{
if(!(other instanceof DesignationInterface)) return false;
DesignationInterface designation = (DesignationInterface)other;
return this.code == designation.getCode();
}
public int hashCode()
{
return this.code;
}
public int compareTo(DesignationInterface designation)
{
return this.code-designation.getCode();
}
}						