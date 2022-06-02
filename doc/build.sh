#!/bin/sh
xelatex --shell-escape ./core/paper.tex
mv ./paper.log ./paper.out ./paper.aux ./core/derivative-files/
mv ./paper.pdf ./pdf/JavaHotel.pdf
