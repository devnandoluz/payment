package uol.compass.payment.controller.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uol.compass.payment.application.dtos.input.SellerDTO;
import uol.compass.payment.application.dtos.output.SellerOutputDTO;
import uol.compass.payment.application.services.SellerService;

import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerApi {

    private final SellerService service;

    public SellerApi(SellerService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SellerOutputDTO> create(@RequestBody @Valid SellerDTO sellerDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(sellerDTO));
    }

    @GetMapping
    public ResponseEntity<List<SellerOutputDTO>> get() {
        return ResponseEntity.ok(service.findAll());
    }
}
