# -*- encoding: utf-8 -*-

'''
author:
	crusher
modify:
	2019/03/19
'''

import cv2
import numpy as np
 
#rects = ((width - 170, height - 38, width, height),(1, 1, 164, 100)) # position of the water (left_top and right_bottom)
 

'''
Parameter:
	dir -- direction of the water
	path -- path of the source pic
'''

def work(path, dir):
	img = cv2.imread(path)
	height, width = img.shape[0:2]

	mask = np.zeros((height, width), np.uint8)

	if dir == 'left_top':
		rect = (1, 1, 164, 100)
	elif dir == 'right_bottom':
		rect = (width - 170, height - 38, width, height)

	x1, y1, x2, y2 = rect
	cv2.rectangle(mask, (x1, y1), (x2, y2), (255, 255, 255), -1)
	img = cv2.inpaint(img, mask, 1.5, cv2.INPAINT_TELEA)

	cv2.imwrite('G:/Transfer/tmp/after_rm_water.jpg', img)
 
 
if __name__ == '__main__':
	work('cai.jpg', 'right_bottom')
