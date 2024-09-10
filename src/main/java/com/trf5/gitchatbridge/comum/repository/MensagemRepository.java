package com.trf5.gitchatbridge.comum.repository;

import com.trf5.gitchatbridge.comum.model.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MensagemRepository extends JpaRepository<Mensagem, Long> {
}