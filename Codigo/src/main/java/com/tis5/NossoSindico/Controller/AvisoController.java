package com.tis5.NossoSindico.Controller;

import com.tis5.NossoSindico.Service.AvisoService;
import com.tis5.NossoSindico.Service.CondominioService;
import com.tis5.NossoSindico.domain.Aviso;
import com.tis5.NossoSindico.domain.AvisoResource;
import com.tis5.NossoSindico.domain.Condominio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin(origins = "*")
public class AvisoController {
    @Autowired
    private AvisoService service;

    @Autowired
    private CondominioService condominioService;

    @RequestMapping(method = RequestMethod.POST, value = "cadAviso")
    public ResponseEntity<Aviso> cadastro(@RequestBody AvisoResource aux) throws ParseException {
        Optional<Condominio> c = condominioService.getCondominioById(aux.getId_condominio());
        if(c.isPresent()) {
            Aviso av = Aviso.builder().condominio(c.get()).conteudo(aux.getConteudo()).titulo(aux.getTitulo()).build();
            Aviso aviso = service.create(av);
            return ResponseEntity.ok(aviso);
        }
        else return ResponseEntity.notFound().build();
    }

    @RequestMapping(method = RequestMethod.POST, value = "listAvisos")
    public ResponseEntity<List<Aviso>> listAvisos(@RequestBody Condominio cond) throws ParseException {
        Optional<List<Aviso>> avisos = service.listAvisosDeCondominio(cond.getId());
        return ResponseEntity.ok(avisos.get());
    }
}