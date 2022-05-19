package com.mendel.app.resource.atdd

import com.mendel.app.config.AcceptanceSpecification
import com.mendel.app.domain.Transaction
import com.mendel.app.mock.TransactionMock
import com.mendel.app.resource.TransactionResource
import org.springframework.beans.factory.annotation.Autowired

class ATransactionTest extends AcceptanceSpecification {


    @Autowired
    Set<Transaction> storeTx;

    @Autowired
    private TransactionResource transactionResource
    private TransactionMock mocks = new TransactionMock()

    def "Se hace un PUT a transactions con datos de un una nueva transaccion"() {

        given: "un request de transaccion"
        def transaction = mocks.validTransaction()
        def id = 10L

        when: "Se crea una transaccion"
        def response = doPutTransactionWith(id, transaction.json)

        then: "Se obtienen los datos de la transaccion guardada"
        response.status == 200
        def responseJson = getJson(response.raw)
        responseJson.amount == 5000
        responseJson.type == "cars"
        responseJson.parent_id == null
    }

    def "Se hace un GET por tipo a transactions y se obtiene una lista de ids agrupados"() {

        given: "un tipo y un grupo de transacciones existentes."
        def type = "cars"
        doPutTransactionWith(10, mocks.validTransaction().json)

        when: "Se crea una transaccion"
        def response = doGetTransactionByTypeWith(type)

        then: "Se obtienen los datos de la transaccion guardada"
        response.status == 200
        def responseJson = getJson(response.raw)
        responseJson == [10]
    }

    def "Se hace un GET sum a transactions y se obtiene la suma total de montos agrupados por parent_id"() {

        given: "un tipo y un grupo de transacciones existentes."
        def id = 10
        doPutTransactionWith(11, mocks.validTransactionParent10Amount10000().json)
        doPutTransactionWith(12, mocks.validTransactionParent10Amount5000().json)

        when: "Se crea una transaccion"
        def response = doGetSumAmountTransactionByParentId(id)

        then: "Se obtienen la suma de todas las transacciones cuyo parent id es 10"
        response.status == 200
        def responseJson = getJson(response.raw)
        responseJson.sum == 15000
    }


    def "Se hace un GET sum a transactions pero no existen transacciones con parent_id entonces retorna NOT_FOUND"() {

        given: "un tipo y un grupo de transacciones existentes."
        def id = 1000000

        when: "Se crea una transaccion"
        def response = doGetSumAmountTransactionByParentId(id)

        then: "Se obtienen una respuesta 404 porque no encuentra transacciones con ese parent_id"
        response.status == 404
    }



}
