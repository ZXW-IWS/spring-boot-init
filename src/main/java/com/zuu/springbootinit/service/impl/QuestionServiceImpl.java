package com.zuu.springbootinit.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zuu.springbootinit.common.domain.enums.ErrorEnum;
import com.zuu.springbootinit.common.exeption.BusinessException;
import com.zuu.springbootinit.domain.dto.UserDto;
import com.zuu.springbootinit.domain.po.Tag;
import com.zuu.springbootinit.domain.vo.req.question.*;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zuu.springbootinit.domain.dto.QuestionDto;
import com.zuu.springbootinit.mapper.QuestionMapper;
import com.zuu.springbootinit.service.QuestionService;
import com.zuu.springbootinit.domain.po.Question;
import com.zuu.springbootinit.service.TagService;
import com.zuu.springbootinit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author zuu
 * @description 针对表【question】的数据库操作Service实现
 * @createDate 2024-10-19 16:10:34
 */
@Service
@RequiredArgsConstructor
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question>
        implements QuestionService {

    public final TagService tagService;
    public final UserService userService;

    //region: crud操作代码

    /**
     * 添加题目接口
     *
     * @param uid            创建人id
     * @param questionAddReq
     * @return
     */
    @Override
    public Long addQuestion(Long uid, QuestionAddReq questionAddReq) {
        if (Objects.isNull(questionAddReq)) {
            throw new BusinessException(ErrorEnum.PARAM_ERROR);
        }
        List<String> tagList = questionAddReq.getTagList();
        JudgeConfig judgeConfig = questionAddReq.getJudgeConfig();
        List<JudgeCase> judgeCase = questionAddReq.getJudgeCase();
        Question question = BeanUtil.copyProperties(questionAddReq, Question.class);
        question.setUserId(uid);
        if (Objects.nonNull(judgeCase)) {
            question.setJudgeCase(JSONUtil.toJsonStr(judgeCase));
        }
        if (Objects.nonNull(judgeConfig)) {
            question.setJudgeConfig(JSONUtil.toJsonStr(judgeConfig));
        }
        validQuestion(question, tagList, true);
        if (Objects.nonNull(tagList) && !tagList.isEmpty()) {
            question.setTags(JSONUtil.toJsonStr(tagList));
        }
        this.save(question);
        return question.getId();
    }

    /**
     * 删除题目接口
     *
     * @param uid
     * @param questionDeleteReq
     */
    @Override
    public void deleteQuestion(Long uid, QuestionDeleteReq questionDeleteReq) {
        Long questionId = questionDeleteReq.getQuestionId();
        Question question = this.getById(questionId);
        if (Objects.isNull(question)) {
            throw new BusinessException(ErrorEnum.PARAM_ERROR);
        }
        //仅本人或管理员可删除
        if (!question.getUserId().equals(uid) && !userService.isAdmin(uid)) {
            throw new BusinessException(ErrorEnum.NO_AUTH);
        }
        this.removeById(question);
    }

    /**
     * 更新题目接口
     *
     * @param questionUpdateReq
     */
    @Override
    public void updateQuestion(QuestionUpdateReq questionUpdateReq) {
        if (Objects.isNull(questionUpdateReq)) {
            throw new BusinessException(ErrorEnum.PARAM_ERROR);
        }
        Question oldQuestion = this.getById(questionUpdateReq.getId());
        if (Objects.isNull(oldQuestion)) {
            throw new BusinessException(ErrorEnum.PARAM_ERROR);
        }
        List<String> tagList = questionUpdateReq.getTagList();
        JudgeConfig judgeConfig = questionUpdateReq.getJudgeConfig();
        List<JudgeCase> judgeCase = questionUpdateReq.getJudgeCase();
        Question question = BeanUtil.copyProperties(questionUpdateReq, Question.class);

        if (Objects.nonNull(judgeConfig)) {
            question.setJudgeConfig(JSONUtil.toJsonStr(judgeConfig));
        }
        if (Objects.nonNull(judgeCase)) {
            question.setJudgeCase(JSONUtil.toJsonStr(judgeCase));
        }
        validQuestion(question, tagList, false);
        if (Objects.nonNull(tagList) && !tagList.isEmpty()) {
            question.setTags(JSONUtil.toJsonStr(tagList));
        }
        this.updateById(question);
    }

    /**
     * id获取题目
     *
     * @param questionId
     * @return
     */
    @Override
    public QuestionDto getQuestionById(Long questionId) {
        Question question = this.getById(questionId);
        if (Objects.isNull(question)) {
            throw new BusinessException(ErrorEnum.NULL_ERROR);
        }
        return toDto(question);
    }

    /**
     * 分页查询题目
     *
     * @param questionPageReq
     * @return
     */
    @Override
    public Page<QuestionDto> getByPage(QuestionPageReq questionPageReq) {
        Long id = questionPageReq.getId();
        String title = questionPageReq.getTitle();
        String description = questionPageReq.getDescription();
        List<String> tagList = questionPageReq.getTagList();
        Integer difficult = questionPageReq.getDifficult();
        Integer current = questionPageReq.getCurrent();
        Integer pageSize = questionPageReq.getPageSize();
        //组装queryWrapper
        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Objects.nonNull(id) && id>0,"id",id);
        queryWrapper.eq(Objects.nonNull(difficult),"difficult",difficult);
        queryWrapper.like(StrUtil.isNotBlank(title),"title",title);
        queryWrapper.like(StrUtil.isNotBlank(description),"description",description);
        if(CollectionUtil.isNotEmpty(tagList)){
            tagList.forEach(tag -> {
                queryWrapper.like("tags", "\"" + tag + "\"");
            });
        }

        Page<Question> page = this.page(new Page<>(current, pageSize), queryWrapper);
        //Question转DTO
        List<QuestionDto> questionDtoList = page.getRecords().stream().map(this::toDto).toList();
        Page<QuestionDto> dtoPage = new Page<>(page.getCurrent(),page.getSize(),page.getTotal());
        dtoPage.setRecords(questionDtoList);
        return dtoPage;
    }


    public QuestionDto toDto(Question question) {
        QuestionDto questionDto = new QuestionDto();
        if (StrUtil.isNotBlank(question.getJudgeConfig())) {
            questionDto.setJudgeConfig(JSONUtil.toBean(question.getJudgeConfig(), JudgeConfig.class));
        }
        BeanUtils.copyProperties(question, questionDto);

        //获取题目标签列表
        String tagStr = question.getTags();
        List<String> tagList = JSONUtil.toList(tagStr, String.class);
        questionDto.setTagList(tagList);
        //获取创建人信息
        UserDto user = userService.getUserById(question.getUserId());
        questionDto.setCreateUser(user);
        return questionDto;
    }

    private void validQuestion(Question question, List<String> tagList, boolean add) {
        String title = question.getTitle();
        String description = question.getDescription();
        Integer difficult = question.getDifficult();
        String answer = question.getAnswer();
        String judgeConfig = question.getJudgeConfig();
        String judgeCase = question.getJudgeCase();
        if (add && StrUtil.hasBlank(title, description)) {
            throw new BusinessException(ErrorEnum.PARAM_ERROR);
        }
        //todo:做一些校验操作

        //校验标签信息
        Set<String> systemTagNameSet = tagService.list().stream().map(Tag::getName).collect(Collectors.toSet());
        if (Objects.nonNull(tagList) && !tagList.isEmpty()) {
            tagList.forEach(tag -> {
                if (!systemTagNameSet.contains(tag)) {
                    throw new BusinessException(ErrorEnum.PARAM_ERROR);
                }
            });
        }
    }

    //region end: crud代码


}




