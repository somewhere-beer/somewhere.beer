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
    ],
        "searchedFor": "steam"
}
```

## Attribute search

### Request: Get [http://somewhere.beer/WebAccess/api/attribueSearch?abv=&ibu=33&brewery=steam&type= ](http://somewhere.beer/WebAccess/api/attribueSearch?abv=&ibu=33&brewery=steam&type= )

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

### Request: Get [http://somewhere.beer/WebAccess/api/category?category=amber](http://somewhere.beer/WebAccess/api/category?category=amber)

In this case, "Amber" is the category of the beer to retrieve.
For a list of the valid categories see the category breakdown sheet.
Note that non-valid category values in the case will have empty json is returned
Note that most categories will build and return thousands of beer JSON objects

Current abbreviated live response to that call:
```json
{
    "numberFound": 1891,
    "beers": [
        {
            "beerId": 54,
            "featured": false,
            "images": null,
            "ABV": 5.7,
            "beerName": "#amberale",
            "brewery": "Mad Beach Craft Brewing Company ",
            "description": null,
            "IBU": null,
            "staffReviews": null,
            "beerStyle": "American-Style Amber/Red Ale"
        },
        {
            "beerId": 80,
            "featured": false,
            "images": null,
            "ABV": 5.8,
            "beerName": "‘Merican",
            "brewery": "Jekyll Brewing ",
            "description": null,
            "IBU": null,
            "staffReviews": null,
            "beerStyle": "American-Style Amber/Red Ale"
        },
        {
            "beerId": 245,
            "featured": false,
            "images": null,
            "ABV": 10.1,
            "beerName": "11th Anniversary Ale",
            "brewery": "Cigar City Brewing ",
            "description": null,
            "IBU": null,
            "staffReviews": null,
            "beerStyle": "American-Style Amber/Red Ale"
        },
        {
            "beerId": 274,
            "featured": false,
            "images": null,
            "ABV": 5.5,
            "beerName": "13 Rebels ESB",
            "brewery": "Crafty Dan ",
            "description": null,
            "IBU": 43,
            "staffReviews": null,
            "beerStyle": "American-Style Amber/Red Ale"
        },
        {
            "beerId": 325,
            "featured": false,
            "images": null,
            "ABV": 4.8,
            "beerName": "1623 Brown Ale",
            "brewery": "Coney Island Brewing Company ",
            "description": null,
            "IBU": 35,
            "staffReviews": null,
            "beerStyle": "American-Style Amber/Red Ale"
        }
    ]
}
``` 

## Get featured

### Request: Get [http://somewhere.beer/WebAccess/api/featured](http://somewhere.beer/WebAccess/api/featured)

Returns the beer that is currently featured.
Note there is only ever one beer featured at a time.

Current live response:
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
## Get light

### Request: Get [http://somewhere.beer/WebAccess/api/light](http://somewhere.beer/WebAccess/api/light)

Returns a list of light beers

Abbreviated example response to that call:
```json
{
    
    "lightBeers": [
        {
            "beerId": 54,
            "featured": false,
            "images": null,
            "ABV": 5.7,
            "beerName": "#amberale",
            "brewery": "Mad Beach Craft Brewing Company ",
            "description": null,
            "IBU": null,
            "staffReviews": null,
            "beerStyle": "American-Style Amber/Red Ale"
        },
        {
            "beerId": 80,
            "featured": false,
            "images": null,
            "ABV": 5.8,
            "beerName": "‘Merican",
            "brewery": "Jekyll Brewing ",
            "description": null,
            "IBU": null,
            "staffReviews": null,
            "beerStyle": "American-Style Amber/Red Ale"
        },
        {
            "beerId": 245,
            "featured": false,
            "images": null,
            "ABV": 10.1,
            "beerName": "11th Anniversary Ale",
            "brewery": "Cigar City Brewing ",
            "description": null,
            "IBU": null,
            "staffReviews": null,
            "beerStyle": "American-Style Amber/Red Ale"
        },
        {
            "beerId": 274,
            "featured": false,
            "images": null,
            "ABV": 5.5,
            "beerName": "13 Rebels ESB",
            "brewery": "Crafty Dan ",
            "description": null,
            "IBU": 43,
            "staffReviews": null,
            "beerStyle": "American-Style Amber/Red Ale"
        },
        {
            "beerId": 325,
            "featured": false,
            "images": null,
            "ABV": 4.8,
            "beerName": "1623 Brown Ale",
            "brewery": "Coney Island Brewing Company ",
            "description": null,
            "IBU": 35,
            "staffReviews": null,
            "beerStyle": "American-Style Amber/Red Ale"
        }
    ]
}
``` 

## Get dark

### Request: Get [http://somewhere.beer/WebAccess/api/dark](http://somewhere.beer/WebAccess/api/dark)

Returns a list of dark beers

Abbreviated example response to that call:
```json
{
    
    "darkBeers": [
        {
            "beerId": 54,
            "featured": false,
            "images": null,
            "ABV": 5.7,
            "beerName": "#amberale",
            "brewery": "Mad Beach Craft Brewing Company ",
            "description": null,
            "IBU": null,
            "staffReviews": null,
            "beerStyle": "American-Style Amber/Red Ale"
        },
        {
            "beerId": 80,
            "featured": false,
            "images": null,
            "ABV": 5.8,
            "beerName": "‘Merican",
            "brewery": "Jekyll Brewing ",
            "description": null,
            "IBU": null,
            "staffReviews": null,
            "beerStyle": "American-Style Amber/Red Ale"
        },
        {
            "beerId": 245,
            "featured": false,
            "images": null,
            "ABV": 10.1,
            "beerName": "11th Anniversary Ale",
            "brewery": "Cigar City Brewing ",
            "description": null,
            "IBU": null,
            "staffReviews": null,
            "beerStyle": "American-Style Amber/Red Ale"
        },
        {
            "beerId": 274,
            "featured": false,
            "images": null,
            "ABV": 5.5,
            "beerName": "13 Rebels ESB",
            "brewery": "Crafty Dan ",
            "description": null,
            "IBU": 43,
            "staffReviews": null,
            "beerStyle": "American-Style Amber/Red Ale"
        },
        {
            "beerId": 325,
            "featured": false,
            "images": null,
            "ABV": 4.8,
            "beerName": "1623 Brown Ale",
            "brewery": "Coney Island Brewing Company ",
            "description": null,
            "IBU": 35,
            "staffReviews": null,
            "beerStyle": "American-Style Amber/Red Ale"
        }
    ]
}
```

## Get light

### Request: Get [http://somewhere.beer/WebAccess/api/light](http://somewhere.beer/WebAccess/api/featured)

Returns a list of light beers

Current live response to that call:
```json
{
    "reviewedBeers": [
        {
            "beerId": 1,
            "featured": true,
            "images": [
                "/media/notarealpic.jpeg"
            ],
            "ABV": 5.6,
            "beerName": "Test beer",
            "brewery": "Testing Lab",
            "description": "This beer is a test",
            "IBU": 33,
            "staffReviews": [
                {
                    "staffMember": "thomas",
                    "review": "It was a good imeginary beer, if only it was real"
                },
                {
                    "staffMember": "thomas",
                    "review": "It was a good imeginary beer, if only it was real"
                }
            ],
            "beerStyle": "Imaginary"
        }
    ]
}
``` 