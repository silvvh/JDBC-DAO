package Model.DAO;

import DB.DBException;
import Model.Entities.Department;
import Model.Entities.Seller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;

public interface SellerDAO {
    void insert(Seller seller);
    void update(Seller seller);
    void deleteById(Integer id);
    Seller findById(Integer id);
    List<Seller> findByDepartment(Department department);
    List<Seller> findAll();

    default Department instantiateDepartment(ResultSet resultSet) {
        try {
            return new Department(resultSet.getInt("department_id"), resultSet.getString("DepName"));
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
