package com.mz.common.filter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.mz.common.utils.XssUtil;

import java.io.IOException;

public class XssSqlStringJsonSerializer extends JsonSerializer<String> {

    @Override
    public Class<String> handledType() {
        return String.class;
    }

    @Override
    public void serialize(String s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (s != null) {
            String encodedValue = XssUtil.stripSqlXss(s);
            jsonGenerator.writeString(encodedValue);
        }
    }
}
