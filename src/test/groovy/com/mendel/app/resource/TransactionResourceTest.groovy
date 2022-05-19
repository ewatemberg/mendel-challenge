package com.mendel.app.resource

import com.mendel.app.BaseTest
import com.mendel.app.config.StaticContextHolder
import com.mendel.app.domain.Transaction
import com.mendel.app.service.TransactionService
import com.mendel.app.service.dto.TransactionDTO
import org.springframework.beans.factory.BeanFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpStatus
import org.springframework.test.context.ContextConfiguration
import spock.mock.DetachedMockFactory

@ContextConfiguration(classes = [TransactionResourceTest.Configuracion])
class TransactionResourceTest extends BaseTest {

    @Autowired
    private Transaction transaction
    @Autowired
    private TransactionService transactionService
    @Autowired
    private TransactionResource transactionResource

    private BeanFactory beanFactory = Mock(BeanFactory)
    private static final String PATH_JSON = "/json/transaction/put/"

    def setup() {
        //StaticContextHolder.CONTEXT = beanFactory
    }

    def "Se hace un PUT para crear una transaccion y la API responde OK con los datos del transaccion creada"() {

        given: "Dado un request de una transaccion"
        def transaction = createObjectFromJson("${PATH_JSON}VALID_CREATE_REQUEST.json", TransactionDTO.class)

        when: "Hago la invocación al metodo del recurso crear una transaccion con el ID 10"
        def response = transactionResource.createTx(10L, transaction)

        then: "Persisto la información y obtengo el objecto creado"
        1 * transactionService.save(_, _) >> {
            return transaction
        }
        response.statusCodeValue == HttpStatus.OK.value()
        response.body.amount == 5000
        response.body.parentId == null
        response.body.type == "cars"
    }

    def "Se hace un GET para obtener las transacciones por tipo y la API responde OK con los datos del transacciones agrupadas"() {

        given: "Dado un tipo de transaccion"
        def type = "cars"

        when: "Hago la invocación al metodo del recurso para obtener las transacciones por tipo"
        def response = transactionResource.getIdsByType(type)

        then: "Obtengo la informacion"
        1 * transactionService.findByType(type) >> {
            return Arrays.asList(10L, 11L, 12L);
        }
        response.statusCodeValue == HttpStatus.OK.value()
        response.body[0] == 10L
        response.body[1] == 11L
        response.body[2] == 12L
    }

    def "Se hace un GET para obtener la suma de todas las transacciones por parent_id y la API responde OK la suma"() {

        given: "Dado un parent_id"
        def parentId = 10

        when: "Hago la invocación al metodo del recurso para obtener la suma"
        def response = transactionResource.sumarizeAmountByParentId(parentId)

        then: "Obtengo la informacion"
        1 * transactionService.sumarizeByParentId(parentId) >> {
            def map = new HashMap<String, Double>()
            map.put("sum", 20000)
            return map
        }
        response.statusCodeValue == HttpStatus.OK.value()
        response.body.sum == 20000
    }


    static class Configuracion {

        def mockFactory = new DetachedMockFactory()

        @Bean
        TransactionService transactionService() {
            return mockFactory.Mock(TransactionService)
        }

        @Bean
        Transaction transaction() {
            return mockFactory.Mock(Transaction)
        }

        @Bean
        TransactionResource transactionResource() {
            return new TransactionResource()
        }

    }
}
