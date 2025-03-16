package com.feng.ojsystem.judge;

import com.feng.ojsystem.model.entity.QuestionSubmit;
import com.feng.ojsystem.model.vo.QuestionSubmitVO;

/**
 * 判题服务
 */
public interface JudgeService {

    /**
     *判题
     * @param questionSubmitId
     * @return
     */
    public QuestionSubmit doJudge(long questionSubmitId);
}
