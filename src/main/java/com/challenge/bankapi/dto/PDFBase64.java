package com.challenge.bankapi.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PDFBase64 implements Serializable {
    private String base64Str;
}
