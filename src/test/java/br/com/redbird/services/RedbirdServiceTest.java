package br.com.redbird.services;

import br.com.redbird.domain.model.Roupa;
import br.com.redbird.repository.RoupaRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RedbirdServiceTest {

    @Autowired
    private RoupaRepository roupaRepository;

    @AfterEach
    void tearDown() {
        roupaRepository.deleteAll();
    }

    @Test
    public void findAll() {
        var roupa = new Roupa(UUID.randomUUID(), "Vestido", "azul", "marisa", "P", 22.10, 2, Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()));
        roupaRepository.save(roupa);

        var roupaService = new RoupaService(roupaRepository);
        List<Roupa> roupaList = roupaService.findAll();
        var lastRoupa = roupaList.get(roupaList.size() - 1);

        assertEquals(roupa.getNome(), lastRoupa.getNome());
        assertEquals(roupa.getCor(), lastRoupa.getCor());
        assertEquals(roupa.getMarca(), lastRoupa.getMarca());
        assertEquals(roupa.getTamanho(), lastRoupa.getTamanho());
        assertEquals(roupa.getPreco(), lastRoupa.getPreco());
        assertEquals(roupa.getQuantidade(), lastRoupa.getQuantidade());
    }

    @Test
    public void findById() {
        var roupaSaved = roupaRepository.save(new Roupa(UUID.randomUUID(), "Vestido", "azul", "marisa", "P", 22.10, 2, Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now())));

        var roupaService = new RoupaService(roupaRepository);
        var roupaFinded = roupaService.findById(roupaSaved.getProductId());

        assertEquals(roupaSaved.getProductId(), roupaFinded.getProductId());
        assertEquals(roupaSaved.getNome(), roupaFinded.getNome());
        assertEquals(roupaSaved.getCor(), roupaFinded.getCor());
        assertEquals(roupaSaved.getMarca(), roupaFinded.getMarca());
        assertEquals(roupaSaved.getTamanho(), roupaFinded.getTamanho());
        assertEquals(roupaSaved.getPreco(), roupaFinded.getPreco());
        assertEquals(roupaSaved.getQuantidade(), roupaFinded.getQuantidade());
    }

    @Test
    public void deleteById() {
        var roupaSaved = roupaRepository.save(new Roupa(UUID.randomUUID(), "Vestido", "azul", "marisa", "P", 22.10, 2, Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now())));

        var roupaService = new RoupaService(roupaRepository);
        var roupaFinded = roupaService.findById(roupaSaved.getProductId());

        assertEquals(roupaSaved.getProductId(), roupaFinded.getProductId());
        assertEquals(roupaSaved.getNome(), roupaFinded.getNome());
        assertEquals(roupaSaved.getCor(), roupaFinded.getCor());
        assertEquals(roupaSaved.getMarca(), roupaFinded.getMarca());
        assertEquals(roupaSaved.getTamanho(), roupaFinded.getTamanho());
        assertEquals(roupaSaved.getPreco(), roupaFinded.getPreco());
        assertEquals(roupaSaved.getQuantidade(), roupaFinded.getQuantidade());
    }

    @Test
    public void saveRoupa() {
        var roupaService = new RoupaService(roupaRepository);
        var roupa = new Roupa(UUID.randomUUID(), "Vestido", "azul", "marisa", "P", 22.10, 2, Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()));

        roupaService.saveRoupa(roupa);
        assertEquals(1.0, roupaRepository.count());
    }

    @Test
    void deleteRoupa() {
        var roupaService = new RoupaService(roupaRepository);
        var roupa = new Roupa(UUID.randomUUID(), "Vestido", "azul", "marisa", "P", 22.10, 2, Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()));

        var roupaSaved = roupaService.saveRoupa(roupa);
        roupaService.deleteById(roupaSaved.getProductId());

        assertEquals(0, roupaRepository.count());
    }
}
