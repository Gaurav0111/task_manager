package service;

import java.sql.*;

import model.*;
import util.DatabaseConnection;

public class OrganizationService {
    public void addOrganization(Organization organization) {
        String query = "INSERT INTO organizations (org_name, domain, head_office_address, size, admin_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, organization.getOrgName());
            pstmt.setString(2, organization.getDomain());
            pstmt.setString(3, organization.getHeadOfficeAddress());
            pstmt.setInt(4, organization.getSize());
            pstmt.setInt(5, organization.getAdminId());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                int orgId = rs.getInt(1);
                organization.setOrgId(orgId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
