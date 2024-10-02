package uol.compass.payment.application.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uol.compass.payment.application.dtos.input.ChargeDTO;
import uol.compass.payment.application.dtos.output.ChargeOutputDTO;
import uol.compass.payment.domain.entities.Charge;
import uol.compass.payment.domain.entities.Seller;
import uol.compass.payment.domain.exceptions.ChargeNotFoundException;
import uol.compass.payment.infrastructure.persistence.ChargeRepository;

import java.util.List;
import java.util.UUID;

@Service
public class ChargeService {
    private final ChargeRepository repository;
    private final SellerService sellerService;

    public ChargeService(ChargeRepository repository, SellerService sellerService) {
        this.repository = repository;
        this.sellerService = sellerService;
    }

    @Transactional
    public ChargeOutputDTO create(ChargeDTO chargeDTO) {
        var seller = getSeller(chargeDTO.sellerCode());
        var entity = new Charge(chargeDTO.description(), chargeDTO.amount());
        entity.setSeller(seller);
        return new ChargeOutputDTO(save(entity));
    }

    @Transactional
    public Charge findByCodeOrElseThrow(UUID code) {
        return repository.findByCode(code)
                .orElseThrow(() -> new ChargeNotFoundException(code));
    }

    @Transactional(readOnly = true)
    public List<ChargeOutputDTO> findAll() {
        return repository.findAll().stream().map(ChargeOutputDTO::new).toList();
    }

    private Seller getSeller(UUID uuid) {
        return sellerService.findByCodeOrElseThrow(uuid);
    }

    private Charge save(Charge charge) {
        return repository.save(charge);
    }
}
