package com.project.webHooks.service;

import com.project.webHooks.entity.WebhookEvent;
import com.project.webHooks.factory.PayloadParserFactory;
import com.project.webHooks.repository.WebhookRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class WebhookEventServiceImpl implements WebhookEventService {

    private final WebhookRepository webhookRepository;
    private final PayloadParserFactory payloadParserFactory;

    public WebhookEventServiceImpl(WebhookRepository webhookRepository, PayloadParserFactory payloadParserFactory) {
        this.webhookRepository = webhookRepository;
        this.payloadParserFactory = payloadParserFactory;
    }

    @Override
    public WebhookEvent saveEvent(String provider, Object payload) {
        WebhookEvent event = payloadParserFactory.getParser(provider).parsePayload(provider,payload);
        event.setEventId(UUID.randomUUID());
        return webhookRepository.save(event);
    }

    @Override
    public List<WebhookEvent> filterEvent(String provider, String eventType, String status) {
        return webhookRepository.filterEvents(provider, eventType, status);
    }

    @Override
    public WebhookEvent updateEventStatus(UUID eventId, String status) {
        WebhookEvent event = webhookRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found"));
        event.setStatus(status);
        return webhookRepository.save(event);
    }
}
