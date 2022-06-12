package com.tis5.NossoSindico.Service;


import com.tis5.NossoSindico.domain.Aviso;
import com.tis5.NossoSindico.domain.Condominio;
import com.tis5.NossoSindico.repository.AvisoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AvisoService {
    @Autowired
    AvisoRepository repository;
    @Autowired
    CondominioService condominioService;

    public Aviso create(Aviso aviso){
        return repository.save(aviso);
    }

    public void deleteAvisoById(long id){
        Optional<Aviso> aviso = repository.findById(id);
        if(aviso.isPresent()) {
            repository.deleteById(id);
        }
    }

    public Optional<List<Aviso>> listAvisosDeCondominio(long condominio_id){
        Condominio condominio = condominioService.getCondominioById(condominio_id);
        if(condominio != null) {
            Optional<List<Aviso>> avisos = repository.findByCondominio(condominio);
            return avisos;
        }
        return Optional.of(Collections.emptyList());
    }
}
