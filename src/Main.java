import Model.DAO.DAOFactory;
import Model.DAO.SellerDAO;
import Model.Entities.Department;
import Model.Entities.Seller;

public class Main {
    public static void main(String[] args) {
        SellerDAO sellerDAO = DAOFactory.createSellerDAO();
        // TEST FIND BY ID
        System.out.println("Search by id result: " + sellerDAO.findById(1) + '\n');
        // TEST FIND BY DEPARTMENT
        Department department = new Department(2, "Electronics");
        System.out.println("Searcy by department result: ");
        for (Seller s : sellerDAO.findByDepartment(department)) {
            System.out.println(s);
        }
    }
}