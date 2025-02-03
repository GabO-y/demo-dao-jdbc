package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

                    Department dep = instantiateDepartment(rs);
                    Seller newSeller = instantiateSeller(rs);
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

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        return new Department(rs.getString("Name"), rs.getInt("Id"));
    }

    private Seller instantiateSeller(ResultSet rs) throws SQLException{
        return new Seller(rs.getInt("Id"), rs.getString("Name"), rs.getString("Email"), rs.getDate("BirthDate"), rs.getDouble("BaseSalary"), null);
    }

//    private Seller instantiateSeller(ResultSet rs) throws SQLException{
//
//        Seller s = new Seller();
//
//        s.setId(rs.getInt("Id"));
//        s.setName(rs.getString("Name"));
//        s.setEmail(rs.getString("Email"));
//        s.setBirthDate(rs.getDate("BirthDate"));
//        s.setBaseSalary(rs.getDouble("BaseSalary"));
//
//        return s;
//
//    }

//    private Department instantiateDepartment(ResultSet rs) throws SQLException {
//
//        Department d = new Department();
//
//        d.setName(rs.getString("Name"));
//        d.setId(rs.getInt("Id"));
//
//        return d;
//
//    }

    @Override
    public List<Seller> findAll() {
        return List.of();
    }
}
