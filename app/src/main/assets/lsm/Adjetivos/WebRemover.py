import os, sys

defaultPath = r"C:\Users\santi\AndroidStudioProjects\Signa\app\src\main\assets\lsm"
superDirectories = os.listdir(defaultPath)

for supDir in superDirectories:
    directories = os.listdir(defaultPath + "/" + supDir)
    for dir in directories:
        if dir == "WebRemover.py":
            continue
        if dir[-7:] == "Web.m4v":
            os.rename(defaultPath + "/" + supDir + "/" + dir, defaultPath + "/" + supDir + "/" + dir[0:-8] + ".m4v")