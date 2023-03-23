package com.usuariosservice.feignClients;

import com.usuariosservice.modelos.Moto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "moto-service", url = "http://localhost:8003")
@RequestMapping("/moto")
public interface MotoFeignClient {

    @PostMapping()
    public Moto save(@RequestBody Moto moto);

    @GetMapping("/usuario/{usuarioId}")
    public List<Moto> getMotos(@PathVariable("usuarioId") Long usuarioId);
}
