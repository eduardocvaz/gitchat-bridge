package com.trf5.gitchatbridge.comum.controller;

import com.trf5.gitchatbridge.comum.model.MensagemDTO;
import com.trf5.gitchatbridge.comum.service.GoogleChatWebhookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mensagens") // Define o caminho base para o endpoint
public class MensagemController {

    @Autowired
    private GoogleChatWebhookService webhookService; // Injete seu serviço

    @PostMapping // Mapeia para o método HTTP POST
    public ResponseEntity<String> enviarMensagem(@RequestBody MensagemDTO mensagemDTO) {
        try {
            webhookService.enviarMensagem(mensagemDTO.getConteudo(), mensagemDTO.getWebhookUrl(), true);
            return ResponseEntity.ok("Mensagem enviada para processamento.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao processar a mensagem: " + e.getMessage());
        }
    }
}
