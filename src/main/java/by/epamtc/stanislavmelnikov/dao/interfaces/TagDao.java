package by.epamtc.stanislavmelnikov.dao.interfaces;

import by.epamtc.stanislavmelnikov.entity.Tag;

import java.util.List;
import java.util.Map;

public interface TagDao {
    List<Tag> findTags(Map<String,String> params);
}
