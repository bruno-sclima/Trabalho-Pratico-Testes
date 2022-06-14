package com.tis5.NossoSindico.Controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tis5.NossoSindico.Service.UsuarioService;
import com.tis5.NossoSindico.domain.Apartamento;
import com.tis5.NossoSindico.domain.Condominio;
import com.tis5.NossoSindico.domain.LoginUsuario;
import com.tis5.NossoSindico.domain.Usuario;
import com.tis5.NossoSindico.repository.ApartamentoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {UsuarioController.class})
@ExtendWith(SpringExtension.class)
class UsuarioControllerTest {
    @MockBean
    private ApartamentoRepository apartamentoRepository;

    @Autowired
    private UsuarioController usuarioController;

    @MockBean
    private UsuarioService usuarioService;

    @Test
    void attUsuarioTest() throws Exception {
        //given
        Usuario usuario = new Usuario();
        usuario.setEmail("exemplo@gmail.com");
        usuario.setId(123L);
        usuario.setNome("Nome");
        usuario.setSenha("Senha");
        usuario.setSobrenome("Sobrenome");
        when(this.usuarioService.update((Usuario) any())).thenReturn(usuario);

        Usuario usuario1 = new Usuario();
        usuario1.setEmail("exemplo@gmail.com");
        usuario1.setId(123L);
        usuario1.setNome("Nome");
        usuario1.setSenha("Senha");
        usuario1.setSobrenome("Sobrenome");
        String content = (new ObjectMapper()).writeValueAsString(usuario1);
        //when
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/att")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        //test
        MockMvcBuilders.standaloneSetup(this.usuarioController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Atualizei o Roberto"));
    }

    @Test
    void cadastroTest() throws Exception {
        //given
        Usuario usuario = new Usuario();
        usuario.setEmail("exemplo@gmail.com");
        usuario.setId(123L);
        usuario.setNome("Nome");
        usuario.setSenha("Senha");
        usuario.setSobrenome("Sobrenome");
        when(this.usuarioService.createUsuario((Usuario) any())).thenReturn(usuario);

        Usuario usuario1 = new Usuario();
        usuario1.setEmail("exemplo@gmail.com");
        usuario1.setId(123L);
        usuario1.setNome("Nome");
        usuario1.setSenha("Senha");
        usuario1.setSobrenome("Sobrenome");
        String content = (new ObjectMapper()).writeValueAsString(usuario1);
        //when
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/cadastro")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        //then
        MockMvcBuilders.standaloneSetup(this.usuarioController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"nome\":\"Nome\",\"sobrenome\":\"Sobrenome\",\"email\":\"exemplo@gmail.com\",\"senha\":\"Senha\"}"));
    }

    @Test
    void deleteUsuarioTest() throws Exception {
        doNothing().when(this.usuarioService).deleteUsuarioById(anyLong());

        Usuario usuario = new Usuario();
        usuario.setEmail("jane.doe@example.org");
        usuario.setId(123L);
        usuario.setNome("Nome");
        usuario.setSenha("Senha");
        usuario.setSobrenome("Sobrenome");
        String content = (new ObjectMapper()).writeValueAsString(usuario);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.usuarioController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Josue deletado"));
    }

    @Test
    void getAptoUsuarioTest() throws Exception {
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

        ArrayList<Apartamento> apartamentoList = new ArrayList<>();
        apartamentoList.add(apartamento);
        Optional<List<Apartamento>> ofResult = Optional.of(apartamentoList);
        when(this.apartamentoRepository.findByUsuario((Usuario) any())).thenReturn(ofResult);

        Usuario usuario1 = new Usuario();
        usuario1.setEmail("jane.doe@example.org");
        usuario1.setId(123L);
        usuario1.setNome("Nome");
        usuario1.setSenha("Senha");
        usuario1.setSobrenome("Sobrenome");
        String content = (new ObjectMapper()).writeValueAsString(usuario1);
        //when
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/getApto")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        //then
        MockMvcBuilders.standaloneSetup(this.usuarioController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"nome\":\"Nome\",\"sobrenome\":\"Sobrenome\",\"email\":\"jane.doe@example.org\",\"senha\":\"Senha\","
                                        + "\"nroApto\":10}"));
    }


    @Test
    void loginTest() throws Exception {
        //given
        Usuario usuario = new Usuario();
        usuario.setEmail("exemplo@gmail.com");
        usuario.setId(123L);
        usuario.setNome("Nome");
        usuario.setSenha("Senha");
        usuario.setSobrenome("Sobrenome");
        Optional<Usuario> ofResult = Optional.of(usuario);
        when(this.usuarioService.getUuarioByEmail((String) any())).thenReturn(ofResult);

        LoginUsuario loginUsuario = new LoginUsuario();
        loginUsuario.setEmail("exemplo@gmail.com");
        loginUsuario.setSenha("Senha");
        String content = (new ObjectMapper()).writeValueAsString(loginUsuario);
        //when
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        //then
        MockMvcBuilders.standaloneSetup(this.usuarioController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"nome\":\"Nome\",\"sobrenome\":\"Sobrenome\",\"email\":\"exemplo@gmail.com\",\"senha\":\"Senha\"}"));
    }

    @Test
    void loginTest3() throws Exception {
        //given
        when(this.usuarioService.getUuarioByEmail((String) any())).thenReturn(Optional.empty());

        LoginUsuario loginUsuario = new LoginUsuario();
        loginUsuario.setEmail("exemplo@gmail.com");
        loginUsuario.setSenha("Senha");
        String content = (new ObjectMapper()).writeValueAsString(loginUsuario);
        //when
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.usuarioController)
                .build()
                .perform(requestBuilder);
        //then
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(203))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Senha Cringe"));
    }
}

