package com.zuu.springbootinit.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zuu.springbootinit.domain.dto.QuestionSubmitDto;
import com.zuu.springbootinit.domain.po.QuestionSubmit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zuu.springbootinit.domain.vo.req.question_submit.QuestionSubmitPageReq;

/**
* @author zuu
* @description 针对表【question_submit】的数据库操作Service
* @createDate 2024-10-19 16:10:34
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {

    Page<QuestionSubmitDto> getPage(QuestionSubmitPageReq questionSubmitPageReq);
}
