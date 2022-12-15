package br.com.redbird.app.controller;

import br.com.redbird.domain.model.Roupa;
import br.com.redbird.services.RoupaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping(path = "redbird")
@Tag(name = "Arara de Roupas Redbird", description = "Controladora que gere o cadastro das peças de roupa disponíveis na loja Redbird")
public class RoupaController {
    private final RoupaService roupaService;

    public RoupaController(RoupaService roupaService) {
        this.roupaService = roupaService;
    }

    @PostMapping(value = "/roupa", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Cadastra uma peça de roupa", responses = {@ApiResponse(description = "Sucesso ao cadastrar", responseCode = "201", content = @Content)})
    ResponseEntity<Roupa> save(@RequestBody Roupa roupa) {
        return new ResponseEntity<>(roupaService.saveRoupa(roupa), CREATED);
    }

    @GetMapping(value = "/roupas", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Lista de peças de roupa", responses = {@ApiResponse(description = "Sucesso ao listar as peças de roupa", responseCode = "200", content = @Content)})
    ResponseEntity<List<Roupa>> findAll() {
        return new ResponseEntity<>(roupaService.findAll(), OK);
    }

    @GetMapping(value = "/roupa/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Localiza uma roupa pelo ID", responses = {@ApiResponse(description = "Sucesso ao consultar a peça de roupa", responseCode = "200", content = @Content)})
    ResponseEntity<Roupa> findById(@PathVariable UUID id) {
        return new ResponseEntity<>(roupaService.findById(id), OK);
    }

    @PutMapping(value = "/roupa", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Atualiza uma roupa", responses = {@ApiResponse(description = "Sucesso ao atualizar", responseCode = "204")})
    ResponseEntity<?> update(@RequestBody Roupa roupa) {
        roupaService.saveRoupa(roupa);
        return new ResponseEntity<>(NO_CONTENT);
    }

    @PatchMapping(value = "/roupa", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Atualiza a marca da roupa", responses = {@ApiResponse(description = "Sucesso ao atualizar", responseCode = "204")})
    ResponseEntity<?> updateMarca(@RequestParam UUID id, @RequestParam String marca) {
        roupaService.saveRoupa(id, marca);
        return new ResponseEntity<>(NO_CONTENT);
    }

    @DeleteMapping("/roupa/{id}")
    @Operation(summary = "Delete a roupa", responses = {@ApiResponse(description = "Sucesso ao deletar a peça de roupa", responseCode = "204")})
    ResponseEntity<?> delete(@RequestParam UUID id) {
        roupaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
