package com.cb.demo.demo;

import com.cb.demo.demo.controller.TestController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.mockito.Mockito.when;

@WebMvcTest(TestController.class)
public class MyTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TestController testController;

    @Test
    public void testMessageEndPoint() throws Exception {
        when(testController.getMessage()).thenReturn(new ResponseEntity<>("Hello CB", HttpStatus.OK));

        // Perform GET request and verify response
        mockMvc.perform(get("/demo-app/get"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello CB"));
    }

}
