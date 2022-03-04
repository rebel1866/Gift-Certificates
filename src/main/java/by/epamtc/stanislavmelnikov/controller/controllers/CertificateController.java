package by.epamtc.stanislavmelnikov.controller.controllers;

import by.epamtc.stanislavmelnikov.entity.Certificate;
import by.epamtc.stanislavmelnikov.entity.Tag;
import by.epamtc.stanislavmelnikov.logic.interfaces.CertificateLogic;
import by.epamtc.stanislavmelnikov.logic.interfaces.TagLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CertificateController {

    private CertificateLogic certificateLogic;
    private TagLogic tagLogic;

    @Autowired
    public void setCertificateLogic(CertificateLogic certificateLogic) {
        this.certificateLogic = certificateLogic;
    }

    @Autowired
    public void setTagLogic(TagLogic tagLogic) {
        this.tagLogic = tagLogic;
    }

    @RequestMapping(value = "/certificates", method = {RequestMethod.POST})
    public String showCertificates(@RequestParam Map<String, String> params, Model model) {
        List<Certificate> certificates = certificateLogic.findCertificates(params);
        model.addAttribute("params", params);
        model.addAttribute("certificates", certificates);
        return "certificates";
    }

    @RequestMapping(value = "/certificates")
    public String showCertificatesGet(Model model) {
        List<Certificate> certificates = certificateLogic.findCertificates(new HashMap<>());
        model.addAttribute("certificates", certificates);
        return "certificates";
    }

    @RequestMapping(value = "/add-certificate-form")
    public String showAddCertificateForm(Model model) {
        List<Tag> tags = tagLogic.findTags(new HashMap<>());
        model.addAttribute("command", new Certificate());
        model.addAttribute("tags", tags);
        return "add-certificate";
    }

    @RequestMapping(value = "/addCertificate")
    public String addCertificate(@ModelAttribute("certificate") Certificate certificate) {
        certificateLogic.addCertificate(certificate);
        return "redirect:/certificates";
    }

    @RequestMapping(value = "/certificate-info", method = RequestMethod.POST)
    public String showCertificateInfo(@RequestParam("giftCertificateId") int giftCertificateId, Model model) {
        Map<String, String> params = new HashMap<>();
        params.put("giftCertificateId", String.valueOf(giftCertificateId));
        Certificate certificate = certificateLogic.findCertificates(params).get(0);
        model.addAttribute("certificate", certificate);
        return "certificate-full-info";
    }

    @RequestMapping(value = "/delete-certificate", method = RequestMethod.POST)
    public String deleteCertificate(@RequestParam("id") int id, Model model) {
        certificateLogic.deleteCertificate(id);
        return "redirect:/certificates";
    }
}
