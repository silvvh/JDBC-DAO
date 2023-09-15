import Model.DAO.DAOFactory;
import Model.DAO.SellerDAO;
import Model.Entities.Department;
import Model.Entities.Seller;

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
        for (Seller s : sellerDAO.findAll()) {
            System.out.println(s);
        }
    }
}