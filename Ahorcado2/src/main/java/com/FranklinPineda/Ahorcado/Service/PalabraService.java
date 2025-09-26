package com.FranklinPineda.Ahorcado.Service;

import com.FranklinPineda.Ahorcado.Model.Palabra;
import java.util.List;

public interface PalabraService {
    List<Palabra> getAllPalabras();
    Palabra getPalabraById(Integer id);
    Palabra savePalabra(Palabra palabra);
    boolean deletePalabra(Integer id);
}