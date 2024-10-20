package com.zuu.springbootinit.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zuu.springbootinit.domain.po.Question;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zuu.springbootinit.domain.vo.req.question.QuestionAddReq;
import com.zuu.springbootinit.domain.vo.req.question.QuestionDeleteReq;
import com.zuu.springbootinit.domain.vo.req.question.QuestionPageReq;
import com.zuu.springbootinit.domain.vo.req.question.QuestionUpdateReq;
import com.zuu.springbootinit.domain.dto.QuestionDto;

/**
* @author zuu
* @description 针对表【question】的数据库操作Service
* @createDate 2024-10-19 16:10:34
*/
public interface QuestionService extends IService<Question> {
    /**
     * 添加题目接口
     * @param uid  创建人id
     * @param questionAddReq
     * @return
     */
    Long addQuestion(Long uid, QuestionAddReq questionAddReq);

    /**
     * 删除题目接口
     *
     * @param uid
     * @param questionDeleteReq
     */
    void deleteQuestion(Long uid, QuestionDeleteReq questionDeleteReq);

    /**
     * 更新题目接口
     * @param questionUpdateReq
     */
    void updateQuestion(QuestionUpdateReq questionUpdateReq);

    /**
     * id获取题目
     * @param questionId
     * @return
     */
    QuestionDto getQuestionById(Long questionId);

    /**
     * 分页查询题目
     * @param questionPageReq
     * @return
     */
    Page<QuestionDto> getByPage(QuestionPageReq questionPageReq);
}
