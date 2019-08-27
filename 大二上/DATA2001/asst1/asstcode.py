import requests
webpage_source = requests.get("*****").text;
print(webpage_source);


from bs4 import BeautifulSoup
page_content = BeautifulSoup(webpage_source, 'html5lib')
mapdetails  = page_content.find(id="map")
print(mapdetails)


# Lookup of a given address via Google Wep-API:
import requests
import json
base_url = 'http://maps.googleapis.com/maps/api/geocode/json'
my_params= {'address': 'Avoca Beach','language':'en'}
response = requests.get(base_url, params = my_params)
#if you want to see the raw response, comment-in the following line
#print(json.dumps(response.json(), indent=4, sort_keys=False))

results      = response.json()['results']
nhood_geo    = results[0]['geometry']['location']
nhood_bounds = results[0]['geometry']['bounds']
print(my_params['address'])
print("Location: ", nhood_geo['lat'], nhood_geo['lng'])
print("Boundary: ", json.dumps(nhood_bounds, indent=4))

