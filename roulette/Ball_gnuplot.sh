#!/bin/bash

gnuplot -e "
set datafile separator ',';
set size square;
set terminal pdf; 
set output 'Ball.pdf';
set title '円軌道';
set xlabel 'x';
set ylabel 'y';
plot 'Ball.csv' using 2:3 with linespoints;
"
