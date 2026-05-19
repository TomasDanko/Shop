package com.learn2code_v2.Shop_V2.controller;

import com.learn2code_v2.Shop_V2.db.service.api.MerchantService;
import com.learn2code_v2.Shop_V2.domain.Merchant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("merchant")
public class MerchantController {

    private final MerchantService merchantService;

    public MerchantController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") int id){
        Merchant merchant = merchantService.get(id);
        if(merchant != null) {
            return new ResponseEntity<>(merchant, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

    }

    @GetMapping
    public ResponseEntity getMerchants(){
        List<Merchant> merchants = merchantService.getMerchants();
        return new ResponseEntity<>(merchants, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addMerchant(@RequestBody Merchant merchant){
        Integer id = merchantService.add(merchant);
        if(id != null) {
            return new ResponseEntity<>(id, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
