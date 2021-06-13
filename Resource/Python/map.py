import webbrowser
import folium
import json
import pandas as pd
import sys
import numpy as np
from pandas.core.frame import DataFrame


def main(argv):
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

    m.save('folium_kr.html')
    return "/Resource/Python/folium_kr.html"


if __name__ == "__main__":
    main(sys.argv)
