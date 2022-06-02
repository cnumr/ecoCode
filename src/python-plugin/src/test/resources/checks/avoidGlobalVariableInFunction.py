global_var = 'global'

def print_global_var_length():
    print(len(global_var)) # Noncompliant

# Compliant
def print_var_length(var):
    print(len(var))

print_var_length(global_var)