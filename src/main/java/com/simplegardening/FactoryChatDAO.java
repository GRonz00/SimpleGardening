package com.simplegardening;

import com.simplegardening.dao.chat_dao.ChatDAO;
import com.simplegardening.dao.chat_dao.ChatDAOFile;
import com.simplegardening.dao.chat_dao.ChatDAOJDBC;
import com.simplegardening.utils.TypesOfPersistenceLayer;

public class FactoryChatDAO {
    public ChatDAO createChatDAO(TypesOfPersistenceLayer type) {
        return switch (type) {
            case JDBC -> new ChatDAOJDBC();
            case FILE_SYSTEM -> new ChatDAOFile();
        };
    }

}
