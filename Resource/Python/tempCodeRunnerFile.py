df = DataFrame(data)
df = df.astype({'region': 'string', 'value': 'int'})
print(df.dtypes)
folium.Choropleth(geo_data=geo_data,
                  data=df,
                  columns=['region', 'value'],
                  key_on='feature.properties.name',
                  fill_color='YlGn',
                  fill_opacity=0.8
                  ).add_to(m)

m.save('folium_kr.html')
webbrowser.open_new("folium_kr.html")