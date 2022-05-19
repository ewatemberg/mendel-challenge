package com.mendel.app.service

import com.mendel.app.BaseTest
import com.mendel.app.config.StaticContextHolder
import com.mendel.app.domain.Transaction
import com.mendel.app.service.TransactionService
import com.mendel.app.service.dto.TransactionDTO
import org.springframework.beans.factory.BeanFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.test.context.ContextConfiguration
import spock.mock.DetachedMockFactory

@ContextConfiguration(classes = [TransactionServiceTest.Configuracion])
class TransactionServiceTest extends BaseTest {

    @Autowired
    Transaction transaction
    @Autowired
    private TransactionService transactionService

    private BeanFactory beanFactory = Mock(BeanFactory)

    def setup() {
        StaticContextHolder.CONTEXT = beanFactory
    }

    def "Se hace un SAVE de una transaccion"() {

        given: "Dado una solicitud de almacenamiento de una transaccion y un id"
        def id = 10
        def transaction = new TransactionDTO().builder()
                .type("cars")
                .amount(10000)
                .build()

        when: "se hace un persist de la transaccion"
        def response = transactionService.save(id, transaction)

        then: "Persisto la información y obtengo el objecto creado"
        response.amount == 10000
        response.type == "cars"
        response.parentId == null
    }


    static class Configuracion {

        def mockFactory = new DetachedMockFactory()

        @Bean
        TransactionService transactionService() {
            return new TransactionService()
        }

        @Bean
        Transaction transaction() {
            return new Transaction()
        }

    }
}
