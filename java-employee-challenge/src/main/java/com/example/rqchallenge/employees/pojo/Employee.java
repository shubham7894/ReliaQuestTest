package com.example.rqchallenge.employees.pojo;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Employee {
	
    private String id;
    @JsonProperty("employee_name")
    @JsonAlias("name")
    private String name;
    @JsonProperty("employee_salary")
    @JsonAlias("salary")
    private int salary;
    @JsonProperty("employee_age")
    @JsonAlias("age")
    private int age;
    @JsonProperty("profile_image")
    private String profileImage;



    public Employee() {
    }

    public Employee(String id, String name, int salary, int age, String profileImage) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.age = age;
        this.profileImage = profileImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
