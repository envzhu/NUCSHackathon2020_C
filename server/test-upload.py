#!/usr/bin/env python3

import requests

SERVER_ADDRESS = '172.16.232.45'
PORT = 5000

url = 'http://' + SERVER_ADDRESS + ':' + str(PORT)

file = {'upload_file': open('./sample.mp3', 'rb')}
res = requests.post(url, files=file)
