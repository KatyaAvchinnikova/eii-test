package com.eii.assessment.repository;

import com.eii.assessment.dto.DataCollectionDto;
import com.eii.assessment.dto.DataCollectionCreateDto;

import java.util.List;

public interface DataCollectionRepository {
    int save(DataCollectionCreateDto dataCollectionDto);

    int update(DataCollectionDto dataCollectionDto);

    DataCollectionDto findById(Integer id);

    int deleteById(Integer id);

    List<DataCollectionDto> findAll();

    int deleteAll();
}
