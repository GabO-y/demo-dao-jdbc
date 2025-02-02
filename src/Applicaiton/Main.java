package Applicaiton;

import model.entities.Department;
import model.entities.Seller;

import java.sql.Date;

public class Main {

    public static void main(String[] args) {

        Department department = new Department("Books", 1);

        Seller seller = new Seller(1, "Gabriel", "Gabriel@gmail.com", Date.valueOf("2000-09-10"), 2000.0, department);

        System.out.println(seller);

    }

}