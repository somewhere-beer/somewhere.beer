# Somewhere.beer Functional Specification
## v1.2

**NOTE:** This is a living document, and is therefore subject to change. At the current time of writing, Thomas is the lead for this document.

### Non-Goals:

User accounts, user reviews, and user-submitted content were all considered in the earliest planning stages of this project, but were ultimately rejected due to concerns over scope. As described below, the site represents the minimum viable product of a "beer encyclopedia." Given additional time and resources, userland features like registering and managing accounts, submitting beer ratings and tasting notes, and adding or editing beer entries would be considered high-priority additions.

### User Stories

1. John is a casual beer drinker who wants to be able to learn more about a particular beer when he finds one he likes. He could Google it, but he doesn’t necessarily care about the company information as much as he would like to know about the beer's key information. John’s friend Dave recommends that he look up the beer on Somewhere.beer. John brings up the site, types “Anchor Steam Barleywine” in the search box, and is redirected to a results page. The top listing is the beer he just had, so he clicks it to navigate to that beer’s page. John reads through the page, noting the beer’s ABV, IBU, and even some tasting notes by the staff. Now John has a better idea of what beer styles he likes, and is better-equipped to find similar beers he might like at his local bar tonight.
2. Jane is a beer aficionado who is well versed in beer styles and what she likes in a beer. She knows she generally prefers beers with an ABV between 4% and 7%, as well as beers with an IBU of no more than around 60. She can use the beer attribute dropdowns on the landing page to filter all beers in the database against her preferred criteria, helping her discover a new beer to try the next time she's in the mood for a pint.

### Pages and Page Elements

#### Navbar

Will be present on all pages.

- Site logo (links to landing page)
- Search bar (used for fuzzy string matching against our database records)
- Attribute search page button (navigates to the advanced search page)

#### Footer

Will be present on all pages.

- Logo (links to landing page)
- Links:
    - About
    - GitHub
    - License

#### Landing page 

Top and center will be a search bar, providing an easy and prominent entry point for users who know what they're looking for (or are willing to explore the results).

Also centered and just below the search bar is the quick category box. This will aid in broad-stroke browsing to pages of the following:
- Dark Beers
- Light Beers
- Staff-reviewed Beers

The lower-left majority of the landing page is the featured beer section, titled “Featured Beer”. Below the title will be a short text element describing why the beer is featured, followed by an excerpt of information from the beer’s page and closing with a hyperlink to said beer page. Lastly, the lower right will hold the attribute search box. The controls of this search box are yet to be nailed down, but some attributes we know we want to be searchable include:

- ABV
- IBU
- Beer type (i.e., ale or lager)
- Beer style category (subtype from ale or lager; e.g., IPA, porter, stout)
- Beer style (e.g., American Porter, German Hefeweizen)
- Country of origin
- Brewery

#### Category page

Beginning right below the navbar will be the title of the category, followed by an expandable list of subcategories. At the deepest level of each subcategory will be a bullet list of beers belonging to that subcategory, each linked to the page for that beer. Lastly, there will be a bullet list of beers that belong to a category but don't belong to any of its subcategories, each linked to the page for that beer.

#### Results page

This page will have a title of “Found `x` results matching your search”. Below this will be a simple list of hyperlinks of the pages that match the user's query.

#### Beer page

Beginning right below the navbar on the top center will be the name of the beer. Below this title on the upper-left will be where any pictures we have of the beer will be displayed. (If we have more than one picture, toggle controls will be displayed at the bottom of the picture; otherwise, a caption will be displayed.) On the right of the picture will be a bullet list of the beer’s key attributes. Centered on the page below these elements will be a description of the beer. If there are staff tasting notes for the beer, they will be presented below the beer description. Staff tasting notes will be labeled with a staff member's name and avatar, followed by their tasting notes.

#### About Us page

At the top of the page, there will a title of "About Us," followed by some information about the project and site. The next element shall be a “Team members” section header, followed by a short block for each team member. Each team member's block will contain the team member’s name, a picture, and some brief text.

#### License page

This page will detail the license information for the project; the beer data from catalog.beer is licensed under CC BY 4.0 (excluding the breweries' rights to names, brands, and trademarks), while the code and content generated by the Somewhere.beer team is licensed under GPL 3.0.
