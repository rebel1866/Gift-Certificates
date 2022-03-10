package com.epam.esm.logic.interfaces;

import com.epam.esm.controller.dto.SearchCertificateRequest;
import com.epam.esm.controller.dto.UpdateCertificateRequest;
import com.epam.esm.entity.Certificate;
import com.epam.esm.logic.exceptions.LogicException;

import java.util.List;
import java.util.Map;

public interface CertificateLogic {
    List<Certificate> findCertificates(SearchCertificateRequest request) throws LogicException;

    void addCertificate(Certificate certificate) throws LogicException;

    void deleteCertificate(int id) throws LogicException;

    void updateCertificate(UpdateCertificateRequest request) throws LogicException;
}
