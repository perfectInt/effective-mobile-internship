package ru.effectivemobile.todolist;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import ru.effectivemobile.todolist.dto.Response;
import ru.effectivemobile.todolist.dto.TaskDto;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    @Order(1)
    public void testCreateTask() throws Exception {
        TaskDto taskDto = new TaskDto();
        taskDto.setTitle("test title");
        taskDto.setDescription("test description");
        taskDto.setCompleted(false);

        RequestBuilder requestBuilderPost = MockMvcRequestBuilders.post("/api/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(taskDto));

        mockMvc.perform(requestBuilderPost).andExpect(status().isOk());
    }

    @Test
    @Order(2)
    public void testGetTask() throws Exception {
        RequestBuilder requestBuilderGet = MockMvcRequestBuilders.get("/api/v1/tasks/1")
                .accept(MediaType.APPLICATION_JSON_VALUE);

        MvcResult mvcResult = mockMvc.perform(requestBuilderGet).andReturn();
        String response = mvcResult.getResponse().getContentAsString();

        Response data = mapper.readValue(response, Response.class);
        assertNotNull(data);
        assertTrue(data.getSuccess());
    }

    @Test
    @Order(3)
    public void testGetNonExistingTask() throws Exception {
        RequestBuilder requestBuilderGet = MockMvcRequestBuilders.get("/api/v1/tasks/1132")
                .accept(MediaType.APPLICATION_JSON_VALUE);

        mockMvc.perform(requestBuilderGet).andExpect(status().isNotFound());
    }

    @Test
    @Order(4)
    public void testUpdateTask() throws Exception {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(1L);
        taskDto.setTitle("test2 title");
        taskDto.setDescription("test2 description");
        taskDto.setCompleted(false);
        RequestBuilder requestBuilderPut = MockMvcRequestBuilders.post("/api/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(taskDto));

        mockMvc.perform(requestBuilderPut).andExpect(status().isOk());
    }

    @Test
    @Order(5)
    public void testDeleteTask() throws Exception {
        RequestBuilder requestBuilderDelete = MockMvcRequestBuilders.delete("/api/v1/tasks//1");
        mockMvc.perform(requestBuilderDelete).andExpect(status().isOk());

        RequestBuilder requestBuilderGet = MockMvcRequestBuilders.get("/api/v1/tasks/1")
                .accept(MediaType.APPLICATION_JSON_VALUE);

        mockMvc.perform(requestBuilderGet).andExpect(status().isNotFound());
    }
}
