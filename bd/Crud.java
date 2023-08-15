package bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import entidades.StudentData;


public class Crud{
    public static String get_password(String username){
        String sqlSelect = "SELECT SENHA FROM LOGIN_SENHA WHERE USERNAME = ?";
        Connection conn = ConnectorFactory.getConn();
        PreparedStatement stmt = null;
        ResultSet rs;
        String password = "";
        try{   
            stmt = conn.prepareStatement(sqlSelect);
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            if(rs.next()){
                password = rs.getString(1);
            }
        }
        catch(SQLException ex){   
            System.out.println("Erro ao consultar os dados" + ex.toString());
        }
        finally{
            ConnectorFactory.closeConn(conn, stmt);
        }       
        return password;
    }

    public static int get_id_by_name(String username){
       String sqlSelect = "SELECT id FROM LOGIN_SENHA WHERE USERNAME = ?";
        Connection conn = ConnectorFactory.getConn();
        PreparedStatement stmt = null;
        ResultSet rs;
        int index = 0;
        try{   
            stmt = conn.prepareStatement(sqlSelect);
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            if(rs.next()){
                index = rs.getInt(1);
            }
        }
        catch(SQLException ex){   
            System.out.println("Erro ao consultar os dados" + ex.toString());
        }
        finally{
            ConnectorFactory.closeConn(conn, stmt);
        }       
        return index; 
    }
    
    public static StudentData get_student_data(StudentData student){
        String sqlSelect = "SELECT final_grade, miss, date, time FROM DADOS WHERE id = ?";
        Connection conn = ConnectorFactory.getConn();
        PreparedStatement stmt = null;
        ResultSet result_set;
        try{   
            stmt = conn.prepareStatement(sqlSelect);
            stmt.setInt(1, student.getId());
            result_set = stmt.executeQuery();
            while(result_set.next()){ 
                student.setDate(result_set.getDate("date"));
                student.setTime(result_set.getTime("time"));
                student.setFinal_grade(result_set.getFloat("final_grade"));
                student.setMiss(result_set.getInt("miss"));
            }
        }
        catch(SQLException ex)
        {   System.out.println("Erro ao buscar todos os dados" + ex.toString());
        }
        finally
        {   
            ConnectorFactory.closeConn(conn, stmt);
        }   
        return student;
        
    }
    
    public static int update_student_data(StudentData student){
        String sqlUpdate = "UPDATE DADOS SET final_grade = ?, miss = ?, date = ?, time = ? WHERE id = ?";
        Connection conn = ConnectorFactory.getConn();
        PreparedStatement stmt = null;
        try{   
            stmt = conn.prepareStatement(sqlUpdate);
            Date date = new Date();  
            stmt.setDouble(1, student.getFinal_grade_double());
            stmt.setInt(2, student.getMiss());
            stmt.setString(3, student.getCurrentDateString());
            stmt.setString(4, student.getCurrentTimeString());
            stmt.setInt(5, student.getId());
            stmt.executeUpdate();
        }
        catch(SQLException e)
        {   try
            {   conn.rollback();
            }
            catch(SQLException ex)
            {   System.out.println("Erro ao alterar os dados" + ex.toString());
            }
        }
        finally
        {   
            ConnectorFactory.closeConn(conn, stmt);
        }  
        return 1;
    }

}