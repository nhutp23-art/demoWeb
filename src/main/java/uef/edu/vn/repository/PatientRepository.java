/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uef.edu.vn.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uef.edu.vn.model.Patient;

/**
 *
 * @author ADMIN
 */
@Repository
public class PatientRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // View all patients
    public List<Patient> getAll() {

        String sql = "SELECT * FROM Patient ORDER BY patientID";

        return jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(Patient.class));
    }

    // Find patient by ID
    public Patient findById(int patientID) {

        String sql = "SELECT * FROM Patient WHERE patientID = ?";

        List<Patient> list = jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(Patient.class),
                patientID);

        return list.isEmpty() ? null : list.get(0);
    }

    // Search patient by name, email or phone
    public List<Patient> search(String keyword) {

        String sql = "SELECT * FROM Patient "
                + "WHERE fullName LIKE ? "
                + "OR email LIKE ? "
                + "OR phoneNumber LIKE ? "
                + "ORDER BY patientID";

        String searchValue = "%" + keyword + "%";

        return jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(Patient.class),
                searchValue,
                searchValue,
                searchValue);
    }

    // Add new patient
    public boolean insert(Patient patient) {

        String sql = "INSERT INTO Patient "
                + "(fullName, dateOfBirth, email, phoneNumber) "
                + "VALUES (?, ?, ?, ?)";

        int rows = jdbcTemplate.update(
                sql,
                patient.getFullName(),
                patient.getDateOfBirth(),
                patient.getEmail(),
                patient.getPhoneNumber());

        return rows > 0;
    }

    // Update patient
    public boolean update(Patient patient) {

        String sql = "UPDATE Patient SET "
                + "fullName = ?, "
                + "dateOfBirth = ?, "
                + "email = ?, "
                + "phoneNumber = ? "
                + "WHERE patientID = ?";

        int rows = jdbcTemplate.update(
                sql,
                patient.getFullName(),
                patient.getDateOfBirth(),
                patient.getEmail(),
                patient.getPhoneNumber(),
                patient.getPatientID());

        return rows > 0;
    }

    // Delete patient
    public boolean delete(int patientID) {

        String sql = "DELETE FROM Patient WHERE patientID = ?";

        int rows = jdbcTemplate.update(sql, patientID);

        return rows > 0;
    }

    // Check duplicate email
    public boolean emailExists(String email) {

        String sql = "SELECT COUNT(*) FROM Patient WHERE email = ?";

        Integer count = jdbcTemplate.queryForObject(
                sql,
                Integer.class,
                email);

        return count != null && count > 0;
    }

    // Check duplicate phone
    public boolean phoneExists(String phoneNumber) {

        String sql = "SELECT COUNT(*) FROM Patient WHERE phoneNumber = ?";

        Integer count = jdbcTemplate.queryForObject(
                sql,
                Integer.class,
                phoneNumber);

        return count != null && count > 0;
    }

}
