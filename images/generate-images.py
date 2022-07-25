#!/usr/bin/env python3

from os import system, listdir

exclude_tex_files = ['main.tex', 'tmp.tex']

for file_name in listdir('.'):
    if file_name not in exclude_tex_files and file_name.endswith('.tex'):
        system(f'cp {file_name} tmp.tex')
        system('pdflatex main')
        system(f'convert -density 1000 main.pdf {file_name[:-4]}.png')

system('rm *.log *.aux main.pdf tmp.pdf tmp.png')

