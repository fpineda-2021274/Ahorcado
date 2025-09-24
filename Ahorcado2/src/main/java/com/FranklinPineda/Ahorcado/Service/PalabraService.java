package com.FranklinPineda.Ahorcado.Service;

import com.FranklinPineda.Ahorcado.Model.Palabra;
import com.FranklinPineda.Ahorcado.Repository.PalabraRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PalabraService {

    private final PalabraRepository palabraRepository;

    public PalabraService(PalabraRepository palabraRepository) {
        this.palabraRepository = palabraRepository;
    }

    public List<Palabra> getAllPalabras() {
        return palabraRepository.findAll();
    }

    public Palabra getPalabraById(Integer id) {
        return palabraRepository.findById(id).orElse(null);
    }

    public Palabra savePalabra(Palabra palabra) {
        List<Palabra> lista = palabraRepository.findAll();
        for (Palabra p : lista) {
            if (p.getPalabra().equalsIgnoreCase(palabra.getPalabra())) {
                palabra.setPalabra("ERROR_PALABRA_REPETIDA");
                return palabra;
            }
        }
        return palabraRepository.save(palabra);
    }

    public Palabra updatePalabra(Integer id, Palabra palabra) {
        Palabra existingPalabra = palabraRepository.findById(id).orElse(null);
        if (existingPalabra != null) {
            List<Palabra> lista = palabraRepository.findAll();
            for (Palabra p : lista) {
                if (!p.getId().equals(id)) {
                    if (p.getPalabra().equalsIgnoreCase(palabra.getPalabra())) {
                        palabra.setPalabra("ERROR_PALABRA_REPETIDA");
                        return palabra;
                    }
                }
            }
            existingPalabra.setPalabra(palabra.getPalabra());
            existingPalabra.setPista1(palabra.getPista1());
            existingPalabra.setPista2(palabra.getPista2());
            existingPalabra.setPista3(palabra.getPista3());
            return palabraRepository.save(existingPalabra);
        }
        return null;
    }

    public void deletePalabra(Integer id) {
        palabraRepository.deleteById(id);
    }
}
