package com.zuu.springbootinit.oss.service.impl;


import com.zuu.springbootinit.common.domain.enums.ErrorEnum;
import com.zuu.springbootinit.common.exeption.BusinessException;
import com.zuu.springbootinit.oss.domain.enums.OssSceneEnum;
import com.zuu.springbootinit.oss.domain.vo.req.OssReq;
import com.zuu.springbootinit.oss.domain.vo.req.UploadUrlReq;
import com.zuu.springbootinit.oss.domain.vo.resp.OssResp;
import com.zuu.springbootinit.oss.service.OssService;
import com.zuu.springbootinit.oss.template.MinIOTemplate;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @Author zuu
 * @Description
 * @Date 2024/7/29 15:05
 */
//todo:开启minio时删除注释
//@Service
public class OssServiceImpl implements OssService {
    @Resource
    MinIOTemplate minIOTemplate;
    @Override
    public OssResp getUploadUrl(Long uid, UploadUrlReq req) {
        String fileName = req.getFileName();
        Integer scene = req.getScene();
        OssSceneEnum ossSceneEnum = OssSceneEnum.of(scene);
        if(Objects.isNull(ossSceneEnum)){
            throw new BusinessException(ErrorEnum.PARAM_ERROR);
        }
        OssReq ossReq = new OssReq();
        ossReq.setFilePath(ossSceneEnum.getPath());
        ossReq.setFileName(fileName);
        ossReq.setUid(uid);

        return minIOTemplate.getPreSignedObjectUrl(ossReq);
    }
}
