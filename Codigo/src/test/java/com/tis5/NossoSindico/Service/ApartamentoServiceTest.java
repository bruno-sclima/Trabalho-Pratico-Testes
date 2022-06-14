package com.tis5.NossoSindico.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tis5.NossoSindico.domain.Apartamento;
import com.tis5.NossoSindico.domain.Condominio;
import com.tis5.NossoSindico.domain.Usuario;
import com.tis5.NossoSindico.repository.ApartamentoRepository;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ApartamentoService.class})
@ExtendWith(SpringExtension.class)
class ApartamentoServiceTest {
    @MockBean
    private ApartamentoRepository apartamentoRepository;

    @Autowired
    private ApartamentoService apartamentoService;

    @MockBean
    private CondominioService condominioService;

    @MockBean
    private UsuarioService usuarioService;

    @Test
    void createTest() {
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
        usuario.setEmail("exemplo@gmail.com");
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
        when(this.apartamentoRepository.save((Apartamento) any())).thenReturn(apartamento);

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
        usuario1.setEmail("exemplo@gmail.com");
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
        //when
        assertSame(apartamento, this.apartamentoService.create(apartamento1));
        //then
        verify(this.apartamentoRepository).save((Apartamento) any());
    }

    @Test
    void getAptoByCondominioENumeroTest() {
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
        when(this.apartamentoRepository.findByCondominio((Condominio) any())).thenReturn(Optional.of(new ArrayList<>()));
        //then
        assertNull(this.apartamentoService.getAptoByCondominioENumero(1L, 10));
        verify(this.condominioService).getCondominioById(anyLong());
        verify(this.apartamentoRepository).findByCondominio((Condominio) any());
    }

    @Test
    void getAptoByCondominioENumeroTest2() {
        //given
        when(this.condominioService.getCondominioById(anyLong())).thenReturn(Optional.empty());
        when(this.apartamentoRepository.findByCondominio((Condominio) any())).thenReturn(Optional.of(new ArrayList<>()));
        //then
        assertNull(this.apartamentoService.getAptoByCondominioENumero(1L, 10));
        verify(this.condominioService).getCondominioById(anyLong());
    }

    @Test
    void getAptoByCondominioENumeroTest3() {
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

        Condominio condominio1 = new Condominio();
        condominio1.setBairro("Bairro");
        condominio1.setCep("Cep");
        condominio1.setCidade("Cidade");
        condominio1.setCode("Code");
        condominio1.setId(123L);
        condominio1.setNome("Nome");
        condominio1.setNumero(10);
        condominio1.setRua("Rua");

        Usuario usuario = new Usuario();
        usuario.setEmail("jane.doe@example.org");
        usuario.setId(123L);
        usuario.setNome("Nome");
        usuario.setSenha("Senha");
        usuario.setSobrenome("Sobrenome");

        Apartamento apartamento = new Apartamento();
        apartamento.setBloco("Bloco");
        apartamento.setCondominio(condominio1);
        apartamento.setId(123L);
        apartamento.setNumero(10);
        apartamento.setSindico(true);
        apartamento.setUsuario(usuario);

        ArrayList<Apartamento> apartamentoList = new ArrayList<>();
        apartamentoList.add(apartamento);
        Optional<List<Apartamento>> ofResult1 = Optional.of(apartamentoList);
        when(this.apartamentoRepository.findByCondominio((Condominio) any())).thenReturn(ofResult1);
        //when
        Apartamento actual = this.apartamentoService.getAptoByCondominioENumero(1L, 10);
        //then
        assertSame(apartamento, actual);
        verify(this.condominioService).getCondominioById(anyLong());
        verify(this.apartamentoRepository).findByCondominio((Condominio) any());
    }

    @Test
    void deleteAptoByIdTest() {
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
        Optional<Apartamento> ofResult = Optional.of(apartamento);
        doNothing().when(this.apartamentoRepository).deleteById((Long) any());
        when(this.apartamentoRepository.findById((Long) any())).thenReturn(ofResult);
        //when
        this.apartamentoService.deleteAptoById(123L);
        //then
        verify(this.apartamentoRepository).findById((Long) any());
        verify(this.apartamentoRepository).deleteById((Long) any());
    }

    @Test
    void deleteAptoByIdtest2() {
        //given
        doNothing().when(this.apartamentoRepository).deleteById((Long) any());
        when(this.apartamentoRepository.findById((Long) any())).thenReturn(Optional.empty());
        //when
        this.apartamentoService.deleteAptoById(123L);
        //then
        verify(this.apartamentoRepository).findById((Long) any());
    }

