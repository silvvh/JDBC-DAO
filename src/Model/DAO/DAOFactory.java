package Model.DAO;

import Model.DAO.Impl.DepartmentDaoJDBC;
import Model.DAO.Impl.SellerDaoJDBC;
import DB.DB;

public class DAOFactory {
    public static SellerDAO createSellerDAO() {
        return new SellerDaoJDBC(DB.startConnection());
    }

    public static DepartmentDAO createDepartmentDAO() {
        return new DepartmentDaoJDBC(DB.startConnection());
    }
}
