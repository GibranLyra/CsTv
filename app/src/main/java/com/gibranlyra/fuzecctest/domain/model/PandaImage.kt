package com.gibranlyra.fuzecctest.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

private const val NORMAL_IMAGE_TYPE = "normal_"
private const val THUMBNAIL_IMAGE_TYPE = "thumb_"

@Parcelize
data class PandaImage(private val imageUrl: String = "") : Parcelable {

    fun getImage(imageType: ImageType): String = when {
        imageUrl.isNotEmpty() -> when (imageType) {
            ImageType.DEFAULT -> imageUrl
            ImageType.NORMAL -> appendImageType(imageUrl, NORMAL_IMAGE_TYPE)
            ImageType.THUMBNAIL -> appendImageType(imageUrl, THUMBNAIL_IMAGE_TYPE)
        }

        else -> imageUrl
    }

    private fun appendImageType(imageUrl: String, imageTypeValue: String): String {
        val lastSlashIndex = imageUrl.lastIndexOf('/')
        return if (lastSlashIndex != -1) {
            val originalValue = imageUrl.substring(lastSlashIndex + 1)
            imageUrl.substring(0, lastSlashIndex + 1) + imageTypeValue + originalValue
        } else {
            imageUrl
        }
    }

    enum class ImageType {
        DEFAULT,
        NORMAL,
        THUMBNAIL
    }
}