package com.feng.ojsystem;

import cn.hutool.json.JSONUtil;
import com.feng.ojsystem.enums.QuestionSubmitLanguageEnum;
import com.feng.ojsystem.judge.codesandbox.CodeSandbox;
import com.feng.ojsystem.judge.codesandbox.CodeSandboxFactory;
import com.feng.ojsystem.judge.codesandbox.model.ExecuteCodeRequest;
import com.feng.ojsystem.judge.codesandbox.model.ExecuteCodeResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import springfox.documentation.spring.web.json.Json;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class OjSystemApplicationTests {

    private static String type = "remote";

    public static void main(String[] args) {
        //使用工厂，根据配置得到的字符串变量创造不同的对象实例（不同的代码沙箱）
        CodeSandbox codeSandbox = CodeSandboxFactory.newInstance(type);
        String code = "public class SimpleCompute {\n" +
                "    public static void main(String[] args) {\n" +
                "        int a = Integer.parseInt(args[0]);\n" +
                "        int b = Integer.parseInt(args[1]);\n" +
                "        int c = a + b;\n" +
                "        System.out.println(\"结果：\" + c);\n" +
                "    }\n" +
                "}";
        String language = QuestionSubmitLanguageEnum.JAVA.getValue();
        List<String> inputList = Arrays.asList("1 2", "3 4");
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputList(inputList)
                .build();

        String json = JSONUtil.toJsonStr(executeCodeRequest);
        System.out.println(json);
//        //调用service:调用代码沙箱接口codeSandbox.executeCode()
//        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
//        Assertions.assertNotNull(executeCodeResponse);
    }
    @Test
    void executeCodeByValue() {

    }

}
