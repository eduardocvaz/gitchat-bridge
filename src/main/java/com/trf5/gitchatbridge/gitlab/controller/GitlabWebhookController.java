package com.trf5.gitchatbridge.gitlab.controller;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.MustacheException;
import com.samskivert.mustache.Template;
import com.trf5.gitchatbridge.gitlab.model.IssueEvent;
import com.trf5.gitchatbridge.gitlab.model.JobEvent;
import com.trf5.gitchatbridge.gitlab.model.MergeRequestEvent;
import com.trf5.gitchatbridge.gitlab.model.PipelineEvent;
import com.trf5.gitchatbridge.comum.service.GoogleChatWebhookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.mustache.MustacheResourceTemplateLoader;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.Reader;

@RestController
@Slf4j
@RequestMapping("/webhooks-gitlab")
public class GitlabWebhookController {
    @Autowired
    private Mustache.Compiler mustacheCompiler;
    @Autowired
    private MustacheResourceTemplateLoader mustacheResourceTemplateLoader;
    @Autowired
    private GoogleChatWebhookService webhookService;

    @Value("${googlechat.webhook.gitlab.url}")
    private String webhookGitlabUrl;

    private Template getTemplate(String templateName) throws Exception {
        Reader reader = mustacheResourceTemplateLoader.getTemplate(templateName);
        return mustacheCompiler.compile(reader);
    }

    private void enviarNotificacao(String templateName, Object payload) {
        try {
            String message = getTemplate(templateName).execute(payload);
            webhookService.enviarMensagem(message, webhookGitlabUrl, true);
            log.info("Notificação enviada com sucesso: {}", message);
        } catch (IOException e) {
            log.error("Erro ao carregar o template {}: {}", templateName, e.getMessage());
        } catch (MustacheException e) {
            log.error("Erro ao renderizar o template {}: {}", templateName, e.getMessage());
        } catch (Exception e) {
            log.error("Erro inesperado ao enviar a notificação: {}", e.getMessage());
        }
    }

    @PostMapping("/merge-request")
    public void handleMergeRequestEvent(@RequestBody MergeRequestEvent payload) {
        log.info("Merge request event received: {}", payload);
        String action = payload.getObjectAttributes().getAction();

        String templateName = switch (action == null ? "unknown" : action) {
            case "open" -> "merge_request_opened";
            case "approved" -> "merge_request_approved";
            case "merge" -> "merge_request_merged";
            case "close" -> "merge_request_closed";
            default -> "default_notification";
        };
        if(!templateName.equals("default_notification")) {
            enviarNotificacao(templateName, payload);
        }
    }

    @PostMapping("/issue")
    public void handleIssueEvent(@RequestBody IssueEvent payload) {
        log.info("Issue event received: {}", payload);
        String action = payload.getObjectAttributes().getAction();

        String templateName = switch (action == null ? "unknown" : action) {
            case "open" -> "issue_opened";
            case "close" -> "issue_closed";
            default -> "default_notification";
        };
        if(!templateName.equals("default_notification")) {
            enviarNotificacao(templateName, payload);
        }
    }

    @PostMapping("/pipeline")
    public void handlePipelineEvent(@RequestBody PipelineEvent payload) {
        log.info("Pipeline event received: {}", payload);
        String status = payload.getObjectAttributes().getStatus();

        String templateName = switch (status == null ? "unknown" : status) {
            case "failed" -> "pipeline_failed";
            case "success" -> "pipeline_success";
            default -> "default_notification";
        };
        if(!templateName.equals("default_notification")) {
            enviarNotificacao(templateName, payload);
        }
    }

    @PostMapping("/job")
    public void handleJobEvent(@RequestBody JobEvent payload) {
        log.info("Job event received: {}", payload);
        if (payload.getBuildStatus().equals("failed")) {
            enviarNotificacao("job_failed", payload);
        }
    }

}
