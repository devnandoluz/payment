package uol.compass.payment.controller.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uol.compass.payment.application.dtos.input.ChargeDTO;
import uol.compass.payment.application.dtos.output.ChargeOutputDTO;
import uol.compass.payment.application.services.ChargeService;

import java.util.List;

@RestController
@RequestMapping("/charge")
public class ChargeApi {

    private final ChargeService service;

    public ChargeApi(ChargeService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ChargeOutputDTO> create(@RequestBody @Valid ChargeDTO chargeDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(chargeDTO));
    }

    @GetMapping
    public ResponseEntity<List<ChargeOutputDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
