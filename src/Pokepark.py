def solve(l):
    pass

#Do not modify below this line
if __name__ == '__main__':
    with open('PokeparkIN.txt', 'r') as f:
        while True:
            testCases = f.readline().strip()
            if testCases == '':
                break
            for i in range(int(testCases)):
                line = f.readline().strip()
                data = (line.split(" "))
                N = int(data[0])
                l = []
                line = f.readline().strip()
                data = line.split(" ")
                for i in range(N):
                    l.append(int(data[i]))
                print(solve(l))
