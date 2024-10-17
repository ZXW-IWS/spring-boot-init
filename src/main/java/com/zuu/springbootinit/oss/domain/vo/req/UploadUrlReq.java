package com.zuu.springbootinit.oss.domain.vo.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class UploadUrlReq {
    @Schema(title = "文件名（带后缀）")
    @NotBlank
    private String fileName;
    @Schema(title = "上传场景1.聊天室,2.表情包")
    @NotNull
    private Integer scene;
}
