package com.FranklinPineda.Ahorcado.Repository;

import com.FranklinPineda.Ahorcado.Model.Palabra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PalabraRepository extends JpaRepository<Palabra, Integer> {
}