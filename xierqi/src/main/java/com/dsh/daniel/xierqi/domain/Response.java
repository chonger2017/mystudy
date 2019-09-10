package com.dsh.daniel.xierqi.domain;

import com.dsh.daniel.xierqi.domain.VO.ResponseVO;
import com.dsh.daniel.xierqi.util.ResponseCode;

public class Response {
    public static ResponseVO<Void> res_ok() {
        return new ResponseVO(200, null, ResponseCode.RESPONSE_OK.getMessage());
    }

}
