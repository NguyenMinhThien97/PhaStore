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

import com.store.pharmacy.utils.PharmacyConstant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
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
		if (sortBy == null         || sortBy.isEmpty())
			sortBy = "createdAt";


		//Get
		List<BizPartner> bizPartnerList = bizPartnerRepository.findAllByBizPartnerNameAndBizTypeCodeAndEnabled(bizPartnerName, bizTypeCode,enabled, Sort.by(sortBy));

		if (bizPartnerList.isEmpty()){
			throw new DataNotFoundException("MSG0023");
		}

		for ( BizPartner bizPartnerInList : bizPartnerList){
			bizPartnerOutputList.add(convertBizPartnerToBizPartnerOutput(bizPartnerInList, PharmacyConstant.FETCH));
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
		ModelMapper modelMapper = new ModelMapper();
		BizPartnerInput bizPartnerInput = modelMapper.map(bizPartner, BizPartnerInput.class);
		return bizPartnerInput;
	}

	/**
	 *
	 * @param bizPartnerInput
	 * @param action
	 * @return
	 */
	public BizPartner convertBizPartnerInputToBizPartner(BizPartnerInput bizPartnerInput,  String action){
		ModelMapper modelMapper = new ModelMapper();
		BizPartner bizPartner = modelMapper.map(bizPartnerInput, BizPartner.class);
		LocalDate today = LocalDate.now();
		if (action.equals(PharmacyConstant.INSERT)){
			bizPartner.setCreatedAt(today);
			bizPartner.setUpdatedAt(today);
		}
		return bizPartner;

	}

	public BizPartnerOutput convertBizPartnerToBizPartnerOutput(BizPartner bizPartner,  String action){
		ModelMapper modelMapper = new ModelMapper();
		BizPartnerOutput bizPartnerOutput = modelMapper.map(bizPartner, BizPartnerOutput.class);

		if (action.equals(PharmacyConstant.INSERT)){
			bizPartnerOutput.setUpdateBy(bizPartner.getCreatedBy());
			bizPartnerOutput.setUpdateAt(bizPartner.getUpdatedAt());
		}
		else if (action.equals(PharmacyConstant.UPDATE) || action.equals(PharmacyConstant.FETCH) ){
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
	public boolean checkStatus (String input ){
		if (!input.equals(getStatus(input))  )
			throw new DataNotFoundException("MSG0025" ,new Object[] {labelService.findByExactLabelCodes("C007","en")});
		return true;
	}
}
