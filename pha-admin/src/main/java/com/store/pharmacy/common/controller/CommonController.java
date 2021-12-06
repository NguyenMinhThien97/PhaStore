package com.store.pharmacy.common.controller;

import com.store.pharmacy.common.model.*;
import com.store.pharmacy.common.service.CommonService;
import com.store.pharmacy.common.service.LabelService;
import com.store.pharmacy.common.service.MessageService;
import com.store.pharmacy.common.service.SendMailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/common")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class CommonController {

    private final CommonService commonService;
    private final LabelService labelService;
    private final MessageService messageService;
    private final SendMailService sendMailService;

    @GetMapping("/{commonCode}/{Lang}")
    public ResponseEntity<List<CommonOutput>> findCommon(@PathVariable("commonCode") String commonCode, @PathVariable("lang") String lang){
        List<CommonOutput> list = commonService.findByCommonCode(commonCode, lang);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping(value="/labels/{lang}")
    public ResponseEntity<List<HashMap>> findLabel(@PathVariable("lang") String lang, @RequestBody Map<String, List<LabelInput>> labelInputs){
        List<HashMap> outParam = labelService.findByLabelCodes(labelInputs.get("labels"), lang);
        return new ResponseEntity<>(outParam, HttpStatus.OK);
    }

    @GetMapping("/messages/{messageCode}/{lang}")
    public ResponseEntity<MessageOutput> findMessage(@PathVariable("messageCode") String messageCode, @PathVariable("lang") String lang){
        MessageOutput outParam = messageService.getByMessageCode(messageCode, lang);
        return new ResponseEntity<>(outParam, HttpStatus.OK);
    }

    // just test for function sendMail
    @GetMapping("/sendEmail")
    public ResponseEntity<Boolean> sendEmail(
        @RequestParam("subject") String subject,
        @RequestParam("templateName") String templateName,
        @RequestParam("mailReceiver") String mailReceiver
    ){
        boolean result = sendMailService.sendMail(subject, templateName, mailReceiver);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
