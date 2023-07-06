package edu.spring.models;

public class Person {

  private int id;
  private String fullName;
  private int year;

  public Person() {
  }

  public Person(int id, String fullName, int year) {
    this.id = id;
    this.fullName = fullName;
    this.year = year;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }
}
