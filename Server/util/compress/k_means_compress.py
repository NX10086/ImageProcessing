# -*- encoding: GBK -*-

'''
file:
	k_means_compress.py
author:
	crusher
modify:
	2019/03/19
function:
	compress the picture in k_means method
description:
	It will take a long time if your pictures are to large.
'''

import numpy as np
import matplotlib.pyplot as plt
from scipy import stats
import imageio
import os
from os.path import join, getsize
from sklearn.cluster import MiniBatchKMeans

def work(iname):
	cname = "after_compress.jpg"

	#��������ɫ����
	n_colors = 32 
	img = imageio.imread(iname)
	
	data = img / 255.0 # ��ɫ��0-255ת��Ϊ0-1֮���С��
	data = data.reshape(-1, 3)
	data.shape
	
	#����KMeans
	kmeans = MiniBatchKMeans(n_colors)
	kmeans.fit(data)
	new_colors = kmeans.cluster_centers_[kmeans.predict(data)]
	
	
	#������ɫ
	new_img = new_colors.reshape(img.shape)
	new_img = (255 * new_img).astype(np.uint8)
	
	imageio.imsave(r'G:/Transfer/tmp/' + cname, new_img)

if __name__ == '__main__':
	work('origin.jpg')
