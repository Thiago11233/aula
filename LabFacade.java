package br.uni.lab.facade;

import br.uni.lab.builder.ExamOrderBuilder;
import br.uni.lab.db.LabDatabase;
import br.uni.lab.model.ExamOrder;
import br.uni.lab.model.ExamResult;
import br.uni.lab.observer.ResultObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LabFacade {
    private final LabDatabase db = LabDatabase.getInstance();
    private final List<ResultObserver> observers = new ArrayList<>();

    public void registerObserver(ResultObserver o) {
        observers.add(o);
    }

    public void unregisterObserver(ResultObserver o) {
        observers.remove(o);
    }

    public String placeOrder(ExamOrder order) {
        db.saveOrder(order);
        System.out.println("Ordem salva: " + order);
        // simulamos processamento assíncrono com thread (simplificado)
        new Thread(() -> processOrder(order)).start();
        return order.getOrderId();
    }

    private void processOrder(ExamOrder order) {
        try { Thread.sleep(order.isUrgent() ? 1000 : 3000); } catch (InterruptedException ignored) {}
        // gerar resultado simples
        String summary = "Exames " + String.join(", ", order.getExams()) + " — resultados dentro dos parâmetros.";
        ExamResult result = new ExamResult(order.getOrderId(), summary);
        db.saveResult(result);
        notifyObservers(result);
        System.out.println("Processado: " + result);
    }

    private void notifyObservers(ExamResult result) {
        for (ResultObserver o : observers) {
            o.onResultReady(result);
        }
    }

    public ExamResult getResult(String orderId) {
        return db.getResult(orderId).orElse(null);
    }
}
