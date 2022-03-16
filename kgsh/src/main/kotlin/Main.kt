import DAO.getTyres
import DAO.saveZamer
import Model.AktTyre
import Model.Akt
import Private.getUrl
import Private.sendUrl
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.skif.model.Vehicles
import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.client.features.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.*
import kotlinx.coroutines.runBlocking
import org.joda.time.DateTime
import java.io.File


val client = HttpClient(Apache) {
    install(Logging) {
        //level = LogLevel.BODY
        level = LogLevel.ALL
    }
    install(HttpTimeout) {
        // timeout config
        socketTimeoutMillis = 30000
        requestTimeoutMillis = 100000
    }
    engine {

    }
}

//процедура отправки http запросов
suspend fun sendRequest(url: String, meth: HttpMethod, js: Any): HttpResponse {
    val file = File("send.json")
    file.writeText(js.toString())
    return client.request {
        url(url) //)
        contentType(ContentType.Application.Xml)
        method = meth
        body = js
        /*if (xToken != null) {
            //если есть токен добавляем заголовок
           // header("X-Appercode-Session-Token", xToken.sessionId)
        }*/

    }
}

fun write2XMLString(obj: Any): String {
    val xmlMapper = XmlMapper(JacksonXmlModule().apply { setDefaultUseWrapper(true) }).apply {
        enable(SerializationFeature.INDENT_OUTPUT)
        enable(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED)
    }

    return xmlMapper.writeValueAsString(obj)
}

@OptIn(InternalAPI::class)
fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")


    //val s = "WWFuZGV4LkZpbnRlY2guQW5kcm9pZA=="
    //println(s.decodeBase64String())
     val autos = Vehicles(getTyres())
     val xml = write2XMLString(autos)
     println(xml)
    runBlocking {
        sendRequest(sendUrl,HttpMethod.Post,write2XMLString(autos))

        val url = getUrl+"from=${DateTime.now().minusDays(4).toLocalDate().toDateTimeAtStartOfDay().toString("dd.MM.yyyy")}&to=${DateTime.now().toLocalDate().toDateTimeAtStartOfDay().toString("dd.MM.yyyy")}"
        val mess = sendRequest(url, HttpMethod.Post, "")
        println(mess)
        /*val akt = Akt("123", "222","21.09.2021", "122",
            mutableListOf(AktTyre("22222", 112.25f)))
        println(write2XMLString(akt))*/

        val xmlMapper: ObjectMapper = XmlMapper()
        val value = xmlMapper.readValue(mess.readText(), Array<Akt>::class.java)
        println(value)
        saveZamer(value)
    }
}
