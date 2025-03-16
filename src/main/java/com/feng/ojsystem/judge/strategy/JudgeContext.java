package com.feng.ojsystem.judge.strategy;

import com.feng.ojsystem.model.dto.question.QuestionJudgeCase;
import com.feng.ojsystem.judge.codesandbox.model.JudgeInfo;
import com.feng.ojsystem.model.entity.Question;
import com.feng.ojsystem.model.entity.QuestionSubmit;
import lombok.Data;

import java.util.List;

@Data
public class JudgeContext {

    private JudgeInfo judgeInfo;

    private List<String> inputList;

    private List<String> outputList;

    private List<QuestionJudgeCase> judgeCaseList;

    private Question question;

    private QuestionSubmit questionSubmit;
}
