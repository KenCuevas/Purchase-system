package com.kencuevas.shoppingsystem.services.impl;

import com.kencuevas.shoppingsystem.dto.UnitMeasureDTO;
import com.kencuevas.shoppingsystem.exceptions.ResourceNotFoundException;
import com.kencuevas.shoppingsystem.models.UnitMeasure;
import com.kencuevas.shoppingsystem.repositories.UnitMeasureRepository;
import com.kencuevas.shoppingsystem.services.UnitMeasureService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UnitMeasureServiceImpl implements UnitMeasureService {
    private UnitMeasureRepository measureRepository;
    private ModelMapper mapper;

    public UnitMeasureServiceImpl(UnitMeasureRepository measureRepository, ModelMapper mapper) {
        this.measureRepository = measureRepository;
        this.mapper = mapper;
    }

    @Override
    public UnitMeasureDTO createMeasure(UnitMeasureDTO measureDTOu) {
        // Convert DTO to entity
        UnitMeasure measure = mapToEntity(measureDTOu);
        UnitMeasure newMeasure = measureRepository.save(measure);

        // Convert Entity to DTO
        UnitMeasureDTO measureResponse = mapToDTO(newMeasure);
        return measureResponse;
    }

    @Override
    public List<UnitMeasureDTO> getAllMeasure() {
        List<UnitMeasure> measureList = measureRepository.findAll();
        return measureList.stream().map(measure -> mapToDTO(measure)).collect(Collectors.toList());

    }

    @Override
    public UnitMeasureDTO getMeasureById(long id) {
        UnitMeasure measure = measureRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Measure", "id", id));
        return mapToDTO(measure);
    }

    @Override
    public UnitMeasureDTO updateMeasure(UnitMeasureDTO measureDTO, long id) {
        // Get unit measure by id from the database
        UnitMeasure measure = measureRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Unit measure", "id", id));

        measure.setDescription(measureDTO.getDescription());
        measure.setStatus(measureDTO.isStatus());

        UnitMeasure updateMeasure = measureRepository.save(measure);

        return mapToDTO(updateMeasure);
    }
    @Override
    public void deleteMeasureById(long id) {
        // Get unit measure by id from the database
        UnitMeasure measure = measureRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Unit measure", "id", id));
        measureRepository.delete(measure);
    }

    //Convert entity into DTO
    private UnitMeasureDTO mapToDTO(UnitMeasure measure){
        UnitMeasureDTO measureDTO = mapper.map(measure, UnitMeasureDTO.class);
//        UnitMeasureDTO measureDTO = new UnitMeasureDTO();
//        measureDTO.setId(measure.getId());
//        measureDTO.setDescription(measure.getDescription());
//        measureDTO.setStatus(measure.isStatus());

        return measureDTO;
    }
    //Convert DTO to entity
    private UnitMeasure mapToEntity(UnitMeasureDTO measureDTO){
        UnitMeasure measure = mapper.map(measureDTO, UnitMeasure.class);

//        UnitMeasure measure = new UnitMeasure();
//        measure.setDescription(measureDTO.getDescription());
//        measure.setStatus(measureDTO.isStatus());

        return measure;
    }
}
