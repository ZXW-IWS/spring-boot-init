package com.zuu.springbootinit.oss.controller;

import com.zuu.springbootinit.common.domain.vo.resp.BaseResponse;
import com.zuu.springbootinit.common.domain.vo.resp.ApiResult;
import com.zuu.springbootinit.common.utils.RequestHolder;
import com.zuu.springbootinit.oss.domain.vo.req.UploadUrlReq;
import com.zuu.springbootinit.oss.domain.vo.resp.OssResp;
import com.zuu.springbootinit.oss.service.OssService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * @Author zuu
 * @Description
 * @Date 2024/7/29 15:02
 */
//todo:使用minio时解除注释
//@RestController
//@RequestMapping("/oss")
//@Tag(name = "oss接口")
public class OssController {
    @Resource
    private OssService ossService;

    @GetMapping("/upload/url")
    @Operation(summary = "获取临时上传链接")
    public BaseResponse<OssResp> getUploadUrl(@Valid UploadUrlReq req) {
        return ApiResult.success(ossService.getUploadUrl(RequestHolder.get().getUid(), req));
    }
}
