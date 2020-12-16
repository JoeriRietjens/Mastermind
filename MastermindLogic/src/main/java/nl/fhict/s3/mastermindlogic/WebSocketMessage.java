package nl.fhict.s3.mastermindlogic;

import lombok.Data;

import java.util.UUID;

@Data
public class WebSocketMessage {
    private WebSocketMessageOperation operation;
    private UUID gameId;
    private int playerId;
    private String content;
}
