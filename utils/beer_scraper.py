#!/usr/bin/env python3

import csv, re, requests
from bs4 import BeautifulSoup
from pathlib import Path

"""
beer_scraper.py - Scrapes the full catalog of beers from https://catalog.beer and outputs information for each beer to a CSV file.
"""

def retrieve_beers_page(session, base_url, page_number):
    """
    Takes in a `requests` Session object, a base URL, and a page number.
    Constructs a URL of a page of beers and returns a `requests` object representing a page of beers from the catalog.
    
    >>> s = requests.Session()
    >>> print(retrieve_beers_page(s, "https://catalog.beer", 1))
    <Response [200]>
    """
    url = base_url + "/beer"
    payload = {"page": page_number}
    return session.get(url, params=payload)

def parse_beers_page(beers_page):
    """
    Takes in a `requests` object representing a page of beers from the catalog.
    Returns the parsed version of the page as a `BeautifulSoup` object.

    >>> s = requests.Session()
    >>> page = s.get("https://catalog.beer/beer?page=1")
    >>> print(parse_beers_page(page).find("div", "col").find("p").text)
    Page 1 of 141
    """
    return BeautifulSoup(beers_page.text, "html.parser")

def extract_beer_links(beers_page_soup):
    """
    Takes in a `BeautifulSoup` object representing a parsed page of beers.
    Returns a list of relative URLs to individual beer pages.

    >>> s = requests.Session()
    >>> page = s.get("https://catalog.beer/beer?page=1")
    >>> soup = BeautifulSoup(page.text, "html.parser")
    >>> links = extract_beer_links(soup)
    >>> print(links[0])
    /beer/4bb4e652-3e83-4136-18eb-52955cd13b6d
    """
    relative_urls = []
    beer_links = beers_page_soup.find("div", "row").find_next_sibling().find_all("a")
    for beer_link in beer_links:
        relative_url = beer_link.get("href")
        relative_urls.append(relative_url)
    return relative_urls

def retrieve_beer_page(session, base_url, beer_url):
    """
    Takes in a `requests` Session object, a base URL, and the relative URL to a unique beer from the catalog.
    Constructs the URL of a beer page and returns a `requests` object representing the catalog page of that beer.

    >>> s = requests.Session()
    >>> print(retrieve_beer_page(s, "https://catalog.beer", "/beer/4bb4e652-3e83-4136-18eb-52955cd13b6d"))
    <Response [200]>
    """
    url = base_url + beer_url
    return session.get(url)

def parse_beer_page(beer_page):
    """
    Takes in a `requests` object representing a unique beer page from the catalog.
    Returns the parsed version of the page as a `BeautifulSoup` object.

    >>> s = requests.Session()
    >>> page = s.get("https://catalog.beer/beer/4bb4e652-3e83-4136-18eb-52955cd13b6d")
    >>> soup = BeautifulSoup(page.text, "html.parser")
    >>> print(soup.find("h1").text)
    “18” Imperial IPA 2
    """
    return BeautifulSoup(beer_page.text, "html.parser")

def extract_beer_info(beer_page_soup):
    """
    Takes in a `BeautifulSoup` object representing a parsed beer page.
    Returns a list containing the beer's name, style, brewer, ABV, and IBU.

    >>> s = requests.Session()
    >>> page = s.get("https://catalog.beer/beer/95c61c88-e053-ca90-ecca-90da512ca3c0")
    >>> soup = BeautifulSoup(page.text, "html.parser")
    >>> print(extract_beer_info(soup))
    ['“633” American Pale Ale', 'American-Style Pale Ale', 6.3, 25, 'Boothbay Craft Brewery, Inc']
    """
    beer_name = beer_page_soup.find("h1").text
    beer_style = beer_page_soup.find("p", "lead").text.strip()
    try:
        abv_element = beer_page_soup.find("strong", text=re.compile(r"ABV")).parent.contents
        abv = float(re.sub(r"%", "", abv_element[1].strip()))
    except AttributeError:
        print(f"WARNING: no ABV info found for {beer_name}")
        abv = None
    try:
        ibu_element = beer_page_soup.find("strong", text=re.compile(r"IBU")).parent.contents
        ibu = int(ibu_element[1].strip())
    except AttributeError:
        print(f"WARNING: no IBU info found for {beer_name}")
        ibu = None
    try:
        brewer_element = beer_page_soup.find("strong", text=re.compile(r"Brewer")).parent
        brewer_name = brewer_element.contents[2].text
    except AttributeError:
        print(f"WARNING: no brewer info found for {beer_name}")
        brewer_name = None

    return [beer_name, beer_style, abv, ibu, brewer_name]

def write_to_csv(beer_info, csv_path):
    """
    Takes in a list of beer information and the path of a CSV file to write to.
    Appends beer information to a CSV file at the specified location, creating one if it doesn't already exist.
    """
    csv_file = Path(csv_path).expanduser()
    with open(csv_file, "a") as c:
        writer = csv.writer(c)
        writer.writerow(beer_info)

if __name__ == "__main__":
    import doctest
    doctest.testmod()

    base_url = "https://catalog.beer"
    # At time of writing, the catalog has 140 pages of beers
    page_numbers = list(range(1, 141))
    # Output CSV file with scraped data to same directory as this script
    csv_path = Path(__file__).parent.joinpath("beers.csv")

    with requests.Session() as session:
        for page_number in page_numbers:
            beers_page = retrieve_beers_page(session, base_url, page_number)
            beers_page_soup = parse_beers_page(beers_page)
            beer_links = extract_beer_links(beers_page_soup)
            for beer_link in beer_links:
                beer_page = retrieve_beer_page(session, base_url, beer_link)
                beer_page_soup = parse_beer_page(beer_page)
                beer_info = extract_beer_info(beer_page_soup)
                write_to_csv(beer_info, csv_path)