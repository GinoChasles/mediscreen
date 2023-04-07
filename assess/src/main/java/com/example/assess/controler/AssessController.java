package com.example.assess.controler;

import com.example.assess.model.AssessPatient;
import com.example.assess.service.AssessService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The type Assess controller.
 */
@Controller
@RequestMapping(value = "/assess")
public class AssessController {

    private final AssessService assessService;

    /**
     * Instantiates a new Assess controller.
     *
     * @param service the service
     */
    public AssessController(AssessService service) {
        assessService = service;
    }

    /**
     * Gets assess.
     *
     * @param id the id
     * @return the assess
     */
    @GetMapping("/{id}")
    ResponseEntity<AssessPatient> getAssess(@PathVariable(value = "id") long id) throws JsonProcessingException {

        return ResponseEntity.ok(assessService.getAssess(id));
    }
}
