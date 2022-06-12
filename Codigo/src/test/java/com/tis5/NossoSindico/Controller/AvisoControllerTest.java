package com.tis5.NossoSindico.Controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tis5.NossoSindico.Service.AvisoService;
import com.tis5.NossoSindico.Service.CondominioService;
import com.tis5.NossoSindico.domain.Aviso;
import com.tis5.NossoSindico.domain.AvisoResource;
import com.tis5.NossoSindico.domain.Condominio;

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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {AvisoController.class})
@ExtendWith(SpringExtension.class)
class AvisoControllerTest {
    @Autowired
    private AvisoController avisoController;

    @MockBean
    private AvisoService avisoService;

    @MockBean
    private CondominioService condominioService;

    /**
     * Method under test: {@link AvisoController#cadastro(AvisoResource)}
     */
    @Test
    void testCadastro() throws Exception {
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

        Aviso aviso = new Aviso();
        aviso.setCondominio(condominio1);
        aviso.setConteudo("Conteudo");
        aviso.setId(123L);
        aviso.setTitulo("Titulo");
        when(this.avisoService.create((Aviso) any())).thenReturn(aviso);

        AvisoResource avisoResource = new AvisoResource();
        avisoResource.setConteudo("Conteudo");
        avisoResource.setId_condominio(1L);
        avisoResource.setTitulo("Titulo");
        String content = (new ObjectMapper()).writeValueAsString(avisoResource);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/cadAviso")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.avisoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"titulo\":\"Titulo\",\"conteudo\":\"Conteudo\",\"condominio\":{\"id\":123,\"nome\":\"Nome\",\"rua\":\"Rua\","
                                        + "\"bairro\":\"Bairro\",\"cep\":\"Cep\",\"cidade\":\"Cidade\",\"numero\":10,\"code\":\"Code\"}}"));
    }

    /**
     * Method under test: {@link AvisoController#listAvisos(Condominio)}
     */
    @Test
    void testListAvisos() throws Exception {
        when(this.avisoService.listAvisosDeCondominio(anyLong())).thenReturn(Optional.of(new ArrayList<>()));

        Condominio condominio = new Condominio();
        condominio.setBairro("Bairro");
        condominio.setCep("Cep");
        condominio.setCidade("Cidade");
        condominio.setCode("Code");
        condominio.setId(123L);
        condominio.setNome("Nome");
        condominio.setNumero(10);
        condominio.setRua("Rua");
        String content = (new ObjectMapper()).writeValueAsString(condominio);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/listAvisos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.avisoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link AvisoController#listAvisos(Condominio)}
     */
    @Test
    void testListAvisos2() throws Exception {
        Condominio condominio = new Condominio();
        condominio.setBairro("?");
        condominio.setCep("?");
        condominio.setCidade("?");
        condominio.setCode("?");
        condominio.setId(123L);
        condominio.setNome("?");
        condominio.setNumero(10);
        condominio.setRua("?");

        Aviso aviso = new Aviso();
        aviso.setCondominio(condominio);
        aviso.setConteudo("?");
        aviso.setId(123L);
        aviso.setTitulo("?");

        ArrayList<Aviso> avisoList = new ArrayList<>();
        avisoList.add(aviso);
        Optional<List<Aviso>> ofResult = Optional.of(avisoList);
        when(this.avisoService.listAvisosDeCondominio(anyLong())).thenReturn(ofResult);

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
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/listAvisos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.avisoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":123,\"titulo\":\"?\",\"conteudo\":\"?\",\"condominio\":{\"id\":123,\"nome\":\"?\",\"rua\":\"?\",\"bairro\":\"?\",\"cep"
                                        + "\":\"?\",\"cidade\":\"?\",\"numero\":10,\"code\":\"?\"}}]"));
    }
}

