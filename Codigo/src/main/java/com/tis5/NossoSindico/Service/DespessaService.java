package com.tis5.NossoSindico.Service;


import com.tis5.NossoSindico.domain.Condominio;
import com.tis5.NossoSindico.domain.Despessa;
import com.tis5.NossoSindico.repository.DespessaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DespessaService {

    @Autowired
    DespessaRepository repository;
    @Autowired
    CondominioService condominioService;

    public Despessa create(Despessa despessa){
        return repository.save(despessa);
    }

    public Optional<List<Despessa>> listDespessasCondominio(Condominio condominio){
        if(condominio != null) {
            Optional<List<Despessa>> despessas = repository.findByCondominio(condominio);
            return despessas;
        }
        return Optional.of(Collections.emptyList());
    }
}