    @Test
    void listApartamentosDeCondominioTest() {
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
        when(this.apartamentoRepository.findByCondominio((Condominio) any())).thenReturn(Optional.of(new ArrayList<>()));
        //when
        List<Apartamento> aptos = this.apartamentoService.listApartamentosDeCondominio(1L);
        //then
        assertTrue(aptos.isEmpty());
        verify(this.condominioService).getCondominioById(anyLong());
        verify(this.apartamentoRepository).findByCondominio((Condominio) any());
    }

    @Test
    void listApartamentosDeCondominioTest2() {
        //given
        when(this.condominioService.getCondominioById(anyLong())).thenReturn(Optional.empty());
        when(this.apartamentoRepository.findByCondominio((Condominio) any())).thenReturn(Optional.of(new ArrayList<>()));
        assertTrue(this.apartamentoService.listApartamentosDeCondominio(1L).isEmpty());
        verify(this.condominioService).getCondominioById(anyLong());
    }


    @Test
    void listApartamentosDeCondominioTest3() {
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

        Condominio condominio1 = new Condominio();
        condominio1.setBairro("Bairro");
        condominio1.setCep("Cep");
        condominio1.setCidade("Cidade");
        condominio1.setCode("Code");
        condominio1.setId(123L);
        condominio1.setNome("Nome");
        condominio1.setNumero(10);
        condominio1.setRua("Rua");

        Usuario usuario = new Usuario();
        usuario.setEmail("jane.doe@example.org");
        usuario.setId(123L);
        usuario.setNome("Nome");
        usuario.setSenha("Senha");
        usuario.setSobrenome("Sobrenome");

        Apartamento apartamento = new Apartamento();
        apartamento.setBloco("Bloco");
        apartamento.setCondominio(condominio1);
        apartamento.setId(123L);
        apartamento.setNumero(10);
        apartamento.setSindico(true);
        apartamento.setUsuario(usuario);

        ArrayList<Apartamento> apartamentoList = new ArrayList<>();
        apartamentoList.add(apartamento);
        Optional<List<Apartamento>> ofResult1 = Optional.of(apartamentoList);
        when(this.apartamentoRepository.findByCondominio((Condominio) any())).thenReturn(ofResult1);
        //when
        List<Apartamento> actual = this.apartamentoService
                .listApartamentosDeCondominio(1L);
        //then
        assertSame(apartamentoList, actual);
        assertEquals(1, actual.size());
        verify(this.condominioService).getCondominioById(anyLong());
        verify(this.apartamentoRepository).findByCondominio((Condominio) any());
    }


    @Test
    void listApartamentosDeCondominioTest4() {
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
        when(this.apartamentoRepository.findByCondominio((Condominio) any())).thenReturn(Optional.empty());
        //when
        List<Apartamento> aptos = this.apartamentoService.listApartamentosDeCondominio(1L);
        //then
        assertTrue(aptos.isEmpty());
        verify(this.condominioService).getCondominioById(anyLong());
        verify(this.apartamentoRepository).findByCondominio((Condominio) any());
    }

    @Test
    void isSindicoTest() {
        //given
        Usuario usuario = new Usuario();
        usuario.setEmail("test@gmail.com");
        usuario.setId(123L);
        usuario.setNome("Nome");
        usuario.setSenha("Senha");
        usuario.setSobrenome("Sobrenome");
        when(this.usuarioService.getUsuarioById(anyLong())).thenReturn(usuario);
        when(this.apartamentoRepository.findByUsuario((Usuario) any()))
                .thenReturn(Optional.of(new ArrayList<>()));
        //when
        boolean resp = this.apartamentoService.isSindico(1L, 1L);
        //then
        assertFalse(resp);
        verify(this.usuarioService).getUsuarioById(anyLong());
        verify(this.apartamentoRepository).findByUsuario((Usuario) any());
    }

    @Test
    void isSindicoTest2() {
        //given
        Usuario usuario = new Usuario();
        usuario.setEmail("teste@gmail.com");
        usuario.setId(123L);
        usuario.setNome("Nome");
        usuario.setSenha("Senha");
        usuario.setSobrenome("Sobrenome");
        when(this.usuarioService.getUsuarioById(anyLong())).thenReturn(usuario);

        Condominio condominio = new Condominio();
        condominio.setBairro("Bairro");
        condominio.setCep("Cep");
        condominio.setCidade("Cidade");
        condominio.setCode("Code");
        condominio.setId(123L);
        condominio.setNome("Nome");
        condominio.setNumero(10);
        condominio.setRua("Rua");

        Usuario usuario1 = new Usuario();
        usuario1.setEmail("jane.doe@example.org");
        usuario1.setId(123L);
        usuario1.setNome("Nome");
        usuario1.setSenha("Senha");
        usuario1.setSobrenome("Sobrenome");

        Apartamento apartamento = new Apartamento();
        apartamento.setBloco("Bloco");
        apartamento.setCondominio(condominio);
        apartamento.setId(123L);
        apartamento.setNumero(10);
        apartamento.setSindico(true);
        apartamento.setUsuario(usuario1);

        ArrayList<Apartamento> apartamentoList = new ArrayList<>();
        apartamentoList.add(apartamento);
        Optional<List<Apartamento>> ofResult = Optional.of(apartamentoList);
        when(this.apartamentoRepository.findByUsuario((Usuario) any())).thenReturn(ofResult);
        assertFalse(this.apartamentoService.isSindico(1L, 1L));
        verify(this.usuarioService).getUsuarioById(anyLong());
        verify(this.apartamentoRepository).findByUsuario((Usuario) any());
    }


