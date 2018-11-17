def search(n, rows, A):
    pass

#Do not modify below this line
if __name__ == '__main__':
    with open('GottaCatchEmAllIN.txt', 'r') as f:
        while True:
            s = f.readline().strip()
            t = f.readline().strip()
            if s == '':
                break
            arr = []
            for i in range(int(t)):
                u = f.readline().strip().split(', ')
                u = [int(i) for i in u]
                arr.append(u)
            print(search(int(s), int(t), arr))
