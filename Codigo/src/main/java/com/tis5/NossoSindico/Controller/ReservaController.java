package com.tis5.NossoSindico.Controller;

import com.tis5.NossoSindico.Service.ReservaService;
import com.tis5.NossoSindico.domain.Condominio;
import com.tis5.NossoSindico.domain.Reserva;
import com.tis5.NossoSindico.domain.ReservaResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin(origins = "*")
public class ReservaController {
    @Autowired
    private ReservaService service;

    @RequestMapping(method = RequestMethod.POST, value = "createReserva")
    public ResponseEntity<Reserva> cria(@RequestBody ReservaResource rr){
        Optional<Reserva> r = service.create(rr);
        if(r.isPresent()) return ResponseEntity.ok(r.get());
        else return ResponseEntity.notFound().build();
    }

    @RequestMapping(method = RequestMethod.POST, value = "listReserva")
    public ResponseEntity<List<Reserva>> lista(@RequestBody Condominio cond){
        return ResponseEntity.ok(service.listByCondominio(cond));
    }
}