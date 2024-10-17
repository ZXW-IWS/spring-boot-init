package com.zuu.springbootinit.oss.domain.vo.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Description: 上传url请求入参
 * Author: <a href="https://github.com/zongzibinbin">abin</a>
 * Date: 2023-03-23
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OssReq {
    @Schema(title = "文件存储路径")
    private String filePath;
    @Schema(title = "文件名")
    private String fileName;
    @Schema(title = "请求的uid")
    private Long uid;
    @Schema(title = "自动生成地址")
    private boolean autoPath = true;
}
