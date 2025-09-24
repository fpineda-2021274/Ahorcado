package com.FranklinPineda.Ahorcado.Controller;

import com.FranklinPineda.Ahorcado.Model.Palabra;
import com.FranklinPineda.Ahorcado.Service.PalabraService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/palabras")
public class PalabraController {

    private final PalabraService palabraService;

    public PalabraController(PalabraService palabraService) {
        this.palabraService = palabraService;
    }

    // Obtener todas las palabras
    @GetMapping
    public List<Palabra> getAllPalabras() {
        return palabraService.getAllPalabras();
    }

    // Obtener palabra por ID
    @GetMapping("/{id}")
    public Palabra getPalabraById(@PathVariable Integer id) {
        return palabraService.getPalabraById(id);
    }

    // Crear nueva palabra
    @PostMapping
    public Palabra savePalabra(@RequestBody Palabra palabra) {
        return palabraService.savePalabra(palabra);
    }

    // Actualizar palabra existente
    @PutMapping("/{id}")
    public Palabra updatePalabra(@PathVariable Integer id, @RequestBody Palabra palabra) {
        return palabraService.updatePalabra(id, palabra);
    }

    // Eliminar palabra
    @DeleteMapping("/{id}")
    public void deletePalabra(@PathVariable Integer id) {
        palabraService.deletePalabra(id);
    }
}
