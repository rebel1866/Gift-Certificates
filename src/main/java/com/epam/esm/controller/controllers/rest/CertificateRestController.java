package com.epam.esm.controller.controllers.rest;

import com.epam.esm.controller.dto.DeleteByIdRequest;
import com.epam.esm.controller.dto.UpdateCertificateRequest;
import com.epam.esm.controller.exceptions.RestControllerException;
import com.epam.esm.controller.dto.SearchCertificateRequest;
import com.epam.esm.entity.Certificate;
import com.epam.esm.logic.exceptions.LogicException;
import com.epam.esm.logic.interfaces.CertificateLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RestController
public class CertificateRestController {
    private CertificateLogic certificateLogic;

    @Autowired
    public void setCertificateLogic(CertificateLogic certificateLogic) {
        this.certificateLogic = certificateLogic;
    }


    @GetMapping(value = "/certificates", consumes = {"application/json"}, produces = {"application/json"})
    public List<Certificate> showCertificates(@ModelAttribute @Valid SearchCertificateRequest searchRequest,
                                              BindingResult bindingResult, Errors errors) throws RestControllerException {
        if (bindingResult.hasErrors()) throw new RestControllerException("Wrong input data", "errorCode=3", errors);
        try {
            return certificateLogic.findCertificates(searchRequest);
        } catch (LogicException e) {
            throw new RestControllerException(e.getMessage(), e.getErrorCode(), e);
        }
    }

    @PostMapping(value = "/certificates", consumes = {"application/json"}, produces = {"application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    public void addCertificate(@RequestBody @Valid Certificate certificate,
                               BindingResult bindingResult, Errors errors) throws RestControllerException {
        if (bindingResult.hasErrors()) {
            throw new RestControllerException("Wrong input data", "errorCode=3", errors);
        }
        try {
            certificateLogic.addCertificate(certificate);
        } catch (LogicException e) {
            throw new RestControllerException(e.getMessage(), e.getErrorCode(), e);
        }
    }

    @DeleteMapping(value = "/certificates", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<HttpStatus> deleteCertificate(@ModelAttribute @Valid DeleteByIdRequest request,
                                                        BindingResult bindingResult, Errors errors) throws RestControllerException {
        if (bindingResult.hasErrors()) throw new RestControllerException("Wrong input data - no correct id entered",
                "errorCode=3", errors);
        int id = request.getId();
        try {
            certificateLogic.deleteCertificate(id);
        } catch (LogicException e) {
            throw new RestControllerException(e.getMessage(), e.getErrorCode(), e);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/certificates", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<HttpStatus> updateCertificate(@RequestBody @Valid UpdateCertificateRequest request,
                                                        BindingResult result, Errors errors) throws RestControllerException {
        if (result.hasErrors()) throw new RestControllerException("Wrong input data", "errorCode=3", errors);
        try {
            certificateLogic.updateCertificate(request);
        } catch (LogicException e) {
            throw new RestControllerException(e.getMessage(), e.getErrorCode(), e);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
