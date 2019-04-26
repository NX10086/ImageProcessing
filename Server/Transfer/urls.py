"""Transfer URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/2.0/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.contrib import admin
from django.views.static import serve 
from django.urls import path
from django.urls import path,re_path
from view.views import get_password, beautify_solve, compress_solve, swap_face_solve, rw_solve,\
    register

urlpatterns = [
    re_path(r'^images/(?P<path>.*)$', serve, {'document_root':r'G:/Transfer/tmp'}),

    path('get_password/', get_password),
    path('beautify_solve/', beautify_solve),
    path('compress_solve/', compress_solve),
    path('swap_face_solve/', swap_face_solve),
    path('rw_solve/', rw_solve),
    path('register/', register),
    path('admin/', admin.site.urls),
]
