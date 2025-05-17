# Question 2: Using recursion to raise # to a power

# Ask user for base and exponent values
base = int(input("Enter the base number: "))
exp = int(input("Enter the exponent number: "))

# Create recursion function to raise number
def exponent(base, exp):
    if(exp == 1):
        return(base)

    if(exp != 1):
        return(base * exponent(base, exp-1))


print(base, "to the power of", exp, "is:",exponent(base, exp))