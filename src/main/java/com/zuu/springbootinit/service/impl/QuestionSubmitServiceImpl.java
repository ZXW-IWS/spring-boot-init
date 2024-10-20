package com.zuu.springbootinit.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zuu.springbootinit.domain.dto.QuestionSubmitDto;
import com.zuu.springbootinit.domain.vo.req.question_submit.JudgeInfo;
import com.zuu.springbootinit.domain.vo.req.question_submit.QuestionSubmitPageReq;
import com.zuu.springbootinit.mapper.QuestionSubmitMapper;
import com.zuu.springbootinit.service.QuestionService;
import com.zuu.springbootinit.service.QuestionSubmitService;
import com.zuu.springbootinit.domain.po.QuestionSubmit;
import com.zuu.springbootinit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author zuu
 * @description 针对表【question_submit】的数据库操作Service实现
 * @createDate 2024-10-19 16:10:34
 */
@Service
@RequiredArgsConstructor
public class QuestionSubmitServiceImpl extends ServiceImpl<QuestionSubmitMapper, QuestionSubmit>
        implements QuestionSubmitService {
    public final QuestionService questionService;
    public final UserService userService;
    @Override
    public Page<QuestionSubmitDto> getPage(QuestionSubmitPageReq questionSubmitPageReq) {
        String language = questionSubmitPageReq.getLanguage();
        Integer status = questionSubmitPageReq.getStatus();
        Long questionId = questionSubmitPageReq.getQuestionId();
        Long userId = questionSubmitPageReq.getUserId();
        Integer current = questionSubmitPageReq.getCurrent();
        Integer pageSize = questionSubmitPageReq.getPageSize();
        //组装queryWrapper
        QueryWrapper<QuestionSubmit> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Objects.nonNull(questionId) && questionId>0,"question_id",questionId);
        queryWrapper.eq(Objects.nonNull(userId) && userId>0,"user_id",userId);
        queryWrapper.eq(Objects.nonNull(status) && status>=0,"status",status);
        queryWrapper.like(StrUtil.isNotBlank(language),"language",language);


        Page<QuestionSubmit> page = this.page(new Page<>(current, pageSize), queryWrapper);
        //Question转DTO
        List<QuestionSubmitDto> questionDtoList = page.getRecords().stream().map(this::toDto).toList();
        Page<QuestionSubmitDto> dtoPage = new Page<>(page.getCurrent(),page.getSize(),page.getTotal());
        dtoPage.setRecords(questionDtoList);

        return dtoPage;
    }

    private QuestionSubmitDto toDto(QuestionSubmit questionSubmit) {
        QuestionSubmitDto questionSubmitDto = new QuestionSubmitDto();
        BeanUtils.copyProperties(questionSubmit,questionSubmitDto);
        if(StrUtil.isNotBlank(questionSubmit.getJudgeInfo())){
            questionSubmitDto.setJudgeInfo(JSONUtil.toBean(questionSubmit.getJudgeInfo(), JudgeInfo.class));
        }

        questionSubmitDto.setQuestionInfo(questionService.getQuestionById(questionSubmit.getQuestionId()));
        questionSubmitDto.setUserInfo(userService.getUserById(questionSubmit.getUserId()));

        return questionSubmitDto;
    }
}




