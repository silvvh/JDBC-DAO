package Model.DAO;

import Model.Entities.Department;
import Model.Entities.Seller;

import java.util.HashSet;
import java.util.List;

public interface SellerDAO {
    void insert(Seller seller);
    void update(Seller seller);
    void deleteById(Integer id);
    Seller findById(Integer id);
    List<Seller> findByDepartment(Department department);
    List<Seller> findAll();
}
