/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;

/**
 *
 * @author milos
 */
public enum FieldState implements Serializable {
    /**
     * Empty field
     */
    EMPTY,
    /**
     * Field on which a ship has been placed
     */
    SHIP,
    /**
     * Empty field which has been shot
     */
    SHOT,
    /**
     * Ship field which has been shot
     */
    HIT
}
