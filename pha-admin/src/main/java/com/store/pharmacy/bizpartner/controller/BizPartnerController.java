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

	@GetMapping(value = {"/"
			, "/{bizPartnerName}/{enabled}/{bizTypeCode}"})
	public ResponseEntity<List<BizPartnerOutput>> findBizPartner(
			@PathVariable(value = "bizPartnerName", required = false) String bizPartnerName,
			@PathVariable(value = "bizTypeCode", required = false) String bizTypeCode,
			@PathVariable(value = "enabled") boolean enabled,
			@RequestParam(value = "sortBy", required = false) String sortBy) {
		List<BizPartnerOutput> outParam = bizPartnerService.findBizPartnerByName(bizPartnerName, bizTypeCode, enabled, sortBy);
		return new ResponseEntity<List<BizPartnerOutput>>(outParam, HttpStatus.OK);
	}
}