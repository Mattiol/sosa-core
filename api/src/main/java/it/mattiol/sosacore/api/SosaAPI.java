package it.mattiol.sosacore.api;

import it.mattiol.sosacore.api.service.*;

public interface SosaAPI {

    PlayerService players();

    MessageService messages();

    SpawnService spawn();

    SentinelService sentinel();

    ChatService chat();
}
