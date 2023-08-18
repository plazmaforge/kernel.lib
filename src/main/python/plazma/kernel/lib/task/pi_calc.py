import math
from decimal import *

def pi_calc_1():
    pi = 6 * math.sqrt(2 * (1 - math.sqrt(3) / 2))
    return pi 

def pi_calc_11():
    H = math.sqrt(3.0 / 4.0)
    print("H=", H)

    h = 1 - H
    print("h=", h)

    l = math.sqrt(math.pow(h, 2) + 1.0 / 4.0)
    L = l * 12.0
    pi = L / 2.0
    return pi

def pi_calc_2():
    #n = Decimal(math.pow(844424930131968, 10))
    #n = Decimal(8444424930131968) 
    #n = Decimal(3072)
    #n = Decimal(12288)
    n = Decimal(6)
    print("n=", n)

    # * 2 * 2 * 2 * 2 * 2 * 1000 * 100*2*2*2*2) 
    
    #52776558133248 #1649267441664 #206158430208 #25769803776 #1000000000000000000 #6442450944 #1610612736 
    #402653184 #100663296 #25165824 #6291456 #3145728 #1572864 #786432 #393216 #196608 #98304 #49152 #24576 #12288 #6144 #3072 #1536 #768 #192
    
    g = Decimal(Decimal(360.0) / n)
    g = g / 2
    print("g=", g)

    a = Decimal(math.radians(g))
    print("a=", a)

    H = Decimal(math.cos(a))
    print("H=", H)

    h = Decimal(Decimal(1.0) - H)
    print("h=", h)

    k = Decimal(math.sin(a))
    print("k=", k)

    l = Decimal(math.sqrt(Decimal(math.pow(h, 2)) + Decimal(math.pow(k, 2))))
    #l = k
    #l = a
    print("l=", l)

    L = Decimal(l * n * 2)
    print("L=", L)

    pi = Decimal(L / Decimal(2.0)) 
    return pi

def main():
    print("PI Calculation...")
    
    pi = pi_calc_1()
    print(pi)
    
    pi = pi_calc_11()
    print(pi) 
    
    pi = pi_calc_2()
    print(pi)

    print()
    print(math.pi)


if __name__ == '__main__':
    main()
