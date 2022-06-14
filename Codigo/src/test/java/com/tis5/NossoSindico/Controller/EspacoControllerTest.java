package com.tis5.NossoSindico.Controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tis5.NossoSindico.Service.EspacoService;
import com.tis5.NossoSindico.domain.Condominio;
import com.tis5.NossoSindico.domain.Espaco;

import java.util.ArrayList;
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

@ContextConfiguration(classes = {EspacoController.class})
@ExtendWith(SpringExtension.class)
class EspacoControllerTest {
    @Autowired
    private EspacoController espacoController;

    @MockBean
    private EspacoService espacoService;

    @Test
    void criaTest() throws Exception {
        //given
        Espaco espaco = new Espaco();
        espaco.setCapacidadeMax(1);
        espaco.setDescricao("Descricao");
        espaco.setId(123L);
        espaco.setId_condominio(1);
        espaco.setNome("Nome");
        when(this.espacoService.create((Espaco) any())).thenReturn(espaco);

        Espaco espaco1 = new Espaco();
        espaco1.setCapacidadeMax(1);
        espaco1.setDescricao("Descricao");
        espaco1.setId(123L);
        espaco1.setId_condominio(1);
        espaco1.setNome("Nome");
        String content = (new ObjectMapper()).writeValueAsString(espaco1);
        //when
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/createEspaco")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        //then
        MockMvcBuilders.standaloneSetup(this.espacoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"nome\":\"Nome\",\"descricao\":\"Descricao\",\"capacidadeMax\":1,\"id_condominio\":1}"));
    }

    @Test
    void listaTest() throws Exception {
        //given
        when(this.espacoService.listByCondominio((Condominio) any())).thenReturn(Optional.of(new ArrayList<>()));

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
        //when
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/listEspaco")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        //then
        MockMvcBuilders.standaloneSetup(this.espacoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void lista2Test() throws Exception {
        //given
        when(this.espacoService.listByCondominio((Condominio) any())).thenReturn(Optional.empty());

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
        //when
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/listEspaco")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        //then
        MockMvcBuilders.standaloneSetup(this.espacoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

