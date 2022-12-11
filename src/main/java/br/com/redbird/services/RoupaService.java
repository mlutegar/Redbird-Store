package br.com.redbird.services;

import br.com.redbird.domain.model.Roupa;
import br.com.redbird.repository.RoupaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoupaService {
    private final RoupaRepository roupaRepository;

    public RoupaService(RoupaRepository roupaRepository) {
        this.roupaRepository = roupaRepository;
    }

    public Roupa saveRoupa(Roupa roupa) {
        return roupaRepository.save(roupa);
    }

    public List<Roupa> findAll() {
        return roupaRepository.findAll();
    }

    public Roupa findById(UUID id) {
        return roupaRepository.findById(id).orElse(new Roupa());
    }

    public void deleteById(UUID id) {
        roupaRepository.deleteById(id);
    }

    public void update(UUID id, String marca) {
        roupaRepository.updateMarca(id, marca);
    }
}
