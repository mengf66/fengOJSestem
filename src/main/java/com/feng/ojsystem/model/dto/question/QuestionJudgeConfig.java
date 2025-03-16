package com.feng.ojsystem.model.dto.question;

import lombok.Data;

@Data
public class QuestionJudgeConfig {
    private long timeLimit;
    private long memoryLimit;
    private long stackLimit;

}
