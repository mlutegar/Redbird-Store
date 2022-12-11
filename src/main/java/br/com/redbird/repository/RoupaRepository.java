package br.com.redbird.repository;

import br.com.redbird.domain.model.Roupa;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("roupaRepository")
public interface RoupaRepository extends JpaRepository<Roupa, UUID> {
    @Modifying
    @Transactional
    @Query(value = "update redbird r set r.marca = :marca where r.productId = :id")
    void updateMarca(UUID id, String marca);
}
