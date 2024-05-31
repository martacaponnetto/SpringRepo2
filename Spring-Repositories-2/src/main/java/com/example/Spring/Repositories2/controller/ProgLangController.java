package com.example.Spring.Repositories2.controller;

import com.example.Spring.Repositories2.entities.ProgrammingLanguage;
import com.example.Spring.Repositories2.repositories.ProgrammingLanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class ProgLangController {
    @Autowired
    private ProgrammingLanguageRepository programmingLanguageRepository;

    @PostMapping
    public ResponseEntity<ProgrammingLanguage> create(@RequestBody ProgrammingLanguage programmingLanguage) {
        ProgrammingLanguage savedProg = programmingLanguageRepository.save(programmingLanguage);
        return ResponseEntity.ok().body(savedProg);
    }

    @GetMapping
    public ResponseEntity<List<ProgrammingLanguage>> getAll() {
        return programmingLanguageRepository.count() == 0 ? ResponseEntity.noContent().build() : ResponseEntity.ok().body(programmingLanguageRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgrammingLanguage> getProgLangById(@PathVariable(name = "id") Long id) {

        return programmingLanguageRepository.findById(id).map(progLang -> ResponseEntity.ok().body(progLang))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProgrammingLanguage> updateProgLang(@PathVariable(name = "id") Long id, @RequestParam String invetor) {
        return programmingLanguageRepository.findById(id)
                .map(progLang -> {
                    progLang.setInventor(invetor);
                    ProgrammingLanguage updatedCar = programmingLanguageRepository.save(progLang);
                    return ResponseEntity.ok().body(updatedCar);})
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgLang(@PathVariable Long id) {
        if (programmingLanguageRepository.existsById(id)) {
            programmingLanguageRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllProgLangs() {
        programmingLanguageRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}

