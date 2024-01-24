package com.greenbridge.unit;

import com.greenbridge.GreenBridgeApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
    @SpringBootTest(classes = GreenBridgeApplication.class)
    @AutoConfigureMockMvc
    public class TF4 {
        @Autowired
        MockMvc mockMvc;

        @Test
        public void testChatbotCorretto() throws Exception {
            String command = "{\"command\":\"/start\"}";

            mockMvc.perform(post("/api/executeCommand")
                            .content(command))
                    .andExpect(status().isOk());

        }
        @Test
        public void testChatbotErrato() throws Exception {
            String command = "{\"command\":\"start\"}";

            mockMvc.perform(post("/api/executeCommand")
                            .content(command))
                    .andExpect(content().string("[\"Comando non riconosciuto\"]"));
        }
    }