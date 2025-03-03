package com.example.cocktailapi.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "drinks")
data class DrinkEntity(
    @PrimaryKey val idDrink: String,
    val strDrink: String,
    val strCategory: String?,
    val strAlcoholic: String?,
    val strGlass: String?,
    val strDrinkThumb: String?,

    @ColumnInfo(name = "is_favorite") var isFavorite: Boolean = false
) {
    fun toDrink(): Drink {
        return Drink(
            idDrink = this.idDrink,
            strDrink = this.strDrink,
            strCategory = this.strCategory,
            strAlcoholic = this.strAlcoholic,
            strGlass = this.strGlass,
            strDrinkThumb = this.strDrinkThumb,
            dateModified = null,
            strCreativeCommonsConfirmed = null,
            strDrinkAlternate = null,
            strIBA = null,
            strImageAttribution = null,
            strImageSource = null,
            strIngredient1 = null,
            strIngredient2 = null,
            strIngredient3 = null,
            strIngredient4 = null,
            strIngredient5 = null,
            strIngredient6 = null,
            strIngredient7 = null,
            strIngredient8 = null,
            strIngredient9 = null,
            strIngredient10 = null,
            strIngredient11 = null,
            strIngredient12 = null,
            strIngredient13 = null,
            strIngredient14 = null,
            strIngredient15 = null,
            strInstructions = null,
            strInstructionsDE = null,
            strInstructionsES = null,
            strInstructionsFR = null,
            strInstructionsIT = null,
            strInstructionsZHHANS = null,
            strInstructionsZHHANT = null,
            strMeasure1 = null,
            strMeasure2 = null,
            strMeasure3 = null,
            strMeasure4 = null,
            strMeasure5 = null,
            strMeasure6 = null,
            strMeasure7 = null,
            strMeasure8 = null,
            strMeasure9 = null,
            strMeasure10 = null,
            strMeasure11 = null,
            strMeasure12 = null,
            strMeasure13 = null,
            strMeasure14 = null,
            strMeasure15 = null,
            strTags = null,
            strVideo = null
        )
    }
}