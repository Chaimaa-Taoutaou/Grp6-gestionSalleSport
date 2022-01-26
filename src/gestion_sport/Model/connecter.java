/*
 
 */
package gestion_sport.Model;


import gestion_sport.Controller.ActivityController;

import java.security.MessageDigest;
import java.sql.*;

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
    public  boolean existe(String name,String pass){
        
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
          public boolean adduser(String n,String p,String e,String pass){
      
    
      String req ="INSERT INTO utilisateur(nom_u,prenom_u,email_u,password_u) VALUES ('" + p + "','" +  n + "','" + e + "','" + pass + "')";
      try { 
          st.executeUpdate(req);
          return true;
          
      }catch(Exception ex){
         // System.out.println("bir prb var");
           return false;
      }
      }
      
        public void edituser(String req){
     
      try { 
          st.executeUpdate(req);
         
          
      }catch(Exception ex){
            System.out.println("benim");
      
      }
      }
      public ResultSet adhinfs(){
      
    
      String req ="SELECT * FROM adhrent";
      try { 
          rs =st.executeQuery(req);
         return rs;
          
      }catch(Exception ex){
     return null;
      
      }}
       public ResultSet salleinfs(){
      
    
      String req ="SELECT * FROM salle";
      try { 
          rs =st.executeQuery(req);
         return rs;
          
      }catch(Exception ex){
     return null;
      
      }
        }
       public ResultSet activiteinfos(){
       //`
       
         String req ="SELECT * FROM type_sport";
      try { 
          rs =st.executeQuery(req);
         return rs;
          
      }catch(Exception ex){
     return null;
      
      }
       }
       
            public void delser(String req ){
      
    
     
      try { 
          st.executeUpdate(req);
        
      }catch(Exception ex){
            System.out.println("benim");
         
      }
      }
            public ResultSet getibfo(String em){
      
    
      String req ="SELECT * FROM utilisateur where email_u='"+ em +"'";
      try { 
          rs =st.executeQuery(req);
         return rs;
          
      }catch(Exception ex){
     return null;
      
      }}
             public boolean updateuser(String req){
     
      try{
          rs =st.executeQuery(req);
         return true;
          
      }catch(Exception ex){
     return false;
      
      }}
            
    
        
        public boolean addsalle(String n,String a,String v,String tel,String e){
      
    
      String req ="INSERT INTO salle(nom_s,adresse,ville,tel,email) VALUES ('" + n + "','" +  a + "','" + v + "','" + tel + "','" + e + "')";
      try { 
          st.executeUpdate(req);
          return true;
          
      }catch(Exception ex){
         System.out.println("bir prb var");
           return false;
      }
      }   
           
          public boolean delsalle(String req ){
      
    
     
      try { 
          st.executeUpdate(req);
          return true;
         
        
      }catch(Exception ex){
            System.out.println("supp benim");
           return false;
         
      }
      } 
         public int recup(String em) {
         
             String req="Select id_s from salle where nom_s='"+ em +"'";
          
            try { 
                rs=st.executeQuery(req);
                if(rs.next()){
                     return rs.getInt(1);
                }
          
        
      }catch(Exception ex){
            
         } return -1; 
          
      }
          public int recupid(String em) {
         
             String req="Select id from admin where nom_s='"+ em +"'";
          
            try { 
                rs=st.executeQuery(req);
                if(rs.next()){
                     return rs.getInt(1);
                }
          
        
      }catch(Exception ex){
            
         } return -1; 
          
      }
           public boolean updatuser(String req){
     
      try { 
          st.executeUpdate(req);
          return true;
        
      }catch(Exception ex){
            System.out.println("prob ");
          return false;
      }   
           }
          public boolean updatesale(String req){
     
      try { 
          st.executeUpdate(req);
          return true;
        
      }catch(Exception ex){
            System.out.println("prob ");
          return false;
      }
      }

    boolean adduser(String p, String pn, String e, MessageDigest md) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
              public ResultSet searchsalle(String n){
                  String req="select * from salle  where nom like '"+ n +"'";
                  
                  
                   try { 
          
          return st.executeQuery(req);
        
      }catch(Exception ex){
            System.out.println("prob ");
          return null;
      }
               }
            public ResultSet chartM() {
      	 
      	 String req="SELECT c.adresse, COUNT(*) FROM adhrent a, club_sportive c where a.id_cs=c.id_cs GROUP by c.adresse order by c.adresse";
      	try {
  			return st.executeQuery(req);
  		} catch (SQLException e) { 
  			// TODO Auto-generated catch block
  			return null;
  		}
  		
        } 
        

       public ResultSet TotalActivity() {
     	  String req="SELECT COUNT(*) FROM type_sport";
       
       try {
 			return st.executeQuery(req);
 		} catch (SQLException e) { 
 			// TODO Auto-generated catch block
 			return null;
 		}
       }
       
       public ResultSet TotalCoach() {
     	  String req="SELECT COUNT(*) FROM formateur";
       
       try {
 			return st.executeQuery(req);
 		} catch (SQLException e) { 
 			// TODO Auto-generated catch block
 			return null;
 		}
       }
       
       public ResultSet TotalAdherent() {
     	  String req="SELECT COUNT(*) FROM adhrent";
       
       try {
 			return st.executeQuery(req);
 		} catch (SQLException e) { 
 			// TODO Auto-generated catch block
 			return null;
 		}
       }
       
       public ResultSet TotalSalle() {
     	  String req="SELECT COUNT(*) FROM club_sportive";
       
       try {
 			return st.executeQuery(req);
 		} catch (SQLException e) { 
 			// TODO Auto-generated catch block
 			return null;
 		}
       }
      
       public ResultSet pieChart() {
     	  String req="SELECT type_abon, COUNT(*) FROM adhrent, abonnement where adhrent.id_abon=abonnement.id_abon GROUP by type_abon";
           
           try {
     			return st.executeQuery(req);
     		} catch (SQLException e) { 
     			// TODO Auto-generated catch block
     			return null;
     		}
       }
       
       
       
       public ResultSet formateurinfs(){
   	    
   	    
   	    String req ="SELECT * FROM formateur";
   	    try { 
   	        rs =st.executeQuery(req);
   	       return rs;
   	        
   	    }catch(Exception ex){
   	   return null;
   	    
   	    }
     }
     


   	public boolean addformateur(String n,String p,String e,String a){
   	    
   	  
   	    String req ="INSERT INTO formateur(nom_f,prenom_f,email_f,adresse_f) VALUES ('" + n + "','" + p + "','" + e + "','" + a + "' )";
   	    try { 
   	        st.executeUpdate(req);
   	        return true;
   	       
   	        
   	    }catch(Exception ex){
   	          System.out.println("f");
   	          return false;
   	    
   	    }
   	}
   	
   	public int recup_formateur(String em) {
           
           String req="Select id_f from formateur where nom_f='"+ em +"'";
        
          try { 
              rs=st.executeQuery(req);
              if(rs.next()){
                   return rs.getInt(1);
              }
        
      
    }catch(Exception ex){
          
       } return -1; 
        
    }
   	
   	
   	public boolean delformteur(String req ){
   	      
   	    
   	     
   	      try { 
   	          st.executeUpdate(req);
   	          return true;
   	         
   	        
   	      }catch(Exception ex){
   	            System.out.println("supp formateur");
   	           return false;
   	         
   	      }
   	 }
   	
   	public boolean updateformateur(String req){
           
           try { 
               st.executeUpdate(req);
               return true;
             
           }catch(Exception ex){
                 System.out.println("prob ");
               return false;
           }
       }
   	
   	
   	
   	public ResultSet searchformateur(String n){
           String req="select * from formateur  where nom_f like '"+ n +"'";
           
           
            try { 
   
           	 return st.executeQuery(req);
 
            }catch(Exception ex){
           	 System.out.println("prob ");
           	 return null;
            }
        }

     public ResultSet activityinfs(){
         String req ="SELECT  type_sport.nom_a,formateur.nom_f,type_sport.prix FROM type_sport, formateur WHERE type_sport.id_f=formateur.id_f";
         try {
             rs =st.executeQuery(req);
             return rs;

         }catch(Exception ex){
             return null;

         }
     }

    public ResultSet getFormateur() {
        String req ="SELECT id_f,nom_f FROM formateur";
        try {
            rs =st.executeQuery(req);
            return rs;

        }catch(Exception ex){
            return null;

        }
    }

    public boolean addactivity(String nom,Float prix,Integer formateur){


        String req ="INSERT INTO type_sport(nom_a,prix,id_f) VALUES ('" + nom+ "','"+prix+"','"+formateur+"')";
       // String req ="INSERT INTO formateur(nom_f,prenom_f,email_f,adresse_f) VALUES ('" + n + "','" + p + "','" + e + "','" + a + "' )";
        try {
            st.executeUpdate(req);
            return true;

        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public int recupActivity(String em) {

        String req="Select id_ts from type_sport where nom_a='"+ em +"'";

        try {
            rs=st.executeQuery(req);
            if(rs.next()){
                return rs.getInt(1);
            }


        }catch(Exception ex){

        } return -1;

    }

    public boolean updateActivity(String req){

        try {
            st.executeUpdate(req);
            return true;

        }catch(Exception ex){
            System.out.println("prob ");
            return false;
        }
    }
    public boolean addseance(String jr, String hf, String hd, Integer id){


        String req ="INSERT INTO seance(jour,heureDebut,heureFin,id_ts) VALUES ('"+jr+"','"+hd+"','"+hf+"',"+id+")";
        // String req ="INSERT INTO formateur(nom_f,prenom_f,email_f,adresse_f) VALUES ('" + n + "','" + p + "','" + e + "','" + a + "' )";
        try {
            st.executeUpdate(req);
            return true;

        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public ResultSet seanceinfs(){
        Integer id= ActivityController.id_s;
        String req ="SELECT heureDebut, heureFin FROM seance where id_ts="+id+"";
        try {
            rs =st.executeQuery(req);
            return rs;

        }catch(Exception ex){
            return null;

        }
    }

    public ResultSet seanceacti(){

        String req ="SELECT id_ts FROM seance";
        try {
            rs =st.executeQuery(req);
            return rs;

        }catch(Exception ex){
            return null;

        }
    }
}