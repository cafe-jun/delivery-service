#!/bin/bash


echo "# delivery-service" >> README.md
git init
git add README.md
git commit -m "first commit"
git branch -M main
git remote add origin https://github.com/cafe-jun/delivery-service.git
git push -u origin main
