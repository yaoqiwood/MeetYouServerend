package com.cx.meetyoubackend;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(classes = MeetYouBackendApplication.class)
@AutoConfigureMockMvc
class MeetYouBackendApplicationTests {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void testYourEndpoint() throws Exception {
    mockMvc.perform(get("/api/open/test"))
        .andExpect(status().isOk());
  }

  @Test
  void contextLoads() {
  }

}
