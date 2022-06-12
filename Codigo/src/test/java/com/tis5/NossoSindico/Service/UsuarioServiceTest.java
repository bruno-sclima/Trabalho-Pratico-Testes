package com.tis5.NossoSindico.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tis5.NossoSindico.domain.Usuario;
import com.tis5.NossoSindico.repository.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UsuarioService.class})
@ExtendWith(SpringExtension.class)
class UsuarioServiceTest {
    @MockBean
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Method under test: {@link UsuarioService#createUsuario(Usuario)}
     */
    @Test
    void testCreateUsuario() {
        Usuario usuario = new Usuario();
        usuario.setEmail("jane.doe@example.org");
        usuario.setId(123L);
        usuario.setNome("Nome");
        usuario.setSenha("Senha");
        usuario.setSobrenome("Sobrenome");
        when(this.usuarioRepository.save((Usuario) any())).thenReturn(usuario);

        Usuario usuario1 = new Usuario();
        usuario1.setEmail("jane.doe@example.org");
        usuario1.setId(123L);
        usuario1.setNome("Nome");
        usuario1.setSenha("Senha");
        usuario1.setSobrenome("Sobrenome");
        assertSame(usuario, this.usuarioService.createUsuario(usuario1));
        verify(this.usuarioRepository).save((Usuario) any());
    }

    /**
     * Method under test: {@link UsuarioService#createUsuario(Usuario)}
     */
    @Test
    void testCreateUsuario2() {
        Usuario usuario = new Usuario();
        usuario.setEmail("jane.doe@example.org");
        usuario.setId(123L);
        usuario.setNome("Nome");
        usuario.setSenha("Senha");
        usuario.setSobrenome("Sobrenome");
        when(this.usuarioRepository.save((Usuario) any())).thenReturn(usuario);
        Usuario usuario1 = mock(Usuario.class);
        doNothing().when(usuario1).setEmail((String) any());
        doNothing().when(usuario1).setId(anyLong());
        doNothing().when(usuario1).setNome((String) any());
        doNothing().when(usuario1).setSenha((String) any());
        doNothing().when(usuario1).setSobrenome((String) any());
        usuario1.setEmail("jane.doe@example.org");
        usuario1.setId(123L);
        usuario1.setNome("Nome");
        usuario1.setSenha("Senha");
        usuario1.setSobrenome("Sobrenome");
        assertSame(usuario, this.usuarioService.createUsuario(usuario1));
        verify(this.usuarioRepository).save((Usuario) any());
        verify(usuario1).setEmail((String) any());
        verify(usuario1).setId(anyLong());
        verify(usuario1).setNome((String) any());
        verify(usuario1).setSenha((String) any());
        verify(usuario1).setSobrenome((String) any());
    }

    /**
     * Method under test: {@link UsuarioService#getUsuarioById(long)}
     */
    @Test
    void testGetUsuarioById() {
        Usuario usuario = new Usuario();
        usuario.setEmail("jane.doe@example.org");
        usuario.setId(123L);
        usuario.setNome("Nome");
        usuario.setSenha("Senha");
        usuario.setSobrenome("Sobrenome");
        Optional<Usuario> ofResult = Optional.of(usuario);
        when(this.usuarioRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(usuario, this.usuarioService.getUsuarioById(123L));
        verify(this.usuarioRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link UsuarioService#getUsuarioById(long)}
     */
    @Test
    void testGetUsuarioById2() {
        when(this.usuarioRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertNull(this.usuarioService.getUsuarioById(123L));
        verify(this.usuarioRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link UsuarioService#getUuarioByEmail(String)}
     */
    @Test
    void testGetUuarioByEmail() {
        Usuario usuario = new Usuario();
        usuario.setEmail("jane.doe@example.org");
        usuario.setId(123L);
        usuario.setNome("Nome");
        usuario.setSenha("Senha");
        usuario.setSobrenome("Sobrenome");
        Optional<Usuario> ofResult = Optional.of(usuario);
        when(this.usuarioRepository.findByEmail((String) any())).thenReturn(ofResult);
        Optional<Usuario> actualUuarioByEmail = this.usuarioService.getUuarioByEmail("jane.doe@example.org");
        assertSame(ofResult, actualUuarioByEmail);
        assertTrue(actualUuarioByEmail.isPresent());
        verify(this.usuarioRepository).findByEmail((String) any());
    }

    /**
     * Method under test: {@link UsuarioService#getUuarioByEmail(String)}
     */
    @Test
    void testGetUuarioByEmail2() {
        Optional<Usuario> emptyResult = Optional.empty();
        when(this.usuarioRepository.findByEmail((String) any())).thenReturn(emptyResult);
        Optional<Usuario> actualUuarioByEmail = this.usuarioService.getUuarioByEmail("jane.doe@example.org");
        assertSame(emptyResult, actualUuarioByEmail);
        assertFalse(actualUuarioByEmail.isPresent());
        verify(this.usuarioRepository).findByEmail((String) any());
    }

    /**
     * Method under test: {@link UsuarioService#listUsuarios()}
     */
    @Test
    void testListUsuarios() {
        when(this.usuarioRepository.findAll()).thenReturn(new ArrayList<>());
        assertNull(this.usuarioService.listUsuarios());
        verify(this.usuarioRepository).findAll();
    }

    /**
     * Method under test: {@link UsuarioService#listUsuarios()}
     */
    @Test
    void testListUsuarios2() {
        Usuario usuario = new Usuario();
        usuario.setEmail("jane.doe@example.org");
        usuario.setId(123L);
        usuario.setNome("Nome");
        usuario.setSenha("Senha");
        usuario.setSobrenome("Sobrenome");

        ArrayList<Usuario> usuarioList = new ArrayList<>();
        usuarioList.add(usuario);
        when(this.usuarioRepository.findAll()).thenReturn(usuarioList);
        List<Usuario> actualListUsuariosResult = this.usuarioService.listUsuarios();
        assertSame(usuarioList, actualListUsuariosResult);
        assertEquals(1, actualListUsuariosResult.size());
        verify(this.usuarioRepository).findAll();
    }

    /**
     * Method under test: {@link UsuarioService#update(Usuario)}
     */
    @Test
    void testUpdate() {
        Usuario usuario = new Usuario();
        usuario.setEmail("jane.doe@example.org");
        usuario.setId(123L);
        usuario.setNome("Nome");
        usuario.setSenha("Senha");
        usuario.setSobrenome("Sobrenome");
        Optional<Usuario> ofResult = Optional.of(usuario);

        Usuario usuario1 = new Usuario();
        usuario1.setEmail("jane.doe@example.org");
        usuario1.setId(123L);
        usuario1.setNome("Nome");
        usuario1.setSenha("Senha");
        usuario1.setSobrenome("Sobrenome");
        when(this.usuarioRepository.save((Usuario) any())).thenReturn(usuario1);
        when(this.usuarioRepository.findById((Long) any())).thenReturn(ofResult);

        Usuario usuario2 = new Usuario();
        usuario2.setEmail("jane.doe@example.org");
        usuario2.setId(123L);
        usuario2.setNome("Nome");
        usuario2.setSenha("Senha");
        usuario2.setSobrenome("Sobrenome");
        assertSame(usuario1, this.usuarioService.update(usuario2));
        verify(this.usuarioRepository).save((Usuario) any());
        verify(this.usuarioRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link UsuarioService#update(Usuario)}
     */
    @Test
    void testUpdate2() {
        Usuario usuario = mock(Usuario.class);
        when(usuario.getSenha()).thenReturn("Senha");
        doNothing().when(usuario).setEmail((String) any());
        doNothing().when(usuario).setId(anyLong());
        doNothing().when(usuario).setNome((String) any());
        doNothing().when(usuario).setSenha((String) any());
        doNothing().when(usuario).setSobrenome((String) any());
        usuario.setEmail("jane.doe@example.org");
        usuario.setId(123L);
        usuario.setNome("Nome");
        usuario.setSenha("Senha");
        usuario.setSobrenome("Sobrenome");
        Optional<Usuario> ofResult = Optional.of(usuario);

        Usuario usuario1 = new Usuario();
        usuario1.setEmail("jane.doe@example.org");
        usuario1.setId(123L);
        usuario1.setNome("Nome");
        usuario1.setSenha("Senha");
        usuario1.setSobrenome("Sobrenome");
        when(this.usuarioRepository.save((Usuario) any())).thenReturn(usuario1);
        when(this.usuarioRepository.findById((Long) any())).thenReturn(ofResult);

        Usuario usuario2 = new Usuario();
        usuario2.setEmail("jane.doe@example.org");
        usuario2.setId(123L);
        usuario2.setNome("Nome");
        usuario2.setSenha("Senha");
        usuario2.setSobrenome("Sobrenome");
        assertSame(usuario1, this.usuarioService.update(usuario2));
        verify(this.usuarioRepository).save((Usuario) any());
        verify(this.usuarioRepository).findById((Long) any());
        verify(usuario).getSenha();
        verify(usuario, atLeast(1)).setEmail((String) any());
        verify(usuario).setId(anyLong());
        verify(usuario, atLeast(1)).setNome((String) any());
        verify(usuario, atLeast(1)).setSenha((String) any());
        verify(usuario, atLeast(1)).setSobrenome((String) any());
    }

    /**
     * Method under test: {@link UsuarioService#update(Usuario)}
     */
    @Test
    void testUpdate3() {
        Usuario usuario = new Usuario();
        usuario.setEmail("jane.doe@example.org");
        usuario.setId(123L);
        usuario.setNome("Nome");
        usuario.setSenha("Senha");
        usuario.setSobrenome("Sobrenome");
        when(this.usuarioRepository.save((Usuario) any())).thenReturn(usuario);
        when(this.usuarioRepository.findById((Long) any())).thenReturn(Optional.empty());
        Usuario usuario1 = mock(Usuario.class);
        when(usuario1.getSenha()).thenReturn("Senha");
        doNothing().when(usuario1).setEmail((String) any());
        doNothing().when(usuario1).setId(anyLong());
        doNothing().when(usuario1).setNome((String) any());
        doNothing().when(usuario1).setSenha((String) any());
        doNothing().when(usuario1).setSobrenome((String) any());
        usuario1.setEmail("jane.doe@example.org");
        usuario1.setId(123L);
        usuario1.setNome("Nome");
        usuario1.setSenha("Senha");
        usuario1.setSobrenome("Sobrenome");

        Usuario usuario2 = new Usuario();
        usuario2.setEmail("jane.doe@example.org");
        usuario2.setId(123L);
        usuario2.setNome("Nome");
        usuario2.setSenha("Senha");
        usuario2.setSobrenome("Sobrenome");
        assertNull(this.usuarioService.update(usuario2));
        verify(this.usuarioRepository).findById((Long) any());
        verify(usuario1).setEmail((String) any());
        verify(usuario1).setId(anyLong());
        verify(usuario1).setNome((String) any());
        verify(usuario1).setSenha((String) any());
        verify(usuario1).setSobrenome((String) any());
    }

    /**
     * Method under test: {@link UsuarioService#update(Usuario)}
     */
    @Test
    void testUpdate4() {
        Usuario usuario = mock(Usuario.class);
        when(usuario.getSenha()).thenReturn("Senha");
        doNothing().when(usuario).setEmail((String) any());
        doNothing().when(usuario).setId(anyLong());
        doNothing().when(usuario).setNome((String) any());
        doNothing().when(usuario).setSenha((String) any());
        doNothing().when(usuario).setSobrenome((String) any());
        usuario.setEmail("jane.doe@example.org");
        usuario.setId(123L);
        usuario.setNome("Nome");
        usuario.setSenha("Senha");
        usuario.setSobrenome("Sobrenome");
        Optional<Usuario> ofResult = Optional.of(usuario);

        Usuario usuario1 = new Usuario();
        usuario1.setEmail("jane.doe@example.org");
        usuario1.setId(123L);
        usuario1.setNome("Nome");
        usuario1.setSenha("Senha");
        usuario1.setSobrenome("Sobrenome");
        when(this.usuarioRepository.save((Usuario) any())).thenReturn(usuario1);
        when(this.usuarioRepository.findById((Long) any())).thenReturn(ofResult);
        Usuario usuario2 = mock(Usuario.class);
        when(usuario2.getEmail()).thenReturn("jane.doe@example.org");
        when(usuario2.getNome()).thenReturn("Nome");
        when(usuario2.getSobrenome()).thenReturn("Sobrenome");
        when(usuario2.getId()).thenReturn(123L);
        doNothing().when(usuario2).setEmail((String) any());
        doNothing().when(usuario2).setId(anyLong());
        doNothing().when(usuario2).setNome((String) any());
        doNothing().when(usuario2).setSenha((String) any());
        doNothing().when(usuario2).setSobrenome((String) any());
        usuario2.setEmail("jane.doe@example.org");
        usuario2.setId(123L);
        usuario2.setNome("Nome");
        usuario2.setSenha("Senha");
        usuario2.setSobrenome("Sobrenome");
        assertSame(usuario1, this.usuarioService.update(usuario2));
        verify(this.usuarioRepository).save((Usuario) any());
        verify(this.usuarioRepository).findById((Long) any());
        verify(usuario).getSenha();
        verify(usuario, atLeast(1)).setEmail((String) any());
        verify(usuario).setId(anyLong());
        verify(usuario, atLeast(1)).setNome((String) any());
        verify(usuario, atLeast(1)).setSenha((String) any());
        verify(usuario, atLeast(1)).setSobrenome((String) any());
        verify(usuario2).getEmail();
        verify(usuario2).getNome();
        verify(usuario2).getSobrenome();
        verify(usuario2).getId();
        verify(usuario2).setEmail((String) any());
        verify(usuario2).setId(anyLong());
        verify(usuario2).setNome((String) any());
        verify(usuario2).setSenha((String) any());
        verify(usuario2).setSobrenome((String) any());
    }

    /**
     * Method under test: {@link UsuarioService#deleteUsuarioById(long)}
     */
    @Test
    void testDeleteUsuarioById() {
        Usuario usuario = new Usuario();
        usuario.setEmail("jane.doe@example.org");
        usuario.setId(123L);
        usuario.setNome("Nome");
        usuario.setSenha("Senha");
        usuario.setSobrenome("Sobrenome");
        Optional<Usuario> ofResult = Optional.of(usuario);
        doNothing().when(this.usuarioRepository).deleteById((Long) any());
        when(this.usuarioRepository.findById((Long) any())).thenReturn(ofResult);
        this.usuarioService.deleteUsuarioById(123L);
        verify(this.usuarioRepository).findById((Long) any());
        verify(this.usuarioRepository).deleteById((Long) any());
    }

    /**
     * Method under test: {@link UsuarioService#deleteUsuarioById(long)}
     */
    @Test
    void testDeleteUsuarioById2() {
        Usuario usuario = mock(Usuario.class);
        when(usuario.getId()).thenReturn(123L);
        doNothing().when(usuario).setEmail((String) any());
        doNothing().when(usuario).setId(anyLong());
        doNothing().when(usuario).setNome((String) any());
        doNothing().when(usuario).setSenha((String) any());
        doNothing().when(usuario).setSobrenome((String) any());
        usuario.setEmail("jane.doe@example.org");
        usuario.setId(123L);
        usuario.setNome("Nome");
        usuario.setSenha("Senha");
        usuario.setSobrenome("Sobrenome");
        Optional<Usuario> ofResult = Optional.of(usuario);
        doNothing().when(this.usuarioRepository).deleteById((Long) any());
        when(this.usuarioRepository.findById((Long) any())).thenReturn(ofResult);
        this.usuarioService.deleteUsuarioById(123L);
        verify(this.usuarioRepository).findById((Long) any());
        verify(this.usuarioRepository).deleteById((Long) any());
        verify(usuario).getId();
        verify(usuario).setEmail((String) any());
        verify(usuario).setId(anyLong());
        verify(usuario).setNome((String) any());
        verify(usuario).setSenha((String) any());
        verify(usuario).setSobrenome((String) any());
    }

    /**
     * Method under test: {@link UsuarioService#deleteUsuarioById(long)}
     */
    @Test
    void testDeleteUsuarioById3() {
        doNothing().when(this.usuarioRepository).deleteById((Long) any());
        when(this.usuarioRepository.findById((Long) any())).thenReturn(Optional.empty());
        Usuario usuario = mock(Usuario.class);
        when(usuario.getId()).thenReturn(123L);
        doNothing().when(usuario).setEmail((String) any());
        doNothing().when(usuario).setId(anyLong());
        doNothing().when(usuario).setNome((String) any());
        doNothing().when(usuario).setSenha((String) any());
        doNothing().when(usuario).setSobrenome((String) any());
        usuario.setEmail("jane.doe@example.org");
        usuario.setId(123L);
        usuario.setNome("Nome");
        usuario.setSenha("Senha");
        usuario.setSobrenome("Sobrenome");
        this.usuarioService.deleteUsuarioById(123L);
        verify(this.usuarioRepository).findById((Long) any());
        verify(usuario).setEmail((String) any());
        verify(usuario).setId(anyLong());
        verify(usuario).setNome((String) any());
        verify(usuario).setSenha((String) any());
        verify(usuario).setSobrenome((String) any());
    }
}

