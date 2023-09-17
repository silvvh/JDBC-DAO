package Model.DAO.Impl;

import Model.DAO.DepartmentDAO;
import Model.Entities.Department;
import DB.DB;
import DB.DBException;
import DB.DBIntegrityException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDAO {
    private Connection connection = DB.startConnection();

    public DepartmentDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Department department) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO department " + "(department_name) " + "VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, department.getName());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    department.setId(id);
                }
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
            DB.closeResultSet(resultSet);
        }
    }

    @Override
    public void update(Department department) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("UPDATE department " + "SET department_name = ? " + "WHERE department_id = ?");
            preparedStatement.setString(1, department.getName());
            preparedStatement.setInt(2, department.getId());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
        finally {
            DB.closeStatement(preparedStatement);
        }
    }

    @Override
    public void deleteById(Integer ID) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("DELETE " + "FROM department " + "WHERE department_id = ?");
            preparedStatement.setInt(1, ID);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DBIntegrityException(e.getMessage());
        }
        finally {
            DB.closeStatement(preparedStatement);
        }
    }

    @Override
    public Department findById(Integer ID) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * " + "FROM department " + "WHERE department_id = ?");
            preparedStatement.setInt(1, ID);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return instantiateDepartment(resultSet);
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeResultSet(resultSet);
            DB.closeStatement(preparedStatement);
        }
        return null;
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * " + "FROM department");
            resultSet = preparedStatement.executeQuery();
            List<Department> departments = new ArrayList<>();
            while (resultSet.next()) {
                Department department = instantiateDepartment(resultSet);
                departments.add(department);
            }
            return departments;
        }
        catch(SQLException e){
            throw new DBException(e.getMessage());
        }
        finally{
            DB.closeStatement(preparedStatement);
            DB.closeResultSet(resultSet);
        }
    }
}
