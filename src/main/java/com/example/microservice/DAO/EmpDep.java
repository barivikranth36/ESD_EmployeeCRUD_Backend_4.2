package com.example.microservice.DAO;

/* This Class Structure is to send data of employee alongwith department name, it
was unable to send directly and hence I've to create another such structure
 */
import com.example.microservice.Bean.Employee;

public class EmpDep {
    private int employee_id;
    private String first_name;
    private String last_name;
    private String email;
    private String title;
    private String photo_path;
    private String name;

    public EmpDep() {
    }

    public EmpDep(int employee_id, String first_name, String last_name, String email, String title, String photo_path, String name) {
        this.employee_id = employee_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.title = title;
        this.photo_path = photo_path;
        this.name = name;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhoto_path() {
        return photo_path;
    }

    public void setPhoto_path(String photo_path) {
        this.photo_path = photo_path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
