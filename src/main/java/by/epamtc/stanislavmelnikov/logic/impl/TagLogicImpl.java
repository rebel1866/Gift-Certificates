package by.epamtc.stanislavmelnikov.logic.impl;

import by.epamtc.stanislavmelnikov.dao.interfaces.TagDao;
import by.epamtc.stanislavmelnikov.entity.Tag;
import by.epamtc.stanislavmelnikov.logic.interfaces.TagLogic;

import java.util.List;
import java.util.Map;

public class TagLogicImpl implements TagLogic {
    private TagDao tagDao;

    public void setTagDao(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    @Override
    public List<Tag> findTags(Map<String, String> params) {
        return tagDao.findTags(params);
    }
}
