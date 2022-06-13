package com.tis5.NossoSindico.Service;

import com.tis5.NossoSindico.domain.Condominio;
import com.tis5.NossoSindico.repository.CondominioRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class CondominioService {
    @Autowired
    private CondominioRepository condominioRepository;

    public Condominio create(Condominio condominio){
        return condominioRepository.save(condominio);
    }

    public List<Condominio> listCondominios(){
        List<Condominio> condominios = condominioRepository.findAll();
        return (condominios!=null && condominios.size() > 0 ? condominios : null);
    }

    public Optional<Condominio> getCondominioById(long id){
        Optional<Condominio> condominio = condominioRepository.findById(id);

        return condominio;
    }

    public Condominio getCondominioByNome(String nome){
        Optional<Condominio> condominio = condominioRepository.findByNome(nome);
        if(condominio.isPresent()) {
            return condominio.get();

        } else return null;
    }

    public void deleteCondominioById(long id){
        Optional<Condominio> condominio = condominioRepository.findById(id);
        if(condominio.isPresent()) {
            condominioRepository.deleteById(id);
        }
    }

    public Condominio update(Condominio condominio){
        Optional<Condominio> optional = condominioRepository.findById(condominio.getId());
        if(optional.isPresent()){
            Condominio condominioAtt = optional.get();
            condominioAtt.setBairro(condominio.getBairro());
            condominioAtt.setCep(condominio.getCep());
            condominioAtt.setCidade(condominio.getCidade());
            condominioAtt.setNome(condominio.getNome());
            condominioAtt.setNumero(condominio.getNumero());
            condominioAtt.setCode(condominioAtt.getCode());
            condominioAtt.setRua(condominio.getRua());
            condominioAtt = condominioRepository.save(condominioAtt);
            return condominioAtt;
        }
        return null;
    }
    public Condominio enterCondominio(String acesso){
        Optional<Condominio> cond = condominioRepository.findByCode(acesso);
        return cond.isPresent() ? cond.get() : null;

    }



}