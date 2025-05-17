import statistics
import numpy as np
# Project 3: Data Analysis

tuple = [13, 15, 16, 16, 19, 20, 20, 21, 22, 22, 25, 25, 25, 25, 30, 33, 33, 35, 35, 35, 35, 36, 40, 45, 46, 52, 70]

print("The mean of the tuple is: ", statistics.mean(tuple))

print("The median of the tuple is: ", statistics.median(tuple))

print("The mode of the data is: ", statistics.mode(tuple),". The data is unimodal.")

midrange = (70+13)/2

print("The midrange of the data is: ", midrange)

print("The variance of the tuple is:", statistics.variance(tuple))

print("The standard deviation of the data is:", statistics.stdev(tuple))

print("The first quartile of the data is: ", np.quantile(tuple, .25))

