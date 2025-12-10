package br.uni.lab;

import br.uni.lab.builder.ExamOrderBuilder;
import br.uni.lab.facade.LabFacade;
import br.uni.lab.model.Patient;
import br.uni.lab.observer.PatientObserver;
import br.uni.lab.util.Utils;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        System.out.println("=== Laboratório de Análises Clínicas — Demo ===");
        String pid = Utils.readLine("ID do paciente: ");
        String name = Utils.readLine("Nome do paciente: ");
        String email = Utils.readLine("Email do paciente: ");

        Patient p = new Patient(pid, name, email);

        String examsLine = Utils.readLine("Liste os exames separados por vírgula (ex: Hemograma,Glicemia): ");
        String[] exams = examsLine.split(",");

        String notes = Utils.readLine("Observações (ou vazio): ");
        int urg = Utils.readInt("Urgente? 1 = sim, 0 = não: ");

        LabFacade facade = new LabFacade();
        // registra observador (paciente)
        facade.registerObserver(new PatientObserver(p.getEmail()));

        ExamOrderBuilder builder = new ExamOrderBuilder()
                .forPatient(p)
                .notes(notes)
                .urgent(urg == 1);

        Arrays.stream(exams).map(String::trim).filter(s -> !s.isEmpty()).forEach(builder::addExam);

        var order = builder.build();
        String orderId = facade.placeOrder(order);

        System.out.println("Ordem enviada! ID: " + orderId);
        System.out.println("Aguardando processamento... (o sistema simula tempo de análise)");
        // espera um pouco para demo (na prática, observador já notificará)
        try { Thread.sleep(5000); } catch (InterruptedException ignored) {}

        var result = facade.getResult(orderId);
        if (result != null) {
            System.out.println("Resultado disponível: " + result);
        } else {
            System.out.println("Resultado ainda não processado.");
        }
    }
}
