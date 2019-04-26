<img src="https://github.com/louieCrusher/MagicAndYoung/blob/master/Image/logo.png" width="300" height="300" />

# MagicAndYoung


### 项目介绍
图像处理小项目, 基于CNN的图片换脸以及基于局部平移算法的瘦脸功能


### 功能介绍
**四大核心功能:**

* 换脸
* 美颜
* 压缩
* 去水印

### 算法介绍
使用dlib库进行人脸识别, dlib使用resnet34卷积神经网络模型, 识别人脸68个特征点.
识别人脸之后, 对于换脸操作, 基于特征点的替换. 对于美颜操作, 基于局部平移算法进行瘦脸, 美白等操作基于图像增强技术. 
压缩图片使用k-means算法, 将RGB色彩体系共256^3种颜色压缩至k(在代码中, 我们取k = 32).
去水印操作采用opencv inpaint算法, 使用快速行进参数, 在代码中mask取值为白色, 故只能进行简单的去除水印操作.

### 开发环境
服务端使用eclipse, Django2.0, python版本为3.6, mysql5.6.
客户端使用android studio3.0.


### 效果演示

##### 换脸

<img src="https://github.com/louieCrusher/MagicAndYoung/blob/master/Image/face_swap.gif" width="150" height="250" />

##### 美颜

<img src="https://github.com/louieCrusher/MagicAndYoung/blob/master/Image/beautify.gif" width="150" height="250" />

##### 压缩

<img src="https://github.com/louieCrusher/MagicAndYoung/blob/master/Image/compress.gif" width="150" height="250" />

##### 去水印

<img src="https://github.com/louieCrusher/MagicAndYoung/blob/master/Image/remove_water.gif" width="150" height="250" />


### 使用说明
将Server目录下的util文件涉及到本地服务器地址的代码改为你服务器的地址.
修改Django中的add_host列表, 添加你当前IP
然后在cmd下进入你当前的服务器目录, 键入以下代码用以开启服务器:
```
python manage.py runserver 0.0.0.0:8000
```
将MagicAndYoung项目中的UrlGet中的ip改为你当前ip, build之后安装


### 联系作者
**Author:**
* Crusher: yuewang_louie@163.com(email), https://github.com/louieCrusher(github)
* NieXu: 1123421827(qq), 1123421827@qq.com(email)