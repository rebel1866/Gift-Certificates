package by.epamtc.stanislavmelnikov.logic.interfaces;

import by.epamtc.stanislavmelnikov.entity.Certificate;

import java.util.List;
import java.util.Map;

public interface CertificateLogic {
     List<Certificate> findCertificates(Map<String,String> params);
}
