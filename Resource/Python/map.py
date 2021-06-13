# import folium

# import pandas as pd

# url = (
#     "https://raw.githubusercontent.com/python-visualization/folium/master/examples/data"
# )
# state_geo = f"{url}/us-states.json"
# state_unemployment = f"{url}/US_Unemployment_Oct2012.csv"
# state_data = pd.read_csv(state_unemployment)

# m = folium.Map(location=[48, -102], zoom_start=3)

# folium.Choropleth(
#     geo_data=state_geo,
#     name="choropleth",
#     data=state_data,
#     columns=["State", "Unemployment"],
#     key_on="feature.id",
#     fill_color="YlGn",
#     fill_opacity=0.7,
#     line_opacity=0.2,
#     legend_name="Unemployment Rate (%)",
# ).add_to(m)

# folium.LayerControl().add_to(m)

import webbrowser
import folium
import json
import pandas as pd

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
