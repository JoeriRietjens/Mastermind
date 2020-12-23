package nl.fhict.s3.mastermindlogic;

public enum WebSocketMessageOperation {
    REGISTER_GAME,
    UNREGISTER_GAME,
    JOIN_GAME,
    LEAVE_GAME,
    SUBMIT_GUESS,
    SUBMIT_CODE,
    GET_EMPTY_ROW
}
