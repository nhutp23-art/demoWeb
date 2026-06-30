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
import uef.edu.vn.model.Appointment;

/**
 *
 * @author ADMIN
 */
@Repository
public class AppointmentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // View all appointments
    public List<Appointment> getAll() {

        String sql =
                "SELECT a.appointmentID, " +
                "a.patientID, " +
                "p.fullName AS patientName, " +
                "a.serviceCode, " +
                "m.serviceName, " +
                "a.appointmentDate, " +
                "a.status, " +
                "a.createdAt " +
                "FROM Appointment a " +
                "INNER JOIN Patient p ON a.patientID = p.patientID " +
                "INNER JOIN MedicalService m ON a.serviceCode = m.serviceCode " +
                "ORDER BY a.appointmentDate DESC";

        return jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(Appointment.class));
    }

    // Appointment history by patient
    public List<Appointment> getByPatient(int patientID) {

        String sql =
                "SELECT a.appointmentID, " +
                "a.patientID, " +
                "p.fullName AS patientName, " +
                "a.serviceCode, " +
                "m.serviceName, " +
                "a.appointmentDate, " +
                "a.status, " +
                "a.createdAt " +
                "FROM Appointment a " +
                "INNER JOIN Patient p ON a.patientID=p.patientID " +
                "INNER JOIN MedicalService m ON a.serviceCode=m.serviceCode " +
                "WHERE a.patientID=? " +
                "ORDER BY a.appointmentDate DESC";

        return jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(Appointment.class),
                patientID);
    }

    // Find appointment
    public Appointment findById(int appointmentID) {

        String sql =
                "SELECT a.appointmentID, " +
                "a.patientID, " +
                "p.fullName AS patientName, " +
                "a.serviceCode, " +
                "m.serviceName, " +
                "a.appointmentDate, " +
                "a.status, " +
                "a.createdAt " +
                "FROM Appointment a " +
                "INNER JOIN Patient p ON a.patientID=p.patientID " +
                "INNER JOIN MedicalService m ON a.serviceCode=m.serviceCode " +
                "WHERE appointmentID=?";

        List<Appointment> list =
                jdbcTemplate.query(
                        sql,
                        new BeanPropertyRowMapper<>(Appointment.class),
                        appointmentID);

        return list.isEmpty() ? null : list.get(0);
    }

    // Book appointment
    public boolean insert(Appointment appointment) {

        String sql =
                "INSERT INTO Appointment " +
                "(patientID,serviceCode,appointmentDate,status) " +
                "VALUES(?,?,?,?)";

        int rows = jdbcTemplate.update(
                sql,
                appointment.getPatientID(),
                appointment.getServiceCode(),
                appointment.getAppointmentDate(),
                appointment.getStatus());

        return rows > 0;
    }

    // Update status
    public boolean updateStatus(int appointmentID, String status) {

        String sql =
                "UPDATE Appointment " +
                "SET status=? " +
                "WHERE appointmentID=?";

        int rows = jdbcTemplate.update(
                sql,
                status,
                appointmentID);

        return rows > 0;
    }

    // Delete
    public boolean delete(int appointmentID) {

        String sql =
                "DELETE FROM Appointment " +
                "WHERE appointmentID=?";

        return jdbcTemplate.update(sql, appointmentID) > 0;
    }

    // Search by patient or service
    public List<Appointment> search(String keyword) {

        String sql =
                "SELECT a.appointmentID, " +
                "a.patientID, " +
                "p.fullName AS patientName, " +
                "a.serviceCode, " +
                "m.serviceName, " +
                "a.appointmentDate, " +
                "a.status, " +
                "a.createdAt " +
                "FROM Appointment a " +
                "INNER JOIN Patient p ON a.patientID=p.patientID " +
                "INNER JOIN MedicalService m ON a.serviceCode=m.serviceCode " +
                "WHERE p.fullName LIKE ? " +
                "OR m.serviceName LIKE ? " +
                "ORDER BY a.appointmentDate DESC";

        String value = "%" + keyword + "%";

        return jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(Appointment.class),
                value,
                value);
    }

    // Check duplicate booking
    public boolean isDuplicateAppointment(
            int patientID,
            String serviceCode,
            java.sql.Date appointmentDate) {

        String sql =
                "SELECT COUNT(*) " +
                "FROM Appointment " +
                "WHERE patientID=? " +
                "AND serviceCode=? " +
                "AND appointmentDate=?";

        Integer count = jdbcTemplate.queryForObject(
                sql,
                Integer.class,
                patientID,
                serviceCode,
                appointmentDate);

        return count != null && count > 0;
    }

    // Check service available
    public boolean isServiceAvailable(String serviceCode) {

        String sql =
                "SELECT COUNT(*) " +
                "FROM MedicalService " +
                "WHERE serviceCode=? " +
                "AND status='Available'";

        Integer count = jdbcTemplate.queryForObject(
                sql,
                Integer.class,
                serviceCode);

        return count != null && count > 0;
    }

}