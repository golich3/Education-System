package Controller;

import Database.DbManager;
import Professor.ProfessorUI;

import java.sql.SQLException;
import java.util.List;

public class Controller {
    DbManager db;

    public Controller() {
        db = new DbManager();
    }

    public void save(List<ProfessorUI> arrayProfessor) throws SQLException, ClassNotFoundException {
        this.db.save(arrayProfessor);
    }
}