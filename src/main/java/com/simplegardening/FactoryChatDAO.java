package com.simplegardening;

import com.simplegardening.dao.chatDAO.ChatDAO;
import com.simplegardening.dao.chatDAO.ChatDAOCSV;
import com.simplegardening.dao.chatDAO.ChatDAOJDBC;
import com.simplegardening.utils.TypesOfPersistenceLayer;

public class FactoryChatDAO {
    public ChatDAO createChatDAO(TypesOfPersistenceLayer type) {
        return switch (type) {
            case JDBC -> new ChatDAOJDBC();
            case FileSystem -> new ChatDAOCSV();
        };
    }

}
