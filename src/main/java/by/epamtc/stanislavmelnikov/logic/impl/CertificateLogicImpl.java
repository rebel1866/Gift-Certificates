package by.epamtc.stanislavmelnikov.logic.impl;

import by.epamtc.stanislavmelnikov.dao.interfaces.CertificateDao;
import by.epamtc.stanislavmelnikov.entity.Certificate;
import by.epamtc.stanislavmelnikov.logic.interfaces.CertificateLogic;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class CertificateLogicImpl implements CertificateLogic {
    @Autowired
    private CertificateDao certificateDao;

    public void setCertificateDao(CertificateDao certificateDao) {
        this.certificateDao = certificateDao;
    }

    @Override
    public List<Certificate> findCertificates(Map<String, String> params) {
        List<Certificate> certificates = certificateDao.findCertificates(params);
        return certificates;
    }

    @Override
    public void addCertificate(Certificate certificate) {
        LocalDateTime now = LocalDateTime.now();
        certificate.setCreationDate(now);
        certificate.setLastUpdateTime(now);
        certificateDao.addCertificate(certificate);
    }
}
