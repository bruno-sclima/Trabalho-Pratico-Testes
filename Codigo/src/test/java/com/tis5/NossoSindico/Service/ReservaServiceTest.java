package com.tis5.NossoSindico.Service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

import com.tis5.NossoSindico.domain.Apartamento;
import com.tis5.NossoSindico.domain.Condominio;
import com.tis5.NossoSindico.domain.Espaco;
import com.tis5.NossoSindico.domain.Reserva;
import com.tis5.NossoSindico.domain.ReservaResource;
import com.tis5.NossoSindico.domain.Usuario;
import com.tis5.NossoSindico.repository.ApartamentoRepository;
import com.tis5.NossoSindico.repository.EspacoRepository;
import com.tis5.NossoSindico.repository.ReservaRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ReservaService.class})
@ExtendWith(SpringExtension.class)
class ReservaServiceTest {
    @MockBean
    private ApartamentoRepository apartamentoRepository;

    @MockBean
    private CondominioService condominioService;

    @MockBean
    private EspacoRepository espacoRepository;

    @MockBean
    private ReservaRepository reservaRepository;

    @Autowired
    private ReservaService reservaService;

    @Test
    void createTest() {
        //given
        Espaco espaco = new Espaco();
        espaco.setCapacidadeMax(1);
        espaco.setDescricao("Descricao");
        espaco.setId(123L);
        espaco.setId_condominio(1);
        espaco.setNome("Nome");
        Optional<Espaco> ofResult = Optional.of(espaco);
        when(this.espacoRepository.findById((Long) any())).thenReturn(ofResult);

        Condominio condominio = new Condominio();
        condominio.setBairro("Bairro");
        condominio.setCep("Cep");
        condominio.setCidade("Cidade");
        condominio.setCode("Code");
        condominio.setId(123L);
        condominio.setNome("Nome");
        condominio.setNumero(10);
        condominio.setRua("Rua");
        Optional<Condominio> ofResult1 = Optional.of(condominio);
        when(this.condominioService.getCondominioById(anyLong())).thenReturn(ofResult1);
        when(this.apartamentoRepository.findByCondominio((Condominio) any())).thenReturn(Optional.of(new ArrayList<>()));
        //when
        Optional<Reserva> actual = this.reservaService.create(new ReservaResource());
        assertFalse(actual.isPresent());
        verify(this.espacoRepository).findById((Long) any());
        verify(this.condominioService).getCondominioById(anyLong());
        verify(this.apartamentoRepository).findByCondominio((Condominio) any());
    }

    @Test
    void createTest2() {
        //given
        Optional<Espaco> emptyResult = Optional.empty();
        when(this.espacoRepository.findById((Long) any())).thenReturn(emptyResult);

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
        //when
        when(this.apartamentoRepository.findByCondominio((Condominio) any())).thenReturn(Optional.of(new ArrayList<>()));
        Optional<Reserva> actualCreateResult = this.reservaService.create(new ReservaResource());
        //when
        assertSame(emptyResult, actualCreateResult);
        assertFalse(actualCreateResult.isPresent());
        verify(this.espacoRepository).findById((Long) any());
        verify(this.condominioService).getCondominioById(anyLong());
    }

    @Test
    void createTest3() {
        //given
        Espaco espaco = new Espaco();
        espaco.setCapacidadeMax(1);
        espaco.setDescricao("Descricao");
        espaco.setId(123L);
        espaco.setId_condominio(1);
        espaco.setNome("Nome");
        Optional<Espaco> ofResult = Optional.of(espaco);
        when(this.espacoRepository.findById((Long) any())).thenReturn(ofResult);
        Optional<Condominio> emptyResult = Optional.empty();
        when(this.condominioService.getCondominioById(anyLong())).thenReturn(emptyResult);
        when(this.apartamentoRepository.findByCondominio((Condominio) any())).thenReturn(Optional.of(new ArrayList<>()));
        //when
        Optional<Reserva> actualCreateResult = this.reservaService.create(new ReservaResource());
        //then
        assertSame(emptyResult, actualCreateResult);
        assertFalse(actualCreateResult.isPresent());
        verify(this.espacoRepository).findById((Long) any());
        verify(this.condominioService).getCondominioById(anyLong());
    }

    @Test
    void listByCondominioTest() {
        //given
        when(this.reservaRepository.findAll()).thenReturn(new ArrayList<>());

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
        List<Reserva> reservaList = this.reservaService.listByCondominio(condominio);
        //then
        assertTrue(reservaList.isEmpty());
        verify(this.reservaRepository).findAll();
    }


