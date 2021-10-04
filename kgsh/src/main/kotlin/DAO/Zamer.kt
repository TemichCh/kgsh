package DAO

import Model.Akt
import Private.kraftPass
import Private.kraftUrl
import Private.kraftUser
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction


fun saveZamer(tyres: Array<Akt>) =
    transaction(getSQLCon(kraftUrl, kraftUser, kraftPass)) {
        tyres.forEach { akt ->
            akt.tyre.forEach { tyre ->
                TransactionManager.current().exec(
                    """
                declare @akt_id varchar(40), @date smalldatetime,@number_nc varchar(50),@zamer_kol int
                set @akt_id='${akt.akt_id}'
                set @date= convert(smalldatetime,'${akt.date}',104)
                set @number_nc= '${tyre.number}'
                set @zamer_kol = ${tyre.value.toInt()}
                exec dbo.p_kgsh_save_fsh013_ksr @akt_id,@date,@number_nc,@zamer_kol
            """.trimIndent()
                )
            }

        }
    }

