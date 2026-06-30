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
import uef.edu.vn.model.MedicalService;

/**
 *
 * @author ADMIN
 */
@Repository
public class MedicalServiceRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // View all services
    public List<MedicalService> getAll() {
        String sql = "SELECT * FROM MedicalService ORDER BY serviceCode";

        return jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(MedicalService.class)
        );
    }

    // Find by service code
    public MedicalService findByCode(String serviceCode) {

        String sql = "SELECT * FROM MedicalService WHERE serviceCode = ?";

        List<MedicalService> list = jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(MedicalService.class),
                serviceCode
        );

        return list.isEmpty() ? null : list.get(0);
    }

    // Search by code or name
    public List<MedicalService> search(String keyword) {

        String sql = "SELECT * FROM MedicalService WHERE serviceCode LIKE ? OR serviceName LIKE ? ORDER BY serviceCode";

        String searchValue = "%" + keyword + "%";

        return jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(MedicalService.class),
                searchValue,
                searchValue
        );
    }

    // Add new service
    public boolean insert(MedicalService service) {

        String sql = "INSERT INTO MedicalService(serviceCode, serviceName, category, description, fee, duration, status) VALUES (?, ?, ?, ?, ?, ?, ?)";

        int rows = jdbcTemplate.update(
                sql,
                service.getServiceCode(),
                service.getServiceName(),
                service.getCategory(),
                service.getDescription(),
                service.getFee(),
                service.getDuration(),
                service.getStatus()
        );

        return rows > 0;
    }

    // Update service
    public boolean update(MedicalService service) {

        String sql = "UPDATE MedicalService SET serviceName = ?, category = ?, description = ?, fee = ?, duration = ?, status = ? WHERE serviceCode = ?";
                     

        int rows = jdbcTemplate.update(
                sql,
                service.getServiceName(),
                service.getCategory(),
                service.getDescription(),
                service.getFee(),
                service.getDuration(),
                service.getStatus(),
                service.getServiceCode()
        );

        return rows > 0;
    }

    // Delete service
    public boolean delete(String serviceCode) {

        String sql = "DELETE FROM MedicalService WHERE serviceCode = ?";
                     

        int rows = jdbcTemplate.update(sql, serviceCode);

        return rows > 0;
    }

    // Check duplicate service code
    public boolean exists(String serviceCode) {

        String sql = "SELECT COUNT(*) FROM MedicalService WHERE serviceCode = ?"; 
                     

        Integer count = jdbcTemplate.queryForObject(
                sql,
                Integer.class,
                serviceCode
        );

        return count != null && count > 0;
    }

    // Get available services for booking
    public List<MedicalService> getAvailableServices() {

        String sql = "SELECT * FROM MedicalService WHERE status = 'Available' ORDER BY serviceName";
                     

        return jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(MedicalService.class)
        );
    }

}
