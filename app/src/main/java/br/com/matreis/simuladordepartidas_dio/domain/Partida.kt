package br.com.matreis.simuladordepartidas_dio.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Partida(
    @SerializedName("descricao" ) var descricao : String?    = null,
    @SerializedName("local"     ) var local     : Local?     = Local(),
    @SerializedName("mandante"  ) var mandante  : Time?      = Time(),
    @SerializedName("visitante" ) var visitante : Time?      = Time()
) : Parcelable
