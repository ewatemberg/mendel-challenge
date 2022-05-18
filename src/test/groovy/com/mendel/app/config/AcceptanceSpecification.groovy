package com.mendel.app.config

import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureCache
@TestPropertySource(properties = [
        "spring.main.allow-bean-definition-overriding=true",
        "spring.http.encoding.force=true"
])
class AcceptanceSpecification extends Specification {

    JsonSlurper jsonSlurper = new JsonSlurper()

    @Autowired
    MockMvc mockMvc

    private Map buildMap(def response) {
        def responseParsed = jsonSlurper.parseText(response.contentAsString)
        [body      : responseParsed,
         status    : response.status,
         raw       : response.contentAsString]
    }

}
