package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

public class SellerDaoJDBC implements SellerDao {

    private Connection conn;

    public SellerDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(Seller obj) {

    }

    @Override
    public void update(Seller obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {

        String sql = "SELECT seller.*, department.Name as DepName " +
                "FROM seller INNER JOIN department " +
                "ON seller.DepartmentId = department.id " +
                "WHERE seller.Id = ?";

        PreparedStatement pt = null;
        ResultSet rs = null;

        try{

            pt = conn.prepareStatement(sql);

            pt.setInt(1, id);

            rs = pt.executeQuery();

                if(rs.next()){

                    Department dep = new Department();
                    Seller newSeller = new Seller();

                    dep.setId(rs.getInt("DepartmentId"));
                    dep.setName(rs.getString("DepName"));

                    newSeller.setId(rs.getInt("Id"));
                    newSeller.setName(rs.getString("Name"));
                    newSeller.setEmail(rs.getString("Email"));
                    newSeller.setBirthDate(rs.getDate("BirthDate"));
                    newSeller.setBaseSalary(rs.getDouble("BaseSalary"));
                    newSeller.setDepartment(dep);

                    return newSeller;

                }

                return null;

        }catch(Exception e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(pt);
            DB.closeResultSet(rs);
        }

    }

    @Override
    public List<Seller> findAll() {
        return List.of();
    }
}
