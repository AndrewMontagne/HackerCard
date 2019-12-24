#!/usr/bin/python3
import sys

line = input()
split = [line[i:i+2] for i in range(0, len(line), 2)]

print("")

i = 0
for byte in split:
    print("(byte)0x" + byte + ", ", end = '')
    i += 1
    if i >= 8:
        i = 0
        print("")

print("")