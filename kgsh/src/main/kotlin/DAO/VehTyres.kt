package DAO

import Private.kraftPass
import Private.kraftUrl
import Private.kraftUser
import com.skif.model.Tyre
import com.skif.model.Vehicle
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object VehTyres : Table("v_kgsh_object_tyres_ksr") {
    val pred_nam = varchar("pred_nam", 150)
    val objrem_id = integer("objrem_id")
    val objrem_nam = varchar("objrem_nam", 250)
    val bort_nc = varchar("bort_nc", 50)
    val shpos_abr = varchar("shpos_abr", 10)
    val shpos_id = integer("shpos_id")
    val numser_nc = varchar("numser_nc", 100)
}

fun getTyres(): MutableList<Vehicle> {
    val autos = mutableListOf<Vehicle>()
    transaction(getSQLCon(kraftUrl, kraftUser, kraftPass)) {

        val rows = VehTyres.selectAll()
        rows.map {
            var curauto = autos.getOrNull(autos.lastIndex)
            if ((curauto?.id ?: 0) != it[VehTyres.objrem_id]) {
                curauto = Vehicle(it[VehTyres.objrem_id], it[VehTyres.objrem_nam], it[VehTyres.bort_nc], mutableListOf<Tyre>())
                autos.add(curauto)
            }
            if (curauto != null)
                curauto.tyre?.add(Tyre(it[VehTyres.shpos_id], it[VehTyres.numser_nc]))
        }
    }
    return autos
}