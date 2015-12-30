package Database;

import Course.CourseUI;
import Professor.ProfessorUI;
import Student.StudentUI;

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

    private void insert(List<ProfessorUI> arrayProfessor,List<StudentUI> arrayStudent,List<CourseUI> arrayCourse) throws SQLException {
        // TODO Save Professor

        String ProfNoSQLCommand = "select professorNo from [JavaTraining].[dbo].[M2.Professor]";
        Statement ProfNoStmt = con.createStatement();
        ResultSet profNoResultSet = ProfNoStmt.executeQuery(ProfNoSQLCommand);
        ArrayList<String> professorNumbers = new ArrayList<>();
        while (profNoResultSet.next()) {
            String professorNo = profNoResultSet.getString("professorNo");
            professorNumbers.add(professorNo);
        }
        String InsertProfessor = "insert into[JavaTraining].[dbo].[M2.Professor] (firstName,lastName,professorNo,phone,email,address,userName,password) VALUES (?,?,?,?,?,?,?,?)";
        for (ProfessorUI professor : arrayProfessor) {
            PreparedStatement stmt = con.prepareStatement(InsertProfessor);
            stmt.setString(1, professor.getFirstName());
            stmt.setString(2, professor.getLastName());
            stmt.setString(3, professor.getProfessorNo());
            stmt.setString(4, professor.getPhone());
            stmt.setString(5, professor.getEmail());
            stmt.setString(6, professor.getAddress());
            stmt.setString(7, professor.getUserName());
            stmt.setString(8, professor.getPassword());

            if (professorNumbers.contains(professor.getProfessorNo())) {
                continue;
            }
            stmt.execute();
            System.out.println("Save");
        }
        // TODO Save Student
        String studentNoSQLCommand = "select studentNo from [JavaTraining].[dbo].[M2.student]";
        Statement studentNoStmt = con.createStatement();
        ResultSet studentNoResultSet = studentNoStmt.executeQuery(studentNoSQLCommand);
        ArrayList<String> studentNumbers = new ArrayList<>();
        while (studentNoResultSet.next()) {
            String studentNo = profNoResultSet.getString("studentNo");
            professorNumbers.add(studentNo);
        }
        String InsertStudent = "insert into[JavaTraining].[dbo].[M2.Professor] (firstName,lastName,studentNo,phone,email,address,userName,password) VALUES (?,?,?,?,?,?,?,?)";
        for (StudentUI student : arrayProfessor) {
            PreparedStatement stmt = con.prepareStatement(InsertStudent);
            stmt.setString(1, student.getFirstName());
            stmt.setString(2, student.getLastName());
            stmt.setString(3, student.getStudentNo());
            stmt.setString(4, student.getPhone());
            stmt.setString(5, student.getEmail());
            stmt.setString(6, student.getAddress());
            stmt.setString(7, student.getUserName());
            stmt.setString(8, student.getPassword());

            if (studentNumbers.contains(student.getStudentNo())) {
                continue;
            }
            stmt.execute();
            System.out.println("Save");
        }
        // TODO Save Course
//        String sqlCourse = "INSERT INTO [JavaTraining].[dbo].[SimpleCalculator]  "+ "VALUES (" + result + ",getdate()" + ")";

//        stmt.executeUpdate(sqlStudent);
//        stmt.executeUpdate(sqlCourse);


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
