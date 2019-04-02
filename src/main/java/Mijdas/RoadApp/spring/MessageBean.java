package Mijdas.RoadApp.spring;

import java.time.LocalTime;
import org.springframework.stereotype.Service;

@Service
public class MessageBean {

    public String getMessage() {
        return "Daddy was clicked " + LocalTime.now();
    }
}
