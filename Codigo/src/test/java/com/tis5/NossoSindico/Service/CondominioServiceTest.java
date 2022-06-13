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

    /**
     * Method under test: {@link CondominioService#create(Condominio)}
     */
    @Test
    void testCreate() {
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
        assertSame(condominio, this.condominioService.create(condominio1));
        verify(this.condominioRepository).save((Condominio) any());
    }

    /**
     * Method under test: {@link CondominioService#listCondominios()}
     */
    @Test
    void testListCondominios() {
        when(this.condominioRepository.findAll()).thenReturn(new ArrayList<>());
        assertNull(this.condominioService.listCondominios());
        verify(this.condominioRepository).findAll();
    }

    /**
     * Method under test: {@link CondominioService#listCondominios()}
     */
    @Test
    void testListCondominios2() {
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
        List<Condominio> actualListCondominiosResult = this.condominioService.listCondominios();
        assertSame(condominioList, actualListCondominiosResult);
        assertEquals(1, actualListCondominiosResult.size());
        verify(this.condominioRepository).findAll();
    }

    /**
     * Method under test: {@link CondominioService#getCondominioById(long)}
     */
    @Test
    void testGetCondominioById() {
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
        Optional<Condominio> actualCondominioById = this.condominioService.getCondominioById(123L);
        assertSame(ofResult, actualCondominioById);
        assertTrue(actualCondominioById.isPresent());
        verify(this.condominioRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link CondominioService#getCondominioByNome(String)}
     */
    @Test
    void testGetCondominioByNome() {
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
        assertSame(condominio, this.condominioService.getCondominioByNome("Nome"));
        verify(this.condominioRepository).findByNome((String) any());
    }

    /**
     * Method under test: {@link CondominioService#getCondominioByNome(String)}
     */
    @Test
    void testGetCondominioByNome2() {
        when(this.condominioRepository.findByNome((String) any())).thenReturn(Optional.empty());
        assertNull(this.condominioService.getCondominioByNome("Nome"));
        verify(this.condominioRepository).findByNome((String) any());
    }

    /**
     * Method under test: {@link CondominioService#deleteCondominioById(long)}
     */
    @Test
    void testDeleteCondominioById() {
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
        this.condominioService.deleteCondominioById(123L);
        verify(this.condominioRepository).findById((Long) any());
        verify(this.condominioRepository).deleteById((Long) any());
    }

    /**
     * Method under test: {@link CondominioService#deleteCondominioById(long)}
     */
    @Test
    void testDeleteCondominioById2() {
        doNothing().when(this.condominioRepository).deleteById((Long) any());
        when(this.condominioRepository.findById((Long) any())).thenReturn(Optional.empty());
        this.condominioService.deleteCondominioById(123L);
        verify(this.condominioRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link CondominioService#update(Condominio)}
     */
    @Test
    void testUpdate() {
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
        assertSame(condominio1, this.condominioService.update(condominio2));
        verify(this.condominioRepository).save((Condominio) any());
        verify(this.condominioRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link CondominioService#update(Condominio)}
     */
    @Test
    void testUpdate2() {
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

    /**
     * Method under test: {@link CondominioService#enterCondominio(String)}
     */
    @Test
    void testEnterCondominio() {
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
        assertSame(condominio, this.condominioService.enterCondominio("Acesso"));
        verify(this.condominioRepository).findByCode((String) any());
    }

    /**
     * Method under test: {@link CondominioService#enterCondominio(String)}
     */
    @Test
    void testEnterCondominio2() {
        when(this.condominioRepository.findByCode((String) any())).thenReturn(Optional.empty());
        assertNull(this.condominioService.enterCondominio("Acesso"));
        verify(this.condominioRepository).findByCode((String) any());
    }
}

