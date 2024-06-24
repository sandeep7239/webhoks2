package com.project.webHooks.parser;

import com.project.webHooks.entity.WebhookEvent;

import java.util.List;

public interface WebhookStore {
    WebhookEvent save(WebhookEvent webhookEvent);
    List<WebhookEvent> filter(String provider, String eventType, String status);
}
