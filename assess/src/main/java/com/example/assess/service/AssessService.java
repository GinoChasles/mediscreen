package com.example.assess.service;

import com.example.assess.model.AssessPatient;
import com.example.assess.utils.Assess;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * The interface Assess service.
 */
public interface AssessService {

    /**
     * Gets assess.
     *
     * @param id the id
     * @return the assess
     * @throws JsonProcessingException the json processing exception
     */
    public AssessPatient getAssess(long id) throws JsonProcessingException;

}
