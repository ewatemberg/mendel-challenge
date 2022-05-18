package com.mendel.app.resource

import com.mendel.app.BaseTest
import com.mendel.app.config.StaticContextHolder
import com.mendel.app.domain.Transaction
import com.mendel.app.service.TransactionService
import org.springframework.beans.factory.BeanFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpStatus
import org.springframework.test.context.ContextConfiguration
import spock.mock.DetachedMockFactory

@ContextConfiguration(classes = [TransactionResourceTest.Configuracion])
class TransactionResourceTest extends BaseTest {

    @Autowired
    private TransactionService transactionService
    @Autowired
    private TransactionResource transactionResource

    private BeanFactory beanFactory = Mock(BeanFactory)
    private static final String PATH_JSON = "/json/transaction/put/"

    def setup() {
        StaticContextHolder.CONTEXT = beanFactory
    }

    def "Se hace un PUT para crear una transaccion y la API responde OK con los datos del transaccion creada"() {

        given: "Dado un request de una transaccion"
        def transaction = createObjectFromJson("${PATH_JSON}VALID_CREATE_REQUEST.json", Transaction.class)

        when: "Hago la invocación al metodo del recurso crear una transaccion con el ID 10"
        def response = transactionResource.updateOrCreateTx(10L, transaction)

        then: "Persisto la información y obtengo el objecto creado"
        1 * transactionService.save(transaction) >> {
            return transaction
        }
        response.statusCodeValue == HttpStatus.OK.value()
        response.body.amount == 5000
        response.body.parentId == null
        response.body.type == "cars"
    }


    static class Configuracion {

        def mockFactory = new DetachedMockFactory()

        @Bean
        TransactionService transactionService() {
            return mockFactory.Mock(TransactionService)
        }

        @Bean
        TransactionResource transactionResource() {
            return new TransactionResource()
        }

    }
}
