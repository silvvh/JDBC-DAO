package Model.DAO.Impl;

import DB.DBException;
import Model.DAO.SellerDAO;
import Model.Entities.Department;
import Model.Entities.Seller;
import DB.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class SellerDaoJDBC implements SellerDAO {

    private final Connection connection;

    public SellerDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Seller seller) {

    }

    @Override
    public void update(Seller seller) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT seller.*, department.department_name as DepName " + "FROM Seller INNER JOIN department " + "ON seller.department_id = department.department_id " + "WHERE seller.seller_id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Department department = instantiateDepartment(resultSet);
                return instantiateSeller(resultSet, department);
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeResultSet(resultSet);
            DB.closeStatement(preparedStatement);
        }
        return null;
    }

    public List<Seller> findByDepartment(Department department) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT seller.* FROM seller " + "INNER JOIN department " + "ON seller.department_id = department.department_id " + "WHERE department.department_name = ? " + "ORDER BY seller.seller_name");
            preparedStatement.setString(1, department.getName());
            resultSet = preparedStatement.executeQuery();
            List<Seller> sellers = new ArrayList<>();
            while (resultSet.next()) {
                Seller seller = instantiateSeller(resultSet, department);
                sellers.add(seller);
            }
            return sellers;
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
            DB.closeResultSet(resultSet);
        }
    }

    private Department instantiateDepartment(ResultSet resultSet) {
        try {
            return new Department(resultSet.getInt("department_id"), resultSet.getString("DepName"));
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    private Seller instantiateSeller(ResultSet resultSet, Department department) {
        try {
            return new Seller(resultSet.getInt("seller_id"), resultSet.getString("seller_name"), resultSet.getString("email"), resultSet.getDate("birthdate"), resultSet.getDouble("basesalary"), department);
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    @Override
    public List<Seller> findAll() {

    }
}
