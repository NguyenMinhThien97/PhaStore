package com.store.pharmacy.bizpartner.controller;

import com.store.pharmacy.bizpartner.model.BizPartnerOutput;
import com.store.pharmacy.bizpartner.service.BizPartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bizpartners")
public class BizPartnerController {

	@Autowired
	BizPartnerService bizPartnerService;

	@GetMapping(value = {"/"})
	public ResponseEntity<List<BizPartnerOutput>> findBizPartner(
			@RequestParam(value = "bizPartnerName", required = false) String bizPartnerName,
			@RequestParam(value = "bizTypeCode", required = false) String bizTypeCode,
			@RequestParam(value = "enabled", required = false) boolean enabled,
			@RequestParam(value = "sortBy", required = false) String sortBy) {
		List<BizPartnerOutput> outParam = bizPartnerService.findBizPartnerByName(bizPartnerName, bizTypeCode, enabled, sortBy);
		return new ResponseEntity<List<BizPartnerOutput>>(outParam, HttpStatus.OK);
	}
}