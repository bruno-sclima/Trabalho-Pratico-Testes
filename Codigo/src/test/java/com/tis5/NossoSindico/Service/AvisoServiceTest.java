package com.tis5.NossoSindico.Service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tis5.NossoSindico.domain.Aviso;
import com.tis5.NossoSindico.domain.Condominio;
import com.tis5.NossoSindico.repository.AvisoRepository;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AvisoService.class})
@ExtendWith(SpringExtension.class)
class AvisoServiceTest {
    @MockBean
    private AvisoRepository avisoRepository;

    @Autowired
    private AvisoService avisoService;

    @MockBean
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

        Aviso aviso = new Aviso();
        aviso.setCondominio(condominio);
        aviso.setConteudo("Conteudo");
        aviso.setId(123L);
        aviso.setTitulo("Titulo");
        when(this.avisoRepository.save((Aviso) any())).thenReturn(aviso);

        Condominio condominio1 = new Condominio();
        condominio1.setBairro("Bairro");
        condominio1.setCep("Cep");
        condominio1.setCidade("Cidade");
        condominio1.setCode("Code");
        condominio1.setId(123L);
        condominio1.setNome("Nome");
        condominio1.setNumero(10);
        condominio1.setRua("Rua");

        Aviso aviso1 = new Aviso();
        aviso1.setCondominio(condominio1);
        aviso1.setConteudo("Conteudo");
        aviso1.setId(123L);
        aviso1.setTitulo("Titulo");
        //when
        Aviso avisoRes = this.avisoService.create(aviso1);
        //then
        assertSame(aviso, avisoRes);
        verify(this.avisoRepository).save((Aviso) any());
    }

    @Test
    void deleteAvisoByIdTest() {
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

        Aviso aviso = new Aviso();
        aviso.setCondominio(condominio);
        aviso.setConteudo("Conteudo");
        aviso.setId(123L);
        aviso.setTitulo("Titulo");
        Optional<Aviso> ofResult = Optional.of(aviso);
        doNothing().when(this.avisoRepository).deleteById((Long) any());
        when(this.avisoRepository.findById((Long) any())).thenReturn(ofResult);
        //when
        this.avisoService.deleteAvisoById(123L);
        //then
        verify(this.avisoRepository).findById((Long) any());
        verify(this.avisoRepository).deleteById((Long) any());
    }

    @Test
    void edleteAvisoByIdTest2() {
        //given
        doNothing().when(this.avisoRepository).deleteById((Long) any());
        when(this.avisoRepository.findById((Long) any())).thenReturn(Optional.empty());
        //when
        this.avisoService.deleteAvisoById(123L);
        //then
        verify(this.avisoRepository).findById((Long) any());
    }

    @Test
    void listAvisosDeCondominioTest() {
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
        when(this.condominioService.getCondominioById(anyLong())).thenReturn(ofResult);
        Optional<List<Aviso>> ofResult1 = Optional.of(new ArrayList<>());
        when(this.avisoRepository.findByCondominio((Condominio) any())).thenReturn(ofResult1);
        //when
        Optional<List<Aviso>> actualListAvisosDeCondominioResult = this.avisoService.listAvisosDeCondominio(1L);
        //then
        assertSame(ofResult1, actualListAvisosDeCondominioResult);
        assertTrue(actualListAvisosDeCondominioResult.isPresent());
        verify(this.condominioService).getCondominioById(anyLong());
        verify(this.avisoRepository).findByCondominio((Condominio) any());
    }

    @Test
    void listAvisosDeCondominioTest2() {
        when(this.condominioService.getCondominioById(anyLong())).thenReturn(Optional.empty());
        when(this.avisoRepository.findByCondominio((Condominio) any())).thenReturn(Optional.of(new ArrayList<>()));
        assertTrue(this.avisoService.listAvisosDeCondominio(1L).isPresent());
        verify(this.condominioService).getCondominioById(anyLong());
    }
}

