package shared;

import java.io.Serializable;

public class NewRegisteredUser implements Serializable
{
  private int id;
  private String firstName;
  private String lastName;
  private String username;
  private String password;
  private String phoneNumber;

  public NewRegisteredUser(int id, String firstName, String lastName,
      String username, String password, String phoneNumber)
  {

    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.password = password;
    this.phoneNumber = phoneNumber;
  }

  public NewRegisteredUser(String firstName, String lastName, String username,
      String password, String phoneNumber)
  {

    this.id = 0;
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.password = password;
    this.phoneNumber = phoneNumber;
  }

  public NewRegisteredUser(String username,String firstName,String lastName)
  {
    this.id = 0;
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.password = password;
    this.phoneNumber = null;
  }
  public NewRegisteredUser(String username,String password)
  {
    this.id = 0;
    this.firstName = null;
    this.lastName = null;
    this.username = username;
    this.password = password;
    this.phoneNumber = null;
  }

  public String getPhoneNumber()
  {
    return phoneNumber;
  }

  public String getFirstName()
  {
    return firstName;
  }

  public String getLastName()
  {
    return lastName;
  }

  public String getUsername()
  {
    return username;
  }

  public String getPassword()
  {
    return password;
  }

  public int getId()
  {
    return id;
  }

  @Override public String toString()
  {
    return "NewRegisteredUser{" + "firstName='" + firstName + '\''
        + ", lastName='" + lastName + '\'' + ", username='" + username + '\''
        + ", password='" + password + '\'' + ", phoneNumber='" + phoneNumber
        + '\'' + '}';
  }
}
