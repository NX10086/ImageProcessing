from db.query import query, insert
from util import util
from django.http.response import HttpResponse



# Create your views here.

def register(request):
    if request.method == 'POST':
        user_name = request.POST.get('username')
        password = request.POST.get('password')
        insert(user_name, password)
        return HttpResponse('OK')

def get_password(request):
    if request.method == 'POST':
        user_name = request.POST.get('username')
        pw = request.POST.get('password')
        password = query(user_name)
        if pw == password:
            return HttpResponse('OK')
        return HttpResponse('0')
    
def beautify_solve(request):
    if request.method == 'POST':
        image = request.FILES.get('image')
        util.save_image(image)
        util.beautify_face(r'G:/Transfer/tmp/src.jpg')
        return HttpResponse(':8000/images/after_lift_face.jpg')
    
def compress_solve(request):
    if request.method == 'POST':
        image = request.FILES.get('image')
        util.save_image(image)
        util.k_means_compress.work(r'G:/Transfer/tmp/src.jpg')
    return HttpResponse(':8000/images/after_compress.jpg')
    
def rw_solve(request):
    if request.method == 'POST':
        image = request.FILES.get('image')
        dir = request.POST.get('dir')
        util.save_image(image)
        util.remove_water(r'G:/Transfer/tmp/src.jpg', dir)
    return HttpResponse(':8000/images/after_rm_water.jpg')
    
def swap_face_solve(request):
    if request.method == 'POST':
        image = request.FILES.get('image')
        dest_face = request.POST.get('dest')
        util.save_image(image)
        util.swap_face(r'G:/Transfer/tmp/src.jpg', r'G:/Transfer/tmp/' + dest_face + ".jpg")
    return HttpResponse(':8000/images/after_swap_face.jpg')
    
    

    
        
        