package br.uni.lab.model;

public class ExamResult {
    private final String orderId;
    private final String summary;

    public ExamResult(String orderId, String summary) {
        this.orderId = orderId;
        this.summary = summary;
    }

    public String getOrderId() { return orderId; }
    public String getSummary() { return summary; }

    @Override
    public String toString() {
        return "Result for " + orderId + ": " + summary;
    }
}
