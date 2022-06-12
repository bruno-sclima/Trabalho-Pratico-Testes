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
    @Disabled("TODO: Complete this test")
    void testCria() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.tis5.NossoSindico.Service.ReservaService.create(com.tis5.NossoSindico.domain.ReservaResource)" because "this.service" is null
        //       at com.tis5.NossoSindico.Controller.ReservaController.cria(ReservaController.java:25)
        //   In order to prevent cria(ReservaResource)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   cria(ReservaResource).
        //   See https://diff.blue/R013 to resolve this issue.

        ReservaController reservaController = new ReservaController();
        reservaController.cria(new ReservaResource());
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
                        .string(
                                "[{\"id\":123,\"data\":[1970,3,1],\"apto\":{\"id\":123,\"bloco\":\"?\",\"numero\":10,\"sindico\":true,\"condominio\":{"
                                        + "\"id\":123,\"nome\":\"?\",\"rua\":\"?\",\"bairro\":\"?\",\"cep\":\"?\",\"cidade\":\"?\",\"numero\":10,\"code\":\"?\"},\"usuario\":"
                                        + "{\"id\":123,\"nome\":\"?\",\"sobrenome\":\"?\",\"email\":\"jane.doe@example.org\",\"senha\":\"?\"}},\"lugar\":{\"id\":123,"
                                        + "\"nome\":\"?\",\"descricao\":\"?\",\"capacidadeMax\":1,\"id_condominio\":1},\"descricao\":\"?\"}]"));
    }
}

