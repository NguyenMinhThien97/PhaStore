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

    @GetMapping("/commons/{commonCode}")
    public ResponseEntity<List<CommonOutput>> findCommon(@PathVariable("commonCode") String commonCode){
        List<CommonOutput> list = commonService.findByCommonCode(commonCode);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping(value="/labels")
    public ResponseEntity<List<HashMap>> findLabel(@RequestBody Map<String, List<LabelInput>> labelInputs){
        List<HashMap> outParam = labelService.findByLabelCodes(labelInputs.get("labels"));
        return new ResponseEntity<>(outParam, HttpStatus.OK);
    }

    @GetMapping("/messages/{messageCode}")
    public ResponseEntity<MessageOutput> findMessage(@PathVariable("messageCode") String messageCode){
        MessageOutput outParam = messageService.getByMessageCode(messageCode);
        return new ResponseEntity<>(outParam, HttpStatus.OK);
    }
}