    @Test
    void isSindicoTest3() {
        //given
        Usuario usuario = new Usuario();
        usuario.setEmail("teste@gmail.com");
        usuario.setId(123L);
        usuario.setNome("Nome");
        usuario.setSenha("Senha");
        usuario.setSobrenome("Sobrenome");
        when(this.usuarioService.getUsuarioById(anyLong())).thenReturn(usuario);

        Condominio condominio = new Condominio();
        condominio.setBairro("Bairro");
        condominio.setCep("Cep");
        condominio.setCidade("Cidade");
        condominio.setCode("Code");
        condominio.setId(123L);
        condominio.setNome("Nome");
        condominio.setNumero(10);
        condominio.setRua("Rua");

        Usuario usuario1 = new Usuario();
        usuario1.setEmail("teste@gmail.com");
        usuario1.setId(123L);
        usuario1.setNome("Nome");
        usuario1.setSenha("Senha");
        usuario1.setSobrenome("Sobrenome");

        Condominio condominio1 = new Condominio();
        condominio1.setBairro("Bairro");
        condominio1.setCep("Cep");
        condominio1.setCidade("Cidade");
        condominio1.setCode("Code");
        condominio1.setId(1L);
        condominio1.setNome("Nome");
        condominio1.setNumero(10);
        condominio1.setRua("Rua");
        Apartamento apartamento = mock(Apartamento.class);
        when(apartamento.isSindico()).thenReturn(true);
        when(apartamento.getCondominio()).thenReturn(condominio1);
        doNothing().when(apartamento).setBloco((String) any());
        doNothing().when(apartamento).setCondominio((Condominio) any());
        doNothing().when(apartamento).setId(anyLong());
        doNothing().when(apartamento).setNumero((Integer) any());
        doNothing().when(apartamento).setSindico(anyBoolean());
        doNothing().when(apartamento).setUsuario((Usuario) any());
        apartamento.setBloco("Bloco");
        apartamento.setCondominio(condominio);
        apartamento.setId(123L);
        apartamento.setNumero(10);
        apartamento.setSindico(true);
        apartamento.setUsuario(usuario1);

        ArrayList<Apartamento> apartamentoList = new ArrayList<>();
        apartamentoList.add(apartamento);
        Optional<List<Apartamento>> ofResult = Optional.of(apartamentoList);
        when(this.apartamentoRepository.findByUsuario((Usuario) any())).thenReturn(ofResult);
        assertTrue(this.apartamentoService.isSindico(1L, 1L));
        verify(this.usuarioService).getUsuarioById(anyLong());
        verify(this.apartamentoRepository).findByUsuario((Usuario) any());
        verify(apartamento).isSindico();
        verify(apartamento).getCondominio();
        verify(apartamento).setBloco((String) any());
        verify(apartamento).setCondominio((Condominio) any());
        verify(apartamento).setId(anyLong());
        verify(apartamento).setNumero((Integer) any());
        verify(apartamento).setSindico(anyBoolean());
        verify(apartamento).setUsuario((Usuario) any());
    }

    @Test
    void listAptosByUsuarioTest() {
        Optional<List<Apartamento>> ofResult = Optional.of(new ArrayList<>());
        when(this.apartamentoRepository.findByUsuario((Usuario) any())).thenReturn(ofResult);

        Usuario usuario = new Usuario();
        usuario.setEmail("teste@gmail.com");
        usuario.setId(123L);
        usuario.setNome("Nome");
        usuario.setSenha("Senha");
        usuario.setSobrenome("Sobrenome");
        Optional<List<Apartamento>> actualListAptosByUsuarioResult = this.apartamentoService.listAptosByUsuario(usuario);
        assertSame(ofResult, actualListAptosByUsuarioResult);
        assertTrue(actualListAptosByUsuarioResult.isPresent());
        verify(this.apartamentoRepository).findByUsuario((Usuario) any());
    }
}

