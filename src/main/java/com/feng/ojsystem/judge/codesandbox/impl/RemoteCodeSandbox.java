package com.feng.ojsystem.judge.codesandbox.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.feng.ojsystem.common.ErrorCode;
import com.feng.ojsystem.exception.BusinessException;
import com.feng.ojsystem.judge.codesandbox.CodeSandbox;
import com.feng.ojsystem.judge.codesandbox.model.ExecuteCodeRequest;
import com.feng.ojsystem.judge.codesandbox.model.ExecuteCodeResponse;
import org.apache.commons.lang3.StringUtils;

/**
 * 远程代码沙箱
 */
public class RemoteCodeSandbox implements CodeSandbox {

    private static final String AUTH_REQUEST_HEADER = "auth";

    private static final String AUTH_REQUEST_SECRET = "secretKey";

    /**
     * 执行代码
     *
     * @param executeCodeRequest
     * @return
     */
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("远程代码沙箱");
        String url = "http://localhost:8090/executeCode";
        String json = JSONUtil.toJsonStr(executeCodeRequest);
        String responseStr = HttpUtil.createPost(url)
                .header(AUTH_REQUEST_SECRET, AUTH_REQUEST_HEADER)
                .body(json)
                .execute()
                .body();
        if(StringUtils.isBlank(responseStr)) {
            throw new BusinessException(ErrorCode.API_REQUEST_ERROR, "executeCode remoteSandBox error, message = " + responseStr);
        }
        System.out.println(responseStr);
        return null;
//        return JSONUtil.toBean(responseStr, ExecuteCodeResponse.class);
    }
}
