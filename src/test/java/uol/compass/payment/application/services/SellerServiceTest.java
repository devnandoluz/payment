package uol.compass.payment.application.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import uol.compass.payment.application.dtos.input.SellerDTO;
import uol.compass.payment.application.dtos.output.SellerOutputDTO;
import uol.compass.payment.domain.entities.Seller;
import uol.compass.payment.domain.exceptions.SellerNotFoundException;
import uol.compass.payment.infrastructure.persistence.SellerRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SellerServiceTest {
    @Mock
    private SellerRepository repository;

    @InjectMocks
    private SellerService sellerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should create a new seller")
    void createSeller() {
        var sellerDTO = new SellerDTO("Seller Name");
        var savedSeller = new Seller(sellerDTO);
        when(repository.save(any(Seller.class))).thenReturn(savedSeller);

        SellerOutputDTO result = sellerService.create(sellerDTO);

        assertNotNull(result);
        assertEquals("Seller Name", result.name());
        verify(repository).save(any(Seller.class));
    }

    @Test
    @DisplayName("Find all sellers")
    void findAllSellers() {
        Seller seller = new Seller();
        when(repository.findAll()).thenReturn(List.of(seller));

        List<SellerOutputDTO> result = sellerService.findAll();

        assertFalse(result.isEmpty());
        verify(repository).findAll();
    }

    @Test
    @DisplayName("Find seller by code")
    void findSellerByCode() {
        UUID code = UUID.randomUUID();
        Seller seller = new Seller();
        when(repository.findByCode(code)).thenReturn(Optional.of(seller));

        Seller result = sellerService.findByCodeOrElseThrow(code);

        assertNotNull(result);
        verify(repository).findByCode(code);
    }

    @Test
    @DisplayName("Throw exception when seller not found by code")
    void throwExceptionWhenSellerNotFoundByCode() {
        UUID code = UUID.randomUUID();
        when(repository.findByCode(code)).thenReturn(Optional.empty());

        assertThrows(SellerNotFoundException.class, () -> sellerService.findByCodeOrElseThrow(code));
        verify(repository).findByCode(code);
    }

    @Test
    @DisplayName("Check seller existence by code")
    void checkSellerExistenceByCode() {
        UUID code = UUID.randomUUID();
        when(repository.existsByCode(code)).thenReturn(true);

        assertDoesNotThrow(() -> sellerService.checkExistsByCodeOrElseThrow(code));
        verify(repository).existsByCode(code);
    }

    @Test
    @DisplayName("Throw exception when seller does not exist by code")
    void throwExceptionWhenSellerDoesNotExistByCode() {
        UUID code = UUID.randomUUID();
        when(repository.existsByCode(code)).thenReturn(false);

        assertThrows(SellerNotFoundException.class, () -> sellerService.checkExistsByCodeOrElseThrow(code));
        verify(repository).existsByCode(code);
    }
}