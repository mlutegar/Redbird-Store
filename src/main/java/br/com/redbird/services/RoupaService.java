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

    public Funcionario save(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public List<Funcionario> findAll() {
        return funcionarioRepository.findAll();
    }

    public Funcionario findById(UUID id) {
        return funcionarioRepository.findById(id).orElse(new Funcionario());
    }

    public void deleteById(UUID id) {
        funcionarioRepository.deleteById(id);
    }

}
