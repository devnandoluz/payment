package uol.compass.payment.application.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uol.compass.payment.application.dtos.input.SellerDTO;
import uol.compass.payment.application.dtos.output.SellerOutputDTO;
import uol.compass.payment.domain.entities.Seller;
import uol.compass.payment.domain.exceptions.SellerNotFoundException;
import uol.compass.payment.infrastructure.persistence.SellerRepository;

import java.util.List;
import java.util.UUID;

@Service
public class SellerService {

    private final SellerRepository repository;

    public SellerService(SellerRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public SellerOutputDTO create(SellerDTO sellerDTO) {
        var entity = new Seller(sellerDTO);
        return new SellerOutputDTO(save(entity));
    }

    @Transactional(readOnly = true)
    public List<SellerOutputDTO> findAll() {
        return repository.findAll().stream().map(SellerOutputDTO::new).toList();
    }

    @Transactional
    public Seller findByCodeOrElseThrow(UUID code) {
        return repository.findByCode(code)
                .orElseThrow(() -> new SellerNotFoundException(code));
    }

    @Transactional(readOnly = true)
    public void checkExistsByCodeOrElseThrow(UUID code) {
        var exist = repository.existsByCode(code);
        if (!exist) throw new SellerNotFoundException(code);
    }

    private Seller save(Seller seller) {
        return repository.save(seller);
    }
}
