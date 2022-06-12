package com.tis5.NossoSindico.Service;

import com.tis5.NossoSindico.domain.*;
import com.tis5.NossoSindico.repository.EspacoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EspacoService {

    @Autowired
    EspacoRepository repository;

    public Espaco create(Espaco espaco){

        return repository.save(espaco);

    }

    public Optional<List<Espaco>> listByCondominio (Condominio condominio) {
        List<Espaco> espacos = repository.findAll();
        Optional<List<Espaco>> esCond = Optional.of(espacos.stream().filter(e -> e.getId_condominio() == condominio.getId()).collect(Collectors.toList()));
        return esCond;
    }


}