    void listByCondominioTest2() {
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

        Usuario usuario = new Usuario();
        usuario.setEmail("ex@gmail.com");
        usuario.setId(123L);
        usuario.setNome("Nome");
        usuario.setSenha("Senha");
        usuario.setSobrenome("Sobrenome");

        Apartamento apartamento = new Apartamento();
        apartamento.setBloco("Bloco");
        apartamento.setCondominio(condominio);
        apartamento.setId(123L);
        apartamento.setNumero(10);
        apartamento.setSindico(true);
        apartamento.setUsuario(usuario);

        Espaco espaco = new Espaco();
        espaco.setCapacidadeMax(1);
        espaco.setDescricao("Descricao");
        espaco.setId(123L);
        espaco.setId_condominio(1);
        espaco.setNome("Nome");

        Reserva reserva = new Reserva();
        reserva.setApto(apartamento);
        reserva.setData(LocalDate.ofEpochDay(1L));
        reserva.setDescricao("Descricao");
        reserva.setId(123L);
        reserva.setLugar(espaco);

        ArrayList<Reserva> reservaList = new ArrayList<>();
        reservaList.add(reserva);
        when(this.reservaRepository.findAll()).thenReturn(reservaList);

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
        List<Reserva> reservas =  this.reservaService.listByCondominio(condominio1);
        //then
        assertEquals(1,reservas.size());
        verify(this.reservaRepository).findAll();
    }

    @Test
    void listByCondominioTest3() {
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

        Usuario usuario = new Usuario();
        usuario.setEmail("jane.doe@example.org");
        usuario.setId(123L);
        usuario.setNome("Nome");
        usuario.setSenha("Senha");
        usuario.setSobrenome("Sobrenome");

        Apartamento apartamento = new Apartamento();
        apartamento.setBloco("Bloco");
        apartamento.setCondominio(condominio);
        apartamento.setId(123L);
        apartamento.setNumero(10);
        apartamento.setSindico(true);
        apartamento.setUsuario(usuario);

        Espaco espaco = new Espaco();
        espaco.setCapacidadeMax(1);
        espaco.setDescricao("Descricao");
        espaco.setId(123L);
        espaco.setId_condominio(1);
        espaco.setNome("Nome");

        Reserva reserva = new Reserva();
        reserva.setApto(apartamento);
        reserva.setData(LocalDate.ofEpochDay(1L));
        reserva.setDescricao("Descricao");
        reserva.setId(123L);
        reserva.setLugar(espaco);

        Condominio condominio1 = new Condominio();
        condominio1.setBairro("Bairro");
        condominio1.setCep("Cep");
        condominio1.setCidade("Cidade");
        condominio1.setCode("Code");
        condominio1.setId(123L);
        condominio1.setNome("Nome");
        condominio1.setNumero(10);
        condominio1.setRua("Rua");

        Usuario usuario1 = new Usuario();
        usuario1.setEmail("ex@gmail.com");
        usuario1.setId(123L);
        usuario1.setNome("Nome");
        usuario1.setSenha("Senha");
        usuario1.setSobrenome("Sobrenome");

        Apartamento apartamento1 = new Apartamento();
        apartamento1.setBloco("Bloco");
        apartamento1.setCondominio(condominio1);
        apartamento1.setId(123L);
        apartamento1.setNumero(10);
        apartamento1.setSindico(true);
        apartamento1.setUsuario(usuario1);

        Espaco espaco1 = new Espaco();
        espaco1.setCapacidadeMax(1);
        espaco1.setDescricao("Descricao");
        espaco1.setId(123L);
        espaco1.setId_condominio(1);
        espaco1.setNome("Nome");

        Reserva reserva1 = new Reserva();
        reserva1.setApto(apartamento1);
        reserva1.setData(LocalDate.ofEpochDay(123L));
        reserva1.setDescricao("Descricao");
        reserva1.setId(123L);
        reserva1.setLugar(espaco1);

        ArrayList<Reserva> reservaList = new ArrayList<>();
        reservaList.add(reserva1);
        reservaList.add(reserva);
        when(this.reservaRepository.findAll()).thenReturn(reservaList);

        Condominio condominio2 = new Condominio();
        condominio2.setBairro("Bairro");
        condominio2.setCep("Cep");
        condominio2.setCidade("Cidade");
        condominio2.setCode("Code");
        condominio2.setId(123L);
        condominio2.setNome("Nome");
        condominio2.setNumero(10);
        condominio2.setRua("Rua");
        //when
        List<Reserva> reservas = this.reservaService.listByCondominio(condominio2);
        //then
        assertEquals(2, reservas.size());
        verify(this.reservaRepository).findAll();
    }

}

