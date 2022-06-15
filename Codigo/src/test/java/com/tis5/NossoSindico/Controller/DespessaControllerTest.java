package com.tis5.NossoSindico.Controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tis5.NossoSindico.Service.CondominioService;
import com.tis5.NossoSindico.Service.DespessaService;
import com.tis5.NossoSindico.domain.Condominio;
import com.tis5.NossoSindico.domain.Despessa;
import com.tis5.NossoSindico.domain.DespessaResource;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
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

@ContextConfiguration(classes = {DespessaController.class})
@ExtendWith(SpringExtension.class)
class DespessaControllerTest {

    @MockBean
    private CondominioService condominioService;

    @Autowired
    private DespessaController despessaController;

    @MockBean
    private DespessaService despessaService;

    @Test
    void cadastroTest() throws Exception {

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

        Despessa despessa = new Despessa();
        despessa.setCondominio(condominio);
        despessa.setData_referente(LocalDate.ofEpochDay(1L));
        despessa.setDescricao("Descricao");
        despessa.setId(123L);
        despessa.setTitulo("Titulo");
        despessa.setValor(10.0d);
        when(this.despessaService.create((Despessa) any())).thenReturn(despessa);

        Condominio condominio1 = new Condominio();
        condominio1.setBairro("Bairro");
        condominio1.setCep("Cep");
        condominio1.setCidade("Cidade");
        condominio1.setCode("Code");
        condominio1.setId(123L);
        condominio1.setNome("Nome");
        condominio1.setNumero(10);
        condominio1.setRua("Rua");
        Optional<Condominio> ofResult = Optional.of(condominio1);
        when(this.condominioService.getCondominioById(anyLong())).thenReturn(ofResult);

        DespessaResource despessaResource = new DespessaResource();
        despessaResource.setData_referente(null);
        despessaResource.setDescricao("Descricao");
        despessaResource.setId_condominio(1L);
        despessaResource.setTitulo("Titulo");
        despessaResource.setValor(10.0d);
        String content = (new ObjectMapper()).writeValueAsString(despessaResource);

        //when
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/cadDespessa")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        //then
        MockMvcBuilders.standaloneSetup(this.despessaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"titulo\":\"Titulo\",\"descricao\":\"Descricao\",\"valor\":10.0,\"data_referente\":[1970,1,2],\"condominio"
                                        + "\":{\"id\":123,\"nome\":\"Nome\",\"rua\":\"Rua\",\"bairro\":\"Bairro\",\"cep\":\"Cep\",\"cidade\":\"Cidade\",\"numero\":10,"
                                        + "\"code\":\"Code\"}}"));
    }

    @Test
    void cadastroTest2() throws Exception {

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

        Despessa despessa = new Despessa();
        despessa.setCondominio(condominio);
        despessa.setData_referente(LocalDate.ofEpochDay(1L));
        despessa.setDescricao("Descricao");
        despessa.setId(123L);
        despessa.setTitulo("Titulo");
        despessa.setValor(10.0d);
        when(this.despessaService.create((Despessa) any())).thenReturn(despessa);
        when(this.condominioService.getCondominioById(anyLong())).thenReturn(Optional.empty());

        DespessaResource despessaResource = new DespessaResource();
        despessaResource.setData_referente(null);
        despessaResource.setDescricao("Descricao");
        despessaResource.setId_condominio(1L);
        despessaResource.setTitulo("Titulo");
        despessaResource.setValor(10.0d);
        String content = (new ObjectMapper()).writeValueAsString(despessaResource);

        //when
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/cadDespessa")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        //then
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.despessaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void listDespessasTest() throws Exception {
        //given
        when(this.despessaService.listDespessasCondominio((Condominio) any())).thenReturn(Optional.of(new ArrayList<>()));

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
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/listDespessa")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        //then
        MockMvcBuilders.standaloneSetup(this.despessaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void listDespessas2Test() throws Exception {
        //given
        Condominio condominio = new Condominio();
        condominio.setBairro("?");
        condominio.setCep("?");
        condominio.setCidade("?");
        condominio.setCode("?");
        condominio.setId(123L);
        condominio.setNome("?");
        condominio.setNumero(10);
        condominio.setRua("?");

        Despessa despessa = new Despessa();
        despessa.setCondominio(condominio);
        despessa.setData_referente(LocalDate.ofEpochDay(59L));
        despessa.setDescricao("?");
        despessa.setId(123L);
        despessa.setTitulo("?");
        despessa.setValor(10.0d);

        ArrayList<Despessa> despessaList = new ArrayList<>();
        despessaList.add(despessa);
        Optional<List<Despessa>> ofResult = Optional.of(despessaList);
        when(this.despessaService.listDespessasCondominio((Condominio) any())).thenReturn(ofResult);

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
        //when
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/listDespessa")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        //then
        MockMvcBuilders.standaloneSetup(this.despessaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":123,\"titulo\":\"?\",\"descricao\":\"?\",\"valor\":10.0,\"data_referente\":[1970,3,1],\"condominio\":{\"id\""
                                        + ":123,\"nome\":\"?\",\"rua\":\"?\",\"bairro\":\"?\",\"cep\":\"?\",\"cidade\":\"?\",\"numero\":10,\"code\":\"?\"}}]"));
    }


    @Test
    void listDespessas3Test() throws Exception {
        //given
        when(this.despessaService.listDespessasCondominio((Condominio) any())).thenReturn(Optional.empty());

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
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/listDespessa")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        //then
        MockMvcBuilders.standaloneSetup(this.despessaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

