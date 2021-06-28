package com.store.pharmacy.common.controller;

import com.store.pharmacy.common.model.*;
import com.store.pharmacy.common.service.CommonService;
import com.store.pharmacy.common.service.LabelService;
import com.store.pharmacy.common.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("common")
@CrossOrigin(origins = "*")
public class CommonController {
    @Autowired
    private CommonService commonService;

    @Autowired
    private LabelService labelService;

    @Autowired
    private MessageService messageService;

    @GetMapping("/findCommon/{commonCode}")
    public ResponseEntity<List<CommonOutput>> findCommon(@PathVariable("commonCode") String commonCode){
        List<CommonOutput> list = commonService.findByCommonCode(commonCode);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping(value="/getLabel")
    public ResponseEntity<List<HashMap>> findLabel(@RequestBody LabelInput[] labelInputs){
        List<HashMap> outParam = labelService.findByLabelCodes(Arrays.asList(labelInputs));
        return new ResponseEntity<>(outParam, HttpStatus.OK);
    }

    @GetMapping("/getMessage/{messageCode}")
    public ResponseEntity<MessageOutput> findMessage(@PathVariable("messageCode") String messageCode){
        MessageOutput outParam = messageService.getByMessageCode(messageCode);
        return new ResponseEntity<>(outParam, HttpStatus.OK);
    }
}
