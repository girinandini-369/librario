package com.librario.controller;

import com.librario.service.OverdueService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/overdues")
public class OverdueController {

    private final OverdueService service;

    public OverdueController(OverdueService service) {
        this.service = service;
    }

    @GetMapping("/check")
    public List<Map<String, Object>> checkOverdues() {
        return service.checkOverdues();
    }
}
