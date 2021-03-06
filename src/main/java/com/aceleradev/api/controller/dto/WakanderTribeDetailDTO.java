package com.aceleradev.api.controller.dto;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aceleradev.api.domain.model.WakanderTribe;
import com.aceleradev.api.exception.NotFoundException;

public class WakanderTribeDetailDTO {
	private static final Logger log = LoggerFactory.getLogger(WakanderTribeDetailDTO.class);
	private String nameTribe;
	private List<WakanderTribeSkillDto> wakanderTribeSkills;

	public WakanderTribeDetailDTO(WakanderTribe wakanderTribe) {
		this.nameTribe = wakanderTribe.getNameTribe();
		this.wakanderTribeSkills = WakanderTribeSkillDto.convert(wakanderTribe.getWakanderTribeSkills());

	}

	public String getNameTribe() {
		return nameTribe;
	}

	public List<WakanderTribeSkillDto> getWakanderTribeSkills() {
		return wakanderTribeSkills;
	}
	
	public static WakanderTribeDetailDTO buildResponse(String wakanderCode, String tribeCode,
			Optional<WakanderTribe> optWakanderTribeDetail) throws NotFoundException {
		WakanderTribeDetailDTO wakanderTribeDetailDTO = optWakanderTribeDetail.map(WakanderTribeDetailDTO::new)
																			.orElseThrow(() -> throwNotfoundException(wakanderCode, tribeCode));
		log.info("returning WakanderTribeDetailDTO {}", wakanderCode);
		return wakanderTribeDetailDTO;
	}

	private static NotFoundException throwNotfoundException(String wakanderCode, String tribeCode) {
		log.info("It is not possible to find a WakanderTribe by wakandaCode {} and tribecode {}", wakanderCode,
				tribeCode);
		return new NotFoundException("It is not possible to find a WakanderTribe by wakandaCode " + wakanderCode
				+ " and tribecode " + tribeCode);
	}
}
