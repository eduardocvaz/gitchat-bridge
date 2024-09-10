package com.trf5.gitchatbridge.logs.config;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import ch.qos.logback.core.encoder.Encoder;
import com.trf5.gitchatbridge.comum.service.GoogleChatWebhookService;
import lombok.Setter;

public class WebhookAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {

    private long lastSend = 0L;
    private long interval = 10L;
    @Setter
    private Encoder<ILoggingEvent> encoder;

    @Setter
    private String errorUrl;

    @Setter
    private GoogleChatWebhookService webhook;

    @Override
    protected void append(ILoggingEvent event) {
        // Not Error level
        if (!event.getLevel().isGreaterOrEqual(Level.ERROR)) return;

        // Limit send rate
        if (event.getTimeStamp() < lastSend + interval) return;

        String message = new String(encoder.encode(event));

        if(message.contains("[NO-WEBHOOK]")) return;

        webhook.enviarMensagem(message, errorUrl, false);

        lastSend = event.getTimeStamp();
    }
}
