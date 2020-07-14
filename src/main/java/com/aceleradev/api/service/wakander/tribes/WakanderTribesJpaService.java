package com.aceleradev.api.service.wakander.tribes;

import com.aceleradev.api.controller.dto.WakanderTribeDTO;
import com.aceleradev.api.domain.model.Tribe;
import com.aceleradev.api.domain.model.WakanderTribe;
import com.aceleradev.api.repository.WakanderTribesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WakanderTribesJpaService implements WakanderTribeService {

    private static final Logger log = LoggerFactory.getLogger(WakanderTribesJpaService.class);
    private WakanderTribesRepository wkTribesRepository;

    public WakanderTribesJpaService(WakanderTribesRepository wkTribesRepository) {
        this.wkTribesRepository = wkTribesRepository;
    }

    @Override
    public List<WakanderTribeDTO> listTribes(String wakanderCode) {
        log.info("Starting listTribes in WakanderTribesJpaService");
        log.info("searching for unlocked tribes");
        List<WakanderTribe> wakanderTribes=wkTribesRepository.listUnlockedTribes(wakanderCode);
        log.info("checking results");
        if(wakanderTribes==null) {
            log.info("no tribe was found");
            return new ArrayList<WakanderTribeDTO>();
        }else {
            log.info("converting unchecked tribes to WakanderTribeDTO");
            return wakanderTribes.stream().map(WakanderTribeDTO::new).collect(Collectors.toList());
        }
    }

}
