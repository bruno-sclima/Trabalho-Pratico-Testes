package com.tis5.NossoSindico.Controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tis5.NossoSindico.Service.ApartamentoService;
import com.tis5.NossoSindico.Service.CondominioService;
import com.tis5.NossoSindico.Service.UsuarioService;
import com.tis5.NossoSindico.domain.Apartamento;
import com.tis5.NossoSindico.domain.Condominio;
import com.tis5.NossoSindico.domain.SindicoResource;
import com.tis5.NossoSindico.domain.Usuario;

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
import org.springframework.test.web.servlet.result.ContentResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ApartamentoController.class})
@ExtendWith(SpringExtension.class)
class ApartamentoControllerTest {
    @Autowired
    private ApartamentoController apartamentoController;

    @MockBean
    private ApartamentoService apartamentoService;

    @MockBean
    private CondominioService condominioService;

    @MockBean
    private UsuarioService usuarioService;

    /**
     * Method under test: {@link ApartamentoController#isSindico(SindicoResource)}
     */
    @Test
    void testIsSindico() throws Exception {
        when(this.apartamentoService.isSindico(anyLong(), anyLong())).thenReturn(true);

        SindicoResource sindicoResource = new SindicoResource();
        sindicoResource.setId_condominio(1L);
        sindicoResource.setId_usuario(1L);
        String content = (new ObjectMapper()).writeValueAsString(sindicoResource);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/isSindico")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions resultActions = MockMvcBuilders.standaloneSetup(this.apartamentoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        ContentResultMatchers contentResult = MockMvcResultMatchers.content();
        resultActions.andExpect(contentResult.string(Boolean.TRUE.toString()));
    }

    /**
     * Method under test: {@link ApartamentoController#listCondUsu(Usuario)}
     */
    @Test
    void testListCondUsu() throws Exception {
        when(this.apartamentoService.listAptosByUsuario((Usuario) any())).thenReturn(Optional.of(new ArrayList<>()));

        Usuario usuario = new Usuario();
        usuario.setEmail("jane.doe@example.org");
        usuario.setId(123L);
        usuario.setNome("Nome");
        usuario.setSenha("Senha");
        usuario.setSobrenome("Sobrenome");
        String content = (new ObjectMapper()).writeValueAsString(usuario);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/listCondUsu")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.apartamentoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ApartamentoController#listCondUsu(Usuario)}
     */
    @Test
    void testListCondUsu2() throws Exception {
        Condominio condominio = new Condominio();
        condominio.setBairro("?");
        condominio.setCep("?");
        condominio.setCidade("?");
        condominio.setCode("?");
        condominio.setId(123L);
        condominio.setNome("?");
        condominio.setNumero(10);
        condominio.setRua("?");

        Usuario usuario = new Usuario();
        usuario.setEmail("jane.doe@example.org");
        usuario.setId(123L);
        usuario.setNome("?");
        usuario.setSenha("?");
        usuario.setSobrenome("?");

        Apartamento apartamento = new Apartamento();
        apartamento.setBloco("?");
        apartamento.setCondominio(condominio);
        apartamento.setId(123L);
        apartamento.setNumero(10);
        apartamento.setSindico(true);
        apartamento.setUsuario(usuario);

        ArrayList<Apartamento> apartamentoList = new ArrayList<>();
        apartamentoList.add(apartamento);
        Optional<List<Apartamento>> ofResult = Optional.of(apartamentoList);
        when(this.apartamentoService.listAptosByUsuario((Usuario) any())).thenReturn(ofResult);

        Usuario usuario1 = new Usuario();
        usuario1.setEmail("jane.doe@example.org");
        usuario1.setId(123L);
        usuario1.setNome("Nome");
        usuario1.setSenha("Senha");
        usuario1.setSobrenome("Sobrenome");
        String content = (new ObjectMapper()).writeValueAsString(usuario1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/listCondUsu")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.apartamentoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":123,\"nome\":\"?\",\"rua\":\"?\",\"bairro\":\"?\",\"cep\":\"?\",\"cidade\":\"?\",\"numero\":10,\"code\":\"?\"}]"));
    }

    /**
     * Method under test: {@link ApartamentoController#listCondUsu(Usuario)}
     */
    @Test
    void testListCondUsu3() throws Exception {
        when(this.apartamentoService.listAptosByUsuario((Usuario) any())).thenReturn(Optional.empty());

        Usuario usuario = new Usuario();
        usuario.setEmail("jane.doe@example.org");
        usuario.setId(123L);
        usuario.setNome("Nome");
        usuario.setSenha("Senha");
        usuario.setSobrenome("Sobrenome");
        String content = (new ObjectMapper()).writeValueAsString(usuario);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/listCondUsu")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.apartamentoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ApartamentoController#listCondUsu(Usuario)}
     */
    @Test
    void testListCondUsu4() throws Exception {
        Condominio condominio = new Condominio();
        condominio.setBairro("?");
        condominio.setCep("?");
        condominio.setCidade("?");
        condominio.setCode("?");
        condominio.setId(123L);
        condominio.setNome("?");
        condominio.setNumero(10);
        condominio.setRua("?");

        Usuario usuario = new Usuario();
        usuario.setEmail("jane.doe@example.org");
        usuario.setId(123L);
        usuario.setNome("?");
        usuario.setSenha("?");
        usuario.setSobrenome("?");

        Apartamento apartamento = new Apartamento();
        apartamento.setBloco("?");
        apartamento.setCondominio(condominio);
        apartamento.setId(123L);
        apartamento.setNumero(10);
        apartamento.setSindico(true);
        apartamento.setUsuario(usuario);

        Condominio condominio1 = new Condominio();
        condominio1.setBairro("?");
        condominio1.setCep("?");
        condominio1.setCidade("?");
        condominio1.setCode("?");
        condominio1.setId(123L);
        condominio1.setNome("?");
        condominio1.setNumero(10);
        condominio1.setRua("?");

        Usuario usuario1 = new Usuario();
        usuario1.setEmail("jane.doe@example.org");
        usuario1.setId(123L);
        usuario1.setNome("?");
        usuario1.setSenha("?");
        usuario1.setSobrenome("?");

        Apartamento apartamento1 = new Apartamento();
        apartamento1.setBloco("?");
        apartamento1.setCondominio(condominio1);
        apartamento1.setId(123L);
        apartamento1.setNumero(10);
        apartamento1.setSindico(true);
        apartamento1.setUsuario(usuario1);

        ArrayList<Apartamento> apartamentoList = new ArrayList<>();
        apartamentoList.add(apartamento1);
        apartamentoList.add(apartamento);
        Optional<List<Apartamento>> ofResult = Optional.of(apartamentoList);
        when(this.apartamentoService.listAptosByUsuario((Usuario) any())).thenReturn(ofResult);

        Usuario usuario2 = new Usuario();
        usuario2.setEmail("jane.doe@example.org");
        usuario2.setId(123L);
        usuario2.setNome("Nome");
        usuario2.setSenha("Senha");
        usuario2.setSobrenome("Sobrenome");
        String content = (new ObjectMapper()).writeValueAsString(usuario2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/listCondUsu")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.apartamentoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":123,\"nome\":\"?\",\"rua\":\"?\",\"bairro\":\"?\",\"cep\":\"?\",\"cidade\":\"?\",\"numero\":10,\"code\":\"?\"},{\"id\":123"
                                        + ",\"nome\":\"?\",\"rua\":\"?\",\"bairro\":\"?\",\"cep\":\"?\",\"cidade\":\"?\",\"numero\":10,\"code\":\"?\"}]"));
    }
}

