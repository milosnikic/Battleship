/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author milos
 */
public class Ship implements Serializable {

    private String name;
    private Coordinates coordinates;
    private int length;
    private boolean vertical;

    public Ship() {
    }

    public Ship(int length) {
        this.length = length;
    }

    public Ship(String name, int length) {
        this.name = name;
        this.length = length;
    }

    public Ship(String name, Coordinates coordinates, int length, boolean vertical) {
        this.name = name;
        this.coordinates = coordinates;
        this.length = length;
        this.vertical = vertical;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public int getLength() {
        return length;
    }

    public boolean isVertical() {
        return vertical;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setVertical(boolean vertical) {
        this.vertical = vertical;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ship other = (Ship) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

}
