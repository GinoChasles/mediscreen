package com.example.assess.proxy;

import com.example.assess.model.Note;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "microservice-notes", url = "localhost:8082")
public interface MicroServiceNote {
    @GetMapping(value = "/patHistory/{id}/all" , produces = "application/json")
    String getPatientNotes(@PathVariable("id") long id);

}
