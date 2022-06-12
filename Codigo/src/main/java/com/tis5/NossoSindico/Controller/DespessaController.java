package com.tis5.NossoSindico.Controller;

import com.tis5.NossoSindico.Service.CondominioService;
import com.tis5.NossoSindico.Service.DespessaService;
import com.tis5.NossoSindico.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.ParseException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin(origins = "*")
public class DespessaController {
    @Autowired
    DespessaService service;

    @Autowired
    private CondominioService condominioService;

    @RequestMapping(method = RequestMethod.POST, value = "cadDespessa")
    public ResponseEntity<Despessa> cadastro(@RequestBody DespessaResource aux) throws ParseException {
        Condominio c = condominioService.getCondominioById(aux.getId_condominio());
        Despessa d = Despessa.builder().condominio(c).data_referente(aux.getData_referente()).descricao(aux.getDescricao())
                .titulo(aux.getTitulo()).valor(aux.getValor()).build();
        Despessa despessa = service.create(d);
        return ResponseEntity.ok(despessa);
    }

    @RequestMapping(method = RequestMethod.POST, value = "listDespessa")
    public ResponseEntity<List<Despessa>> listDespessas(@RequestBody Condominio cond) throws ParseException {
        Optional<List<Despessa>> despessas = service.listDespessasCondominio(cond);
        if (despessas.isPresent()) return ResponseEntity.ok(despessas.get());
        else return ResponseEntity.ok(Collections.emptyList());
    }
}