package com.eii.assessment.service;

import com.eii.assessment.dto.DataCollectionDto;
import com.eii.assessment.dto.DataCollectionCreateDto;

import java.util.List;

public interface DataCollectionService {
    int save(DataCollectionCreateDto dataCollectionCreateDto);

    List<DataCollectionDto> findAll();

    DataCollectionDto findById(Integer id);

    void update(int id, DataCollectionCreateDto dataCollectionDto);
}
