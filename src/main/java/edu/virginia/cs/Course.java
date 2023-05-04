package edu.virginia.cs;

public class Course {
    private int ID;
    private String Department;
    private int Catalog;
    public Course(String Department, int catalog){
        this.Department = Department;
        this.Catalog = catalog;

    }
    public int getID(){
        return ID;
    }
    public String getDepartment(){
        return Department;
    }
    public int getCatalog(){
        return Catalog;
    }
}
