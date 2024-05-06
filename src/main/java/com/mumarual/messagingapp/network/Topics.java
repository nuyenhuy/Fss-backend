package com.mumarual.messagingapp.network;

public class Topics {
    //topics for Partner
    public static final String PARTNER_TICK_DATA = "Partner.Data.Tick";
    public static final String PARTNER_SIGNAL_DATA = "Partner.Data.Signal";

    //topics for Worker
    public static final String WORKER_NAME = "Fintech.Worker.%s";
    public static final String WORKER_BROADCAST = "Fintech.Worker.Broadcast";
    public static final String WORKER_TICK_DATA = "Fintech.Data.Tick";
    public static final String WORKER_SIGNAL_DATA = "Fintech.Data.Signal";

    //Topic for client socket
    private static final String SOCKET_TOPIC = "/socket/topic";
    public static final String CLIENT_TOPIC = SOCKET_TOPIC + "/client.%s";
    public static final String CLIENT_ALL_TOPIC = SOCKET_TOPIC + "/client.all";

    public static final String CHAT_PUBLIC = SOCKET_TOPIC + "/chat/public";
    public static final String CHAT_USER = SOCKET_TOPIC + "/chat/user.%s";

    public static final String SYMBOL_TOPIC = SOCKET_TOPIC + "/sec.%s";
    public static final String GUEST_TOPIC = SOCKET_TOPIC + "/guest.%s";
}
