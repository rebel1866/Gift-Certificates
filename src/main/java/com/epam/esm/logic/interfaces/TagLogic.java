package com.epam.esm.logic.interfaces;

import com.epam.esm.controller.dto.AddTagRequest;
import com.epam.esm.controller.dto.DeleteByIdRequest;
import com.epam.esm.controller.dto.SearchTagRequest;
import com.epam.esm.entity.Tag;
import com.epam.esm.logic.exceptions.LogicException;

import java.util.List;
import java.util.Map;

public interface TagLogic {
    List<Tag> findTags(SearchTagRequest request) throws LogicException;

    void addTag(AddTagRequest request) throws LogicException;

    void deleteTag(DeleteByIdRequest request) throws LogicException;
}
