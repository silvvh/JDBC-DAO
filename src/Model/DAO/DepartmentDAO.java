package Model.DAO;

import DB.DBException;
import Model.Entities.Department;
import Model.Entities.Seller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface DepartmentDAO {
    void insert(Department department);
    void update(Department department);
    void deleteById(Integer ID);
    Department findById(Integer ID);
    List<Department> findAll();
    default Department instantiateDepartment(ResultSet resultSet) {
        try {
            return new Department(resultSet.getInt("department_id"), resultSet.getString("department_name"));
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    default Seller instantiateSeller(ResultSet resultSet, Department department) {
        try {
            return new Seller(resultSet.getInt("seller_id"), resultSet.getString("seller_name"), resultSet.getString("email"), resultSet.getDate("birthdate"), resultSet.getDouble("basesalary"), department);
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }
}
