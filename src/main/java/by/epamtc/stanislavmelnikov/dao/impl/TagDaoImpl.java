package by.epamtc.stanislavmelnikov.dao.impl;

import by.epamtc.stanislavmelnikov.dao.interfaces.TagDao;
import by.epamtc.stanislavmelnikov.dao.mappers.CertificateMapper;
import by.epamtc.stanislavmelnikov.dao.mappers.TagMapper;
import by.epamtc.stanislavmelnikov.dao.sqlgenerator.SqlGenerator;
import by.epamtc.stanislavmelnikov.entity.Tag;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class TagDaoImpl implements TagDao {
    private JdbcTemplate jdbcTemplate;
    private static final String tagSQL = "select tag_name, tag_id from tags";

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Tag> findTags(Map<String, String> params) {
        String targetSql = SqlGenerator.generateSQL(tagSQL, params);
        List<Tag> tags = jdbcTemplate.query(targetSql, new TagMapper());
        return tags;
    }
}
