# -*- coding:utf8 -*-

import socketserver
import threading
# import connector_predict
import predict_1

class RequestHandler(socketserver.StreamRequestHandler):
    def handle(self):

        # get socket request
        socket = self.request

        # show client
        print('Connect with : ' + self.client_address[0])

        # set file name
        # num = random.random() * 100000
        # file_name = 'image_temp/file_' + str(int(num)) + '.jpg'

        # get image file size from client
        file_name = socket.recv(1024).decode('UTF-8', 'ignore')

        print('file_name : ', file_name)

        # file_name = '/home/miruware/temp/img/' + file_name

        # tensorflow image classfication
        # connector_inst = connector_predict.Connect(file_name)
        # label = connector_inst.get_result()

        label = predict_1.predict(file_name)

        socket.sendall(bytes(label, 'UTF-8'))
        socket.close()

        
if __name__ == '__main__':
    HOST = '127.0.0.1'
    PORT = 5000

    server = socketserver.TCPServer((HOST, PORT), RequestHandler)

    print('Socket is now listening ...')
    server_thread = threading.Thread(target=server.serve_forever())
    server_thread.setDaemon(True)
    server_thread.start()
