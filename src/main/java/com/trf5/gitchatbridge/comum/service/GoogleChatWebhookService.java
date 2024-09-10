package com.trf5.gitchatbridge.comum.service;

import com.trf5.gitchatbridge.comum.model.Mensagem;
import com.trf5.gitchatbridge.comum.repository.MensagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;


@Service
public class GoogleChatWebhookService {

    private RestTemplate restTemplate;

    @Autowired
    private MensagemRepository mensagemRepository;


    public GoogleChatWebhookService() {
        this.restTemplate = new RestTemplate();
    }



    public void enviarMensagem(String mensagem, String webhookUrl, boolean salvar) {
        Mensagem novaMensagem = Mensagem.builder().conteudo(mensagem).webhookUrl(webhookUrl).dataHoraEnvio(LocalDateTime.now()).build();
        if(salvar) {
            enviarMensagemSalvarAsync(novaMensagem);
        } else {
            enviarMensagemAsync(novaMensagem);
        }
    }

    public CompletableFuture<String> enviarMensagemAsync(Mensagem mensagem) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<String> request = new HttpEntity<>("{\"text\": \"" + mensagem.getConteudo() + "\"}", headers);
                restTemplate.postForEntity(mensagem.getWebhookUrl(), request, String.class);
                return "Mensagem enviada com sucesso";

            } catch (Exception e) {
                return "Erro ao enviar a mensagem: " + e.getMessage();
            }
        });
    }

    public CompletableFuture<String> enviarMensagemSalvarAsync(Mensagem mensagem) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<String> request = new HttpEntity<>("{\"text\": \"" + mensagem.getConteudo() + "\"}", headers);
                restTemplate.postForEntity(mensagem.getWebhookUrl(), request, String.class);
                mensagem.setStatus("ENVIADA"); // Atualiza o status para "ENVIADA"
                mensagemRepository.save(mensagem); // Salva a mensagem atualizada com delay
                return "Mensagem enviada com sucesso";
            } catch (Exception e) {
                mensagem.setStatus("ERRO: " + e.getMessage()); // Atualiza o status para "ERRO"
                mensagemRepository.save(mensagem); // Salva a mensagem atualizada
                return "Erro ao enviar a mensagem: " + e.getMessage();
            }
        });
    }

}
