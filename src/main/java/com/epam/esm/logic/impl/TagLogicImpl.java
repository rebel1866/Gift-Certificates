package com.epam.esm.logic.impl;

import com.epam.esm.controller.dto.AddTagRequest;
import com.epam.esm.controller.dto.DeleteByIdRequest;
import com.epam.esm.controller.dto.SearchTagRequest;
import com.epam.esm.dao.exceptions.DaoException;
import com.epam.esm.dao.interfaces.TagDao;
import com.epam.esm.entity.Tag;
import com.epam.esm.logic.exceptions.LogicException;
import com.epam.esm.logic.interfaces.TagLogic;
import com.epam.esm.logic.logicutils.ObjectToMapConverter;
import com.google.common.base.CaseFormat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TagLogicImpl implements TagLogic {
    private TagDao tagDao;

    public void setTagDao(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    @Override
    public List<Tag> findTags(SearchTagRequest request) throws LogicException {
        Map<String, String> params = ObjectToMapConverter.convertToMap(request);
        var iterator = params.entrySet().iterator();
        Map<String, String> newParams = new HashMap<>();
        while (iterator.hasNext()) {
            var el = iterator.next();
            String key = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, el.getKey());
            String value = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, String.valueOf(el.getValue()));
            newParams.put(key, value);
        }
        try {
            return tagDao.findTags(newParams);
        } catch (DaoException e) {
            throw new LogicException(e.getMessage(), e.getErrorCode(),e);
        }
    }

    @Override
    public void addTag(AddTagRequest request) throws LogicException {
        Tag tag = new Tag();
        tag.setTagName(request.getTagName());
        try {
            tagDao.addTag(tag);
        } catch (DaoException e) {
            throw new LogicException(e.getMessage(), e.getErrorCode(),e);
        }
    }

    @Override
    public void deleteTag(DeleteByIdRequest request) throws LogicException {
        int id = request.getId();
        try {
            tagDao.deleteTag(id);
        } catch (DaoException e) {
            throw new LogicException(e.getMessage(), e.getErrorCode(), e);
        }
    }
}