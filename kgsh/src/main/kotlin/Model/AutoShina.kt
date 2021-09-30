package com.skif.model

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

//JacksonXmlRootElement(localName = "Tyre")
data class Tyre(
    @get:JacksonXmlProperty(isAttribute = true)
    val slot_id:Int,
    @get:JacksonXmlProperty(isAttribute = true)
    val number:String?
)

@JacksonXmlRootElement(localName = "Vehicle")
data class Vehicle(
    @get:JacksonXmlProperty(isAttribute = true)
    val id:Int,
    @get:JacksonXmlProperty(isAttribute = true)
    val model:String,
    @get:JacksonXmlProperty(isAttribute = true)
    val number:String?,
    @get:JacksonXmlElementWrapper(localName = "Tyres",useWrapping = false)
    @field:JacksonXmlProperty(localName = "Tyre")
    //@get:@JacksonXmlProperty(localName = "card")
    val tyre:MutableList<Tyre>?
)

data class Vehicles (
    @get:JacksonXmlElementWrapper(localName = "Vehicles",useWrapping = false)
    @field:JacksonXmlProperty(localName = "Vehicle")
    val vehicle:MutableList<Vehicle>)
