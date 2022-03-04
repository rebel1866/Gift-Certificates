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
    private static final String addCertificateSQL = "insert into gifts.gift_certificates (certificate_name, description, price, " +
            "duration, creation_date, last_update_time) values (?,?,?,?,?,?)";
    private static final String addCertificateTagsSQL = "insert into gifts.cert_tags (gift_certificate_id, tag_id) " +
            "values ((select gift_certificate_id from gifts.gift_certificates order by gift_certificate_id desc limit 1),?)";
    private static final String removeCertificateSql = "delete from gifts.gift_certificates where gift_certificate_id = ?";


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
            List<Tag> tags = jdbcTemplate.query(findTagsByIdSQL, new TagMapper(), certificateId);
            certificate.setTags(tags);
        }
        return certificates;
    }

    @Override
    public void addCertificate(Certificate certificate) {
        jdbcTemplate.update(addCertificateSQL, certificate.getCertificateName(), certificate.getDescription(), certificate.getPrice(),
                certificate.getDuration(), certificate.getCreationDate(), certificate.getLastUpdateTime());
        List<Tag> tags = certificate.getTags();
        for (Tag tag : tags) {
            jdbcTemplate.update(addCertificateTagsSQL, tag.getTagId());
        }
    }

    @Override
    public void deleteCertificate(int id) {
        jdbcTemplate.update(removeCertificateSql, id);
    }
}
