package es.cic.curso25.proy009.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.cic.curso25.proy009.model.Arbol;
import es.cic.curso25.proy009.model.Rama;
import es.cic.curso25.proy009.service.ProyService;

@SpringBootTest
@AutoConfigureMockMvc
public class ProyControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ProyService proyService;

    @Test
    void testIntegration() throws Exception{

        Arbol arbol = new Arbol();
        arbol.setEdad(20);
        arbol.setAltura(10d);
        arbol.setTipo("Manzano");

        Rama rama1 = new Rama();
        rama1.setLongitud(3);
        rama1.setArbol(arbol);

        Rama rama2 = new Rama();
        rama2.setLongitud(5);
        rama2.setArbol(arbol);

        arbol.setRamas(List.of(rama1, rama2));

        String arbolJSON = objectMapper.writeValueAsString(arbol);

        MvcResult mvcResult = mockMvc.perform(post("/arbol")
                .contentType("application/json")
                .content(arbolJSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(result -> {
                    assertNotNull(
                        objectMapper.readValue(
                            result.getResponse().getContentAsString(), Arbol.class)
                    );
                })
                .andReturn();

        Arbol arbolCreado = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Arbol.class);
        Long idArbol = arbolCreado.getId();

        mockMvc.perform(get("/arbol/" + idArbol))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(result -> {
                    assertEquals(
                        objectMapper.readValue(result.getResponse().getContentAsString(), Arbol.class).getId(), idArbol);
                });

        arbolCreado.setTipo("Nogal");
        arbolCreado.getRamas().get(0).setLongitud(30d);
        arbolCreado.getRamas().get(1).setLongitud(40d);

        String arbolCreadoJSON = objectMapper.writeValueAsString(arbolCreado);

        mockMvc.perform(put("/arbol")
                .contentType("application/json")
                .content(arbolCreadoJSON))
                .andDo(print())
                .andExpect(status().isOk());

        mockMvc.perform(delete("/arbol/" + idArbol))
                .andDo(print())
                .andExpect(status().isOk());

    }

}
