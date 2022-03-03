package edu.bowiestate.covidTracker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/start")
    public String start() {
        return "Hello this is the start";
    }
}
