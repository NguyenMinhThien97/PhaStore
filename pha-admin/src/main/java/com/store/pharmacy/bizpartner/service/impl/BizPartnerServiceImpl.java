package com.store.pharmacy.bizpartner.service.impl;
import com.store.pharmacy.bizpartner.model.BizPartner;
import com.store.pharmacy.bizpartner.model.BizPartnerInput;
import com.store.pharmacy.bizpartner.model.BizPartnerOutput;
import com.store.pharmacy.bizpartner.repository.BizPartnerRepository;
import com.store.pharmacy.bizpartner.service.BizPartnerService;
import com.store.pharmacy.common.model.Common;
import com.store.pharmacy.common.repository.CommonRepository;
import com.store.pharmacy.common.service.LabelService;
import com.store.pharmacy.exception.DataNotFoundException;
import com.store.pharmacy.utils.BizPartnerConstant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

@Service
@Transactional
public class BizPartnerServiceImpl implements BizPartnerService {

	@Autowired
	BizPartnerRepository bizPartnerRepository;

	@Autowired
	private CommonRepository commonRepository;

	@Autowired
	private LabelService labelService;
	private final String INSERT = "INSERT";
	private final String UPDATE = "UPDATE";
	private final String SEARCH = "SEARCH";


	/**
	 *
	 * @param bizPartnerName
	 * @param bizTypeCode
	 * @param enabled
	 * @param sortBy
	 * @return
	 */
	@Override
	public List<BizPartnerOutput> findBizPartnerByName (String bizPartnerName, String bizTypeCode, boolean enabled , String sortBy){
		List<BizPartnerOutput> bizPartnerOutputList =  new ArrayList<>();

		//Check condition
		//++Check null
		if (bizPartnerName == null || bizPartnerName.isEmpty())
			throw new DataNotFoundException("MSG0020" ,new Object[] {labelService.findByExactLabelCodes("C005","en")});
		if (bizTypeCode == null    ||  bizTypeCode.isEmpty() )
			throw new DataNotFoundException("MSG0020" ,new Object[] {labelService.findByExactLabelCodes("C006","en")});
		if (sortBy == null         || sortBy.isEmpty())
			sortBy = "createdAt";

		//++Check exist
		checkBizTypeCode(bizTypeCode);

		//Get
		List<BizPartner> bizPartnerList = bizPartnerRepository.findAllByBizPartnerNameAndBizTypeCodeAndEnabled(bizPartnerName, bizTypeCode,enabled, Sort.by(sortBy));

		if (bizPartnerList.isEmpty()){
			throw new DataNotFoundException("MSG0023");
		}

		for ( BizPartner bizPartnerInList : bizPartnerList){
			bizPartnerOutputList.add(convertBizPartnerToBizPartnerOutput(bizPartnerInList, "SEARCH"));
		}
		return bizPartnerOutputList;
	}

	/**
	 *
	 * @param bizPartner
	 * @param action
	 * @return
	 */
	public BizPartnerInput convertBizPartnerToBizPartnerInput ( BizPartner bizPartner , String action ){
		BizPartnerInput bizPartnerInput = new BizPartnerInput();

		String bizTypeCode = getBizTypeCodeName(bizPartner.getBizTypeCode().trim());
		String status = getStatus(bizPartner.getStatus().trim());

		bizPartnerInput.setBizTypeCode(bizTypeCode);
		bizPartnerInput.setStatus(status);
		return bizPartnerInput;
	}

	/**
	 *
	 * @param bizPartnerInput
	 * @param action
	 * @return
	 */
	public BizPartner convertBizPartnerInputToBizPartner(BizPartnerInput bizPartnerInput,  String action){
		BizPartner bizPartner = new BizPartner();

		LocalDate today = LocalDate.now();
		if (action.equals(INSERT)){
			bizPartner.setBizPartnerName(bizPartnerInput.getBizPartnerName());
			bizPartner.setTaxCode((bizPartnerInput.getTaxCode()));
			bizPartner.setBizTypeCode(bizPartnerInput.getBizTypeCode());
			bizPartner.setCountry(bizPartnerInput.getCountry());
			bizPartner.setAddress(bizPartnerInput.getAddress());
			bizPartner.setEmail(bizPartnerInput.getEmail());
			bizPartner.setPhoneNumber(bizPartnerInput.getPhoneNumber());
			bizPartner.setDescription(bizPartnerInput.getDescription());
			bizPartner.setCreatedBy(bizPartnerInput.getUserId());
			bizPartner.setCreatedAt(today);
			bizPartner.setStatus(bizPartnerInput.getStatus());
			bizPartner.setEnabled(bizPartnerInput.getEnabled());
		}
		return bizPartner;

	}

	public BizPartnerOutput convertBizPartnerToBizPartnerOutput(BizPartner bizPartner,  String action){
		BizPartnerOutput bizPartnerOutput = new BizPartnerOutput();

		bizPartnerOutput.setBizPartnerId(bizPartner.getBizPartnerId());
		bizPartnerOutput.setBizPartnerName(bizPartner.getBizPartnerName());
		bizPartnerOutput.setTaxCode(bizPartner.getTaxCode());
		bizPartnerOutput.setBizTypeCode(bizPartner.getBizTypeCode());
		bizPartnerOutput.setCountry(bizPartner.getCountry());
		bizPartnerOutput.setAddress(bizPartner.getAddress());
		bizPartnerOutput.setEmail(bizPartner.getEmail());
		bizPartnerOutput.setPhoneNumber(bizPartner.getPhoneNumber());
		bizPartnerOutput.setDescription(bizPartner.getDescription());
		bizPartnerOutput.setEnabled(bizPartner.getEnabled());

		if (action.equals(INSERT)){
			bizPartnerOutput.setUpdateBy(bizPartner.getCreatedBy());
			bizPartnerOutput.setUpdateAt(bizPartner.getUpdatedAt());
		}
		else if (action.equals(UPDATE) || action.equals(SEARCH) ){
			bizPartnerOutput.setUpdateBy(bizPartner.getUpdatedBy());
			bizPartnerOutput.setUpdateAt(bizPartner.getUpdatedAt());
		}
		return  bizPartnerOutput;
	}

	/**
	 *
	 * @param input
	 * @return
	 */
	public String getBizTypeCodeName (String input){
		Common common =  commonRepository.findByCommonCodeAndName(BizPartnerConstant.BIZ_TYPE_CODE, input.trim());
		if (common == null)
			return "";
		return common.getName();
	}

	/**
	 *
	 * @param input
	 * @return
	 */
	public String getStatus (String input){
		Common common =  commonRepository.findByCommonCodeAndName(BizPartnerConstant.STATUS, input.trim());
		if (common == null)
			return "";
		return common.getName();
	}
	/**
	 *
	 * @param input
	 * @return
	 */
	public boolean checkBizTypeCode (String input ){
		if (!input.equals(getBizTypeCodeName(input))  )
			throw new DataNotFoundException("MSG0025" ,new Object[] {labelService.findByExactLabelCodes("C006","en")});
		return true;
	}

	/**
	 *
	 * @param input
	 * @return
	 */
	public boolean checkStatus (String input ){
		if (!input.equals(getStatus(input))  )
			throw new DataNotFoundException("MSG0025" ,new Object[] {labelService.findByExactLabelCodes("C007","en")});
		return true;
	}
}
