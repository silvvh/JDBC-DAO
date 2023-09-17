import Model.DAO.DAOFactory;
import Model.DAO.SellerDAO;
import Model.Entities.Department;
import Model.Entities.Seller;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        SellerDAO sellerDAO = DAOFactory.createSellerDAO();
        // TEST FIND BY ID
        System.out.println("------------------- TEST SEARCH BY DEPARTMENT: " + '\n' + sellerDAO.findById(1));
        // TEST FIND BY DEPARTMENT
        System.out.println("==============================================");
        Department department = new Department(2, "Electronics");
        System.out.println("------------------- TEST SEARCH BY DEPARTMENT: ");
        for (Seller s : sellerDAO.findByDepartment(department)) {
            System.out.println(s);
        }
        // TEST FIND ALL
        System.out.println("==============================================");
        System.out.println("------------------- TEST SEARCH ALL: ");
        for (Seller s : sellerDAO.findAll()) {
            System.out.println(s);
        }
        // TEST INSERT
        System.out.println("==============================================");
        System.out.println("------------------- TEST INSERT: ");
        Seller seller = new Seller(null, "Test", "test@email.com", new Date(), 2500.00, department);
        // sellerDAO.insert(seller);
        // System.out.println(sellerDAO.findById(seller.getId()));
        // TEST UPDATE
        System.out.println("==============================================");
        System.out.println("------------------- TEST UPDATE: ");
        seller = sellerDAO.findById(3);
        seller.setName("Adam Adams");
        sellerDAO.update(seller);
        System.out.println("Updated data: " + sellerDAO.findById(3));
    }
}