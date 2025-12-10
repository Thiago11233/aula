package br.uni.lab.model;

import java.util.List;

public class ExamOrder {
    private final String orderId;
    private final Patient patient;
    private final List<String> exams;
    private final String notes;
    private final boolean urgent;

    public ExamOrder(String orderId, Patient patient, List<String> exams, String notes, boolean urgent) {
        this.orderId = orderId;
        this.patient = patient;
        this.exams = exams;
        this.notes = notes;
        this.urgent = urgent;
    }

    public String getOrderId() { return orderId; }
    public Patient getPatient() { return patient; }
    public List<String> getExams() { return exams; }
    public String getNotes() { return notes; }
    public boolean isUrgent() { return urgent; }

    @Override
    public String toString() {
        return "Order[" + orderId + "] for " + patient + " exams=" + exams + (urgent ? " (URGENT)" : "");
    }
}
