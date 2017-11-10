/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flippingnotecard;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author cschunsiu
 */
public class sql_connection {
    Connection conn = null;
  
  public static Connection ConnecrDB() {
    try { 
      Class.forName("org.sqlite.JDBC");
      Connection conn = DriverManager.getConnection("jdbc:sqlite:course.sqlite");
      System.out.println("Connected");
      return conn;
    }
    catch (Exception e) {
      System.out.println(e); }
    return null;
  }
    
}
