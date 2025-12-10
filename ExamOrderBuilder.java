package br.uni.lab.builder;

import br.uni.lab.model.ExamOrder;
import br.uni.lab.model.Patient;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ExamOrderBuilder {
    private String orderId;
    private Patient patient;
    private List<String> exams = new ArrayList<>();
    private String notes = "";
    private boolean urgent = false;

    public ExamOrderBuilder() {
        this.orderId = UUID.randomUUID().toString();
    }

    public ExamOrderBuilder withOrderId(String id) {
        this.orderId = id; return this;
    }

    public ExamOrderBuilder forPatient(Patient p) {
        this.patient = p; return this;
    }

    public ExamOrderBuilder addExam(String exam) {
        this.exams.add(exam); return this;
    }

    public ExamOrderBuilder notes(String notes) {
        this.notes = notes; return this;
    }

    public ExamOrderBuilder urgent(boolean urgent) {
        this.urgent = urgent; return this;
    }

    public ExamOrder build() {
        if (patient == null) throw new IllegalStateException("Patient required");
        return new ExamOrder(orderId, patient, exams, notes, urgent);
    }
}
