package com.tis5.NossoSindico.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
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

    @Test
    void createUsuarioTest() {
        //given
        Usuario usuario = new Usuario();
        usuario.setEmail("ex@gmail.com");
        usuario.setId(123L);
        usuario.setNome("Nome");
        usuario.setSenha("Senha");
        usuario.setSobrenome("Sobrenome");
        when(this.usuarioRepository.save((Usuario) any())).thenReturn(usuario);

        Usuario usuario1 = new Usuario();
        usuario1.setEmail("ex@gmail.com");
        usuario1.setId(123L);
        usuario1.setNome("Nome");
        usuario1.setSenha("Senha");
        usuario1.setSobrenome("Sobrenome");
        //when
        Usuario actual =  this.usuarioService.createUsuario(usuario1);
        //then
        assertSame(usuario,actual);
        verify(this.usuarioRepository).save((Usuario) any());
    }

    @Test
    void createUsuarioTest2() {
        Usuario usuario = new Usuario();
        usuario.setEmail("ex@gmail.com");
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
        usuario1.setEmail("ex@gmail.com");
        usuario1.setId(123L);
        usuario1.setNome("Nome");
        usuario1.setSenha("Senha");
        usuario1.setSobrenome("Sobrenome");
        assertSame(usuario, this.usuarioService.createUsuario(usuario1));
        verify(this.usuarioRepository).save((Usuario) any());
        verify(usuario1).setEmail((String) any());
        verify(usuario1).setId(anyLong());
        verify(usuario1).setNome(anyString());
        verify(usuario1).setSenha(anyString());
        verify(usuario1).setSobrenome(anyString());
    }

    @Test
    void getUsuarioByIdTest() {
        //given
        Usuario usuario = new Usuario(123L,"Nome","Sobrenome","ex@mail.com","Senha");
        Optional<Usuario> res = Optional.of(usuario);
        when(this.usuarioRepository.findById((Long) any())).thenReturn(res);
        //when
        Usuario actual =  this.usuarioService.getUsuarioById(123L);
        //then
        assertSame(usuario,actual);
        verify(this.usuarioRepository).findById((Long) any());
    }

    void getUsuarioByEmailTest() {
        //given
        Usuario usuario = new Usuario(123L,"Nome","Sobrenome","ex@mail.com","Senha");
        Optional<Usuario> ofResult = Optional.of(usuario);
        when(this.usuarioRepository.findByEmail((String) any())).thenReturn(ofResult);
        //when
        Optional<Usuario> actualUuarioByEmail = this.usuarioService.getUuarioByEmail("ex@mail.com");
        //then
        assertSame(ofResult, actualUuarioByEmail);
        assertTrue(actualUuarioByEmail.isPresent());
        verify(this.usuarioRepository).findByEmail((String) any());
    }

    @Test
    void getUsuarioByEmailTest2() {
        Optional<Usuario> emptyResult = Optional.empty();
        when(this.usuarioRepository.findByEmail((String) any())).thenReturn(emptyResult);
        Optional<Usuario> actualUuarioByEmail = this.usuarioService.getUuarioByEmail("ex@mail.com");
        assertSame(emptyResult, actualUuarioByEmail);
        assertFalse(actualUuarioByEmail.isPresent());
        verify(this.usuarioRepository).findByEmail((String) any());
    }


    @Test
    void listUsuariosTest() {
        when(this.usuarioRepository.findAll()).thenReturn(new ArrayList<>());
        assertNull(this.usuarioService.listUsuarios());
        verify(this.usuarioRepository).findAll();
    }

    @Test
    void listUsuariosTest2() {
        //given
        Usuario usuario = new Usuario(123L,"Nome","Sobrenome","ex@mail.com","Senha");
        ArrayList<Usuario> usuarioList = new ArrayList<>();
        usuarioList.add(usuario);
        when(this.usuarioRepository.findAll()).thenReturn(usuarioList);
        //when
        List<Usuario> actual = this.usuarioService.listUsuarios();
        //then
        assertSame(usuarioList, actual);
        assertEquals(1, actual.size());
        verify(this.usuarioRepository).findAll();
    }

    @Test
    void updateTest() {
        //given
        Usuario usuario = new Usuario(123L,"Nome","Sobrenome","ex@mail.com","Senha");
        Optional<Usuario> ofResult = Optional.of(usuario);

        Usuario usuario1 = new Usuario();
        usuario1.setEmail("ex@mail.com");
        usuario1.setId(123L);
        usuario1.setNome("Nome");
        usuario1.setSenha("Senha");
        usuario1.setSobrenome("Sobrenome");
        when(this.usuarioRepository.save((Usuario) any())).thenReturn(usuario1);
        when(this.usuarioRepository.findById((Long) any())).thenReturn(ofResult);

        Usuario usuario2 = new Usuario();
        usuario2.setEmail("ex@mail.com");
        usuario2.setId(123L);
        usuario2.setNome("Nome");
        usuario2.setSenha("Senha");
        usuario2.setSobrenome("Sobrenome");
        //when
        Usuario actual = this.usuarioService.update(usuario2);
        //then
        assertSame(usuario1,actual );
        verify(this.usuarioRepository).save((Usuario) any());
        verify(this.usuarioRepository).findById((Long) any());
    }

    @Test
    void updateTest2() {
        //given
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
        usuario1.setEmail("ex@mail.com");
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
        //when
        Usuario actual = this.usuarioService.update(usuario2);
        //then
        assertSame(usuario1,actual);
        verify(this.usuarioRepository).save((Usuario) any());
        verify(this.usuarioRepository).findById((Long) any());
        verify(usuario).getSenha();
        verify(usuario, atLeast(1)).setEmail((String) any());
        verify(usuario).setId(anyLong());
        verify(usuario, atLeast(1)).setNome((String) any());
        verify(usuario, atLeast(1)).setSenha((String) any());
        verify(usuario, atLeast(1)).setSobrenome((String) any());
    }

    @Test
    void deleteUsuarioByIdTest() {
        //given
        Usuario usuario = new Usuario();
        usuario.setEmail("ex@gmail.com");
        usuario.setId(123L);
        usuario.setNome("Nome");
        usuario.setSenha("Senha");
        usuario.setSobrenome("Sobrenome");
        Optional<Usuario> res = Optional.of(usuario);
        doNothing().when(this.usuarioRepository).deleteById((Long) any());
        when(this.usuarioRepository.findById((Long) any())).thenReturn(res);
        //when
        this.usuarioService.deleteUsuarioById(123L);
        //then
        verify(this.usuarioRepository).findById((Long) any());
        verify(this.usuarioRepository).deleteById((Long) any());
    }


}

