package com.zuu.springbootinit.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zuu.springbootinit.common.annotation.AuthCheck;
import com.zuu.springbootinit.common.constant.UserConstant;
import com.zuu.springbootinit.common.domain.vo.resp.ApiResult;
import com.zuu.springbootinit.common.domain.vo.resp.BaseResponse;
import com.zuu.springbootinit.common.utils.RequestHolder;
import com.zuu.springbootinit.domain.vo.req.question.QuestionAddReq;
import com.zuu.springbootinit.domain.vo.req.question.QuestionDeleteReq;
import com.zuu.springbootinit.domain.vo.req.question.QuestionPageReq;
import com.zuu.springbootinit.domain.vo.req.question.QuestionUpdateReq;
import com.zuu.springbootinit.domain.dto.QuestionDto;
import com.zuu.springbootinit.service.QuestionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;


/**
 * @Author zuu
 * @Description
 * @Date 2024/10/19 16:55
 */
@RestController
@RequestMapping("/question")
@Tag(name = "题目相关接口")
public class QuestionController {
    @Resource
    public QuestionService questionService;
    // region 增删改查

    /**
     * 创建
     *
     * @param questionAddReq
     * @return
     */
    @PutMapping("/add")
    @AuthCheck(minRole = UserConstant.USER_ROLE_ADMIN)
    public BaseResponse<Long> addQuestion(@RequestBody QuestionAddReq questionAddReq) {
        Long uid = RequestHolder.get();
        Long questionId = questionService.addQuestion(uid,questionAddReq);

        return ApiResult.success(questionId);
    }

    /**
     * 删除
     * @param questionDeleteReq
     * @return
     */
    @DeleteMapping("/delete")
    @AuthCheck(minRole = UserConstant.USER_ROLE_ADMIN)
    public BaseResponse<Void> deleteQuestion(@RequestBody QuestionDeleteReq questionDeleteReq){
        Long uid = RequestHolder.get();
        questionService.deleteQuestion(uid,questionDeleteReq);

        return ApiResult.success();
    }

    /**
     * 更新
     * @param questionUpdateReq
     * @return
     */
    @PutMapping("/update")
    @AuthCheck(minRole = UserConstant.USER_ROLE_ADMIN)
    public BaseResponse<Void> updateQuestion(@RequestBody QuestionUpdateReq questionUpdateReq){
        questionService.updateQuestion(questionUpdateReq);

        return ApiResult.success();
    }

    /**
     * 根据id获取题目
     * @param questionId
     * @return
     */
    @GetMapping("/get/{questionId}")
    public BaseResponse<QuestionDto> getById(@PathVariable Long questionId){
        return ApiResult.success(questionService.getQuestionById(questionId));
    }

    /**
     * 根据条件分页获取题目
     * @return
     */
    @PostMapping("/list/page")
    public BaseResponse<Page<QuestionDto>> getByPage(@RequestBody QuestionPageReq questionPageReq){
        return ApiResult.success(questionService.getByPage(questionPageReq));
    }
    // endregion
}
