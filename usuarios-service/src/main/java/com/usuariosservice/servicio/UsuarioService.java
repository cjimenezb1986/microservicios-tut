package com.usuariosservice.servicio;

import com.usuariosservice.entidades.Usuario;
import com.usuariosservice.feignClients.CarroFeignClient;
import com.usuariosservice.feignClients.MotoFeignClient;
import com.usuariosservice.modelos.Carro;
import com.usuariosservice.modelos.Moto;
import com.usuariosservice.repositorio.UsuarioRepository;
import com.zaxxer.hikari.util.ConcurrentBag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UsuarioService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CarroFeignClient carroFeignClient;

    @Autowired
    private MotoFeignClient motoFeignClient;

    public List<Carro> getCarros(Long usuarioId){
        List<Carro> carros = restTemplate.getForObject("http://localhost:8002/carro/usuario/"+ usuarioId, List.class);
        return carros;

    }

    public List<Moto> getMotos(Long usuarioId){
        List<Moto> motos = restTemplate.getForObject("http://localhost:8003/moto/usuario/",List.class);
        return motos;
    }


    public List<Usuario>getAll(){
        return usuarioRepository.findAll();
    }

    public Usuario getUsuarioById(Long id){
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario save(Usuario usuario){
        Usuario nuevoUsuario = usuarioRepository.save(usuario);
        return nuevoUsuario;
    }


    public Carro saveCarro(Long usuarioId, Carro carro){
        carro.setUsuarioId(usuarioId);
        Carro nuevoCarro = carroFeignClient.save(carro);
        return nuevoCarro;
    }

    public Moto saveMoto(Long usuarioId, Moto moto){
        moto.setUsuarioId(usuarioId);
        Moto nuevaMoto = motoFeignClient.save(moto);
        return nuevaMoto;
    }

    public Map<String, Object> getUsuarioAndVehiculos(Long usuarioId){
        Map<String, Object> resultado = new HashMap<>();
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);

        if(usuario==null){
            resultado.put("Mensaje","El usuario no existe");
            return resultado;
        }
        resultado.put("Usuario",usuario);
        List<Carro>carros = carroFeignClient.getCarros(usuarioId);
        if(carros.isEmpty()){
            resultado.put("Carros","El usuario no tiene carros");
        }else{
            resultado.put("Carros",carros);
        }

        List<Moto>motos = motoFeignClient.getMotos(usuarioId);
        if(motos.isEmpty()){
            resultado.put("Motos","El usuario no tiene carros");
        }else{
            resultado.put("Motos",motos);
        }

        return resultado;
    }

}
