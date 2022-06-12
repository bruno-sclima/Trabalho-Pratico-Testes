package com.tis5.NossoSindico.Controller;

import com.tis5.NossoSindico.Service.ApartamentoService;
import com.tis5.NossoSindico.Service.CondominioService;
import com.tis5.NossoSindico.Service.UsuarioService;
import com.tis5.NossoSindico.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@CrossOrigin(origins = "*")
public class ApartamentoController {
    @Autowired
    private CondominioService service;
    @Autowired
    private ApartamentoService aptoService;
    @Autowired
    private UsuarioService usuService;

    @RequestMapping(method = RequestMethod.POST, value = "listCondUsu")
    public ResponseEntity<List<Condominio>> listCondUsu(@RequestBody Usuario usuario) {
        Optional<List<Apartamento>> apto = aptoService.listAptosByUsuario(usuario);
        if (apto.isPresent()) {
            return ResponseEntity.ok(apto.get().stream().map(e -> e.getCondominio()).collect(Collectors.toList()));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @RequestMapping(method = RequestMethod.POST, value = "isSindico")
    public ResponseEntity<Boolean> isSindico(@RequestBody SindicoResource sindicoResource){
        return ResponseEntity.ok(aptoService.isSindico(sindicoResource.getId_usuario(),sindicoResource.getId_condominio()));
    }
}