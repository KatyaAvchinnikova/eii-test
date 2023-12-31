package com.eii.assessment.controller;

import com.eii.assessment.dto.DataCollectionDto;
import com.eii.assessment.dto.DataCollectionCreateDto;
import com.eii.assessment.service.DataCollectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/data-collections")
@RequiredArgsConstructor
@Slf4j
public class DataCollectionController {
    private final DataCollectionService dataCollectionService;

    @PostMapping()
    public ResponseEntity<String> saveDataCollection(@RequestBody DataCollectionCreateDto dataCollectionCreateDto) {
        try {
            dataCollectionService.save(dataCollectionCreateDto);
            log.info("Data collection was created successfully");
            return new ResponseEntity<>("Data collection was created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping()
    public ResponseEntity<List<DataCollectionDto>> getDataCollections() {
        List<DataCollectionDto> dataCollections = dataCollectionService.findAll();

        if (dataCollections != null) {
            return new ResponseEntity<>(dataCollections, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataCollectionDto> getDataCollectionById(@PathVariable Integer id) {
        DataCollectionDto dataCollections = dataCollectionService.findById(id);
        return new ResponseEntity<>(dataCollections, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTutorial(@PathVariable("id") int id, @RequestBody DataCollectionCreateDto dataCollectionDto) {
        dataCollectionService.update(id, dataCollectionDto);
        return new ResponseEntity<>("Data collection was updated successfully.", HttpStatus.OK);
    }
//
//    @PostMapping("/tutorials")
//    public ResponseEntity<String> createTutorial(@RequestBody TutorialDtoRequest tutorialRequest) {
//        try {
//            tutorialRepository.save(tutorialRequest);
//            return new ResponseEntity<>("Tutorial was created successfully.", HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
}
