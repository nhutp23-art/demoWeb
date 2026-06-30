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
import uef.edu.vn.model.DoctorAssignment;

/**
 *
 * @author ADMIN
 */
@Repository
public class DoctorAssignmentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // View Assignment List
    public List<DoctorAssignment> getAll() {

        String sql = "SELECT "
                + "da.assignmentID, "
                + "da.serviceCode, "
                + "ms.serviceName, "
                + "da.doctorID, "
                + "d.fullName AS doctorName, "
                + "da.assignmentDate "
                + "FROM DoctorAssignment da "
                + "INNER JOIN MedicalService ms "
                + "ON da.serviceCode = ms.serviceCode "
                + "INNER JOIN Doctor d "
                + "ON da.doctorID = d.doctorID "
                + "ORDER BY da.assignmentID";

        return jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(DoctorAssignment.class));
    }

    // Find Assignment By ID
    public DoctorAssignment findById(int assignmentID) {

        String sql = "SELECT "
                + "da.assignmentID, "
                + "da.serviceCode, "
                + "ms.serviceName, "
                + "da.doctorID, "
                + "d.fullName AS doctorName, "
                + "da.assignmentDate "
                + "FROM DoctorAssignment da "
                + "INNER JOIN MedicalService ms "
                + "ON da.serviceCode = ms.serviceCode "
                + "INNER JOIN Doctor d "
                + "ON da.doctorID = d.doctorID "
                + "WHERE da.assignmentID = ?";

        List<DoctorAssignment> list = jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(DoctorAssignment.class),
                assignmentID);

        return list.isEmpty() ? null : list.get(0);
    }

    // Assign Doctor
    public boolean insert(DoctorAssignment assignment) {

        String sql = "INSERT INTO DoctorAssignment "
                + "(serviceCode, doctorID, assignmentDate) "
                + "VALUES (?, ?, ?)";

        int rows = jdbcTemplate.update(
                sql,
                assignment.getServiceCode(),
                assignment.getDoctorID(),
                assignment.getAssignmentDate());

        return rows > 0;
    }

    // Update Assignment
    public boolean update(DoctorAssignment assignment) {

        String sql = "UPDATE DoctorAssignment SET "
                + "serviceCode = ?, "
                + "doctorID = ?, "
                + "assignmentDate = ? "
                + "WHERE assignmentID = ?";

        int rows = jdbcTemplate.update(
                sql,
                assignment.getServiceCode(),
                assignment.getDoctorID(),
                assignment.getAssignmentDate(),
                assignment.getAssignmentID());

        return rows > 0;
    }

    // Delete Assignment
    public boolean delete(int assignmentID) {

        String sql = "DELETE FROM DoctorAssignment "
                + "WHERE assignmentID = ?";

        int rows = jdbcTemplate.update(sql, assignmentID);

        return rows > 0;
    }

    // Check Service Assigned
    public boolean existsByService(String serviceCode) {

        String sql = "SELECT COUNT(*) "
                + "FROM DoctorAssignment "
                + "WHERE serviceCode = ?";

        Integer count = jdbcTemplate.queryForObject(
                sql,
                Integer.class,
                serviceCode);

        return count != null && count > 0;
    }

}
