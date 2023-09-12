package com.little.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVO<T> {
    //状态码
    private int code;
    //提示信息
    private String message;
    //用户返回给浏览器的数据
    private T data;

    public void success(String custom) {
        this.setCode(200);
        this.setMessage(custom);
    }

    public void fail(String custom) {
        this.setCode(400);
        this.setMessage(custom);
    }

    public void add(T custom) {
        this.setData(custom);
    }
}


