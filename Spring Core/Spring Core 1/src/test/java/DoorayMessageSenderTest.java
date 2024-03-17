import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;

import com.nhn.dooray.client.DoorayHook;
import com.nhn.dooray.client.DoorayHookSender;
import com.nhnacademy.edu.springframework.User;
import com.nhnacademy.edu.springframework.messagesender.DoorayMessageSender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class DoorayMessageSenderTest {

    @Mock
    private DoorayHookSender doorayHookSenderMock;

    @InjectMocks
    private DoorayMessageSender doorayMessageSender;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSendMessage() {
        User user = new User("user");
        String message = "안녕";

        doNothing().when(doorayHookSenderMock).send(any(DoorayHook.class));
        boolean result = doorayMessageSender.sendMessage(user, message);
        Assertions.assertTrue(result);
    }
}
