package by.epamtc.stanislavmelnikov.dao.interfaces;

import by.epamtc.stanislavmelnikov.entity.Certificate;

import java.util.List;
import java.util.Map;

public interface CertificateDao {
    List<Certificate> findCertificates(Map<String,String> params);
}
