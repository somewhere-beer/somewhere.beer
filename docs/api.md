Somewhere API

## Get beer

### Request: Get [somwhere.beer/api/beer/1](https://somwhere.beer/api/beer/1)

In this case, 1 is the id of the beer to retrieve.

Example response:

{

"beerName": "Edmund Fitzgerald Porter",

"beerStyle": "American Porter",

"brewery": "Great Lakes Brewing Co",

"ABV": 6.0,

"featured": false,

"IBUs": 37,

"images": ["/media/pictures/beer_1_photo1", "/media/pictures/beer_1_photo2"],

"description": "Robust and complex,our Porter is a bittersweet tribute to the legendary freighter fallen crew--taken too soon when the gales of November came early.",

"staffReviews": [{

"staffMember": "thomas",

"review": "A nice porter that dosen’ t carry to heavy into the chocolate side of the roast flavor"

}, {

"staffMember": "david",

"review": "A enjoyable nice rich Porter, complex yet drinkable"

}]

}

  

Note IBUs, images, description and staffReviews fields can return null

## Text search

### Request: Get [somwhere.beer/api/](https://somwhere.beer/api/beer/1)textSearch/california

In this case, california is what was put into the search textbox.

Example response:

{

"searchedFor": "california",

"numberFound": 1,

"beers": [{

"beerId": 456,

"beerName": "Anchor California Lager",

"brewery": "Anchor Brewing Company",

"beerStyle": "American Lager",

"ABV": 4.9,

"featured": false,

"IBUs": null,

"Images": null,

"description": "Anchor Steam's roots go back to the Gold Rush, long before icehouses and modern refrigeration made traditional lagers a viable California option. In 1876, thanks to an ice pond in the mountains and a belief that anything is possible in the Golden State, a little brewery named Boca created California's first genuine lager. Anchor California Lager is our re-creation of this historic American beer.",

"staffReviews": null

}]

}

  

  

## Attribute search

### Request: Get [somwhere.beer/api/](https://somwhere.beer/api/beer/1)attribueSearch/abv-null_ibu-null-type_porter_brewery-null

In this case,the type porter is what was put into the attribute search.

Example response:

{

"attribSearchedFor": {

"abv": null,

"ibu": null,

"brewery": null,

"type": "porter"

},

"numberFound": 2,

"beers": [{

"beerName": "Edmund Fitzgerald Porter",

"beerStyle": "American Porter",

"brewery": "Great Lakes Brewing Co",

"ABV": 6.0,

"featured": false,

"IBUs": 37,

"images": ["/media/pictures/beer_1_photo1", "/media/pictures/beer_1_photo2"],

"description": "Robust and complex,our Porter is a bittersweet tribute to the legendary freighter fallen crew--taken too soon when the gales of November came early.",

"staffReviews": [{

"staffMember": "thomas",

"review": "A nice porter that dosen’ t carry to heavy into the chocolate side of the roast flavor"

}, {

"staffMember": "david",

"review": "A enjoyable nice rich Porter, complex yet drinkable"

},

{

"beerId": 458,

"beerName": "Anchor Porter",

"beerStyle": "American Porter",

"brewery": "Anchor Steam",

"ABV": 5.6,

"featured": false,

"IBUs": null,

"images": ["/media/pictures/beer_458_photo1"],

"description": "A blend of specially roasted pale, caramel, chocolate, and black malts, along with our top-fermenting yeast, creates complexity without bitterness. The brew is hopped at a high rate, and naturally carbonated. The result is dark in the glass, but surprisingly light on the palate.",

"staffReviews": [{

"staffMember": "thomas",

"review": "One of my favorite american dark beers"

}, {

"staffMember": "david",

"review": "Low head for a porter, but a very smooth finsh and has a warm caramel smell to it"

}]

}

]

}]

}

## Get category 

### Request: Get [somwhere.beer/api/category/](https://somwhere.beer/api/beer/1)american-porter

In this case, american porter  is the category of the beer to retrieve.

Example response:

{

"category": "american porter",

"numberFound": 2,

"beers": [{

"beerName": "Edmund Fitzgerald Porter",

"beerStyle": "American Porter",

"brewery": "Great Lakes Brewing Co",

"ABV": 6.0,

"featured": false,

"IBUs": 37,

"images": ["/media/pictures/beer_1_photo1", "/media/pictures/beer_1_photo2"],

"description": "Robust and complex,our Porter is a bittersweet tribute to the legendary freighter fallen crew--taken too soon when the gales of November came early.",

"staffReviews": [{

"staffMember": "thomas",

"review": "A nice porter that dosen’ t carry to heavy into the chocolate side of the roast flavor"

}, {

"staffMember": "david",

"review": "A enjoyable nice rich Porter, complex yet drinkable"

},

{

"beerId": 458,

"beerName": "Anchor Porter",

"beerStyle": "American Porter",

"brewery": "Anchor Steam",

"ABV": 5.6,

"featured": false,

"IBUs": null,

"images": ["/media/pictures/beer_458_photo1"],

"description": "A blend of specially roasted pale, caramel, chocolate, and black malts, along with our top-fermenting yeast, creates complexity without bitterness. The brew is hopped at a high rate, and naturally carbonated. The result is dark in the glass, but surprisingly light on the palate.",

"staffReviews": [{

"staffMember": "thomas",

"review": "One of my favorite american dark beers"

}, {

"staffMember": "david",

"review": "Low head for a porter, but a very smooth finsh and has a warm caramel smell to it"

}]

}

]

}]

}

  

## Get featured

### Request: Get [somwhere.beer/api/](https://somwhere.beer/api/beer/1)featured

Returns the beer that is currently featured.

Example response:

{

"beerName": "Kellerweis",

"beerStyle": "German Hefeweizen",

"brewery": "Sierra Nevada Brewing Co",

"ABV": 4.8,

"featured": true,

"IBUs": null,

"images": ["/media/pictures/beer_5669_photo1"],

"description": "A amerian made bavarian-style wheat beer with a warm and sweet flavor",

"staffReviews": [{

"staffMember": "thomas",

"review": "Has smell notes of clove and slight banana. The taste is classic to the style"

}, {

"staffMember": "david",

"review": "Not a big wheat beer guy, but this is one of the better ones I have tried recently"

}]

}
