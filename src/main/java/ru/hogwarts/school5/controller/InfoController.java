package ru.hogwarts.school5.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.IntStream;
import java.util.stream.Stream;

@RestController
public class InfoController {
    public final static Logger logger = LoggerFactory.getLogger(StudentController.class);
    @Value("${server.port}")
    private String port;

    @GetMapping("/port")
    public String getPort() {
        return "Приложение запускается на порту: " + port;
    }

    @GetMapping("/calculateSum")
    public int calculateSum() {
        long startTime = System.currentTimeMillis();
        int sum = IntStream.iterate(1, a -> a + 1)
                .limit(1_000_000)
                .reduce(0, (a, b) -> a + b);
        logger.info("Performing time: {}", System.currentTimeMillis() - startTime);
        return sum;
    }

}