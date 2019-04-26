from util.beautify_app import beautify
from util.compress import k_means_compress
from util.remove_watermarking import rm_water
from util.image_swap import face_swap
from django.core.files.uploadedfile import InMemoryUploadedFile




import cv2
from PIL import Image

def save_image(image):
    image_name = r'G:\Transfer\tmp\src.jpg' 
    with open(image_name, 'wb+') as file:
        for chunk in image.chunks():
            file.write(chunk)
    

def beautify_face(path):
    beautify.work(path)

def compress(path):
    k_means_compress.work(path)

def remove_water(path, dir):
    rm_water.work(path, dir)

def swap_face(src_path, dest_path):
    face_swap.work(src_path, dest_path)
    
