package uol.compass.payment.application.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import uol.compass.payment.application.dtos.input.ChargeDTO;
import uol.compass.payment.application.dtos.output.ChargeOutputDTO;
import uol.compass.payment.domain.entities.Charge;
import uol.compass.payment.domain.entities.Seller;
import uol.compass.payment.domain.exceptions.ChargeNotFoundException;
import uol.compass.payment.infrastructure.persistence.ChargeRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ChargeServiceTest {
    @Mock
    private ChargeRepository repository;

    @Mock
    private SellerService sellerService;

    @InjectMocks
    private ChargeService chargeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should create a new charge")
    void shouldCreateCharge() {
        var chargeDTO = new ChargeDTO("Test Description", UUID.randomUUID(), BigDecimal.TEN);
        var seller = new Seller();
        var savedCharge = new Charge(chargeDTO.description(), chargeDTO.amount());
        savedCharge.setSeller(seller);

        when(sellerService.findByCodeOrElseThrow(any(UUID.class))).thenReturn(seller);
        when(repository.save(any(Charge.class))).thenReturn(savedCharge);

        ChargeOutputDTO result = chargeService.create(chargeDTO);

        assertNotNull(result);
        verify(repository).save(any(Charge.class));
    }

    @Test
    @DisplayName("Should find charge by code")
    void shouldFindChargeByCode() {
        UUID code = UUID.randomUUID();
        Charge charge = new Charge();
        when(repository.findByCode(code)).thenReturn(Optional.of(charge));

        Charge result = chargeService.findByCodeOrElseThrow(code);

        assertNotNull(result);
        verify(repository).findByCode(code);
    }

    @Test
    @DisplayName("Should throw exception when charge not found by code")
    void shouldThrowExceptionWhenChargeNotFoundByCode() {
        UUID code = UUID.randomUUID();
        when(repository.findByCode(code)).thenReturn(Optional.empty());

        assertThrows(ChargeNotFoundException.class, () -> chargeService.findByCodeOrElseThrow(code));
        verify(repository).findByCode(code);
    }

    @Test
    @DisplayName("Should find all charges")
    void shouldFindAllCharges() {
        var charge = new Charge();
        charge.setSeller(new Seller());
        when(repository.findAll()).thenReturn(List.of(charge));

        List<ChargeOutputDTO> result = chargeService.findAll();

        assertFalse(result.isEmpty());
        verify(repository).findAll();
    }
}