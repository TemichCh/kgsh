package Model

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

@JacksonXmlRootElement
data class Akt(
    @JsonProperty("id")
    @get:JacksonXmlProperty(isAttribute = true,localName = "akt_id")
    val akt_id: String,
    @JsonProperty("number")
    @get:JacksonXmlProperty(isAttribute = true, localName = "number")
    val number: String,
    @JsonProperty("date")
    @get:JacksonXmlProperty(isAttribute = true,localName = "date")
    val date:String,
    @JsonProperty("vehicle_id")
    @get:JacksonXmlProperty(isAttribute = true,localName = "vehicle_id")
    val vehicle_id: String,
    @JsonProperty("Tyre")
    @get:JacksonXmlElementWrapper(localName = "Tyres",useWrapping = false)
    @field:JacksonXmlProperty(localName = "Tyre")
    val tyre:  List<AktTyre> = ArrayList()
)

data class AktTyre(
    @JsonProperty("number")
    @get:JacksonXmlProperty(isAttribute = true )
    val number: String,
    @JsonProperty("value")
    @get:JacksonXmlProperty(isAttribute = true)
    val value: Float
)