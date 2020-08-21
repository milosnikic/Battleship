
package wsclient;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for response complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="response">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="coordinates" type="{http://ws/}coordinates" minOccurs="0"/>
 *         &lt;element name="hit" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="map" type="{http://ws/}map" minOccurs="0"/>
 *         &lt;element name="operation" type="{http://ws/}operation" minOccurs="0"/>
 *         &lt;element name="rankList" type="{http://ws/}rankItem" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="responseStatus" type="{http://ws/}responseStatus" minOccurs="0"/>
 *         &lt;element name="ship" type="{http://ws/}ship" minOccurs="0"/>
 *         &lt;element name="user" type="{http://ws/}user" minOccurs="0"/>
 *         &lt;element name="userPlaying" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "response", propOrder = {
    "coordinates",
    "hit",
    "map",
    "operation",
    "rankList",
    "responseStatus",
    "ship",
    "user",
    "userPlaying"
})
public class Response {

    protected Coordinates coordinates;
    protected Boolean hit;
    protected Map map;
    protected Operation operation;
    @XmlElement(nillable = true)
    protected List<RankItem> rankList;
    protected ResponseStatus responseStatus;
    protected Ship ship;
    protected User user;
    protected Boolean userPlaying;

    /**
     * Gets the value of the coordinates property.
     * 
     * @return
     *     possible object is
     *     {@link Coordinates }
     *     
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Sets the value of the coordinates property.
     * 
     * @param value
     *     allowed object is
     *     {@link Coordinates }
     *     
     */
    public void setCoordinates(Coordinates value) {
        this.coordinates = value;
    }

    /**
     * Gets the value of the hit property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isHit() {
        return hit;
    }

    /**
     * Sets the value of the hit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHit(Boolean value) {
        this.hit = value;
    }

    /**
     * Gets the value of the map property.
     * 
     * @return
     *     possible object is
     *     {@link Map }
     *     
     */
    public Map getMap() {
        return map;
    }

    /**
     * Sets the value of the map property.
     * 
     * @param value
     *     allowed object is
     *     {@link Map }
     *     
     */
    public void setMap(Map value) {
        this.map = value;
    }

    /**
     * Gets the value of the operation property.
     * 
     * @return
     *     possible object is
     *     {@link Operation }
     *     
     */
    public Operation getOperation() {
        return operation;
    }

    /**
     * Sets the value of the operation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Operation }
     *     
     */
    public void setOperation(Operation value) {
        this.operation = value;
    }

    /**
     * Gets the value of the rankList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rankList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRankList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RankItem }
     * 
     * 
     */
    public List<RankItem> getRankList() {
        if (rankList == null) {
            rankList = new ArrayList<RankItem>();
        }
        return this.rankList;
    }

    /**
     * Gets the value of the responseStatus property.
     * 
     * @return
     *     possible object is
     *     {@link ResponseStatus }
     *     
     */
    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    /**
     * Sets the value of the responseStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseStatus }
     *     
     */
    public void setResponseStatus(ResponseStatus value) {
        this.responseStatus = value;
    }

    /**
     * Gets the value of the ship property.
     * 
     * @return
     *     possible object is
     *     {@link Ship }
     *     
     */
    public Ship getShip() {
        return ship;
    }

    /**
     * Sets the value of the ship property.
     * 
     * @param value
     *     allowed object is
     *     {@link Ship }
     *     
     */
    public void setShip(Ship value) {
        this.ship = value;
    }

    /**
     * Gets the value of the user property.
     * 
     * @return
     *     possible object is
     *     {@link User }
     *     
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the value of the user property.
     * 
     * @param value
     *     allowed object is
     *     {@link User }
     *     
     */
    public void setUser(User value) {
        this.user = value;
    }

    /**
     * Gets the value of the userPlaying property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isUserPlaying() {
        return userPlaying;
    }

    /**
     * Sets the value of the userPlaying property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setUserPlaying(Boolean value) {
        this.userPlaying = value;
    }

}
