package mult.second.project.domain.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class TestController {

    @GetMapping(value = "/")
    public String layout() {
        return "main_layout";
    }

    @GetMapping(value = "/test")
    public String test() {
        return "test";
    }
}
