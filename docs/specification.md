# Somewhere.beer Functional Specification
## v1.2

**NOTE:** This is a living document, and is therefore subject to change. At the current time of writing, Thomas is the lead for this document.

### Non-Goals:

- User accounts
- User reviews
- User-submitted conctent

### User Story

John likes beer, also John is a nerd thus wants to be able to learn more about particular when he finds one he likes. John could google the beer, but he doesn’t care about the company information as much as he would like to know about the beer’s attributes. John’s very cool friend Dave recommends that he look up the beer on Somewhere.beer. John brings up the site and types in “Anchor Steam BarleyWine”, then he is redirected to a search results page. The top listing is the beer he just had so he clicks it to navigate to that beer’s page. John reads through the page learning about the beer’s IBU, ABV, and even some tasting notes by the staff. Now educated and inebriated John is better prepared to sound ‘smart’ at his local bar tonight.

## Pages

### NavBar

All pages of Somewhere.beer.

- Site logo (which will navigate home when clicked on. This is a common convention of navigation) 
- Home button (This is a tad redundant, but it makes navigation idiot proof) 
- Search bar (used for fuzzy matching text searching the records) 
- Attribute search page button (which navigate to the advanced search page) 

### Footer

Logo

Links 

- About us  
- Lince page 
- Home page 

### Landing page 

Top and center will be a search bar (giving easy access for those users that know what they're looking for). Also centered and just below the search bar is the quick category box. (This will aid in broad stroke browsing to our category pages. {The current working categories are

- Light 
- Dark 
- IPA 
- Staff reviewed 
- Malt 
- Black & Tan}) 

The lower left majority of the landing page is featured beer section. It will be titled “Featured Beer” (open to a better name), right below the title holding a short paragraph of why the beer is featured. That will be followed by an excerpt of the beer’s page and closing with a hyperlink to said beer page. Lastly, the lower right will hold the attribute search box. The controls of this search box are yet to be nailed down, but some attributes are known that we want to be searchable include {

- ABV
- IBU 
- Ale or Lager 
- Type {sub-from ale or lager}(ie IPA, Porter, Stouts) 
- Country of origin 
- Brewery} 

### Category page

Beginning right below the aforementioned navbar shall be the title of the category. Continuing down next shall be a paragraph(s) giving a description of the category. Below this, there will be an expandable list of subcategories. At the deepest drilling of the subcategories shall be a bullet list of beers of said subcategory (These bullet list items will be hyperlinks to beer said beer page). Lastly, there will be a bullet list of beers that belong to the category, but no one the above subcategories (again said list items will be hyperlinks to the beer pages).

### Results Page

This page will have a title of “Found ## results matching your search” (the hashtags standing in for the number of beer pages match the query). Below this will be a simple list of hyperlinks of the pages that match the user's query.

### Beer page 

Beginning right below the navbar on the top center will be the name of the beer. Below this title on the upper left will be where any pictures we have of the beer will be displayed(if we have more then one picture at the bottom of the picture shall be toggle controls. If we do not have a picture of the beer the description should supersede the empty picture space). On the right of the picture will be a bullet list of the beer’s key attributes. Below these elements centered on the page will be the beer’s description, lastly shall be a box for staff tasting notes. Said Box will have a title of “Staff reviews” (probably should make a better title). Below this staff reviews will consist of a head of the review staff member’s name followed the body of the staff member’s review tasting notes (I’m thinking like 1 - 3 short paragraphs). (This box should dynamically be made visible depending on if any staff reviews exist of said beer).

### About us

At the top of the page, there will a title of “About Us”. Below the title element, there will be a few paragraphs about the site and the project to create the site. Moving down the page the next element shall be a section header “Team members”. Below this section header, each team member shall have a bio-block. Bio-blocks shall consist of a title of the team member’s name, a headshot picture of the team member, and a paragraph or two about said staff member. (I see the contents of this page being largely static)

### License page

This page will detail the license information for the project; the beer data from catalog.beer is licensed under CC BY 4.0 (excluding the breweries' rights to names, brands, and trademarks), while the code and content generated by the Somewhere.beer team is licensed under GPL 3.0.