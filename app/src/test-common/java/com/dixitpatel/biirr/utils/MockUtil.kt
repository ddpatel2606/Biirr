package com.dixitpatel.biirr.utils

import com.dixitpatel.biirr.model.*

object MockUtil {

  fun mockBeerModel() = BeerModel(
    id="c4f2KE",
    name = "'Murican Pilsner", abv = "5.5" , ibu = null, description = null,
    LabelsModel(mediumIcon="https://brewerydb-images.s3.amazonaws.com/beer/c4f2KE/upload_jjKJ7g-medium.png",
      contentAwareMedium="https://brewerydb-images.s3.amazonaws.com/beer/c4f2KE/upload_jjKJ7g-contentAwareMedium.png") , available = null,
    StyleModel(category= CategoryModel(name="North American Lager", createDate="2012-03-21 20:06:46"),
    name="American-Style Pilsener", description="This classic and unique pre-Prohibition American-style Pilsener is straw to deep gold in color. Hop bitterness, flavor and aroma are medium to high, and use of noble-type hops for flavor and aroma is preferred. Up to 25 percent corn and/or rice in the grist should be used. Malt flavor and aroma are medium. This is a light-medium to medium-bodied beer. Sweet corn-like dimethylsulfide (DMS), fruity esters and American hop-derived citrus flavors or aromas should not be perceived. Diacetyl is not acceptable. There should be no chill haze. Competition organizers may wish to subcategorize this style into rice and corn subcategories.",
   ibuMin=25.0F, ibuMax=40.0F, abvMin=5.0F, abvMax=6.0F),createDate="2013-08-19 11:58:12")

  fun mockBeerList() = listOf(mockBeerModel())

}
