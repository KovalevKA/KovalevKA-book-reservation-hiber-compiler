package com.example.bookreservationhibercompiler.controller;

import com.example.bookreservationhibercompiler.dto.TranslatorDTO;
import com.example.bookreservationhibercompiler.service.TranslatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("translators")
public class TranslatorController {

  @Autowired
  private TranslatorService translatorService;

  @GetMapping
  public List<TranslatorDTO> getAllTranslators() {
    return translatorService.findAll();
  }

  @PostMapping
  public TranslatorDTO addTranslator(@RequestBody TranslatorDTO translatorDTO) {
    return translatorService.create(translatorDTO);
  }

  @GetMapping("find-name-like")
  public List<TranslatorDTO> getTranslatorsByNameLike(
          @RequestParam(name = "name") String name) {
    return translatorService.getByNameLike(name);
  }

  @PatchMapping("{id}")
  public TranslatorDTO editTranslator(
          @PathVariable Long id,
          @RequestBody TranslatorDTO translatorDTO) {
    return translatorService.update(id, translatorDTO);
  }

  @DeleteMapping("{id}")
  public void deleteTranslator(@PathVariable Long id) {
    translatorService.deleteById(id);
  }
}
