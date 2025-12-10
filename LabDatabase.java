package br.uni.lab.db;

import br.uni.lab.model.ExamOrder;
import br.uni.lab.model.ExamResult;

import java.util.*;

public class LabDatabase {
    private static LabDatabase instance;
    private final Map<String, ExamOrder> orders = new HashMap<>();
    private final Map<String, ExamResult> results = new HashMap<>();

    private LabDatabase() {}

    public static synchronized LabDatabase getInstance() {
        if (instance == null) {
            instance = new LabDatabase();
        }
        return instance;
    }

    public void saveOrder(ExamOrder order) {
        orders.put(order.getOrderId(), order);
    }

    public Optional<ExamOrder> getOrder(String orderId) {
        return Optional.ofNullable(orders.get(orderId));
    }

    public void saveResult(ExamResult result) {
        results.put(result.getOrderId(), result);
    }

    public Optional<ExamResult> getResult(String orderId) {
        return Optional.ofNullable(results.get(orderId));
    }
}
