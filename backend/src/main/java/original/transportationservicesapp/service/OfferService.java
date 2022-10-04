package original.transportationservicesapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import original.transportationservicesapp.dto.OfferDto;
import original.transportationservicesapp.entity.Offer;
import original.transportationservicesapp.exception.EntityNotFoundException;
import original.transportationservicesapp.mapper.Mapper;
import original.transportationservicesapp.repository.OfferRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OfferService {
    private final OfferRepo offerRepo;
    private final Mapper mapper;

    public OfferDto get(Long id) {
        return mapper.toOfferDto(retrieve(id));
    }

    public List<OfferDto> getAll() {
        return offerRepo.findAll().stream().map(mapper::toOfferDto).collect(Collectors.toList());
    }

    public OfferDto create(OfferDto dto) {
        return mapper.toOfferDto(offerRepo.save(mapper.toOffer(dto)));
    }

    public OfferDto update(Long id, OfferDto dto) {
        Offer offer = retrieve(id);
        mapper.mergeOffer(dto, offer);
        return mapper.toOfferDto(offerRepo.save(offer));
    }

    public void delete(Long id) {
        offerRepo.deleteById(id);
    }

    private Offer retrieve(Long id) {
        return offerRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Offer", id));
    }
}