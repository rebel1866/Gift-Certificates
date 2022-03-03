package by.epamtc.stanislavmelnikov.dao.impl;

import by.epamtc.stanislavmelnikov.dao.interfaces.CertificateDao;
import by.epamtc.stanislavmelnikov.dao.mappers.CertificateMapper;
import by.epamtc.stanislavmelnikov.dao.sqlgenerator.SqlGenerator;
import by.epamtc.stanislavmelnikov.dao.mappers.TagMapper;
import by.epamtc.stanislavmelnikov.entity.Certificate;
import by.epamtc.stanislavmelnikov.entity.Tag;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CertificateDaoImpl implements CertificateDao {
    private JdbcTemplate jdbcTemplate;
    private static final String certificatesSQL = "select gift_certificate_id, certificate_name, price, duration," +
            " creation_date, last_update_time, description from gifts.gift_certificates inner join gifts.cert_tags" +
            " using (gift_certificate_id) inner join gifts.tags using (tag_id)";
    private static final String findTagsByIdSQL = "select tags.tag_id, tag_name from gifts.gift_certificates inner join gifts.cert_tags" +
            " using (gift_certificate_id) inner join gifts.tags using (tag_id) where gift_certificates.gift_certificate_id =?";

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Certificate> findCertificates(Map<String, String> params) {
        //transaction
        String targetSql = SqlGenerator.generateSQL(certificatesSQL, params);
        List<Certificate> certificates = jdbcTemplate.query(targetSql, new CertificateMapper());
        certificates = certificates.stream().distinct().collect(Collectors.toList());
        for (Certificate certificate : certificates) {
            int certificateId = certificate.getGiftCertificateId();
            List<Tag> tags = jdbcTemplate.query(findTagsByIdSQL, new TagMapper(),certificateId);
            certificate.setTags(tags);
        }
        return certificates;
    }
}
