package com.trf5.gitchatbridge.comum.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@Table(name = "mensagem")
@AllArgsConstructor
public class Mensagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // (opcional) Se você tiver uma tabela com IDs auto-incrementais
    @Column(name = "conteudo")
    private String conteudo;

    @Column(name = "webhook_url")
    private String webhookUrl;

    @Column(name = "data_hora_envio", columnDefinition = "TIMESTAMP")
    private LocalDateTime dataHoraEnvio; // Para registrar quando a mensagem foi enviada

    @Column(name = "status")
    private String status; // "ENVIADA", "ERRO", etc.

    public Mensagem() {

    }
}
