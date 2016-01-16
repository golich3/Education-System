package Database;

import Course.CourseUI;
import Professor.ProfessorUI;
import Student.StudentUI;
import java.sql.*;
import java.util.ArrayList;

public class DbManager {
    public Connection con;

    public void save(ArrayList<ProfessorUI> arrayProfessor,ArrayList<StudentUI> arrayStudent,ArrayList<CourseUI> arrayCource) throws SQLException {
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

    private void insert(ArrayList<ProfessorUI> arrayProfessor,ArrayList<StudentUI> arrayStudent,ArrayList<CourseUI> arrayCourse) throws SQLException {
        // TODO Save Professor

        String ProfNoSQLCommand = "select professorNo from [JavaTraining].[dbo].[M2.Professor]";
        String profNoDelete = "delete from [JavaTraining].[dbo].[M2.Professor] where professorNo = ?";
        Statement ProfNoStmt = con.createStatement();
        ResultSet profNoResultSet = ProfNoStmt.executeQuery(ProfNoSQLCommand);
        ArrayList<String> professorNumbers = new ArrayList<>();
        while (profNoResultSet.next()) {
            String professorNo = profNoResultSet.getString("professorNo");
            professorNumbers.add(professorNo);
        }

        boolean contains;
        for (int i = 0; i < professorNumbers.size(); i++) {
            contains = false;
            for (int j = 0; j < arrayProfessor.size(); j++) {
                if (arrayProfessor.get(j).getProfessorNo().equals(professorNumbers.get(i))) {
                    contains = true;
                    break;
                }
            }
            if (!contains) {
                PreparedStatement deleteProfessorStatement = con.prepareStatement(profNoDelete);
                deleteProfessorStatement.setString(1, professorNumbers.get(i));
                deleteProfessorStatement.executeUpdate();
                System.out.print("Professor deleted");
            }
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

            System.out.println("Professor Saved");
        }
        // TODO Save Student
        String studentNoSQLCommand = "select studentNo from [JavaTraining].[dbo].[M2.student]";
        String studentNoDelete = "delete from [JavaTraining].[dbo].[M2.Student] where studentNo = ?";
        Statement studentNoStmt = con.createStatement();
        ResultSet studentNoResultSet = studentNoStmt.executeQuery(studentNoSQLCommand);
        ArrayList<String> studentNumbers = new ArrayList<>();
        while (studentNoResultSet.next()) {
            String studentNo = studentNoResultSet.getString("studentNo");
            studentNumbers.add(studentNo);
        }
        for (int i = 0; i < studentNumbers.size(); i++) {
            contains = false;
            for (int j = 0; j < arrayStudent.size(); j++) {
                if (arrayStudent.get(j).getStudentNo().equals(studentNumbers.get(i))) {
                    contains = true;
                    break;
                }
            }
            if (!contains) {
                PreparedStatement deleteStudentStatement = con.prepareStatement(studentNoDelete);
                deleteStudentStatement.setString(1, studentNumbers.get(i));
                deleteStudentStatement.executeUpdate();
                System.out.print("Student deleted");
            }
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
            System.out.println("Student Saved");
        }
        // TODO Save Course
        String courseNoSQLCommand = "select courseNo from [JavaTraining].[dbo].[M2.Course]";
        String courseNoDelete = "delete from [JavaTraining].[dbo].[M2.Course] where CourseNo = ?";
        Statement courseNoStmt = con.createStatement();
        ResultSet courseNoResultSet = courseNoStmt.executeQuery(courseNoSQLCommand);
        ArrayList<String> courseNumbers = new ArrayList<>();
        while (courseNoResultSet.next()) {
            String courseNo = courseNoResultSet.getString("courseNo");
            courseNumbers.add(courseNo);
        }
//        for (int i = 0; i < courseNumbers.size(); i++) {
//            contains = false;
//            for (int j = 0; j < arrayCourse.size(); j++) {
//                if (arrayCourse.get(j).getCourseNo().equals(courseNumbers.get(i))) {
//                    contains = true;
//                    break;
//                }
//            }
//            if (!contains) {
//                PreparedStatement deleteCourseStatement = con.prepareStatement(courseNoDelete);
//                deleteCourseStatement.setString(1, studentNumbers.get(i));
//                deleteCourseStatement.executeUpdate();
//                System.out.print("Course deleted");
//            }
//        }
//        String InsertCourse = "insert into[JavaTraining].[dbo].[M2.Course] (CourseNo,CourseName,ProfessorName) VALUES (?,?,?)";
//        for (CourseUI course : arrayCourse) {
//            PreparedStatement insertCourseStatement = con.prepareStatement(InsertCourse);
//            insertCourseStatement.setString(1, course.getCourseNo());
//            insertCourseStatement.setString(2, course.getCourseName());
//            insertCourseStatement.setString(3, course.getProfessor());
//
//            if (courseNumbers.contains(course.getCourseNo())) {
//                continue;
//            }
//            insertCourseStatement.execute();
//            System.out.println("Save");
//        }
    }

    public void disconnect() {
        if (con != null) {
            try {
                con.close();
                System.out.println("Disconnected");
            } catch (SQLException e) {
                System.out.println("Could not disconnect");
            }
        }
    }

    public ArrayList<ProfessorUI> loadProfDBData() throws SQLException {
        connect();
        String ProfListSQLCommand = "select * from [JavaTraining].[dbo].[M2.Professor]";
        Statement ProfListStmt = con.createStatement();
        ResultSet profListResultSet = ProfListStmt.executeQuery(ProfListSQLCommand);
        ArrayList<ProfessorUI> professorList = new ArrayList<>();
        while (profListResultSet.next()) {
            ProfessorUI newProfessor = new ProfessorUI();
            newProfessor.setProfessorNo(profListResultSet.getString("ProfessorNo"));
            newProfessor.setFirstName(profListResultSet.getString("FirstName"));
            newProfessor.setLastName(profListResultSet.getString("LastName"));
            newProfessor.setEmail(profListResultSet.getString("Email"));
            newProfessor.setPhone(profListResultSet.getString("Phone"));
            newProfessor.setUserName(profListResultSet.getString("UserName"));
            newProfessor.setPassword(profListResultSet.getString("Password"));
            newProfessor.setAddress(profListResultSet.getString("Address"));
            professorList.add(newProfessor);
        }
        return professorList;
    }

    public ArrayList<StudentUI> loadStudentDBData() throws SQLException {
        connect();
        String studentListSQLCommand = "select * from [JavaTraining].[dbo].[M2.Student]";
        Statement studentListStmt = con.createStatement();
        ResultSet studentListResultSet = studentListStmt.executeQuery(studentListSQLCommand);
        ArrayList<StudentUI> studentList = new ArrayList<>();
        while (studentListResultSet.next()) {
            StudentUI newStudent = new StudentUI();
            newStudent.setStudentNo(studentListResultSet.getString("studentNo"));
            newStudent.setFirstName(studentListResultSet.getString("FirstName"));
            newStudent.setLastName(studentListResultSet.getString("LastName"));
            newStudent.setEmail(studentListResultSet.getString("Email"));
            newStudent.setPhone(studentListResultSet.getString("Phone"));
            newStudent.setUserName(studentListResultSet.getString("UserName"));
            newStudent.setPassword(studentListResultSet.getString("Password"));
            newStudent.setAddress(studentListResultSet.getString("Address"));
            studentList.add(newStudent);
        }
        return studentList;
    }
}
