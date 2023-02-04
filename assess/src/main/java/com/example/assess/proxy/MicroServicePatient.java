package com.example.assess.proxy;

import com.example.assess.model.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "microservice-patients", url = "localhost:8081")
public interface MicroServicePatient {
    @GetMapping(value = "/patient/{id}")
    Patient getPatient(@PathVariable("id") long id);
}
