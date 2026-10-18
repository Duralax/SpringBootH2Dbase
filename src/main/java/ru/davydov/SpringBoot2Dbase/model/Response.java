package ru.davydov.SpringBoot2Dbase.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Response {
    private Object data;

    private String status;
    private String message;

    private Response(Object data, String status, String message) {
        this.data = data;
        this.status = status;
        this.message = message;
    }

    public static Response success(Object data, String message) {
        return new Response(data, "success", message);
    }

    public static Response error(Object data, String message) {
        return new Response(data, "error", message);
    }
}
