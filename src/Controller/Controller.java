package Controller;

import Course.CourseUI;
import Database.DbManager;
import Professor.ProfessorUI;
import Student.StudentUI;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    DbManager db;

    public Controller() {
        db = new DbManager();
    }

    public void save(List<ProfessorUI> arrayProfessor,List<StudentUI> arrayStudent,List<CourseUI> arrayCourse) throws SQLException, ClassNotFoundException {
        this.db.save(arrayProfessor,arrayStudent,arrayCourse);
    }

    public List<ProfessorUI> loadTableData() throws SQLException {
        return this.db.loadTableData();
    }
}