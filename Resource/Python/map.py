import webbrowser
import folium
import json
import pandas as pd
import sys
import numpy as np
from pandas.core.frame import DataFrame
import time
from selenium import webdriver
from PIL import Image
import os


def main(argv):
    # region = '울산광역시,대전광역시,광주광역시,전라남도,경기도,전라북도,부산광역시,경상남도,대구광역시,충청북도,서울특별시,충청남도,제주특별자치도,경상북도,강원도,인천광역시,세종특별자치시'.split(
    #     ",")
    # value = '45,36,48,77,754,97,139,146,93,101,308,105,26,126,57,165,14'.split(
    #     ",")
    print("start")
    region = argv[1].split(",")
    value = argv[2].split(",")
    m = folium.Map(location=[36, 127], tiles="OpenStreetMap", zoom_start=8)
    geo_data = 'https://raw.githubusercontent.com/southkorea/southkorea-maps/master/kostat/2018/json/skorea-provinces-2018-geo.json'

    data = {
        "region": region, "value": value
    }

    df = DataFrame(data)
    df = df.astype({'region': 'string', 'value': 'int'})
    folium.Choropleth(geo_data=geo_data,
                      data=df,
                      columns=['region', 'value'],
                      key_on='feature.properties.name',
                      fill_color='YlGn',
                      fill_opacity=0.8
                      ).add_to(m)

    m.save("map.html")
    delay = 5

    # Save the map as an HTML file
    fn = 'map.html'
    tmpurl = 'file://{path}/{mapfile}'.format(path=os.getcwd(), mapfile=fn)
    m.save(fn)
    options = webdriver.ChromeOptions()
    options.add_experimental_option("excludeSwitches", ["enable-logging"])
    # Open a browser window...
    browser = webdriver.Chrome("chromedriver.exe", options=options)
    # ..that displays the map...
    browser.get(tmpurl)
    # Give the map tiles some time to load
    time.sleep(delay)
    # Grab the screenshot
    browser.save_screenshot('map.png')
    # Close the browser
    browser.quit()


if __name__ == "__main__":
    main(sys.argv)
