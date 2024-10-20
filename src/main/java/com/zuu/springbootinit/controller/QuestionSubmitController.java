package com.zuu.springbootinit.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zuu.springbootinit.common.domain.vo.resp.ApiResult;
import com.zuu.springbootinit.common.domain.vo.resp.BaseResponse;
import com.zuu.springbootinit.domain.dto.QuestionSubmitDto;
import com.zuu.springbootinit.domain.vo.req.question_submit.QuestionSubmitPageReq;
import com.zuu.springbootinit.service.QuestionSubmitService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zuu
 * @Description
 * @Date 2024/10/20 12:16
 */
@RestController
@RequestMapping("/question_submit")
@Tag(name = "题目提交相关接口")
public class QuestionSubmitController {
    @Resource
    private QuestionSubmitService questionSubmitService;

    /**
     * 分页获取题目提交信息
     * @return
     */
    @PostMapping("/public/submit/list")
    public BaseResponse<Page<QuestionSubmitDto>> getPage(@RequestBody QuestionSubmitPageReq questionSubmitPageReq){
        return ApiResult.success(questionSubmitService.getPage(questionSubmitPageReq));
    }
}
