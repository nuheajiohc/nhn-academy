import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.springmvc.controller.StudentRestController;
import com.nhnacademy.springmvc.domain.Student;
import com.nhnacademy.springmvc.domain.StudentModifyRequest;
import com.nhnacademy.springmvc.domain.StudentRegisterRequest;
import com.nhnacademy.springmvc.exception.StudentNotFoundException;
import com.nhnacademy.springmvc.exception.ValidationFailedException;
import com.nhnacademy.springmvc.repository.StudentRepository;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

public class StudentRestControllerTest {

    private MockMvc mockMvc;
    private StudentRepository studentRepository;

    private Long studentId = 1L;


    @BeforeEach
    void setUp() {
        studentRepository = mock(StudentRepository.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new StudentRestController(studentRepository)).build();
    }

    @Test
    void getTest() throws Exception {
        Student student = new Student(1, "김학생", "kim.student@nhnacademy.com", 100, "훌륭");
        when(studentRepository.exists(studentId)).thenReturn(true);
        when(studentRepository.getStudent(studentId)).thenReturn(student);

        mockMvc.perform(get("/students/{studentId}", studentId).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(studentId));
    }

    @Test
    void getStudentNotFoundExceptionTest() {

        Throwable th = catchThrowable(
                () -> mockMvc.perform(get("/students/{studentId}", studentId).accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(jsonPath("$.id").value(studentId)));

        assertThat(th).isInstanceOf(NestedServletException.class).hasCauseInstanceOf(StudentNotFoundException.class);
    }

    @Test
    void postTest() throws Exception {
        StudentRegisterRequest request = new StudentRegisterRequest("김학생", "kim.student@nhnacademy.com", 100, "훌륭");

        Student student = new Student(1, "김학생", "kim.student@nhnacademy.com", 100, "훌륭");
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(request);

        when(studentRepository.register(anyString(), anyString(), anyInt(), anyString())).thenReturn(student);

        mockMvc.perform(post("/students").contentType(MediaType.APPLICATION_JSON).content(content))
                .andExpect(status().isCreated()).andExpect(jsonPath("$.name").value("김학생"))
                .andExpect(jsonPath("$.email").value("kim.student@nhnacademy.com"))
                .andExpect(jsonPath("$.score").value(100)).andExpect(jsonPath("$.comment").value("훌륭"));
    }

    @Test
    void postValidExceptionTest() throws Exception {
        StudentRegisterRequest request = new StudentRegisterRequest("김학생", "kim.student@nhnacademy.com", 110, "훌륭");

        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(request);

        Throwable th = catchThrowable(
                () -> mockMvc.perform(post("/students").contentType(MediaType.APPLICATION_JSON).content(content)));

        assertThat(th).isInstanceOf(NestedServletException.class).hasCauseInstanceOf(ValidationFailedException.class);
    }

    @Test
    void putTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        StudentModifyRequest registerStudent = new StudentModifyRequest("김학생", "kim.student@nhnacademy.com", 100, "훌륭");
        String content = objectMapper.writeValueAsString(registerStudent);

        Student student = new Student(studentId, "김학생", "kim.student@nhnacademy.com", 50, "보통");
        when(studentRepository.exists(studentId)).thenReturn(true);
        when(studentRepository.getStudent(studentId)).thenReturn(student);

        mockMvc.perform(put("/students/{studentId}", studentId).content(content).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void putValidExceptionTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        StudentModifyRequest student = new StudentModifyRequest("김학생", "kim.student@nhnacademy.com", 101, "훌륭");
        String content = objectMapper.writeValueAsString(student);

        Throwable th = catchThrowable(() -> mockMvc.perform(
                put("/students/{studentId}", studentId).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).content(content)));
        AssertionsForClassTypes.assertThat(th).isInstanceOf(NestedServletException.class)
                .hasCauseInstanceOf(ValidationFailedException.class);
    }

    @Test
    void putStudentNotFoundExceptionTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        StudentModifyRequest student = new StudentModifyRequest("김학생", "kim.student@nhnacademy.com", 100, "훌륭");
        String content = objectMapper.writeValueAsString(student);

        Throwable th = catchThrowable(() -> mockMvc.perform(
                put("/students/{studentId}", studentId).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).content(content)));

        AssertionsForClassTypes.assertThat(th).isInstanceOf(NestedServletException.class)
                .hasCauseInstanceOf(StudentNotFoundException.class);
    }

}