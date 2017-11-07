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
public class Content {
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public HashMap<String, String> showContent(String courseName) {
      String query = "select * from courseList where course = (select courseID from course where courseName = ? )";
      HashMap<String, String> rsArr = new HashMap();
      try
      {
        conn = sql_connection.ConnecrDB();
        pst = conn.prepareStatement(query);
        pst.setString(1, courseName);
        rs = pst.executeQuery();
        System.out.println("executed");
        while (rs.next()) {
            rsArr.put(rs.getString("cardName"), rs.getString("cardAws"));
        }
        conn.close();
      } catch (Exception e) {
        System.out.println(e);
      }
      return rsArr;
    }

    public void deleteAllContent(String courseName) {
      String query = "delete FROM courseList where course = (select courseID from course where courseName = ?)";
      try {
        conn = sql_connection.ConnecrDB();
        pst = conn.prepareStatement(query);
        pst.setString(1, courseName);
        pst.execute();
        System.out.println("deleteAllContent executed");
        conn.close();
      } catch (Exception e) {
        System.err.println(e);
        System.out.println("Error, cant execute");
      }
    }

    public void deleteSelectedContent(String cardName) {
      String query = "delete FROM courseList where cardName = ?";
      try {
        conn = sql_connection.ConnecrDB();
        pst = conn.prepareStatement(query);
        pst.setString(1, cardName);
        pst.execute();
        System.out.println("delete Content executed");
        conn.close();
      } catch (Exception e) {
        System.err.println(e);
        System.out.println("Error, cant execute");
      }
    }

    public void addContent(String cardName, String cardAnswer, String courseName)
    {
      String query = "INSERT into courseList(cardName,cardAws,course) VALUES (?,?,(select courseID from course where courseName = ?))";
      try
      {
        conn = sql_connection.ConnecrDB();
        pst = conn.prepareStatement(query);
        pst.setString(1, cardName);
        pst.setString(2, cardAnswer);
        pst.setString(3, courseName);
        pst.execute();
        System.out.println("course added");
        conn.close();
      }
      catch (Exception e) {
        System.out.println(e);
      }
    }
}
