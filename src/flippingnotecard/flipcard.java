/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flippingnotecard;

import java.util.*;

/**
 *
 * @author cschunsiu
 */
public class flipcard {
    private ArrayList<String> indexedMap;
    private HashMap<String, String> listMap;
    public static int counter = 0;
 
    public void setMap(HashMap mapping) {
      this.listMap = mapping;
    }

    public HashMap<String, String> getListMap() {
      return this.listMap;
    }

    public ArrayList getIndexedMap() {
      return this.indexedMap;
    }

    public int getCounter() {
      return counter;
    }


    public void initRandomListMap() {
      this.indexedMap = new ArrayList(this.listMap.keySet());
      Collections.shuffle(this.indexedMap);
      System.err.println(this.indexedMap);
    }  
}
