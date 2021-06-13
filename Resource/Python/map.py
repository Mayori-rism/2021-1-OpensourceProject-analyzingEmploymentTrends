import webbrowser
import folium
import json
import pandas as pd
import sys

# if __name__ == "__main__":
#     main(sys.argv)

m = folium.Map(location=[36, 127], tiles="OpenStreetMap", zoom_start=8)
geo_data = json.load(
    open('southkorea-maps\skorea-provinces-2018-geo.json', encoding='utf-8'))

df = pd.read_csv('ddd.csv', encoding='utf-8', dtype={'code': 'str'})
folium.Choropleth(geo_data=geo_data,
                  data=df,
                  columns=['sigun', 'avg_income'],
                  key_on='feature.properties.name',
                  fill_color='YlGn',
                  fill_opacity=0.8
                  ).add_to(m)

m.save('folium_kr.html')
webbrowser.open_new("folium_kr.html")
