package Database;

import Course.CourseUI;
import Professor.ProfessorUI;
import Student.StudentUI;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbManager {
    public Connection con;

    public void save(List<ProfessorUI> arrayProfessor,List<StudentUI> arrayStudent,List<CourseUI> arrayCource) throws SQLException {
        connect();
        insert(arrayProfessor,arrayStudent,arrayCource);
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
            PreparedStatement insertProfessorStatement = con.prepareStatement(InsertProfessor);
            insertProfessorStatement.setString(1, professor.getFirstName());
            insertProfessorStatement.setString(2, professor.getLastName());
            insertProfessorStatement.setString(3, professor.getProfessorNo());
            insertProfessorStatement.setString(4, professor.getPhone());
            insertProfessorStatement.setString(5, professor.getEmail());
            insertProfessorStatement.setString(6, professor.getAddress());
            insertProfessorStatement.setString(7, professor.getUserName());
            insertProfessorStatement.setString(8, professor.getPassword());

            if (professorNumbers.contains(professor.getProfessorNo())) {
                continue;
            }
            insertProfessorStatement.execute();
            System.out.println("Save");
        }
        // TODO Save Student
        String studentNoSQLCommand = "select studentNo from [JavaTraining].[dbo].[M2.student]";
        Statement studentNoStmt = con.createStatement();
        ResultSet studentNoResultSet = studentNoStmt.executeQuery(studentNoSQLCommand);
        ArrayList<String> studentNumbers = new ArrayList<>();
        while (studentNoResultSet.next()) {
            String studentNo = studentNoResultSet.getString("studentNo");
            studentNumbers.add(studentNo);
        }
        String InsertStudent = "insert into[JavaTraining].[dbo].[M2.Student] (firstName,lastName,studentNo,phone,email,address,userName,password) VALUES (?,?,?,?,?,?,?,?)";
        for (StudentUI student : arrayStudent) {
            PreparedStatement insertStudentStatement = con.prepareStatement(InsertStudent);
            insertStudentStatement.setString(1, student.getFirstName());
            insertStudentStatement.setString(2, student.getLastName());
            insertStudentStatement.setString(3, student.getStudentNo());
            insertStudentStatement.setString(4, student.getPhone());
            insertStudentStatement.setString(5, student.getEmail());
            insertStudentStatement.setString(6, student.getAddress());
            insertStudentStatement.setString(7, student.getUserName());
            insertStudentStatement.setString(8, student.getPassword());

            if (studentNumbers.contains(student.getStudentNo())) {
                continue;
            }
            insertStudentStatement.execute();
            System.out.println("Save");
        }
        // TODO Save Course
        String courseNoSQLCommand = "select courseNo from [JavaTraining].[dbo].[M2.Course]";
        Statement courseNoStmt = con.createStatement();
        ResultSet courseNoResultSet = courseNoStmt.executeQuery(courseNoSQLCommand);
        ArrayList<String> courseNumbers = new ArrayList<>();
        while (courseNoResultSet.next()) {
            String courseNo = courseNoResultSet.getString("courseNo");
            courseNumbers.add(courseNo);
        }
        String InsertCourse = "insert into[JavaTraining].[dbo].[M2.Course] (CourseNo,CourseName,ProfessorName) VALUES (?,?,?)";
        for (CourseUI course : arrayCourse) {
            PreparedStatement insertCourseStatement = con.prepareStatement(InsertCourse);
            insertCourseStatement.setString(1, course.getCourseNo());
            insertCourseStatement.setString(2, course.getCourseName());
            insertCourseStatement.setString(3, course.getProfessor());

            if (courseNumbers.contains(course.getCourseNo())) {
                continue;
            }
            insertCourseStatement.execute();
            System.out.println("Save");
        }


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
