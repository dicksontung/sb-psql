package com.moneylion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class PersonController {

    private PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/persons")
    public ResponseEntity<Page<Person>> list(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "50") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(personRepository.findAll(pageable));
    }

    @GetMapping("/personsByName")
    public ResponseEntity<Collection<Person>> listByName(@RequestParam String name) {
        return ResponseEntity.ok(personRepository.findByNameNative(name));
    }

    @PutMapping("/persons")
    public ResponseEntity<Person> put(@RequestBody Person person) {
        person = personRepository.save(person);
        return ResponseEntity.ok(person);
    }
}
