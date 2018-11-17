#!/usr/bin/env python3


def palindrome(s):
    pass


if __name__ == "__main__":
    with open("PalindromeSSIN.txt", "r") as f:
        n = int(f.readline())
        for i in range(n):
            print(palindrome(f.readline().strip()))
