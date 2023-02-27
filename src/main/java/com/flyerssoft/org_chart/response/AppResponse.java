package com.flyerssoft.org_chart.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppResponse<T> {

    private Integer statusCode;

    private Boolean responseResults;

    private T data;
}
