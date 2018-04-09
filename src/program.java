import java.sql.*;
public class program {

	public program() {
		// TODO Auto-generated constructor stub
	}
	public static void insert_statement(boolean a){   
		long startTime = System.nanoTime();
        try{  
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con = DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/lab8","root","");  
            
            con.setAutoCommit(a);
            Statement st = con.createStatement();
            Statement _deleteTableDtataStmt = con.createStatement();
            String _deleteTableData ="TRUNCATE TABLE student";
            _deleteTableDtataStmt.executeUpdate(_deleteTableData);
            String sql;
            String userName = "talha";
            String x;
            for(int i=0; i< 5000; i++){
            x = userName + Integer.toString(i);
            System.out.println(i);
            sql = "INSERT INTO student (id,name, sem) VALUES(default,'"+x+"' ,'1')";
            st.executeUpdate(sql);
            }
            if(a == false){
                con.commit();
            }
            con.close();  
    }
    catch(Exception e)
    { 
        System.out.println(e);
    } 
        long endTime = System.nanoTime();
        long Time = endTime-startTime;
        System.out.println(Time);
        
}
    public static void Pre_Ins(boolean a){
    	long startTime = System.nanoTime();
        try{  
        	Class.forName("com.mysql.jdbc.Driver");  
            Connection con = DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/lab8","root","");  
               
            con.setAutoCommit(a);
            PreparedStatement stmt;
            Statement _deleteTableDtataStmt = con.createStatement();
            String _deleteTableData ="TRUNCATE TABLE student1";
            _deleteTableDtataStmt.executeUpdate(_deleteTableData);
            String name= "TALHA";
            for(int i=0; i< 5000; i++){
                System.out.println(i);
                stmt=con.prepareStatement("insert into student1(id,name,sem) VALUES(default,?,?)");  
                stmt.setString(1,name+Integer.toString(i));//1 specifies the first parameter in the query  
                stmt.setString(2,"3");
               
                stmt.executeUpdate();  
            }  
            
            if(a == false){
                con.commit();
            }
            
            con.close();  
  
        }
        catch(Exception e){ 
            System.out.println(e);}  
        long endTime = System.nanoTime();
        long Time = endTime-startTime;
        System.out.println(Time);
        }
    public static void Batch(boolean a){
    	long sTime = System.nanoTime();
        try{  
        	Class.forName("com.mysql.jdbc.Driver");  
            Connection con = DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/lab8","root",""); 
            
            con.setAutoCommit(a);
            Statement st = con.createStatement();
            Statement _deleteTableDtataStmt = con.createStatement();
            String _deleteTableData ="TRUNCATE TABLE student2";
            _deleteTableDtataStmt.executeUpdate(_deleteTableData);
            String sql;
            String userName = "talha";
            String x;
            for(int i=0; i< 5000; i++){
            x = userName + Integer.toString(i);
            System.out.println(i);
            sql = "INSERT INTO student2 ( id,name, sem) VALUES(default,'"+x+"' ,'1')";
            st.addBatch(sql);   
            }
            
            st.executeBatch();//executing the batch 
            if(a == false){
                con.commit();
            }
            con.close();  
    }
    catch(Exception e)
    { 
        System.out.println(e);
    } 
        long eTime = System.nanoTime();
        long Time = eTime-sTime;
        System.out.println(Time);
}
    public static void stored(){
 	   long startTime = System.nanoTime();
     try{
         Class.forName("com.mysql.jdbc.Driver");  
         Connection con = DriverManager.getConnection(  
             "jdbc:mysql://localhost:3306/lab8","root","");  
         String simpleProc = "{ call my_fun() }";
     
         CallableStatement cs = con.prepareCall(simpleProc);
         cs.execute();
     }
     catch(Exception e)
     { 
         System.out.println(e);
     }
     long endTime = System.nanoTime();
     long Time = endTime-startTime;
     System.out.println(Time);
 }

	public static void main(String[] args) {
		insert_statement(false);
		insert_statement(true);
		Pre_Ins(true);
		Pre_Ins(false);
		Batch(true);
		Batch(false);
		stored();
	}
}
