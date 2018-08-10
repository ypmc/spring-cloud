package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	@RequestMapping("/icbc")
    public String greeting(@RequestParam(value="name", defaultValue="world") String name) {
        return "icbc " + name ;
    }
}
