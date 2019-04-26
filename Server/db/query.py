from db import models
from db.models import Account

def query(uname):
    return models.Account.objects.get(username = uname).password

def insert(uname, pw):
    user = Account(username = uname, password = pw)
    user.save()
