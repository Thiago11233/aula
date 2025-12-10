package br.uni.lab.observer;

import br.uni.lab.model.ExamResult;

public interface ResultObserver {
    void onResultReady(ExamResult result);
}
