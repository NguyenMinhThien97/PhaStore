package com.store.pharmacy.common.controller;

import com.store.pharmacy.common.model.CommonOutput;
import com.store.pharmacy.common.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("common")
@CrossOrigin(origins = "*")
public class CommonController {
    @Autowired
    private CommonService commonService;

    @GetMapping("/findCommon/{commonCode}")
    public ResponseEntity<List<CommonOutput>> findCommon(@PathVariable("commonCode") String commonCode){
        List<CommonOutput> list = commonService.findByCommonCode(commonCode);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
