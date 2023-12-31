package com.eii.assessment.service;

import com.eii.assessment.dto.DataCollectionCreateDto;
import com.eii.assessment.dto.DataCollectionDto;
import com.eii.assessment.repository.DataCollectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DataCollectionImpl implements DataCollectionService {
    private final DataCollectionRepository dataCollectionRepository;

    @Override
    public int save(DataCollectionCreateDto dataCollectionCreateDto) {
        return dataCollectionRepository.save(dataCollectionCreateDto);
    }

    @Override
    public List<DataCollectionDto> findAll() {
        return dataCollectionRepository.findAll();
    }

    @Override
    public DataCollectionDto findById(Integer id) {
        return dataCollectionRepository.findById(id);
    }

    @Override
    public void update(int id, DataCollectionCreateDto dataCollectionDto) {
        DataCollectionDto dto = dataCollectionRepository.findById(id);
        dto.setFileIdAssets(dataCollectionDto.getFileIdAssets());
        dto.setFileIdInventory(dataCollectionDto.getFileIdInventory());
        dto.setFileIdOrders(dataCollectionDto.getFileIdAssets());
        dto.setNote(dataCollectionDto.getNote());
        dto.setTag(dataCollectionDto.getTag());
        dto.setStatus(dataCollectionDto.getStatus());
        dto.setUpdatedOn(Timestamp.valueOf(LocalDateTime.now()));

        dataCollectionRepository.update(dto);
    }
}
