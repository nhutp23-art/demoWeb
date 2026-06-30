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
import uef.edu.vn.model.Doctor;

/**
 *
 * @author ADMIN
 */
@Repository
public class DoctorRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // View all doctors
    public List<Doctor> getAll() {

        String sql = "SELECT * FROM Doctor ORDER BY doctorID";

        return jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(Doctor.class));
    }

    // Find doctor by ID
    public Doctor findById(int doctorID) {

        String sql = "SELECT * FROM Doctor WHERE doctorID = ?";

        List<Doctor> list = jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(Doctor.class),
                doctorID);

        return list.isEmpty() ? null : list.get(0);
    }

    // Search doctor by name or specialty
    public List<Doctor> search(String keyword) {

        String sql = "SELECT * FROM Doctor "
                + "WHERE fullName LIKE ? "
                + "OR specialty LIKE ? "
                + "ORDER BY doctorID";

        String searchValue = "%" + keyword + "%";

        return jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(Doctor.class),
                searchValue,
                searchValue);
    }

    // Add new doctor
    public boolean insert(Doctor doctor) {

        String sql = "INSERT INTO Doctor "
                + "(fullName, specialty, email, phoneNumber) "
                + "VALUES (?, ?, ?, ?)";

        int rows = jdbcTemplate.update(
                sql,
                doctor.getFullName(),
                doctor.getSpecialty(),
                doctor.getEmail(),
                doctor.getPhoneNumber());

        return rows > 0;
    }

    // Update doctor
    public boolean update(Doctor doctor) {

        String sql = "UPDATE Doctor SET "
                + "fullName = ?, "
                + "specialty = ?, "
                + "email = ?, "
                + "phoneNumber = ? "
                + "WHERE doctorID = ?";

        int rows = jdbcTemplate.update(
                sql,
                doctor.getFullName(),
                doctor.getSpecialty(),
                doctor.getEmail(),
                doctor.getPhoneNumber(),
                doctor.getDoctorID());

        return rows > 0;
    }

    // Delete doctor
    public boolean delete(int doctorID) {

        String sql = "DELETE FROM Doctor WHERE doctorID = ?";

        int rows = jdbcTemplate.update(sql, doctorID);

        return rows > 0;
    }

    // Check duplicate email
    public boolean emailExists(String email) {

        String sql = "SELECT COUNT(*) FROM Doctor WHERE email = ?";

        Integer count = jdbcTemplate.queryForObject(
                sql,
                Integer.class,
                email);

        return count != null && count > 0;
    }

    // Check duplicate phone
    public boolean phoneExists(String phoneNumber) {

        String sql = "SELECT COUNT(*) FROM Doctor WHERE phoneNumber = ?";

        Integer count = jdbcTemplate.queryForObject(
                sql,
                Integer.class,
                phoneNumber);

        return count != null && count > 0;
    }

}
