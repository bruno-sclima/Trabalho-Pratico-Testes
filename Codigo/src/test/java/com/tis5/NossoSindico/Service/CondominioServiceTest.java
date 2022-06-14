package com.tis5.NossoSindico.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tis5.NossoSindico.domain.Condominio;
import com.tis5.NossoSindico.repository.CondominioRepository;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CondominioService.class})
@ExtendWith(SpringExtension.class)
class CondominioServiceTest {
    @MockBean
    private CondominioRepository condominioRepository;

    @Autowired
    private CondominioService condominioService;

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
        when(this.condominioRepository.save((Condominio) any())).thenReturn(condominio);

        Condominio condominio1 = new Condominio();
        condominio1.setBairro("Bairro");
        condominio1.setCep("Cep");
        condominio1.setCidade("Cidade");
        condominio1.setCode("Code");
        condominio1.setId(123L);
        condominio1.setNome("Nome");
        condominio1.setNumero(10);
        condominio1.setRua("Rua");
        //when
        Condominio actual =  this.condominioService.create(condominio1);
        //then
        assertSame(condominio,actual);
        verify(this.condominioRepository).save((Condominio) any());
    }

    @Test
    void listCondominiosTest() {
        when(this.condominioRepository.findAll()).thenReturn(new ArrayList<>());
        assertNull(this.condominioService.listCondominios());
        verify(this.condominioRepository).findAll();
    }

    @Test
    void listCondominiosTest2() {
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

        ArrayList<Condominio> condominioList = new ArrayList<>();
        condominioList.add(condominio);
        when(this.condominioRepository.findAll()).thenReturn(condominioList);
        //when
        List<Condominio> actual = this.condominioService.listCondominios();
        //then
        assertSame(condominioList, actual);
        assertEquals(1, actual.size());
        verify(this.condominioRepository).findAll();
    }


    @Test
    void getCondominioByIdTest() {
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
        Optional<Condominio> ofResult = Optional.of(condominio);
        when(this.condominioRepository.findById((Long) any())).thenReturn(ofResult);
        //when
        Optional<Condominio> actual = this.condominioService.getCondominioById(123L);
        //then
        assertSame(ofResult, actual);
        assertTrue(actual.isPresent());
        verify(this.condominioRepository).findById((Long) any());
    }


    @Test
    void getCondominioByNomeTest() {
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
        Optional<Condominio> ofResult = Optional.of(condominio);
        when(this.condominioRepository.findByNome((String) any())).thenReturn(ofResult);
        //when
        Condominio actual = this.condominioService.getCondominioByNome("Nome");
        //then
        assertSame(condominio,actual );
        verify(this.condominioRepository).findByNome((String) any());
    }


    @Test
    void getCondominioByNomeTest2() {
        when(this.condominioRepository.findByNome((String) any())).thenReturn(Optional.empty());
        assertNull(this.condominioService.getCondominioByNome("Nome"));
        verify(this.condominioRepository).findByNome((String) any());
    }


    @Test
    void deleteCondominioByIdTest() {
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
        Optional<Condominio> ofResult = Optional.of(condominio);
        doNothing().when(this.condominioRepository).deleteById((Long) any());
        when(this.condominioRepository.findById((Long) any())).thenReturn(ofResult);
        //when
        this.condominioService.deleteCondominioById(123L);
        //then
        verify(this.condominioRepository).findById((Long) any());
        verify(this.condominioRepository).deleteById((Long) any());
    }

    @Test
    void updateTest() {
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
        Optional<Condominio> ofResult = Optional.of(condominio);

        Condominio condominio1 = new Condominio();
        condominio1.setBairro("Bairro");
        condominio1.setCep("Cep");
        condominio1.setCidade("Cidade");
        condominio1.setCode("Code");
        condominio1.setId(123L);
        condominio1.setNome("Nome");
        condominio1.setNumero(10);
        condominio1.setRua("Rua");
        when(this.condominioRepository.save((Condominio) any())).thenReturn(condominio1);
        when(this.condominioRepository.findById((Long) any())).thenReturn(ofResult);

        Condominio condominio2 = new Condominio();
        condominio2.setBairro("Bairro");
        condominio2.setCep("Cep");
        condominio2.setCidade("Cidade");
        condominio2.setCode("Code");
        condominio2.setId(123L);
        condominio2.setNome("Nome");
        condominio2.setNumero(10);
        condominio2.setRua("Rua");
        //then
        assertSame(condominio1, this.condominioService.update(condominio2));
        verify(this.condominioRepository).save((Condominio) any());
        verify(this.condominioRepository).findById((Long) any());
    }


    @Test
    void updateTest2() {
        Condominio condominio = new Condominio();
        condominio.setBairro("Bairro");
        condominio.setCep("Cep");
        condominio.setCidade("Cidade");
        condominio.setCode("Code");
        condominio.setId(123L);
        condominio.setNome("Nome");
        condominio.setNumero(10);
        condominio.setRua("Rua");
        when(this.condominioRepository.save((Condominio) any())).thenReturn(condominio);
        when(this.condominioRepository.findById((Long) any())).thenReturn(Optional.empty());

        Condominio condominio1 = new Condominio();
        condominio1.setBairro("Bairro");
        condominio1.setCep("Cep");
        condominio1.setCidade("Cidade");
        condominio1.setCode("Code");
        condominio1.setId(123L);
        condominio1.setNome("Nome");
        condominio1.setNumero(10);
        condominio1.setRua("Rua");
        assertNull(this.condominioService.update(condominio1));
        verify(this.condominioRepository).findById((Long) any());
    }

    @Test
    void enterCondominioTest() {
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
        Optional<Condominio> ofResult = Optional.of(condominio);
        when(this.condominioRepository.findByCode((String) any())).thenReturn(ofResult);
        //when
        Condominio actual =  this.condominioService.enterCondominio("Acesso");
        //then
        assertSame(condominio,actual);
        verify(this.condominioRepository).findByCode((String) any());
    }


    @Test
    void enterCondominioTest2() {
        when(this.condominioRepository.findByCode((String) any())).thenReturn(Optional.empty());
        assertNull(this.condominioService.enterCondominio("Acesso"));
        verify(this.condominioRepository).findByCode((String) any());
    }
}

