# Somewhere.beer API

**Note:** `IBU`, `images`, `description` and `staffReviews` fields can return null.

## Get beer

### Request: Get [somewhere.beer/api/beer/1](https://somewhere.beer/api/beer/1)

In this case, we're retrieving the beer with a `beerId` of 1.

Example response:
```json
{
  "beerId": 1,
  "beerName": "Edmund Fitzgerald Porter",
  "beerStyle": "American Porter",
  "brewery": "Great Lakes Brewing Co",
  "ABV": 6.0,
  "IBU": 37,
  "featured": false,
  "images": [
    "/media/pictures/beer_1_photo1",
    "/media/pictures/beer_1_photo2"
  ],
  "description": "Robust and complex, our Porter is a bittersweet tribute to the legendary freighter fallen crew--taken too soon when the gales of November came early.",
  "staffReviews": [
    {
      "staffMember": "thomas",
      "review": "A nice porter that doesn’t carry too heavy into the chocolate side of the roast flavor"
    },
    {
      "staffMember": "david",
      "review": "A enjoyable nice rich Porter, complex yet drinkable"
    }
  ]
}
```

## Text search

### Request: Get [somewhere.beer/api/textSearch/california](https://somewhere.beer/api/textSearch/california)

In this case, "california" was entered into the search textbox.
Note the text search is not case sensitive

Example response:
```json
{
  "searchedFor": "california",
  "numberFound": 1,
  "beers": [
    {
      "beerId": 456,
      "beerName": "Anchor California Lager",
      "brewery": "Anchor Brewing Company",
      "beerStyle": "American Lager",
      "ABV": 4.9,
      "IBU": null,
      "featured": false,
      "images": null,
      "description": "Anchor Steam's roots go back to the Gold Rush, long before icehouses and modern refrigeration made traditional lagers a viable California option. In 1876, thanks to an ice pond in the mountains and a belief that anything is possible in the Golden State, a little brewery named Boca created California's first genuine lager. Anchor California Lager is our re-creation of this historic American beer.",
      "staffReviews": null
    }
  ]
}
```

## Attribute search

### Request: Get [somewhere.beer/api/attributeSearch/abv-null_ibu-null_type-porter_brewery-null](https://somewhere.beer/api/attributeSearch/abv-null_ibu-null_type-porter_brewery-null)

In this case, the type "Porter" was typed in on the attribute search menu.

Example response:
```json
{
  "attribSearchedFor": {
    "abv": null,
    "ibu": null,
    "brewery": null,
    "type": "porter"
  },
  "numberFound": 2,
  "beers": [
    {
      "beerId": 1,
      "beerName": "Edmund Fitzgerald Porter",
      "beerStyle": "American Porter",
      "brewery": "Great Lakes Brewing Co",
      "ABV": 6.0,
      "IBU": 37,
      "featured": false,
      "images": [
        "/media/pictures/beer_1_photo1",
        "/media/pictures/beer_1_photo2"
      ],
      "description": "Robust and complex,our Porter is a bittersweet tribute to the legendary freighter fallen crew--taken too soon when the gales of November came early.",
      "staffReviews": [
        {
          "staffMember": "thomas",
          "review": "A nice porter that doesn’t carry too heavy into the chocolate side of the roast flavor"
        },
        {
          "staffMember": "david",
          "review": "A enjoyable nice rich Porter, complex yet drinkable"
        }
      ],
    },
    {
      "beerId": 458,
      "beerName": "Anchor Porter",
      "beerStyle": "American Porter",
      "brewery": "Anchor Steam",
      "ABV": 5.6,
      "IBU": null,
      "featured": false,
      "images": [
        "/media/pictures/beer_458_photo1"
      ],
      "description": "A blend of specially roasted pale, caramel, chocolate, and black malts, along with our top-fermenting yeast, creates complexity without bitterness. The brew is hopped at a high rate, and naturally carbonated. The result is dark in the glass, but surprisingly light on the palate.",
      "staffReviews": [
        {
          "staffMember": "thomas",
          "review": "One of my favorite American dark beers"
        },
        {
          "staffMember": "david",
          "review": "Low head for a porter, but a very smooth finish and has a warm caramel smell to it"
        }
      ]
    }
  ]
}
```

## Get category 

### Request: Get [somewhere.beer/api/category/porter](https://somewhere.beer/api/category/porter)

In this case, "Porter" is the category of the beer to retrieve.
For a list of the valid categories see the category breakdown sheet.
Note that non-valid category values in the case will have a null returned

Example response:
```json
{
  "category": "Porter",
  "numberFound": 2,
  "beers": [
    {
      "beerId": 1,
      "beerName": "Edmund Fitzgerald Porter",
      "beerStyle": "American Porter",
      "brewery": "Great Lakes Brewing Co",
      "ABV": 6.0,
      "IBU": 37,
      "featured": false,
      "images": [
        "/media/pictures/beer_1_photo1",
        "/media/pictures/beer_1_photo2"
      ],
      "description": "Robust and complex,our Porter is a bittersweet tribute to the legendary freighter fallen crew--taken too soon when the gales of November came early.",
      "staffReviews": [
        {
          "staffMember": "thomas",
          "review": "A nice porter that doesn’t carry too heavy into the chocolate side of the roast flavor"
        },
        {
          "staffMember": "david",
          "review": "A enjoyable nice rich Porter, complex yet drinkable"
        }
      ]
    },
    {
      "beerId": 458,
      "beerName": "Anchor Porter",
      "beerStyle": "American Porter",
      "brewery": "Anchor Steam",
      "ABV": 5.6,
      "IBU": null,
      "featured": false,
      "images": [
        "/media/pictures/beer_458_photo1"
      ],
      "description": "A blend of specially roasted pale, caramel, chocolate, and black malts, along with our top-fermenting yeast, creates complexity without bitterness. The brew is hopped at a high rate, and naturally carbonated. The result is dark in the glass, but surprisingly light on the palate.",
      "staffReviews": [
        {
          "staffMember": "thomas",
          "review": "One of my favorite American dark beers"
        },
        {
          "staffMember": "david",
          "review": "Low head for a porter, but a very smooth finish and has a warm caramel smell to it"
        }
      ]
    }
  ]
}
``` 

## Get featured

### Request: Get [somwhere.beer/api/featured](https://somewhere.beer/api/featured)

Returns the beer that is currently featured on the front page.

Example response:
```json
{
  "beerId": 1029,
  "beerName": "Kellerweis",
  "beerStyle": "German Hefeweizen",
  "brewery": "Sierra Nevada Brewing Co",
  "ABV": 4.8,
  "IBU": null,
  "featured": true,
  "images": [
    "/media/pictures/beer_5669_photo1"
  ],
  "description": "A amerian made bavarian-style wheat beer with a warm and sweet flavor",
  "staffReviews": [
    {
      "staffMember": "thomas",
      "review": "Has smell notes of clove and slight banana. The taste is classic to the style"
    },
    {
      "staffMember": "david",
      "review": "Not a big wheat beer guy, but this is one of the better ones I have tried recently"
    }
  ]
}
```
