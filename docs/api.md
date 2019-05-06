# Somewhere.beer API

**Note:** `IBU`, `images`, `description` and `staffReviews` fields can return null.

## Get beer

### Request: Get [http://somewhere.beer/WebAccess/api/beer?id=1](http://somewhere.beer/WebAccess/api/beer?id=1)

In this case, we're retrieving the beer with a `beerId` of 1.
The id query param is reguired, if no value is given to this param a error is returned

Current live response to that call:
```json
{
    "beerId": 1,
    "featured": true,
    "images": [
        "/pictues/notthere.img"
    ],
    "ABV": 5,
    "beerName": "test beer",
    "brewery": "test",
    "description": "this is a test record",
    "IBU": 33,
    "staffReviews": [
        {
            "staffMember": "thomas",
            "review": "this was a good imaginary beer"
        }
    ],
    "beerStyle": "the best style"
}
```

## Text search

### Request: Get [http://somewhere.beer/WebAccess/api/textSearch?target=steam](http://somewhere.beer/WebAccess/api/textSearch?target=steam)

In this case, "steam" was search target.
Note the text search is not case sensitive.
Note the text search is inclusive, thus sub-word possitive hits will be returned.
Note the text search compares againist the text of the beer name, brewery name, description, staff reviews and the beer style.

Current abbreviated live response to that call:
```json
{
    "numberFound": 151,
    "beers": [
        {
            "beerId": 640,
            "featured": false,
            "images": null,
            "ABV": 5.3,
            "beerName": "2X Steam",
            "brewery": "Rupert’s Brew House ",
            "description": null,
            "IBU": null,
            "staffReviews": null,
            "beerStyle": "American-Style Pale Ale"
        },
        {
            "beerId": 2347,
            "featured": false,
            "images": null,
            "ABV": 5.5,
            "beerName": "Amber Steam Style",
            "brewery": "American Sky Brewing Co. ",
            "description": null,
            "IBU": 19,
            "staffReviews": null,
            "beerStyle": "American-Style Amber/Red Ale"
        }
    ]
}
```

## Attribute search

### Request: Get [http://somewhere.beer:80/WebAccess/api/attribueSearch?abv=&ibu=33&brewery=steam&type= ](http://somewhere.beer:80/WebAccess/api/attribueSearch?abv=&ibu=33&brewery=steam&type= )

In this case, the search looked for beers that came from a brewery with steam in the name and a ibu of 33.
Note all query parameters are reguired, thus for parameters that current search does not care about just asign no value.
Note the parameter order is abv, ibu, brewery, type and thus order is to be maintained by all calls to the server.
Note the brewery and type parameter are inclusive and non-case sensitive.
Note if an integer value is given for the abv the decimal of the abv becomes inclusive. Thus a search abv of 5 would match on 5, 5.2, 5.9 ... but a search abv with the decimal is not inclusive. Thus a search of 5.5 would only match 5.5

Current live response:
```json
{
    "numberFound": 3,
    "attriSearchedFor": {
        "abv": null,
        "brewry": "steam",
        "type": "",
        "ibu": 33
    },
    "beers": [
        {
            "beerId": 1780,
            "featured": false,
            "images": null,
            "ABV": 8.5,
            "beerName": "Ale Epeteios",
            "brewery": "Steamworks Brewing Company ",
            "description": null,
            "IBU": 33,
            "staffReviews": null,
            "beerStyle": "Golden or Blonde Ale"
        },
        {
            "beerId": 28959,
            "featured": false,
            "images": null,
            "ABV": 5.5,
            "beerName": "Irish Red",
            "brewery": "Steamworks Brewing Company ",
            "description": null,
            "IBU": 33,
            "staffReviews": null,
            "beerStyle": "Irish-Style Red Ale"
        },
        {
            "beerId": 59433,
            "featured": false,
            "images": null,
            "ABV": 5.1,
            "beerName": "Winter Wonder",
            "brewery": "Steamworks Brewing Company ",
            "description": null,
            "IBU": 33,
            "staffReviews": null,
            "beerStyle": "Specialty Stouts"
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
