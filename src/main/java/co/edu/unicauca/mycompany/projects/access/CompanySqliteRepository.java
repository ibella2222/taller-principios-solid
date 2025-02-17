package co.edu.unicauca.mycompany.projects.access;

import co.edu.unicauca.mycompany.projects.domain.entities.Company;
import co.edu.unicauca.mycompany.projects.domain.entities.Sector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CompanySqliteRepository implements ICompanyRepository {
    private Connection conn;

    public CompanySqliteRepository() {
        initDatabase();
    }

    @Override
    public boolean save(Company newCompany) {
        connect();
        try {
            if (newCompany == null || newCompany.getNit().isBlank()) {
                return false;
            }

            String sql = "INSERT INTO Company (Nit, Name, Phone, Email, PageWeb, Sector, Password) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newCompany.getNit());
            pstmt.setString(2, newCompany.getName());
            pstmt.setString(3, newCompany.getPhone());
            pstmt.setString(4, newCompany.getEmail());
            pstmt.setString(5, newCompany.getPageWeb());
            pstmt.setString(6, newCompany.getSector().toString());
            pstmt.setString(7, newCompany.getPassword());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CompanySqliteRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            disconnect();
        }
    }

    @Override
    public List<Company> listAll() {
        List<Company> companies = new ArrayList<>();
        connect();
        try {
            String sql = "SELECT * FROM Company";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Company company = new Company();
                company.setNit(rs.getString("Nit"));
                company.setName(rs.getString("Name"));
                company.setPhone(rs.getString("Phone"));
                company.setEmail(rs.getString("Email"));
                company.setPageWeb(rs.getString("PageWeb"));
                company.setSector(Sector.valueOf(rs.getString("Sector")));
                company.setPassword(rs.getString("Password"));
                companies.add(company);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CompanySqliteRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            disconnect();
        }
        return companies;
    }

    private void initDatabase() {
        String sql = "CREATE TABLE IF NOT EXISTS Company (\n"
                + " Nit text PRIMARY KEY,\n"
                + " Name text NOT NULL,\n"
                + " Phone text,\n"
                + " Email text,\n"
                + " PageWeb text,\n"
                + " Sector text,\n"
                + " Password text\n"
                + ");";

        try {
            connect();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(CompanySqliteRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            disconnect();
        }
    }

    public void connect() {
        String url = "jdbc:sqlite:./mydatabase.db"; // Cambia esto si necesitas una base de datos en memoria
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException ex) {
            Logger.getLogger(CompanySqliteRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void disconnect() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public Company findByNit(String nit) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(Company company) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
