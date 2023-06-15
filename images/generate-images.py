#!/usr/bin/env python3

from os import system, listdir
import sys


if __name__ == '__main__':
    if len(sys.argv) <= 1:
        print('Required argument is missing!')
        print('Usage:', sys.argv[0], 'tex-file-name|all')
        sys.exit(0)

    argument = sys.argv[1]
    file_names = listdir('.') if argument == 'all' else [argument, ]

    exclude_tex_files = ['cover.tex', 'main.tex', 'main_dark.tex', 'tmp.tex', 'header.tex', 'profile.tex', 'sandbox.tex']

    file_names = list(filter(lambda f: f not in exclude_tex_files and f.endswith('.tex'), file_names))

    for i, file_name in enumerate(sorted(file_names)):
        system(f'cp {file_name} tmp.tex')
        print(f'Processing {file_name[:-4]} ({i + 1}/{len(file_names)})...', end='')

        # light picture
        system('pdflatex main > /dev/null 2>&1')
        system(f'convert -density 700 -colorspace RGB main.pdf {file_name[:-4]}.png')

        # dark picture
        system('pdflatex main_dark > /dev/null 2>&1')
        system(f'convert -density 700 -colorspace RGB main_dark.pdf {file_name[:-4]}_dark.png')
        
        print('OK')

    system('rm *.log *.aux main.pdf')
