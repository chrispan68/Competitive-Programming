#!/usr/bin/env python3

def portals(r, c, m):
    pass


if __name__ == "__main__":
    with open("PortalsIN.txt", "r") as f:
        n = int(f.readline())
        for i in range(n):
            r, c = [int(x) for x in f.readline().split()]
            m = [list(f.readline()) for _ in range(r)]
            print(portals(r, c, m))
