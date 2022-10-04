package original.transportationservicesapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import original.transportationservicesapp.dto.TransporterDto;
import original.transportationservicesapp.entity.Transporter;
import original.transportationservicesapp.exception.EntityNotFoundException;
import original.transportationservicesapp.mapper.Mapper;
import original.transportationservicesapp.repository.TransporterRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransporterService {
    private final TransporterRepo transporterRepo;
    private final Mapper mapper;

    public TransporterDto get(Long id) {
        return mapper.toTransporterDto(retrieve(id));
    }

    public List<TransporterDto> getAll() {
        return transporterRepo.findAll().stream().map(mapper::toTransporterDto).collect(Collectors.toList());
    }

    public TransporterDto create(TransporterDto dto) {
        return mapper.toTransporterDto(transporterRepo.save(mapper.toTransporter(dto)));
    }

    public TransporterDto update(Long id, TransporterDto dto) {
        Transporter transporter = retrieve(id);
        mapper.mergeTransporter(dto, transporter);
        return mapper.toTransporterDto(transporterRepo.save(transporter));
    }

    public void delete(Long id) {
        transporterRepo.deleteById(id);
    }

    private Transporter retrieve(Long id) {
        return transporterRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Transporter", id));
    }
}
