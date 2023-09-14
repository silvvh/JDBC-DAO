package Model.DAO;

import Model.Entities.Department;

import java.util.List;

public interface DepartmentDAO {
    void insert(Department department);
    void update(Department department);
    void deleteById(Integer ID);
    Department findById(Integer ID);
    List<Department> findAll();
}
