package com.project.webHooks.service;

import com.project.webHooks.entity.WebhookEvent;

import java.util.List;
import java.util.UUID;

/**
 * Webh
 */
public interface WebhookEventService {
    WebhookEvent saveEvent(String provider, Object event);

    List<WebhookEvent> filterEvent(String provider, String eventType, String status);

    WebhookEvent updateEventStatus(UUID eventId, String status);
}
