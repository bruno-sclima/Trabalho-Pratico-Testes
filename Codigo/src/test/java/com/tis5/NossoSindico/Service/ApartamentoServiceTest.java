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

import org.junit.jupiter.api.Disabled;

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

    /**
     * Method under test: {@link ApartamentoService#create(Apartamento)}
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
        usuario1.setEmail("jane.doe@example.org");
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
        assertSame(apartamento, this.apartamentoService.create(apartamento1));
        verify(this.apartamentoRepository).save((Apartamento) any());
    }

    /**
     * Method under test: {@link ApartamentoService#getAptoByCondominioENumero(long, int)}
     */
    @Test
    void testGetAptoByCondominioENumero() {
        Condominio condominio = new Condominio();
        condominio.setBairro("Bairro");
        condominio.setCep("Cep");
        condominio.setCidade("Cidade");
        condominio.setCode("Code");
        condominio.setId(123L);
        condominio.setNome("Nome");
        condominio.setNumero(10);
        condominio.setRua("Rua");
        when(this.condominioService.getCondominioById(anyLong())).thenReturn(condominio);
        when(this.apartamentoRepository.findByCondominio((Condominio) any())).thenReturn(Optional.of(new ArrayList<>()));
        assertNull(this.apartamentoService.getAptoByCondominioENumero(1L, 10));
        verify(this.condominioService).getCondominioById(anyLong());
        verify(this.apartamentoRepository).findByCondominio((Condominio) any());
    }

    /**
     * Method under test: {@link ApartamentoService#getAptoByCondominioENumero(long, int)}
     */
    @Test
    void testGetAptoByCondominioENumero2() {
        Condominio condominio = new Condominio();
        condominio.setBairro("Bairro");
        condominio.setCep("Cep");
        condominio.setCidade("Cidade");
        condominio.setCode("Code");
        condominio.setId(123L);
        condominio.setNome("Nome");
        condominio.setNumero(10);
        condominio.setRua("Rua");
        when(this.condominioService.getCondominioById(anyLong())).thenReturn(condominio);

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
        Optional<List<Apartamento>> ofResult = Optional.of(apartamentoList);
        when(this.apartamentoRepository.findByCondominio((Condominio) any())).thenReturn(ofResult);
        assertSame(apartamento, this.apartamentoService.getAptoByCondominioENumero(1L, 10));
        verify(this.condominioService).getCondominioById(anyLong());
        verify(this.apartamentoRepository).findByCondominio((Condominio) any());
    }

    /**
     * Method under test: {@link ApartamentoService#getAptoByCondominioENumero(long, int)}
     */
    @Test
    void testGetAptoByCondominioENumero3() {
        Condominio condominio = new Condominio();
        condominio.setBairro("Bairro");
        condominio.setCep("Cep");
        condominio.setCidade("Cidade");
        condominio.setCode("Code");
        condominio.setId(123L);
        condominio.setNome("Nome");
        condominio.setNumero(10);
        condominio.setRua("Rua");
        when(this.condominioService.getCondominioById(anyLong())).thenReturn(condominio);
        when(this.apartamentoRepository.findByCondominio((Condominio) any())).thenReturn(null);
        assertNull(this.apartamentoService.getAptoByCondominioENumero(1L, 10));
        verify(this.condominioService).getCondominioById(anyLong());
        verify(this.apartamentoRepository).findByCondominio((Condominio) any());
    }

    /**
     * Method under test: {@link ApartamentoService#getAptoByCondominioENumero(long, int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetAptoByCondominioENumero4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:143)
        //       at com.tis5.NossoSindico.Service.ApartamentoService.listApartamentosDeCondominio(ApartamentoService.java:51)
        //       at com.tis5.NossoSindico.Service.ApartamentoService.getAptoByCondominioENumero(ApartamentoService.java:32)
        //   In order to prevent getAptoByCondominioENumero(long, int)
        //   from throwing NoSuchElementException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   getAptoByCondominioENumero(long, int).
        //   See https://diff.blue/R013 to resolve this issue.

        Condominio condominio = new Condominio();
        condominio.setBairro("Bairro");
        condominio.setCep("Cep");
        condominio.setCidade("Cidade");
        condominio.setCode("Code");
        condominio.setId(123L);
        condominio.setNome("Nome");
        condominio.setNumero(10);
        condominio.setRua("Rua");
        when(this.condominioService.getCondominioById(anyLong())).thenReturn(condominio);
        when(this.apartamentoRepository.findByCondominio((Condominio) any())).thenReturn(Optional.empty());
        this.apartamentoService.getAptoByCondominioENumero(1L, 10);
    }

    /**
     * Method under test: {@link ApartamentoService#getAptoByCondominioENumero(long, int)}
     */
    @Test
    void testGetAptoByCondominioENumero5() {
        Condominio condominio = new Condominio();
        condominio.setBairro("Bairro");
        condominio.setCep("Cep");
        condominio.setCidade("Cidade");
        condominio.setCode("Code");
        condominio.setId(123L);
        condominio.setNome("Nome");
        condominio.setNumero(10);
        condominio.setRua("Rua");
        when(this.condominioService.getCondominioById(anyLong())).thenReturn(condominio);

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
        Apartamento apartamento = mock(Apartamento.class);
        when(apartamento.getNumero()).thenReturn(1);
        doNothing().when(apartamento).setBloco((String) any());
        doNothing().when(apartamento).setCondominio((Condominio) any());
        doNothing().when(apartamento).setId(anyLong());
        doNothing().when(apartamento).setNumero((Integer) any());
        doNothing().when(apartamento).setSindico(anyBoolean());
        doNothing().when(apartamento).setUsuario((Usuario) any());
        apartamento.setBloco("Bloco");
        apartamento.setCondominio(condominio1);
        apartamento.setId(123L);
        apartamento.setNumero(10);
        apartamento.setSindico(true);
        apartamento.setUsuario(usuario);

        ArrayList<Apartamento> apartamentoList = new ArrayList<>();
        apartamentoList.add(apartamento);
        Optional<List<Apartamento>> ofResult = Optional.of(apartamentoList);
        when(this.apartamentoRepository.findByCondominio((Condominio) any())).thenReturn(ofResult);
        assertNull(this.apartamentoService.getAptoByCondominioENumero(1L, 10));
        verify(this.condominioService).getCondominioById(anyLong());
        verify(this.apartamentoRepository).findByCondominio((Condominio) any());
        verify(apartamento).getNumero();
        verify(apartamento).setBloco((String) any());
        verify(apartamento).setCondominio((Condominio) any());
        verify(apartamento).setId(anyLong());
        verify(apartamento).setNumero((Integer) any());
        verify(apartamento).setSindico(anyBoolean());
        verify(apartamento).setUsuario((Usuario) any());
    }

    /**
     * Method under test: {@link ApartamentoService#deleteAptoById(long)}
     */
    @Test
    void testDeleteAptoById() {
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
        this.apartamentoService.deleteAptoById(123L);
        verify(this.apartamentoRepository).findById((Long) any());
        verify(this.apartamentoRepository).deleteById((Long) any());
    }

    /**
     * Method under test: {@link ApartamentoService#deleteAptoById(long)}
     */
    @Test
    void testDeleteAptoById2() {
        doNothing().when(this.apartamentoRepository).deleteById((Long) any());
        when(this.apartamentoRepository.findById((Long) any())).thenReturn(Optional.empty());
        this.apartamentoService.deleteAptoById(123L);
        verify(this.apartamentoRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ApartamentoService#listApartamentosDeCondominio(long)}
     */
    @Test
    void testListApartamentosDeCondominio() {
        Condominio condominio = new Condominio();
        condominio.setBairro("Bairro");
        condominio.setCep("Cep");
        condominio.setCidade("Cidade");
        condominio.setCode("Code");
        condominio.setId(123L);
        condominio.setNome("Nome");
        condominio.setNumero(10);
        condominio.setRua("Rua");
        when(this.condominioService.getCondominioById(anyLong())).thenReturn(condominio);
        when(this.apartamentoRepository.findByCondominio((Condominio) any())).thenReturn(Optional.of(new ArrayList<>()));
        assertTrue(this.apartamentoService.listApartamentosDeCondominio(1L).isEmpty());
        verify(this.condominioService).getCondominioById(anyLong());
        verify(this.apartamentoRepository).findByCondominio((Condominio) any());
    }

    /**
     * Method under test: {@link ApartamentoService#listApartamentosDeCondominio(long)}
     */
    @Test
    void testListApartamentosDeCondominio2() {
        Condominio condominio = new Condominio();
        condominio.setBairro("Bairro");
        condominio.setCep("Cep");
        condominio.setCidade("Cidade");
        condominio.setCode("Code");
        condominio.setId(123L);
        condominio.setNome("Nome");
        condominio.setNumero(10);
        condominio.setRua("Rua");
        when(this.condominioService.getCondominioById(anyLong())).thenReturn(condominio);

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
        Optional<List<Apartamento>> ofResult = Optional.of(apartamentoList);
        when(this.apartamentoRepository.findByCondominio((Condominio) any())).thenReturn(ofResult);
        List<Apartamento> actualListApartamentosDeCondominioResult = this.apartamentoService
                .listApartamentosDeCondominio(1L);
        assertSame(apartamentoList, actualListApartamentosDeCondominioResult);
        assertEquals(1, actualListApartamentosDeCondominioResult.size());
        verify(this.condominioService).getCondominioById(anyLong());
        verify(this.apartamentoRepository).findByCondominio((Condominio) any());
    }

    /**
     * Method under test: {@link ApartamentoService#listApartamentosDeCondominio(long)}
     */
    @Test
    void testListApartamentosDeCondominio3() {
        Condominio condominio = new Condominio();
        condominio.setBairro("Bairro");
        condominio.setCep("Cep");
        condominio.setCidade("Cidade");
        condominio.setCode("Code");
        condominio.setId(123L);
        condominio.setNome("Nome");
        condominio.setNumero(10);
        condominio.setRua("Rua");
        when(this.condominioService.getCondominioById(anyLong())).thenReturn(condominio);
        when(this.apartamentoRepository.findByCondominio((Condominio) any())).thenReturn(null);
        assertTrue(this.apartamentoService.listApartamentosDeCondominio(1L).isEmpty());
        verify(this.condominioService).getCondominioById(anyLong());
        verify(this.apartamentoRepository).findByCondominio((Condominio) any());
    }

    /**
     * Method under test: {@link ApartamentoService#listApartamentosDeCondominio(long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testListApartamentosDeCondominio4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:143)
        //       at com.tis5.NossoSindico.Service.ApartamentoService.listApartamentosDeCondominio(ApartamentoService.java:51)
        //   In order to prevent listApartamentosDeCondominio(long)
        //   from throwing NoSuchElementException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   listApartamentosDeCondominio(long).
        //   See https://diff.blue/R013 to resolve this issue.

        Condominio condominio = new Condominio();
        condominio.setBairro("Bairro");
        condominio.setCep("Cep");
        condominio.setCidade("Cidade");
        condominio.setCode("Code");
        condominio.setId(123L);
        condominio.setNome("Nome");
        condominio.setNumero(10);
        condominio.setRua("Rua");
        when(this.condominioService.getCondominioById(anyLong())).thenReturn(condominio);
        when(this.apartamentoRepository.findByCondominio((Condominio) any())).thenReturn(Optional.empty());
        this.apartamentoService.listApartamentosDeCondominio(1L);
    }

    /**
     * Method under test: {@link ApartamentoService#isSindico(long, long)}
     */
    @Test
    void testIsSindico() {
        Usuario usuario = new Usuario();
        usuario.setEmail("jane.doe@example.org");
        usuario.setId(123L);
        usuario.setNome("Nome");
        usuario.setSenha("Senha");
        usuario.setSobrenome("Sobrenome");
        when(this.usuarioService.getUsuarioById(anyLong())).thenReturn(usuario);
        when(this.apartamentoRepository.findByUsuario((Usuario) any())).thenReturn(Optional.of(new ArrayList<>()));
        assertFalse(this.apartamentoService.isSindico(1L, 1L));
        verify(this.usuarioService).getUsuarioById(anyLong());
        verify(this.apartamentoRepository).findByUsuario((Usuario) any());
    }

    /**
     * Method under test: {@link ApartamentoService#isSindico(long, long)}
     */
    @Test
    void testIsSindico2() {
        Usuario usuario = new Usuario();
        usuario.setEmail("jane.doe@example.org");
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

    /**
     * Method under test: {@link ApartamentoService#isSindico(long, long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testIsSindico3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:143)
        //       at com.tis5.NossoSindico.Service.ApartamentoService.isSindico(ApartamentoService.java:59)
        //   In order to prevent isSindico(long, long)
        //   from throwing NoSuchElementException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   isSindico(long, long).
        //   See https://diff.blue/R013 to resolve this issue.

        Usuario usuario = new Usuario();
        usuario.setEmail("jane.doe@example.org");
        usuario.setId(123L);
        usuario.setNome("Nome");
        usuario.setSenha("Senha");
        usuario.setSobrenome("Sobrenome");
        when(this.usuarioService.getUsuarioById(anyLong())).thenReturn(usuario);
        when(this.apartamentoRepository.findByUsuario((Usuario) any())).thenReturn(Optional.empty());
        this.apartamentoService.isSindico(1L, 1L);
    }

    /**
     * Method under test: {@link ApartamentoService#isSindico(long, long)}
     */
    @Test
    void testIsSindico4() {
        Usuario usuario = new Usuario();
        usuario.setEmail("jane.doe@example.org");
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

        Condominio condominio1 = new Condominio();
        condominio1.setBairro("Bairro");
        condominio1.setCep("Cep");
        condominio1.setCidade("Cidade");
        condominio1.setCode("Code");
        condominio1.setId(123L);
        condominio1.setNome("Nome");
        condominio1.setNumero(10);
        condominio1.setRua("Rua");

        Usuario usuario2 = new Usuario();
        usuario2.setEmail("jane.doe@example.org");
        usuario2.setId(123L);
        usuario2.setNome("Nome");
        usuario2.setSenha("Senha");
        usuario2.setSobrenome("Sobrenome");

        Apartamento apartamento1 = new Apartamento();
        apartamento1.setBloco("Bloco");
        apartamento1.setCondominio(condominio1);
        apartamento1.setId(123L);
        apartamento1.setNumero(10);
        apartamento1.setSindico(true);
        apartamento1.setUsuario(usuario2);

        ArrayList<Apartamento> apartamentoList = new ArrayList<>();
        apartamentoList.add(apartamento1);
        apartamentoList.add(apartamento);
        Optional<List<Apartamento>> ofResult = Optional.of(apartamentoList);
        when(this.apartamentoRepository.findByUsuario((Usuario) any())).thenReturn(ofResult);
        assertFalse(this.apartamentoService.isSindico(1L, 1L));
        verify(this.usuarioService).getUsuarioById(anyLong());
        verify(this.apartamentoRepository).findByUsuario((Usuario) any());
    }

    /**
     * Method under test: {@link ApartamentoService#isSindico(long, long)}
     */
    @Test
    void testIsSindico5() {
        Usuario usuario = new Usuario();
        usuario.setEmail("jane.doe@example.org");
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

    /**
     * Method under test: {@link ApartamentoService#listAptosByUsuario(Usuario)}
     */
    @Test
    void testListAptosByUsuario() {
        Optional<List<Apartamento>> ofResult = Optional.of(new ArrayList<>());
        when(this.apartamentoRepository.findByUsuario((Usuario) any())).thenReturn(ofResult);

        Usuario usuario = new Usuario();
        usuario.setEmail("jane.doe@example.org");
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

