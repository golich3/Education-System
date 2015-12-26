package Database;
import Professor.ProfessorUI;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbManager {
    public Connection con;

    public void save(List<ProfessorUI> arrayProfessor) throws SQLException {
        connect();
        insert(arrayProfessor);
//        disconnect();
    }
    public boolean connect() throws SQLException {
        if (con != null) {
            return true;
        }
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String connectionURL = "jdbc:sqlserver://swsql.mahanair.aero;user=sa;password=123;database=javaTraining";
        con = DriverManager.getConnection(connectionURL);
        System.out.println("Connected");

        if (con == null) {
            return false;
        }
        return true;
    }
    private void insert(List<ProfessorUI> arrayProfessor) throws SQLException {
        // TODO Save Professor

        String SQLCheckCommand = "select professorNo from [JavaTraining].[dbo].[M2.Professor]";
        Statement checkStm = con.createStatement();
        ResultSet rs = checkStm.executeQuery(SQLCheckCommand);
        ArrayList<String> professorNumbers = new ArrayList<>() ;
        while(rs.next()){
           String professorNo = rs.getString("professorNo");
            professorNumbers.add(professorNo);
        }
        String sqlProfessor = "insert into[JavaTraining].[dbo].[M2.Professor] (firstName,lastName,professorNo,phone,email,address,userName,password) VALUES (?,?,?,?,?,?,?,?)";
        for (ProfessorUI professor : arrayProfessor) {
            PreparedStatement stmt = con.prepareStatement(sqlProfessor);
            stmt.setString(1, professor.getFirstName());
            stmt.setString(2, professor.getLastName());
            stmt.setString(3, professor.getProfessorNo());
            stmt.setString(4, professor.getPhone());
            stmt.setString(5, professor.getEmail());
            stmt.setString(6, professor.getAddress());
            stmt.setString(7, professor.getUserName());
            stmt.setString(8, professor.getPassword());
           {
                if (professor.getProfessorNo().equals(rs.getString("professorNo"))){
                    break;
                }
            }

            stmt.execute();
        }
        // TODO Save Student
//        String sqlStudent = "INSERT INTO [JavaTraining].[dbo].[SimpleCalculator]  "+ "VALUES (" + result + ",getdate()" + ")";
        // TODO Save Course
//        String sqlCourse = "INSERT INTO [JavaTraining].[dbo].[SimpleCalculator]  "+ "VALUES (" + result + ",getdate()" + ")";

//        stmt.executeUpdate(sqlStudent);
//        stmt.executeUpdate(sqlCourse);
        System.out.println("Save");

    }
//    public void disconnect() {
//        if (con != null) {
//            try {
//                con.close();
//                System.out.println("Disconnected");
//            } catch (SQLException e) {
//                System.out.println("Could not disconnect");
//            }
//        }
//    }
}
