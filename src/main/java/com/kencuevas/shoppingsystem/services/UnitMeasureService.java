package com.kencuevas.shoppingsystem.services;

import com.kencuevas.shoppingsystem.dto.UnitMeasureDTO;

import java.util.List;

public interface UnitMeasureService {
    UnitMeasureDTO createMeasure(UnitMeasureDTO measureDTOu);

    List<UnitMeasureDTO>getAllMeasure();

    UnitMeasureDTO getMeasureById(long id);

    UnitMeasureDTO updateMeasure(UnitMeasureDTO measureDTO, long id);

    void deleteMeasureById(long id);
}
