package com.store.pharmacy.common.controller;

import com.store.pharmacy.common.model.*;
import com.store.pharmacy.common.service.CommonService;
import com.store.pharmacy.common.service.LabelService;
import com.store.pharmacy.common.service.MessageService;
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
public class CommonController {
    @Autowired
    private CommonService commonService;

    @Autowired
    private LabelService labelService;

    @Autowired
    private MessageService messageService;

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
}
