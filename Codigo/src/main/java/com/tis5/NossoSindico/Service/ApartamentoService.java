package com.tis5.NossoSindico.Service;

import com.tis5.NossoSindico.domain.Apartamento;
import com.tis5.NossoSindico.domain.Condominio;
import com.tis5.NossoSindico.domain.Usuario;
import com.tis5.NossoSindico.domain.UsuarioComApto;
import com.tis5.NossoSindico.repository.ApartamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ApartamentoService {
    @Autowired
    ApartamentoRepository repository;
    @Autowired
    CondominioService condominioService;
    @Autowired
    UsuarioService usuarioService;

    public Apartamento create(Apartamento apartamento){
        return repository.save(apartamento);
    }

    public Apartamento getAptoByCondominioENumero(long condominio_id,int numero){
        List<Apartamento> aptos = listApartamentosDeCondominio(condominio_id);
        if(aptos != null && aptos.size() > 0){
            Optional<Apartamento> apto = aptos.stream().filter(e -> e.getNumero() == numero).findFirst();
            if(apto.isPresent()) return apto.get();
        }
        return null;
    }

    public void deleteAptoById(long id){
        Optional<Apartamento> apto = repository.findById(id);
        if(apto.isPresent()) {
            repository.deleteById(id);
        }
    }

    public List<Apartamento> listApartamentosDeCondominio(long condominio_id){
        Condominio condominio = condominioService.getCondominioById(condominio_id);
        if(condominio != null) {
            Optional<List<Apartamento>> aptos = repository.findByCondominio(condominio);
            return (aptos != null && aptos.get().size() > 0) ? aptos.get() : Collections.emptyList();
        }
        return Collections.emptyList();
    }

    public boolean isSindico(long usuario_id,long condominio_id){
        Usuario usuario = usuarioService.getUsuarioById(usuario_id);
        Optional<List<Apartamento>> aptos = listAptosByUsuario(usuario);
        if(aptos.get().size() >0){
            Optional<Apartamento> apto = aptos.get().stream().filter(e -> e.getCondominio().getId() == condominio_id).findFirst();
            if(apto.isPresent()) return apto.get().isSindico();
        }
        return false;
    }

    public Optional<List<Apartamento>> listAptosByUsuario(Usuario usuario) {
        return repository.findByUsuario(usuario);
    }
}
