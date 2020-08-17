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
    private int fieldsAlive;

    public Ship(int length) {
        this.length = length;
    }

    public Ship(String name, int length) {
        this.name = name;
        this.length = length;
        this.fieldsAlive = length;
    }

    public Ship(String name, Coordinates coordinates, int length, boolean vertical, int fieldsAlive) {
        this.name = name;
        this.coordinates = coordinates;
        this.length = length;
        this.vertical = vertical;
        this.fieldsAlive = fieldsAlive;
    }

    public boolean isAlive() {
        return getFieldsAlive() > 0;
    }

    public void hit() {
        if (isAlive()) {
            this.setFieldsAlive(this.getFieldsAlive() - 1);
        }
    }

    @Override
    public String toString() {
        return this.getName();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
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

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the coordinates
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * @param coordinates the coordinates to set
     */
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * @return the length
     */
    public int getLength() {
        return length;
    }

    /**
     * @param length the length to set
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * @return the vertical
     */
    public boolean isVertical() {
        return vertical;
    }

    /**
     * @param vertical the vertical to set
     */
    public void setVertical(boolean vertical) {
        this.vertical = vertical;
    }

    /**
     * @return the fieldsAlive
     */
    public int getFieldsAlive() {
        return fieldsAlive;
    }

    /**
     * @param fieldsAlive the fieldsAlive to set
     */
    public void setFieldsAlive(int fieldsAlive) {
        this.fieldsAlive = fieldsAlive;
    }

}
