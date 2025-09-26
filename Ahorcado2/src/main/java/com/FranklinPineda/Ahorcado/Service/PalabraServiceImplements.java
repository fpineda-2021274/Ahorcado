package com.FranklinPineda.Ahorcado.Service;

import com.FranklinPineda.Ahorcado.Model.Palabra;
import com.FranklinPineda.Ahorcado.Repository.PalabraRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PalabraServiceImplements implements PalabraService {

    private final PalabraRepository palabraRepository;

    public PalabraServiceImplements(PalabraRepository palabraRepository) {
        this.palabraRepository = palabraRepository;
    }

    @Override
    public List<Palabra> getAllPalabras() {
        return palabraRepository.findAll();
    }

    @Override
    public Palabra getPalabraById(Integer id) {
        return palabraRepository.findById(id).orElse(null);
    }

    @Override
    public Palabra savePalabra(Palabra palabra) {
        return palabraRepository.save(palabra);
    }

    @Override
    public boolean deletePalabra(Integer id) {
        if (palabraRepository.existsById(id)) {
            palabraRepository.deleteById(id);
            return true;
        }
        return false;
    }
}