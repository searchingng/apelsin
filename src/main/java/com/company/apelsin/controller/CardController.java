package com.company.apelsin.controller;

import com.company.apelsin.dto.CardDTO;
import com.company.apelsin.service.CardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
@RequiredArgsConstructor
@Slf4j
public class CardController {

    private final CardService cardService;

    @PostMapping
    public ResponseEntity<CardDTO> create(@RequestBody CardDTO dto){
        log.info("New Card Added " + dto);
        return ResponseEntity.ok(cardService.addCard(dto));
    }

    /*@GetMapping
    public ResponseEntity<Page<CardDTO>> getAll(Pageable pageable){
        log.debug("All Cards are shown");
        return ResponseEntity.ok(cardService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardDTO> getById(@PathVariable Long id){
        log.debug("Card is gotten with ID: {}", id);
        return ResponseEntity.ok(cardService.getById(id));
    }

    @GetMapping("/number/{number}")
    public ResponseEntity<CardDTO> getById(@PathVariable String number){
        log.debug("Card is gotten with Number: {}", number);
        return ResponseEntity.ok(cardService.getByNumber(number));
    }

    @PostMapping("/app/get")
    public ResponseEntity<CardDTO> findByNumberAndExpDate(@RequestBody CardDTO dto){
        return ResponseEntity.ok(cardService.findByNumberAndExpDate(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        log.warn("Card is deleted with ID: {}", id);
        return ResponseEntity.ok(cardService.deleteById(id));
    }*/

}
