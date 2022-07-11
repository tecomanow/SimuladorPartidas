package br.com.matreis.simuladordepartidas_dio.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Time(
    @SerializedName("nome"   ) var nome   : String? = null,
    @SerializedName("forca"  ) var forca  : Int?    = null,
    @SerializedName("imagem" ) var imagem : String? = null,
    var pontuacao : Int? = null
) : Parcelable
