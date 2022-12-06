# split up the input into two parts and process differently

def main(file_path: str):
  with open(file_path, "r") as file:
    initial_state = []

    # Input for initial state
    while (True):
      line = file.readline()[:-1]
      if (line[1] == "1"): break
      else: initial_state.append(line)

    num_stacks = int((len(initial_state[0]) + 1) / 4)
    stacks = [[] for n in range(num_stacks)]

    # load in backwards
    for i in range(len(initial_state) - 1, -1, -1):
      line = initial_state[i]
      for j in range(num_stacks):
        char = line[j * 4 + 1]
        if (char != " "):
          stacks[j].append(char)

    # skip empty line
    file.readline()

    # Input instructions
    while (True):
      line = file.readline().rstrip()
      if line == "": break
      command = line.split(" ")
      amount = int(command[1])
      source = int(command[3]) - 1 # python uses 0-index but input is 1-index
      destination = int(command[5]) - 1 # same as above

      for i in range(amount):
        stacks[destination].append(stacks[source].pop())
    
    print("".join([stacks[i][-1] for i in range(num_stacks)]))


main("advent/day5/input.txt")