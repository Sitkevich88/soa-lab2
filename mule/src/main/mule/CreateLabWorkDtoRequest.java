//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2024.01.10 at 07:53:23 PM MSK 
//


package com.example.labworkservice.catalog;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="coordinates" type="{http://com/example/labWorkService/catalog}CoordinatesRequestDto"/&gt;
 *         &lt;element name="minimalPoint" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="difficulty" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="discipline" type="{http://com/example/labWorkService/catalog}DisciplineRequestDto"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "name",
    "coordinates",
    "minimalPoint",
    "difficulty",
    "discipline"
})
@XmlRootElement(name = "createLabWorkDtoRequest")
public class CreateLabWorkDtoRequest {

    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected CoordinatesRequestDto coordinates;
    protected int minimalPoint;
    @XmlElement(required = true)
    protected String difficulty;
    @XmlElement(required = true)
    protected DisciplineRequestDto discipline;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the coordinates property.
     * 
     * @return
     *     possible object is
     *     {@link CoordinatesRequestDto }
     *     
     */
    public CoordinatesRequestDto getCoordinates() {
        return coordinates;
    }

    /**
     * Sets the value of the coordinates property.
     * 
     * @param value
     *     allowed object is
     *     {@link CoordinatesRequestDto }
     *     
     */
    public void setCoordinates(CoordinatesRequestDto value) {
        this.coordinates = value;
    }

    /**
     * Gets the value of the minimalPoint property.
     * 
     */
    public int getMinimalPoint() {
        return minimalPoint;
    }

    /**
     * Sets the value of the minimalPoint property.
     * 
     */
    public void setMinimalPoint(int value) {
        this.minimalPoint = value;
    }

    /**
     * Gets the value of the difficulty property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDifficulty() {
        return difficulty;
    }

    /**
     * Sets the value of the difficulty property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDifficulty(String value) {
        this.difficulty = value;
    }

    /**
     * Gets the value of the discipline property.
     * 
     * @return
     *     possible object is
     *     {@link DisciplineRequestDto }
     *     
     */
    public DisciplineRequestDto getDiscipline() {
        return discipline;
    }

    /**
     * Sets the value of the discipline property.
     * 
     * @param value
     *     allowed object is
     *     {@link DisciplineRequestDto }
     *     
     */
    public void setDiscipline(DisciplineRequestDto value) {
        this.discipline = value;
    }

}
