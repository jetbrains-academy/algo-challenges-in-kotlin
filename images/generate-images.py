#!/usr/bin/env python3

from os import system, listdir

exclude_tex_files = ['main.tex', 'main_dark.tex', 'tmp.tex', 'header.tex']

for file_name in listdir('.'):
    if file_name not in exclude_tex_files and file_name.endswith('.tex'):
        system(f'cp {file_name} tmp.tex')
        print(f'Processing {file_name[:-4]}')

        # light picture
        system('pdflatex main > /dev/null 2>&1')
        system(f'convert -density 1000 main.pdf {file_name[:-4]}.png')

        # uncomment this later, when Idea is able to process two types of images
        # # dark picture
        # system('pdflatex main_dark > /dev/null 2>&1')
        # system(f'convert -density 1000 main_dark.pdf {file_name[:-4]}_dark.png')

system('rm *.log *.aux main.pdf')
