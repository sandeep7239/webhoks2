package com.project.webHooks.controller;

import com.project.webHooks.entity.WebhookEvent;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

public interface WebhookEventController {
    @PostMapping("/{provider}/save")
    WebhookEvent saveEvent(@PathVariable String provider, @RequestBody Object webPayloadData);

    @GetMapping("/filter")
    Iterable<WebhookEvent> filterEvent(@RequestParam(required = false) String provider,
                                       @RequestParam(required = false) String eventType,
                                       @RequestParam(required = false) String status);

    @PutMapping("/update-status/{eventId}")
    WebhookEvent updateEventStatus(@PathVariable UUID eventId, @RequestParam String status);
}
