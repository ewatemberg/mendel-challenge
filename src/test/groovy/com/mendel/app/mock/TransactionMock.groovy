package com.mendel.app.mock

import com.mendel.app.service.dto.TransactionDTO

class TransactionMock extends Mock{

    private static String BASE_PATH_PUT = "/json/transaction/put/"

    private static String PATH_VALID_TRANSACTION = "${BASE_PATH_PUT}VALID_CREATE_REQUEST.json"
    private static String PATH_VALID_TRANSACTION_PARENT_10_AMOUNT_10000 = "${BASE_PATH_PUT}VALID_CREATE_REQUEST_PARENT_10_AMOUNT_10000.json"
    private static String PATH_VALID_TRANSACTION_PARENT_10_AMOUNT_5000 = "${BASE_PATH_PUT}VALID_CREATE_REQUEST_PARENT_10_AMOUNT_5000.json"

    Map validTransaction() {
        buildMapResponse(PATH_VALID_TRANSACTION, TransactionDTO)
    }

    Map validTransactionParent10Amount10000() {
        buildMapResponse(PATH_VALID_TRANSACTION_PARENT_10_AMOUNT_10000, TransactionDTO)
    }

    Map validTransactionParent10Amount5000() {
        buildMapResponse(PATH_VALID_TRANSACTION_PARENT_10_AMOUNT_5000, TransactionDTO)
    }
}
