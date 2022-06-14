package com.tis5.NossoSindico.Controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
    @Disabled("TODO: Complete this test")
    void testCadastro() throws ParseException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.tis5.NossoSindico.Service.CondominioService.getCondominioById(long)" because "this.condominioService" is null
        //       at com.tis5.NossoSindico.Controller.DespessaController.cadastro(DespessaController.java:30)
        //   In order to prevent cadastro(DespessaResource)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   cadastro(DespessaResource).
        //   See https://diff.blue/R013 to resolve this issue.

        DespessaController despessaController = new DespessaController();

        DespessaResource despessaResource = new DespessaResource();
        despessaResource.setData_referente(LocalDate.ofEpochDay(1L));
        despessaResource.setDescricao("Descricao");
        despessaResource.setId_condominio(1L);
        despessaResource.setTitulo("Titulo");
        despessaResource.setValor(10.0d);
        despessaController.cadastro(despessaResource);
    }


    @Disabled("TODO: Complete this test")
    void testCadastro2() throws ParseException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.tis5.NossoSindico.Service.CondominioService.getCondominioById(long)" because "this.condominioService" is null
        //       at com.tis5.NossoSindico.Controller.DespessaController.cadastro(DespessaController.java:30)
        //   In order to prevent cadastro(DespessaResource)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   cadastro(DespessaResource).
        //   See https://diff.blue/R013 to resolve this issue.

        DespessaController despessaController = new DespessaController();
        DespessaResource despessaResource = mock(DespessaResource.class);
        when(despessaResource.getId_condominio()).thenReturn(1L);
        doNothing().when(despessaResource).setData_referente((LocalDate) any());
        doNothing().when(despessaResource).setDescricao((String) any());
        doNothing().when(despessaResource).setId_condominio(anyLong());
        doNothing().when(despessaResource).setTitulo((String) any());
        doNothing().when(despessaResource).setValor(anyDouble());
        despessaResource.setData_referente(LocalDate.ofEpochDay(1L));
        despessaResource.setDescricao("Descricao");
        despessaResource.setId_condominio(1L);
        despessaResource.setTitulo("Titulo");
        despessaResource.setValor(10.0d);
        despessaController.cadastro(despessaResource);
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

