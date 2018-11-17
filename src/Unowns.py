#!/usr/bin/env python3

def challenge(k, lst):
    pass


# DO NOT EDIT BELOW THIS LINE
if __name__ == "__main__":
    with open("UnownsIN.txt", "r") as f:
        for line in f:
            k, n = line.split(" ", 1)
            print(" ".join(str(x) for x in challenge(int(k), [int(x) for x in n.split(" ")])))
