package br.uni.lab.observer;

import br.uni.lab.model.ExamResult;

public class PatientObserver implements ResultObserver {
    private final String patientEmail;

    public PatientObserver(String email) { this.patientEmail = email; }

    @Override
    public void onResultReady(ExamResult result) {
        // Simula envio de notificação
        System.out.println("[NOTIF] Enviando email para " + patientEmail + ": " + result.getSummary());
    }
}
