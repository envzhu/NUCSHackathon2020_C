#!/usr/bin/env python3

from http.server import BaseHTTPRequestHandler, HTTPServer 
from urllib.parse import parse_qs, urlparse

CLIENT_ADDRESS = '' # Accept all IP address
PORT = 5000

class HandleRequests(BaseHTTPRequestHandler):
    def do_GET(self):
        with open('./data.txt', 'rb') as f:
            body = f.read()
            self.send_response(200)
            self.send_header('Accept-Ranges', 'bytes')
            self.send_header('Content-Type', 'audio/mpeg')
            self.send_header('Content-Length', str(len(body)))
            self.end_headers()
            self.wfile.write(body)
        

    def do_POST(self):
       
        print('path = {}'.format(self.path))

        parsed_path = urlparse(self.path)
        print('parsed: path = {}, query = {}'.format(parsed_path.path, parse_qs(parsed_path.query)))

        print('headers\r\n-----\r\n{}-----'.format(self.headers))

        content_length = int(self.headers['content-length'])
        
        print('body = {}'.format(self.rfile.read(content_length).decode('utf-8')))
        print('body_end') 
        self.send_response(200)
        self.send_header('Content-Type', 'text/plain; charset=utf-8')
        self.end_headers()
        self.wfile.write(b'Hello from do_POST')
        
    def do_PUT(self):
        self.do_POST()


HTTPServer((CLIENT_ADDRESS, PORT), HandleRequests).serve_forever()
