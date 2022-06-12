package com.tis5.NossoSindico.Controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tis5.NossoSindico.Service.ApartamentoService;
import com.tis5.NossoSindico.Service.CondominioService;
import com.tis5.NossoSindico.Service.UsuarioService;
import com.tis5.NossoSindico.domain.AcessoCondominioResource;
import com.tis5.NossoSindico.domain.Apartamento;
import com.tis5.NossoSindico.domain.CadCondAptoResource;
import com.tis5.NossoSindico.domain.Condominio;
import com.tis5.NossoSindico.domain.Usuario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {CondominioController.class})
@ExtendWith(SpringExtension.class)
class CondominioControllerTest {
    @MockBean
    private ApartamentoService apartamentoService;

    @Autowired
    private CondominioController condominioController;

    @MockBean
    private CondominioService condominioService;

    @MockBean
    private UsuarioService usuarioService;

    /**
     * Method under test: {@link CondominioController#att(Condominio)}
     */
    @Test
    void testAtt() throws Exception {
        Condominio condominio = new Condominio();
        condominio.setBairro("Bairro");
        condominio.setCep("Cep");
        condominio.setCidade("Cidade");
        condominio.setCode("Code");
        condominio.setId(123L);
        condominio.setNome("Nome");
        condominio.setNumero(10);
        condominio.setRua("Rua");
        when(this.condominioService.update((Condominio) any())).thenReturn(condominio);

        Condominio condominio1 = new Condominio();
        condominio1.setBairro("Bairro");
        condominio1.setCep("Cep");
        condominio1.setCidade("Cidade");
        condominio1.setCode("Code");
        condominio1.setId(123L);
        condominio1.setNome("Nome");
        condominio1.setNumero(10);
        condominio1.setRua("Rua");
        String content = (new ObjectMapper()).writeValueAsString(condominio1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/attCond")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.condominioController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"nome\":\"Nome\",\"rua\":\"Rua\",\"bairro\":\"Bairro\",\"cep\":\"Cep\",\"cidade\":\"Cidade\",\"numero\":10,\"code"
                                        + "\":\"Code\"}"));
    }

    /**
     * Method under test: {@link CondominioController#cadastro(CadCondAptoResource)}
     */
    @Test
    void testCadastro() throws Exception {
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
        when(this.condominioService.create((Condominio) any())).thenReturn(condominio);

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

        Apartamento apartamento = new Apartamento();
        apartamento.setBloco("Bloco");
        apartamento.setCondominio(condominio1);
        apartamento.setId(123L);
        apartamento.setNumero(10);
        apartamento.setSindico(true);
        apartamento.setUsuario(usuario1);
        when(this.apartamentoService.create((Apartamento) any())).thenReturn(apartamento);

        CadCondAptoResource cadCondAptoResource = new CadCondAptoResource();
        cadCondAptoResource.setBairro("Bairro");
        cadCondAptoResource.setCep("Cep");
        cadCondAptoResource.setCidade("Cidade");
        cadCondAptoResource.setId_condominio(1L);
        cadCondAptoResource.setId_usuario(1L);
        cadCondAptoResource.setNome("Nome");
        cadCondAptoResource.setNumero(10);
        cadCondAptoResource.setNumeroApto(10);
        cadCondAptoResource.setRua("Rua");
        String content = (new ObjectMapper()).writeValueAsString(cadCondAptoResource);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/cadCond")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.condominioController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":0,\"nome\":\"Nome\",\"rua\":\"Rua\",\"bairro\":\"Bairro\",\"cep\":\"Cep\",\"cidade\":\"Cidade\",\"numero\":10,\"code\""
                                        + ":\"-846559024\"}"));
    }

    /**
     * Method under test: {@link CondominioController#codigoAcesso(Condominio)}
     */
    @Test
    void testCodigoAcesso() throws Exception {
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
        String content = (new ObjectMapper()).writeValueAsString(condominio1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/codigoAcesso")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.condominioController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Code"));
    }

    /**
     * Method under test: {@link CondominioController#entrarCondominio(AcessoCondominioResource)}
     */
    @Test
    void testEntrarCondominio() throws Exception {
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
        when(this.condominioService.enterCondominio((String) any())).thenReturn(condominio);

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

        Apartamento apartamento = new Apartamento();
        apartamento.setBloco("Bloco");
        apartamento.setCondominio(condominio1);
        apartamento.setId(123L);
        apartamento.setNumero(10);
        apartamento.setSindico(true);
        apartamento.setUsuario(usuario1);
        when(this.apartamentoService.create((Apartamento) any())).thenReturn(apartamento);

        AcessoCondominioResource acessoCondominioResource = new AcessoCondominioResource();
        acessoCondominioResource.setAcesso("Acesso");
        acessoCondominioResource.setId_usuario(1L);
        acessoCondominioResource.setNumeroApto(10);
        String content = (new ObjectMapper()).writeValueAsString(acessoCondominioResource);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/entraCond")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.condominioController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"nome\":\"Nome\",\"rua\":\"Rua\",\"bairro\":\"Bairro\",\"cep\":\"Cep\",\"cidade\":\"Cidade\",\"numero\":10,\"code"
                                        + "\":\"Code\"}"));
    }
}

