package com.grand.navigation.models
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.text.DateFormat

@Parcelize
data class Product(
    val brand: String,
    val category: String,
    val description: String,
    val discountPercentage: Double,
    val id: Int,
    val images: List<String>,
    val price: Int,
    val rating: Double,
    val stock: Int,
    val thumbnail: String,
    val title: String
): Parcelable {
    val createdDateFormatted: String
        get() = DateFormat.getDateTimeInstance().format(id)
}
