package Tests;

import Model.DAO.DAOFactory;
import Model.DAO.DepartmentDAO;
import Model.Entities.Department;

public class TestDepartment {
    public static void main(String[] args) {
        DepartmentDAO departmentDAO = DAOFactory.createDepartmentDAO();
        // TEST - FIND BY ID
        System.out.println("==============================================");
        System.out.println("------------------- TEST FIND BY ID: ");
        System.out.println(departmentDAO.findById(2));
        // TEST FIND ALL
        System.out.println("==============================================");
        System.out.println("------------------- TEST FIND ALL: ");
        for (Department dep : departmentDAO.findAll()) {
            System.out.println(dep);
        }
        // TEST - INSERT
        System.out.println("==============================================");
        System.out.println("------------------- TEST INSERT: ");
        Department department = new Department(null, "Publicity");
        departmentDAO.insert(department);
        System.out.println(departmentDAO.findById(department.getId()));
        // TEST UPDATE
        System.out.println("==============================================");
        System.out.println("------------------- TEST UPDATE: ");
        department.setName("Test");
        departmentDAO.update(department);
        System.out.println("UPDATE: " + departmentDAO.findById(department.getId()));
        // TEST DELETE
        System.out.println("==============================================");
        System.out.println("------------------- TEST DELETE: ");
        departmentDAO.deleteById(department.getId());
        System.out.println(departmentDAO.findById(department.getId()) == null ? "DELETE COMPLETED" : "ID NOT FOUND");
    }
}