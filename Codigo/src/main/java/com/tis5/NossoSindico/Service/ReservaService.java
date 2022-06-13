package com.tis5.NossoSindico.Service;

import com.tis5.NossoSindico.domain.*;
import com.tis5.NossoSindico.repository.ApartamentoRepository;
import com.tis5.NossoSindico.repository.EspacoRepository;
import com.tis5.NossoSindico.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservaService {
    @Autowired
    private ReservaRepository repository;
    @Autowired
    private EspacoRepository espacoRepository;
    @Autowired
    private ApartamentoRepository aptoRepository;
    @Autowired
    private CondominioService condominioService;

    public Optional<Reserva> create(ReservaResource rr){
        if (rr != null) {
            Optional<Espaco> e = espacoRepository.findById(rr.getId_espaco());
            Optional<Condominio> c = condominioService.getCondominioById(rr.getId_condominio());
                if (c.isPresent() && e.isPresent()) {
                    Optional<List<Apartamento>> aptos = aptoRepository.findByCondominio(c.get());
                    if (aptos.isPresent()) {
                        Optional<Apartamento> apto = aptos.get().stream().filter(v -> v.getNumero() == rr.getNumero()).findFirst();
                        if (apto.isPresent()) {
                            Reserva r = Reserva.builder().data(rr.getData()).lugar(e.get()).descricao(rr.getDescricao()).apto(apto.get()).build();
                            return Optional.of(repository.save(r));
                        } else return Optional.empty();
                    } else return Optional.empty();
                } else return Optional.empty();
        } else return Optional.empty();
    }

    public List<Reserva> listByCondominio (Condominio condominio) {
        List<Reserva> reservas = repository.findAll();
        List<Reserva> resCond = reservas.stream().filter(e -> e.getApto().getCondominio()
                .getId() == condominio.getId()).collect(Collectors.toList());
        return resCond != null && !resCond.isEmpty() ? resCond : new ArrayList<>();
    }

}