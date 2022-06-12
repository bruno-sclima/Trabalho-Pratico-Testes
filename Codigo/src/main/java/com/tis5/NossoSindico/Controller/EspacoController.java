package com.tis5.NossoSindico.Controller;

import com.tis5.NossoSindico.Service.AvisoService;
import com.tis5.NossoSindico.Service.CondominioService;
import com.tis5.NossoSindico.Service.EspacoService;
import com.tis5.NossoSindico.Service.ReservaService;
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

@Controller
@CrossOrigin(origins = "*")
public class EspacoController {

    @Autowired
    private EspacoService service;

    @RequestMapping(method = RequestMethod.POST, value = "createEspaco")
    public ResponseEntity<Espaco> cria(@RequestBody Espaco espaco){
        return ResponseEntity.ok(service.create(espaco));
    }

    @RequestMapping(method = RequestMethod.POST, value = "listEspaco")
    public ResponseEntity<List<Espaco>> lista(@RequestBody Condominio cond){
        return ResponseEntity.ok(service.listByCondominio(cond).isPresent() ? service.listByCondominio(cond).get() : Collections.emptyList());
    }

}
