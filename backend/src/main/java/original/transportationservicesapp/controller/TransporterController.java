package original.transportationservicesapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import original.transportationservicesapp.dto.TransporterDto;
import original.transportationservicesapp.service.TransporterService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/transporters")
public class TransporterController {
    private final TransporterService service;
    @GetMapping("/{id}")
    public TransporterDto get(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping
    public List<TransporterDto> getAll() {
        return service.getAll();
    }

    @PostMapping
    public TransporterDto create(@RequestBody TransporterDto transporter) {
        return service.create(transporter);
    }

    @PutMapping("/{id}")
    public TransporterDto update(@PathVariable Long id, @RequestBody TransporterDto transporter) {
        return service.update(id, transporter);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
