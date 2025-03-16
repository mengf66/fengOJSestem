package com.feng.ojsystem.judge.strategy;

import cn.hutool.json.JSONUtil;
import com.feng.ojsystem.enums.JudgeInfoMessageEnum;
import com.feng.ojsystem.model.dto.question.QuestionJudgeCase;
import com.feng.ojsystem.model.dto.question.QuestionJudgeConfig;
import com.feng.ojsystem.judge.codesandbox.model.JudgeInfo;
import com.feng.ojsystem.model.entity.Question;

import java.util.List;

public class JavaJudgeStrategy implements JudgeStrategy{
    /**
     * 执行判题
     *
     * @param judgeContext
     * @return
     */
    @Override
    public JudgeInfo doJudge(JudgeContext judgeContext) {

        JudgeInfo judgeInfo = judgeContext.getJudgeInfo();
        List<String> inputList = judgeContext.getInputList();
        List<String> outputList = judgeContext.getOutputList();
        List<QuestionJudgeCase> judgeCaseList = judgeContext.getJudgeCaseList();
        Question question = judgeContext.getQuestion();
        Long memory = judgeInfo.getMemory();
        Long time = judgeInfo.getTime();
        JudgeInfo judgeInfoResponse = new JudgeInfo();
        judgeInfoResponse.setMessage(judgeInfoResponse.getMessage());
        judgeInfoResponse.setMemory(memory);
        judgeInfoResponse.setTime(time);


        JudgeInfoMessageEnum judgeInfoMessageEnum = JudgeInfoMessageEnum.WAITING;
        if(outputList.size() != inputList.size()) {
            judgeInfoMessageEnum = JudgeInfoMessageEnum.WRONG_ANSWER;
            judgeInfoResponse.setMessage(judgeInfoResponse.getMessage());
            return judgeInfoResponse;
        }

        for(int i = 0; i < judgeCaseList.size(); i++) {
            QuestionJudgeCase judgeCase = judgeCaseList.get(i);
            if(!judgeCase.getOutput().equals(outputList.get(i))) {
                judgeInfoMessageEnum = JudgeInfoMessageEnum.WRONG_ANSWER;
                judgeInfoResponse.setMessage(judgeInfoResponse.getMessage());
                return judgeInfoResponse;
            }
        }
        String judgeConfigStr = question.getJudgeConfig();
        QuestionJudgeConfig judgeConfig = JSONUtil.toBean(judgeConfigStr, QuestionJudgeConfig.class);
        Long needMemoryLimit = judgeConfig.getMemoryLimit();
        Long needTimeLimit = judgeConfig.getTimeLimit();
        if(memory > needMemoryLimit) {
            judgeInfoMessageEnum = JudgeInfoMessageEnum.MEMORY_LIMIT_EXCEEDED;
            judgeInfoResponse.setMessage(judgeInfoResponse.getMessage());
            return judgeInfoResponse;
        }
        long JAVA_PROGRAM_TIME_COST = 10000L;
        if((time - JAVA_PROGRAM_TIME_COST) > needTimeLimit) {
            judgeInfoMessageEnum = JudgeInfoMessageEnum.TIME_LIMIT_EXCEEDED;
            judgeInfoResponse.setMessage(judgeInfoResponse.getMessage());
            return judgeInfoResponse;
        }
        judgeInfoMessageEnum = JudgeInfoMessageEnum.ACCEPTED;
        judgeInfoResponse.setMessage(judgeInfoResponse.getMessage());
        return judgeInfoResponse;
    }
}
