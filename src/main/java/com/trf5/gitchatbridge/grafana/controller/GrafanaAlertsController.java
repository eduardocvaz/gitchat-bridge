package com.trf5.gitchatbridge.grafana.controller;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@RestController
public class GrafanaAlertsController {

    private List<Class<?>> loadedClasses = new ArrayList<>();

    private List<byte[]> memoryHog = new ArrayList<>();

    @GetMapping("/consume-cpu")
    public String loadTest(@RequestParam(defaultValue = "15") int seconds) {
        int numThreads = 8; // Ajuste o número de threads
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        try {
            for (int i = 0; i < numThreads; i++) {
                executor.submit(() -> {
                    long startTime = System.currentTimeMillis();
                    int duration = seconds * 1000;

                    while (System.currentTimeMillis() - startTime < duration) {
                        BigInteger factorial = BigInteger.ONE;
                        for (int j = 2; j <= 10000; j++) {
                            factorial = factorial.multiply(BigInteger.valueOf(j));
                        }
                    }
                });
            }
        } catch (Exception e) {
            return "Erro durante o teste de carga: " + e.getMessage();
        } finally {
            executor.shutdown();
        }

        return String.format("Teste de carga iniciado! (%d threads, %d segundos)", numThreads, seconds);
    }

    @GetMapping("/consume-memory-heap")
    public String consumeMemory(@RequestParam(defaultValue = "10") int megabytes) {
        for (int i = 0; i < megabytes; i++) {
            memoryHog.add(new byte[1024 * 1024]); // Adiciona 1 MB à lista
        }
        return "Consumindo " + megabytes + " MB de memória Heap";
    }

    @GetMapping("/release-memory")
    public String releaseMemory() {
        memoryHog.clear(); // Limpa a lista
        System.gc(); // Solicita a execução do Garbage Collector
        return "Memória liberada (com solicitação de GC)";
    }



    @GetMapping("/consume-memory-nonheap")
    public String consumeCompressedClassSpace(@RequestParam(defaultValue = "1000") int numClassesToLoad) {

        for (int i = 0; i < numClassesToLoad; i++) {
            Class<?> dynamicClass = new ByteBuddy()
                    .subclass(Object.class)
                    .name("DynamicClass" + i)
                    .make()
                    .load(getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
                    .getLoaded();
            loadedClasses.add(dynamicClass);
        }

        return "Memória Non-Heap consumida com sucesso!";
    }

    @GetMapping("/trigger-errors-alert")
    public String triggerErrorsAlert(
            @RequestParam(defaultValue = "60") int errorsPerMinute,
            @RequestParam(defaultValue = "5") int durationMinutes
    ) {
        ExecutorService executor = Executors.newFixedThreadPool(2); // Pool de threads para os endpoints
        executor.submit(() -> executeAlertTask(errorsPerMinute, durationMinutes, true)); // Executa em thread separada
        return "Processo de disparo de alerta de erros iniciado.";
    }

    @GetMapping("/trigger-warnings-alert")
    public String triggerWarningsAlert(
            @RequestParam(defaultValue = "60") int warningsPerMinute,
            @RequestParam(defaultValue = "5") int durationMinutes
    ) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(() -> executeAlertTask(warningsPerMinute, durationMinutes, false)); // Executa em thread separada
        return "Processo de disparo de alerta de warnings iniciado.";
    }

    private void executeAlertTask(int eventsPerMinute, int durationMinutes, boolean isError) {
        int totalEvents = eventsPerMinute * durationMinutes;
        long intervalMillis = (long) (60000 / eventsPerMinute);

        for (int i = 0; i < totalEvents; i++) {
            if (isError) {
                log.error("[NO-WEBHOOK] Erro simulado para disparar alerta - {}", i);
            } else {
                log.warn("[NO-WEBHOOK] Warning simulado para disparar alerta - {}", i);
            }
            try {
                Thread.sleep(intervalMillis);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Lidar com interrupção da thread
            }
        }
    }

    @GetMapping("/endpoint-sobrecarregado")
    public String endpointSobrecarregado() {
        try {
            Thread.sleep(10); // Simula algum processamento
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "Requisição processada";
    }

    @GetMapping("/endpoint-lento")
    public String endpointLento(@RequestParam(defaultValue = "5") int segundos) {
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "Requisição processada após " + segundos + " segundos";
    }

}
