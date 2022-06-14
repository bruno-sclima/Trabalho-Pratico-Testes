package com.tis5.NossoSindico.Service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tis5.NossoSindico.domain.Condominio;
import com.tis5.NossoSindico.domain.Despessa;
import com.tis5.NossoSindico.repository.DespessaRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {DespessaService.class})
@ExtendWith(SpringExtension.class)
class DespessaServiceTest {
    @MockBean
    private CondominioService condominioService;

    @MockBean
    private DespessaRepository despessaRepository;

    @Autowired
    private DespessaService despessaService;


    @Test
    void createTest() {
        //given
        Condominio condominio = new Condominio();
        condominio.setBairro("Bairro");
        condominio.setCep("Cep");
        condominio.setCidade("Cidade");
        condominio.setCode("Code");
        condominio.setId(123L);
        condominio.setNome("Nome");
        condominio.setNumero(10);
        condominio.setRua("Rua");

        Despessa despessa = new Despessa();
        despessa.setCondominio(condominio);
        despessa.setData_referente(LocalDate.ofEpochDay(1L));
        despessa.setDescricao("Descricao");
        despessa.setId(123L);
        despessa.setTitulo("Titulo");
        despessa.setValor(10.0d);
        when(this.despessaRepository.save((Despessa) any())).thenReturn(despessa);

        Condominio condominio1 = new Condominio();
        condominio1.setBairro("Bairro");
        condominio1.setCep("Cep");
        condominio1.setCidade("Cidade");
        condominio1.setCode("Code");
        condominio1.setId(123L);
        condominio1.setNome("Nome");
        condominio1.setNumero(10);
        condominio1.setRua("Rua");

        Despessa despessa1 = new Despessa();
        despessa1.setCondominio(condominio1);
        despessa1.setData_referente(LocalDate.ofEpochDay(1L));
        despessa1.setDescricao("Descricao");
        despessa1.setId(123L);
        despessa1.setTitulo("Titulo");
        despessa1.setValor(10.0d);
        //when
        Despessa actual =  this.despessaService.create(despessa1);
        //then
        assertSame(despessa,actual);
        verify(this.despessaRepository).save((Despessa) any());
    }

    @Test
    void listDespessasCondominioTest() {
        //given
        Optional<List<Despessa>> ofResult = Optional.of(new ArrayList<>());
        when(this.despessaRepository.findByCondominio((Condominio) any())).thenReturn(ofResult);

        Condominio condominio = new Condominio();
        condominio.setBairro("Bairro");
        condominio.setCep("Cep");
        condominio.setCidade("Cidade");
        condominio.setCode("Code");
        condominio.setId(123L);
        condominio.setNome("Nome");
        condominio.setNumero(10);
        condominio.setRua("Rua");
        //when
        Optional<List<Despessa>> actual = this.despessaService
                .listDespessasCondominio(condominio);
        //then
        assertSame(ofResult, actual);
        assertTrue(actual.isPresent());
        verify(this.despessaRepository).findByCondominio((Condominio) any());
    }
}

