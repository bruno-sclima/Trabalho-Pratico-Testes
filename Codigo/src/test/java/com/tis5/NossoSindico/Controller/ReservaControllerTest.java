package com.tis5.NossoSindico.Controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tis5.NossoSindico.Service.ReservaService;
import com.tis5.NossoSindico.domain.Apartamento;
import com.tis5.NossoSindico.domain.Condominio;
import com.tis5.NossoSindico.domain.Espaco;
import com.tis5.NossoSindico.domain.Reserva;
import com.tis5.NossoSindico.domain.ReservaResource;
import com.tis5.NossoSindico.domain.Usuario;

import java.time.LocalDate;
import java.util.ArrayList;
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

@ContextConfiguration(classes = {ReservaController.class})
@ExtendWith(SpringExtension.class)
class ReservaControllerTest {
    @Autowired
    private ReservaController reservaController;

    @MockBean
    private ReservaService reservaService;

    /**
     * Method under test: {@link ReservaController#cria(ReservaResource)}
     */
    @Test
    void testCria() throws Exception {
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

        Espaco espaco = new Espaco();
        espaco.setCapacidadeMax(1);
        espaco.setDescricao("Descricao");
        espaco.setId(123L);
        espaco.setId_condominio(1);
        espaco.setNome("Nome");

        Reserva reserva = new Reserva();
        reserva.setApto(apartamento);
        reserva.setData(LocalDate.ofEpochDay(1L));
        reserva.setDescricao("Descricao");
        reserva.setId(123L);
        reserva.setLugar(espaco);
        Optional<Reserva> ofResult = Optional.of(reserva);
        when(this.reservaService.create((ReservaResource) any())).thenReturn(ofResult);

        ReservaResource reservaResource = new ReservaResource();
        reservaResource.setData(null);
        reservaResource.setDescricao("Descricao");
        reservaResource.setId_condominio(1L);
        reservaResource.setId_espaco(1L);
        reservaResource.setNumero(10);
        reservaResource.setNumero_pessoas(10);
        String content = (new ObjectMapper()).writeValueAsString(reservaResource);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/createReserva")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.reservaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"data\":[1970,1,2],\"apto\":{\"id\":123,\"bloco\":\"Bloco\",\"numero\":10,\"sindico\":true,\"condominio\""
                                        + ":{\"id\":123,\"nome\":\"Nome\",\"rua\":\"Rua\",\"bairro\":\"Bairro\",\"cep\":\"Cep\",\"cidade\":\"Cidade\",\"numero\":10,\"code"
                                        + "\":\"Code\"},\"usuario\":{\"id\":123,\"nome\":\"Nome\",\"sobrenome\":\"Sobrenome\",\"email\":\"jane.doe@example.org\","
                                        + "\"senha\":\"Senha\"}},\"lugar\":{\"id\":123,\"nome\":\"Nome\",\"descricao\":\"Descricao\",\"capacidadeMax\":1,\"id"
                                        + "_condominio\":1},\"descricao\":\"Descricao\"}"));
    }

    /**
     * Method under test: {@link ReservaController#cria(ReservaResource)}
     */
    @Test
    void testCria2() throws Exception {
        when(this.reservaService.create((ReservaResource) any())).thenReturn(Optional.empty());

        ReservaResource reservaResource = new ReservaResource();
        reservaResource.setData(null);
        reservaResource.setDescricao("Descricao");
        reservaResource.setId_condominio(1L);
        reservaResource.setId_espaco(1L);
        reservaResource.setNumero(10);
        reservaResource.setNumero_pessoas(10);
        String content = (new ObjectMapper()).writeValueAsString(reservaResource);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/createReserva")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.reservaController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link ReservaController#lista(Condominio)}
     */
    @Test
    void testLista() throws Exception {
        when(this.reservaService.listByCondominio((Condominio) any())).thenReturn(new ArrayList<>());

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
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/listReserva")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.reservaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ReservaController#lista(Condominio)}
     */
    @Test
    void testLista2() throws Exception {
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

        Espaco espaco = new Espaco();
        espaco.setCapacidadeMax(1);
        espaco.setDescricao("?");
        espaco.setId(123L);
        espaco.setId_condominio(1);
        espaco.setNome("?");

        Reserva reserva = new Reserva();
        reserva.setApto(apartamento);
        reserva.setData(LocalDate.ofEpochDay(59L));
        reserva.setDescricao("?");
        reserva.setId(123L);
        reserva.setLugar(espaco);

        ArrayList<Reserva> reservaList = new ArrayList<>();
        reservaList.add(reserva);
        when(this.reservaService.listByCondominio((Condominio) any())).thenReturn(reservaList);

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
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/listReserva")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.reservaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("[{\"id\":123,\"data\":[1970,3,1],\"apto\":{\"id\":123,\"bloco\":\"?\",\"numero\":10,\"sindico\":true,\"condominio\":{"
                                        + "\"id\":123,\"nome\":\"?\",\"rua\":\"?\",\"bairro\":\"?\",\"cep\":\"?\",\"cidade\":\"?\",\"numero\":10,\"code\":\"?\"},\"usuario\":"
                                        + "{\"id\":123,\"nome\":\"?\",\"sobrenome\":\"?\",\"email\":\"jane.doe@example.org\",\"senha\":\"?\"}},\"lugar\":{\"id\":123,"
                                        + "\"nome\":\"?\",\"descricao\":\"?\",\"capacidadeMax\":1,\"id_condominio\":1},\"descricao\":\"?\"}]"));
    }
}

