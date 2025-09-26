package com.FranklinPineda.Ahorcado.Controller;

import com.FranklinPineda.Ahorcado.Model.Palabra;
import com.FranklinPineda.Ahorcado.Service.PalabraService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/palabras")
public class PalabraController {

    private final PalabraService palabraService;

    public PalabraController(PalabraService palabraService) {
        this.palabraService = palabraService;
    }

    @GetMapping
    public List<Palabra> getAllPalabras() {
        return palabraService.getAllPalabras();
    }

    @GetMapping("/{id}")
    public Palabra getPalabraById(@PathVariable Integer id) {
        return palabraService.getPalabraById(id);
    }

    @PostMapping
    public String createPalabra(@RequestBody Palabra palabra) {
        palabraService.savePalabra(palabra);
        return "Nueva palabra agregada";
    }

    @DeleteMapping("/{id}")
    public String deletePalabra(@PathVariable Integer id) {
        boolean deleted = palabraService.deletePalabra(id);
        if (deleted) {
            return "Palabra eliminada con éxito";
        } else {
            return "La id de la palabra no existe";
        }
    }
}