package com.FranklinPineda.Ahorcado.Service;

import com.FranklinPineda.Ahorcado.Model.Palabra;
import java.util.List;

public interface PalabraServicee {

    List<Palabra> getAllPalabras();
    Palabra getPalabraById(Integer id);
    Palabra savePalabra(Palabra palabra);
    Palabra updatePalabra(Integer id, Palabra palabra);
    void deletePalabra(Integer id);
}
