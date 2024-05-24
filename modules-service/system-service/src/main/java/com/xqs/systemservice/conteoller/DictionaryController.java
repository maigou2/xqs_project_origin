package com.xqs.systemservice.conteoller;

import com.xqs.commoncore.base.response.BaseResponseDTO;
import com.xqs.commoncore.enums.FourOperationsTypeEnum;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/dictionary")
public class DictionaryController {

    @RequestMapping("/getFourOperationsTypeList")
    public BaseResponseDTO<List<FourOperationsTypeEnum>> getFourOperationsTypeList() {
        return BaseResponseDTO.success(Arrays.asList(FourOperationsTypeEnum.values()));
    }
}
