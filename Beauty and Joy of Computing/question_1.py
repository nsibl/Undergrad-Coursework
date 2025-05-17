# Question 1: Ackermann Function
# If m = 0 then return n + 1
# If n = 0 then return ackermann (m - 1, 1)
# Otherwise, return ackermann (m - 1, ackermann (m, n - 1))

# Create main function
def main():
    # Ask for user input on values of m and n
    m = int(input("Enter value of m: "))
    n = int(input("Enter value of n: "))
    # Call Ackermann function
    print(ackermann(m, n))

# Create Ackermann function
def ackermann(m,n):
    if m == 0:
        print(m, n)
        return(n+1)

    elif n == 0:
        print(m, n)
        return ackermann(m-1, 1)

    else:
        print(m, n)
        return ackermann(m-1, ackermann(m, n-1))

# Call the main function
main()