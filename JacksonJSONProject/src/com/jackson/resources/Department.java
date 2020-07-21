package com.jackson.resources;

public class Department
{
   public Department(String deptName)
   {
      this.deptName = deptName;
   }
 
   private String deptName;
 
   public String getDeptName()
   {
      return deptName;
   }
 
   public void setDeptName(String deptName)
   {
      this.deptName = deptName;
   }
    
   @Override
   public String toString()
   {
      return "Department [deptName="+deptName+"]";
   }
}
