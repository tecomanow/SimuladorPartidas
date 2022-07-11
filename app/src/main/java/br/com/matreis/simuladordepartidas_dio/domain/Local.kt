package br.com.matreis.simuladordepartidas_dio.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Local(
    @SerializedName("nome"   ) var nome   : String? = null,
    @SerializedName("imagem" ) var imagem : String? = null
) : Parcelable