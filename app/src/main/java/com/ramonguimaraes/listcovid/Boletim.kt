package com.ramonguimaraes.listcovid

import org.json.JSONArray
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class Boletim(
    var suspeitos: Int=0,
    var confirmados: Int=0,
    var descartados: Int=0,
    var monitoramento: Int=0,
    var curados: Int=0,
    var sDomiciliar: Int=0,
    var sHospitalar: Int=0,
    var mortes: Int=0,
    var data: String,
    var hora: String
)
