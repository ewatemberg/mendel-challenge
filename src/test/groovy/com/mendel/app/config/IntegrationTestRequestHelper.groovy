package com.mendel.app.config

import com.mendel.app.resource.TransactionResource
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

class IntegrationTestRequestHelper {

    public static final String PATH_TRANSACTION_ID = "/api/${ApiVersion.V1}${TransactionResource.PATH}"
    public static final String PATH_TRANSACTION_TYPE = "/api/${ApiVersion.V1}${TransactionResource.PATH}/types"
    public static final String PATH_TRANSACTION_SUM = "/api/${ApiVersion.V1}${TransactionResource.PATH}/sum"

    protected static def POST(MockMvc mockMvc, String path, String jsonBody) {
        String mediaType = MediaType.APPLICATION_JSON
        def response = mockMvc
                .perform(
                        MockMvcRequestBuilders.post(path)
                                .contentType(mediaType)
                                .content(jsonBody)
                ).andReturn().response
        return response
    }

    protected static def GET(MockMvc mockMvc, String path, String mediaType = MediaType.APPLICATION_JSON) {
        return mockMvc
                .perform(
                        MockMvcRequestBuilders.get(path)
                                .contentType(mediaType)
                ).andReturn().response
    }

    protected static def PATCH(MockMvc mockMvc, String path, String jsonBody, String mediaType = MediaType.APPLICATION_JSON) {
        return mockMvc
                .perform(
                        MockMvcRequestBuilders.patch(path)
                                .contentType(mediaType)
                                .content(jsonBody)
                ).andReturn().response
    }

    protected static def PUT(MockMvc mockMvc, String path, String jsonBody, String mediaType = MediaType.APPLICATION_JSON) {
        return mockMvc
                .perform(
                        MockMvcRequestBuilders.put(path)
                                .contentType(mediaType)
                                .content(jsonBody)
                ).andReturn().response
    }

    protected static def DELETE(MockMvc mockMvc, String path, String mediaType = MediaType.APPLICATION_JSON) {
        return mockMvc
                .perform(
                        MockMvcRequestBuilders.delete(path)
                                .contentType(mediaType)
                ).andReturn().response
    }
}
