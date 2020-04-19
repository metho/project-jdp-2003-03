package com.kodilla.ecommercee.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodilla.ecommercee.entity.ProductGroup;
import com.kodilla.ecommercee.repository.ProductGroupRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerMock {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ProductGroupRepository repository;

    @Autowired
    private UserRepository userRepository;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void testSimpleStatusOk() throws Exception {
        this.mvc.perform(get("/v1/group"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testSimpleStatusNotFound() throws Exception {
        this.mvc.perform(get("/v1/group/100"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void testHttpGetStatusOk() throws Exception {
        String name = "GroupA";
        // Given
        ProductGroup group = new ProductGroup(name);

        // When
        repository.save(group);
        Long id = group.getId();

        // Then
        this.mvc.perform(get("/v1/group/" + id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.name").value(name));

        // Clean
        repository.deleteById(id);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testAuthorisationRoleAdmin() throws Exception {
        String name = "GroupC";
        // Given
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/v1/group")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(asJsonString(new ProductGroup(name)));
        // When

        // Then
        this.mvc.perform(builder)
                .andDo(print())
                .andExpect(status().isOk());
        ProductGroup group = repository.findFirstByName(name).orElseGet(() -> new ProductGroup("none"));
        Assert.assertEquals(name, group.getName());

        // Clean
        repository.deleteById(group.getId());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testAuthorisationRoleUser() throws Exception {
        String name = "GroupD";
        // Given
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/v1/group")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(asJsonString(new ProductGroup(name)));
        // When

        // Then
        this.mvc.perform(builder)
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testAuthorisationDelete() throws Exception {
        String name = "GroupE";
        // Given
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/v1/group")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(asJsonString(new ProductGroup(name)));

        // When
        this.mvc.perform(builder)
                .andDo(print())
                .andExpect(status().isOk());
        ProductGroup group = repository.findFirstByName(name).orElseGet(() -> new ProductGroup("none"));

        // Then
        this.mvc.perform(get("/v1/group/" + group.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.name").value(name));

        // Clean
        repository.deleteById(group.getId());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
