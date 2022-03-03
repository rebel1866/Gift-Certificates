package by.epamtc.stanislavmelnikov.dao.mappers;

import by.epamtc.stanislavmelnikov.entity.Certificate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CertificateMapper implements RowMapper<Certificate> {
    public Certificate mapRow(ResultSet rs, int rowNum) throws SQLException {
        Certificate certificate = new Certificate();
        certificate.setGiftCertificateId(rs.getInt("gift_certificate_id"));
        certificate.setCertificateName(rs.getString("certificate_name"));
        certificate.setDescription(rs.getString("description"));
        certificate.setPrice(rs.getInt("price"));
        certificate.setDuration(rs.getInt("duration"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        certificate.setCreationDate(LocalDateTime.parse(rs.getString("creation_date"), formatter));
        certificate.setLastUpdateTime(LocalDateTime.parse(rs.getString("last_update_time"), formatter));
        return certificate;
    }
}