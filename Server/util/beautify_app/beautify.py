# -*- encoding: UTF-8 -*-

'''
file:
	beautify.py
author:
	crusher
modify:
	2019/03/19
function:
	make_up and lift the face
'''
from util.beautify_app import make_up
from util.beautify_app import face_lift


def work(path):
	make_up.work(path)
	face_lift.work('G:/Transfer/tmp/after_make_up.jpg')


if __name__ == '__main__':
	work('origin.jpg')

