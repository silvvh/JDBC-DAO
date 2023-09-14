package Model.DAO;

import Model.DAO.Impl.SellerDaoJDBC;

public class DAOFactory {
    public static SellerDAO createSellerDAO () {
        return new SellerDaoJDBC();
    }
}
