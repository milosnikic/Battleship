/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.Serializable;

/**
 *
 * @author milos
 */
public enum Operation implements Serializable {
    LOGIN,
    CREATE_GAME,
    START_GAME,
    USER_SHOOT,
    SERVER_SHOOT,
    USER_WIN,
    SERVER_WIN,
    SCOREBOARD,
    END
}
