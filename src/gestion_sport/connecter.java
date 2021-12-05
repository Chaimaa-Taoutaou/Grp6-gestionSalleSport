/*
 
 */
package gestion_sport;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class connecter {
           
    private Connection cn;
    private Statement st;
    private ResultSet rs;
    
    public connecter() {
        try { 
           Class.forName("com.mysql.jdbc.Driver" );
          cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/club","root","");
          st=cn.createStatement();
      }catch(Exception ex){
      System.out.println(ex.getMessage());
      }
    }
      boolean existe(String name,String pass){
        
    String req ="SELECT * FROM admin WHERE usernam='" + name + "' and  passsword='" + pass + "'";
      try { 
          rs =st.executeQuery(req);
          if(rs.next()){ return true;}
          
      }catch(Exception ex){
      System.out.println(ex.getMessage());
      }
     return false;
    }
      //
      public ResultSet utilsateurinfs(){
      
    
      String req ="SELECT * FROM utilisateur";
      try { 
          rs =st.executeQuery(req);
         return rs;
          
      }catch(Exception ex){
     return null;
      
      }
      
      }
          public void adduser(String n,String p,String e,String pass){
      
    
      String req ="INSERT INTO utilisateur(prenom,nom,email,pass) VALUES ('" + n + "','" + p + "','" + e + "','" + pass + "' )";
      try { 
          st.executeUpdate(req);
         
          
      }catch(Exception ex){
            System.out.println("benim");
      
      }
      }
      
        public void edituser(String req){
     
      try { 
          st.executeUpdate(req);
         
          
      }catch(Exception ex){
            System.out.println("benim");
      
      }
      }
      
       public ResultSet salleinfs(){
      
    
      String req ="SELECT * FROM salle";
      try { 
          rs =st.executeQuery(req);
         return rs;
          
      }catch(Exception ex){
     return null;
      
      }
        }
}