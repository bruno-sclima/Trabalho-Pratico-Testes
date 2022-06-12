package com.tis5.NossoSindico.Service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tis5.NossoSindico.domain.Condominio;
import com.tis5.NossoSindico.domain.Espaco;
import com.tis5.NossoSindico.repository.EspacoRepository;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {EspacoService.class})
@ExtendWith(SpringExtension.class)
class EspacoServiceTest {
    @MockBean
    private EspacoRepository espacoRepository;

    @Autowired
    private EspacoService espacoService;

    /**
     * Method under test: {@link EspacoService#create(Espaco)}
     */
    @Test
    void testCreate() {
        Espaco espaco = new Espaco();
        espaco.setCapacidadeMax(1);
        espaco.setDescricao("Descricao");
        espaco.setId(123L);
        espaco.setId_condominio(1);
        espaco.setNome("Nome");
        when(this.espacoRepository.save((Espaco) any())).thenReturn(espaco);

        Espaco espaco1 = new Espaco();
        espaco1.setCapacidadeMax(1);
        espaco1.setDescricao("Descricao");
        espaco1.setId(123L);
        espaco1.setId_condominio(1);
        espaco1.setNome("Nome");
        assertSame(espaco, this.espacoService.create(espaco1));
        verify(this.espacoRepository).save((Espaco) any());
    }

    /**
     * Method under test: {@link EspacoService#listByCondominio(Condominio)}
     */
    @Test
    void testListByCondominio() {
        when(this.espacoRepository.findAll()).thenReturn(new ArrayList<>());

        Condominio condominio = new Condominio();
        condominio.setBairro("Bairro");
        condominio.setCep("Cep");
        condominio.setCidade("Cidade");
        condominio.setCode("Code");
        condominio.setId(123L);
        condominio.setNome("Nome");
        condominio.setNumero(10);
        condominio.setRua("Rua");
        assertTrue(this.espacoService.listByCondominio(condominio).isPresent());
        verify(this.espacoRepository).findAll();
    }

    /**
     * Method under test: {@link EspacoService#listByCondominio(Condominio)}
     */
    @Test
    void testListByCondominio2() {
        Espaco espaco = new Espaco();
        espaco.setCapacidadeMax(1);
        espaco.setDescricao("Descricao");
        espaco.setId(123L);
        espaco.setId_condominio(1);
        espaco.setNome("Nome");

        ArrayList<Espaco> espacoList = new ArrayList<>();
        espacoList.add(espaco);
        when(this.espacoRepository.findAll()).thenReturn(espacoList);

        Condominio condominio = new Condominio();
        condominio.setBairro("Bairro");
        condominio.setCep("Cep");
        condominio.setCidade("Cidade");
        condominio.setCode("Code");
        condominio.setId(123L);
        condominio.setNome("Nome");
        condominio.setNumero(10);
        condominio.setRua("Rua");
        assertTrue(this.espacoService.listByCondominio(condominio).isPresent());
        verify(this.espacoRepository).findAll();
    }

    /**
     * Method under test: {@link EspacoService#listByCondominio(Condominio)}
     */
    @Test
    void testListByCondominio3() {
        Espaco espaco = new Espaco();
        espaco.setCapacidadeMax(1);
        espaco.setDescricao("Descricao");
        espaco.setId(123L);
        espaco.setId_condominio(1);
        espaco.setNome("Nome");

        Espaco espaco1 = new Espaco();
        espaco1.setCapacidadeMax(1);
        espaco1.setDescricao("Descricao");
        espaco1.setId(123L);
        espaco1.setId_condominio(1);
        espaco1.setNome("Nome");

        ArrayList<Espaco> espacoList = new ArrayList<>();
        espacoList.add(espaco1);
        espacoList.add(espaco);
        when(this.espacoRepository.findAll()).thenReturn(espacoList);

        Condominio condominio = new Condominio();
        condominio.setBairro("Bairro");
        condominio.setCep("Cep");
        condominio.setCidade("Cidade");
        condominio.setCode("Code");
        condominio.setId(123L);
        condominio.setNome("Nome");
        condominio.setNumero(10);
        condominio.setRua("Rua");
        assertTrue(this.espacoService.listByCondominio(condominio).isPresent());
        verify(this.espacoRepository).findAll();
    }

    /**
     * Method under test: {@link EspacoService#listByCondominio(Condominio)}
     */
    @Test
    void testListByCondominio4() {
        Espaco espaco = new Espaco();
        espaco.setCapacidadeMax(1);
        espaco.setDescricao("Descricao");
        espaco.setId(123L);
        espaco.setId_condominio(1);
        espaco.setNome("Nome");

        ArrayList<Espaco> espacoList = new ArrayList<>();
        espacoList.add(espaco);
        when(this.espacoRepository.findAll()).thenReturn(espacoList);

        Condominio condominio = new Condominio();
        condominio.setBairro("Bairro");
        condominio.setCep("Cep");
        condominio.setCidade("Cidade");
        condominio.setCode("Code");
        condominio.setId(1L);
        condominio.setNome("Nome");
        condominio.setNumero(10);
        condominio.setRua("Rua");
        assertTrue(this.espacoService.listByCondominio(condominio).isPresent());
        verify(this.espacoRepository).findAll();
    }
}

