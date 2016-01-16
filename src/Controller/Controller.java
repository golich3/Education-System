package Controller;

import Course.CourseUI;
import Database.DbManager;
import Professor.ProfessorUI;
import Student.StudentUI;
import java.sql.SQLException;
import java.util.ArrayList;

public class Controller {
    DbManager db;

    public Controller() {
        db = new DbManager();
    }

    public void save(ArrayList<ProfessorUI> arrayProfessor,ArrayList<StudentUI> arrayStudent,ArrayList<CourseUI> arrayCourse) throws SQLException, ClassNotFoundException {
        this.db.save(arrayProfessor,arrayStudent,arrayCourse);
    }

    public ArrayList<ProfessorUI> loadProfDBData() throws SQLException {
        return this.db.loadProfDBData();
    }
    public ArrayList<StudentUI> loadStudentDBData() throws SQLException {
        return this.db.loadStudentDBData();
    }
}