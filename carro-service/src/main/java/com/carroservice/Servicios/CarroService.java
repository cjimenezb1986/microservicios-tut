package com.carroservice.Servicios;

import com.carroservice.entidades.Carro;
import com.carroservice.repositorio.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarroService {
    @Autowired
    private CarroRepository carroRepository;

    public List<Carro> getAll(){
        return carroRepository.findAll();
    }

    public Carro getCarroById(Long id){

        return  carroRepository.findById(id).orElse(null);
    }

    public Carro save(Carro carro){
        Carro nuevoCarro = carroRepository.save(carro);
        return nuevoCarro;
    }
    public List<Carro> byUsuarioId(Long usuarioId){
        return carroRepository.findByUsuarioId(usuarioId);
    }
}
