package com.tis5.NossoSindico.Controller;

import com.tis5.NossoSindico.Service.ApartamentoService;
import com.tis5.NossoSindico.Service.CondominioService;
import com.tis5.NossoSindico.Service.UsuarioService;
import com.tis5.NossoSindico.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@Controller
@CrossOrigin(origins = "*")
public class CondominioController {
    @Autowired
    private CondominioService service;
    @Autowired
    private ApartamentoService aptoService;
    @Autowired
    private UsuarioService usuService;

    @RequestMapping(method = RequestMethod.POST, value = "cadCond")
    public ResponseEntity<Condominio> cadastro(@RequestBody CadCondAptoResource aux) throws ParseException {
        Condominio cond = Condominio.builder().cep(aux.getCep()).bairro(aux.getBairro()).nome(aux.getNome()).cidade(aux.getCidade())
                .rua(aux.getRua()).numero(aux.getNumero()).build();
        cond.setCode(String.valueOf(cond.hashCode()));
        service.create(cond);
        Usuario usu = usuService.getUsuarioById(aux.getId_usuario());
        Apartamento apto = aptoService.create(Apartamento.builder().condominio(cond).numero(aux.getNumeroApto()).usuario(usu).sindico(true)
                .build());
        return ResponseEntity.ok(cond);
    }

    @RequestMapping(method = RequestMethod.POST, value = "entraCond")
    public ResponseEntity<Condominio> entrarCondominio(@RequestBody AcessoCondominioResource acr) {
        Condominio cond = service.enterCondominio(acr.getAcesso());
        if(cond !=null){
            Usuario usu = usuService.getUsuarioById(acr.getId_usuario());
            Apartamento apto = aptoService.create(Apartamento.builder().condominio(cond).numero(acr.getNumeroApto()).usuario(usu).sindico(false)
                    .build());
            return ResponseEntity.ok(cond);
        } else return null;
    }

    @RequestMapping(method = RequestMethod.GET, value = "codigoAcesso")
    public ResponseEntity<String> codigoAcesso(@RequestBody Condominio condominio){
        Condominio cond = service.getCondominioById(condominio.getId());
        return ResponseEntity.ok(cond.getCode());
    }

    @RequestMapping(method = RequestMethod.POST, value = "attCond")
    public ResponseEntity<Condominio> att(@RequestBody Condominio condominio) {
        Condominio c = service.update(condominio);
        return ResponseEntity.ok(c);
    }
}