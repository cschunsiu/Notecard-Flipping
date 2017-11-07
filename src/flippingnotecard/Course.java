/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flippingnotecard;

import java.sql.*;
import java.util.*;

/**
 *
 * @author cschunsiu
 */
public class Course {
  Connection conn = null;
  ResultSet rs = null;
  PreparedStatement pst = null;
  PreparedStatement pst2 = null;
  
  public ArrayList<String> showCourse()
  {
    String query = "select * from course";
    ArrayList<String> rsArr = new ArrayList();
    try
    {
      conn = sql_connection.ConnecrDB();
      pst = conn.prepareStatement(query);
      rs = pst.executeQuery();
      System.out.println("executed");
      while (rs.next()) {
        rsArr.add(rs.getString("courseName"));
      }
      conn.close();
    } catch (Exception e) {
      System.out.println(e);
    }
    return rsArr;
  }
  
  public void addCourse(String courseName) {
    String query = "INSERT into course(courseName) VALUES (?)";
    try
    {
      conn = sql_connection.ConnecrDB();
      pst = conn.prepareStatement(query);
      pst.setString(1, courseName);
      pst.execute();
      System.out.println("course added");
      conn.close();
    }
    catch (Exception e) {
      System.out.println("Error, cant add course");
    }
  }
  
  public void deleteCourse(String courseName) {
    String query = "delete from course where courseName = ?";
    String query2 = "delete FROM courseList where course = (select courseID from course where courseName = ?)";
    try
    {
      conn = sql_connection.ConnecrDB();
      pst = conn.prepareStatement(query2);
      pst.setString(1, courseName);
      pst.execute();
      pst2 = conn.prepareStatement(query);
      pst2.setString(1, courseName);
      pst2.execute();
      conn.close();
      
      System.out.println("course deleted");
    }
    catch (Exception e) {
      System.err.println(e);
      System.out.println("Error, cant delete course");
    }
  }
  
  public void deleteAllCourse() {
    String query = "delete from course";
    String query2 = "delete FROM courseList";
    try
    {
      conn = sql_connection.ConnecrDB();
      pst = conn.prepareStatement(query);
      pst.execute();
      System.out.println("All course deleted");
      pst2 = conn.prepareStatement(query2);
      pst2.execute();
      conn.close();
    }
    catch (Exception e) {
      System.out.println("Error, cant delete course");
    }
  }
}
