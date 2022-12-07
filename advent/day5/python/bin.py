import sys

def read_initial_state(file):
  initial_state = []

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

  # skip empty line between setup and instructions
  file.readline()

  return stacks

def parse_instruction(instruction: str):
  command = instruction.split(" ")
  amount = int(command[1])
  source = int(command[3]) - 1 # python uses 0-index but input is 1-index
  destination = int(command[5]) - 1 # same as above
  return (amount, source, destination)

def print_stack_tops(stacks):
  print("".join([stacks[i][-1] for i in range(len(stacks))]))

def part1(file_path: str):
  with open(file_path, "r") as file:
    # Split up the input into two parts and process differently
    # Process setup instructions
    stacks = read_initial_state(file)

    # Process instructions
    while (True):
      line = file.readline().rstrip()
      if line == "": break
      (amount, source, destination) = parse_instruction(line)

      for i in range(amount):
        stacks[destination].append(stacks[source].pop())

    print_stack_tops(stacks)

def part2(file_path: str):
  with open(file_path, "r") as file:
    # Split up the input into two parts and process differently
    # Process setup instructions
    stacks = read_initial_state(file)

    # temporary stack used for reversing order
    tmp = []

    # Process instructions
    while (True):
      line = file.readline().rstrip()
      if line == "": break
      (amount, source, destination) = parse_instruction(line)
      for i in range(amount):
        tmp.append(stacks[source].pop())
      
      for i in range(len(tmp)):
        stacks[destination].append(tmp.pop())

    print_stack_tops(stacks)

def main(input_file: str):
  part = sys.argv[1] if 1 < len(sys.argv) else 1
  if (part == "1"): part1(input_file)
  elif (part == "2"): part2(input_file)

if __name__ == "__main__":
  main("advent/day5/input.txt")