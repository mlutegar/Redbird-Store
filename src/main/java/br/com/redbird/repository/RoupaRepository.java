package br.com.redbird.repository;

import br.com.redbird.domain.model.Roupa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("roupaRepository")
public interface RoupaRepository extends JpaRepository<Roupa, UUID> {
}
