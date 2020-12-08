package nl.fhict.s3.mastermindlogic;

import lombok.Data;

@Data
public class WebSocketMessage {
    private WebSocketMessageOperation operation;
    private String property;
    private String content;
}
