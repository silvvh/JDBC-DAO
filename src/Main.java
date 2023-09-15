import Model.DAO.DAOFactory;
import Model.DAO.SellerDAO;
import Model.Entities.Department;

public class Main {
    public static void main(String[] args) {
        SellerDAO sellerDAO = DAOFactory.createSellerDAO();
        System.out.println(sellerDAO.findById(1));
    }
}