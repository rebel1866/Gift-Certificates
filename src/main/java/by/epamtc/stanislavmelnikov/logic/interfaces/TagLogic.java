package by.epamtc.stanislavmelnikov.logic.interfaces;

import by.epamtc.stanislavmelnikov.entity.Tag;

import java.util.List;
import java.util.Map;

public interface TagLogic {
    List<Tag> findTags(Map<String,String> params);
}
