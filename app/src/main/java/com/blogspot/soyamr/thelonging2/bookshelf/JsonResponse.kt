package com.blogspot.soyamr.thelonging2.bookshelf

data class JsonResponse(
    val items: List<Item>
)

data class Item(
    val volumeInfo: VolumeInfo
)


data class VolumeInfo(
    val authors: List<String>,
    val averageRating: Double,
    val description: String,
    val imageLinks: ImageLinks,
    val language: String,
    val maturityRating: String,
    val pageCount: Int,
    val previewLink: String,
    val publishedDate: String,
    val publisher: String,
    val ratingsCount: Int,
    val title: String
)

data class ImageLinks(
    val smallThumbnail: String,
    val thumbnail: String
)