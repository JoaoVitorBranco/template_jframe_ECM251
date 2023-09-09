package bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    
    public static void create(StudentData data){
        String sqlInsert = "INSERT INTO DADOS VALUES(0, ?, ?, ?, ?)";
        Connection conn = ConnectorFactory.getConn();
        PreparedStatement stmt = null;
        try{
            stmt = conn.prepareStatement(sqlInsert);
            stmt.setInt(1, data.getIdUser());
            stmt.setDouble(2, data.getGrade());
            stmt.setString(3, data.getCurrentDateString());
            stmt.setString(4, data.getCurrentTimeString());
            stmt.executeUpdate();
        }
        catch(SQLException e){
            try{   
                conn.rollback();
            }
            catch(SQLException ex){
                System.out.println("Erro ao incluir os dados" + ex.toString());
            }
        }
        finally{   
            ConnectorFactory.closeConn(conn, stmt);
        }
    }

    public static StudentData get(int id_grade){
        String sqlSelect = "SELECT * FROM DADOS WHERE id_grade = ?";
        Connection conn = ConnectorFactory.getConn();
        PreparedStatement stmt = null;
        ResultSet rs;
        StudentData data = new StudentData();
        try{   
            stmt = conn.prepareStatement(sqlSelect);
            stmt.setInt(1, id_grade);
            rs = stmt.executeQuery();
            while(rs.next()){
                data.setIdGrade(rs.getInt("id_grade"));
                data.setIdUser(rs.getInt("id_user"));
                data.setGrade(rs.getFloat("grade"));
                data.setDate(rs.getDate("date"));
                data.setTime(rs.getTime("time"));
            }
        }
        catch(SQLException ex){   
            System.out.println("Erro ao consultar os dados" + ex.toString());
        }
        finally{
            ConnectorFactory.closeConn(conn, stmt);
        }       
        return data;
    }
    
    public static void update(StudentData data){
        String sqlUpdate = "UPDATE DADOS SET grade = ?, date = ?, time = ? WHERE id_grade = ?";
        Connection conn = ConnectorFactory.getConn();
        PreparedStatement stmt = null;
        try{   
            stmt = conn.prepareStatement(sqlUpdate);
            stmt.setDouble(1, data.getGrade());
            stmt.setString(2, data.getCurrentDateString());
            stmt.setString(3, data.getCurrentTimeString());
            stmt.setInt(4, data.getIdGrade());
            stmt.executeUpdate();
        }
        catch(SQLException e){
            try{
                conn.rollback();
            }
            catch(SQLException ex){
                System.out.println("Erro ao alterar os dados" + ex.toString());
            }
        }
        finally{   
            ConnectorFactory.closeConn(conn, stmt);
        }  
    }
    
    public static void delete(int id_grade){
        String sqlDelete = "DELETE FROM DADOS WHERE id_grade = ?";
        Connection conn = ConnectorFactory.getConn();
        PreparedStatement stmt = null;
        try{   
            stmt = conn.prepareStatement(sqlDelete);
            stmt.setInt(1, id_grade);
            stmt.executeUpdate();
        }
        catch(SQLException e){   
            try{
                conn.rollback();
            }
            catch(SQLException ex){
                System.out.println("Erro ao excluir os dados" + ex.toString());
            }
        }
        finally{
            ConnectorFactory.closeConn(conn, stmt);
        }
    }
    
    public static ArrayList<StudentData> get_all(){
        ArrayList<StudentData> dados = new ArrayList<>();
        String sqlSelect = "SELECT * FROM DADOS";
        Connection conn = ConnectorFactory.getConn();
        PreparedStatement stmt = null;
        ResultSet result_set;
        try{   
            stmt = conn.prepareStatement(sqlSelect);
            result_set = stmt.executeQuery();
            while(result_set.next()){  
                StudentData data = new StudentData(
                    result_set.getInt("id_grade"),
                    result_set.getInt("id_user")
                );
                data.setGrade(result_set.getFloat("grade"));
                data.setDate(result_set.getDate("date"));
                data.setTime(result_set.getTime("time"));
                dados.add(data);
            }
        }
        catch(SQLException ex){
            System.out.println("Erro ao buscar todos os dados" + ex.toString());
        }
        finally{   
            ConnectorFactory.closeConn(conn, stmt);
        }   
        return dados;
    }
}