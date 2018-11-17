def solve(field, l1, l2):
    pass

#Do not modify below this line
if __name__ == '__main__':
    with open('RectanglesIN.txt', 'r') as f:
        while True:
            testCases = f.readline().strip()
            if testCases == '':
                break
            for i in range(int(testCases)):
                line = f.readline().strip()
                data = (line.split(" "))
                field = []
                for j in range(4):
                    field.append(int(data[j]))
                line = f.readline().strip()
                data = (line.split(" "))
                N = int(data[0])
                M = int(data[1])
                l1 = []
                l2 = []
                for j in range(N):
                    line = f.readline().strip()
                    data = (line.split(" "))
                    l1.append([int(x) for x in data])
                for j in range(M):
                    line = f.readline().strip()
                    data = (line.split(" "))
                    l2.append([int(x) for x in data])
                print(solve(field, l1, l2))
                
