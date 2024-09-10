package com.trf5.gitchatbridge.logs.controller;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.*;

@Slf4j
@RestController
public class LogErrorController {

    private static final Random random = new Random();



    @GetMapping("/log-error")
    public String logRandomError() {
        try {
            switch (random.nextInt(20)) {
                case 0:
                    throw new NullPointerException("Objeto nulo encontrado inesperadamente."); 
                case 1:
                    throw new IllegalArgumentException("Argumento inválido fornecido.");
                case 2:
                    throw new FileNotFoundException("Arquivo não encontrado no sistema.");
                case 3:
                    throw new SQLException("Erro ao executar operação no banco de dados.");
                case 4:
                    throw new IOException("Erro de entrada/saída genérico.");
                case 5:
                    throw new ArithmeticException("Erro aritmético, como divisão por zero.");
                case 6:
                    throw new ArrayIndexOutOfBoundsException("Índice de array fora dos limites.");
                case 7:
                    throw new StringIndexOutOfBoundsException("Índice de string fora dos limites.");
                case 8:
                    throw new ClassCastException("Erro ao converter tipos incompatíveis.");
                case 9:
                    throw new NumberFormatException("Erro ao converter string em número.");
                case 10:
                    throw new UnsupportedOperationException("Operação não suportada.");
                case 11:
                    throw new NoSuchElementException("Elemento não encontrado na coleção.");
                case 12:
                    throw new ConnectException("Falha ao conectar a um host remoto.");
                case 13:
                    throw new SocketTimeoutException("Tempo limite excedido na conexão de socket.");
                case 14:
                    throw new UnknownHostException("Host desconhecido.");
                case 15:
                    throw new MalformedURLException("URL malformada.");
                case 16:
                    throw new NoSuchAlgorithmException("Algoritmo criptográfico não encontrado.");
                case 17:
                    throw new InvalidKeyException("Chave criptográfica inválida.");
                case 18:
                    throw new IllegalBlockSizeException("Tamanho de bloco ilegal na criptografia.");
                case 19:
                    throw new BadPaddingException("Padding incorreto na criptografia.");
            }
        } catch (Exception e) {

            log.error("Erro aleatório gerado: {}", e.getMessage());
            return "Erro registrado: " + e.getClass().getSimpleName() + " - " + e.getMessage();
        }
        return "Nenhum erro gerado desta vez.";
    }
}

